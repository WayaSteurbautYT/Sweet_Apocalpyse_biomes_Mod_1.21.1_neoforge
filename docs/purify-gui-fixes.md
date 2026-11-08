# Purify GUI: duplication fix + bug pass

Scope: the corruption / purification system (`PurifyGUIElementCustom`, `PurifyGUIMenu`,
`PurifyGUIScreen`, `PurifyGUICraftPacket`, `PurifyGUIStatePacket`, `PurifyGUIConvertProcedure`,
`PurifyLDLib*`, `FoodCorruptionHelper`).

## Root cause of the duplication glitch

The purification chamber was a **single `ItemStackHandler` stored in a static map keyed by player
UUID** and it was handed to *both* GUIs on *both* sides:

```java
// before
private static final Map<UUID, ItemStackHandler> purifyInventories = new ConcurrentHashMap<>();
public static ItemStackHandler getOrCreatePurifyInventory(Player player) {
    return getOrCreatePurifyInventory(player.getUUID());
}
```

In every singleplayer world the client and the integrated server share that static map, so the
client's GUI slots and the server's GUI slots were literally **the same mutable container**. On top
of that, several code paths moved items without emptying the slot they came from, so items could
exist in two places at once:

| Vector | What happened |
| --- | --- |
| Client and server sharing one chamber | GUI clicks / drags on the client wrote into the same container the server used, bypassing the normal "server removes it from your inventory" step. |
| `PurifyGUIMenu.removed()` | MCreator's give-back-on-close code handed items to the player and then cleared the slot with `setStackInSlot` - which the chamber **silently ignores while a purification is running**. Items were given out while still being in the machine. |
| `onPurifierBlockBroken` | The stored potion was replaced by `new ItemStack(Items.GLASS_BOTTLE, count)` *before* dropping the contents: the potion was destroyed and fresh glass bottles were dropped. |
| `PurifyGUIConvertProcedure` | The `PurificationPotion` item (a normal stackable item, `finishUsingItem` returns no bottle) was "bottled": consuming it created a glass bottle that never existed. |
| `onContainerClose` (client) | Removed the map entry from the **shared** static map, so in singleplayer the server's real chamber contents became unreachable (items appeared to vanish / desync, and a batch could keep running against an orphaned container). |
## What changed

1. **Server-authoritative chamber (fixes the duplication)**
   `PurifyGUIElementCustom` now keeps two *separate* maps: `serverInventories` (the real chamber, only
   touched by the server thread) and `clientInventories` (a display mirror that the container menu
   syncs). `getOrCreatePurifyInventory(Player)` resolves by side, so a client can never write into the
   real machine contents. New helpers: `getServerPurifyInventory(Player)` (null on the client) and
   `collectOutput(ServerPlayer, IItemHandlerModifiable)` which **extracts first, hands out second** and
   refuses to give anything if the slot could not be emptied.
2. **Closing a GUI no longer deletes server state** - only the client mirror is dropped, so chamber
   contents survive closing the window (no more "items disappeared" reports).
3. **Safe item give-back in `PurifyGUIMenu.removed()`**: the running purification is cancelled first
   (which unlocks the input slots), then each slot is extracted and only handed over if the slot is
   really empty afterwards. Death/disconnect drops the items instead.
4. **Honest output accounting in `PurifyGUIConvertProcedure`**: one corrupted item in -> exactly one
   purified item out, no `grow()` guessing. Potions are handled by `consumeOnePotion()`: exactly one
   potion is consumed, leftover potions keep their slot, and only real bottle-based potions leave an
   empty bottle behind (the custom Purify Potion no longer conjures glass bottles).
5. **Breaking the Purifier block drops exactly what is inside** the chamber (no fabricated bottles),
   after cancelling the batch so the slots are unlocked.
6. **Packet hardening (`PurifyGUICraftPacket`)**: server-bound only, requires a `ServerPlayer`, uses
   the authoritative chamber, and refuses to act unless the purify GUI is actually the open container
   (`PurifyGUIMenu` or an LDLib `ModularUIContainerMenu` with the purify UI id). Double clicking the
   button can no longer collect twice.
7. `PurifyLDLibPurifyButtonClickProcedure` only sends from the client; `PurifyLDLibGUITickProcedure`
   refuses to run client side; `PurifyGUIScreen.containerTick()` guards against a null button.

## Other bugs fixed in the same pass

* **Keybind GUI closed instantly / "not valid"** - `OpenPurifyGUIPacket` opened the menu with
  `BlockPos.ZERO`, so `PurifyGUIMenu.stillValid()` checked for a purifier block at 0,0,0. The packet now
  sends an absent-position flag, and `PurifyGUIMenu` reads it (nullable pos -> the GUI is valid
  anywhere). **Note:** the packet layout changed, so client and server must be updated together.
* **Progress bar used wall-clock time** (`System.currentTimeMillis()`), so it jumped, ignored lag and
  kept running while the game was paused. It now counts server ticks (`elapsedTicks`) and pauses with
  the world. `getRemainingTicks()` was added.
* **Redundant state packets** - the GUI state is pushed from a single `syncGuiState(ServerPlayer)`
  helper instead of two code paths that could fight each other.
* **`FoodCorruptionHelper`** resolves its three item tags once instead of rebuilding them on every
  call (they run in tick/eat/GUI hot paths) and all "mark as purified" writes go through one
  `markPurified(ItemStack)` helper (`PurifyFoodPacket.transformToNormal` is now a thin wrapper around
  `FoodCorruptionHelper.toNormalFood` instead of doing its own thing).
* Duplicate import in `PurifyGUIConvertProcedure` removed.

## Files that were not compiling (fixed, unrelated to purification)

The workspace did not build at all before this pass:

* `SweetapocalpysebiomeModFluids` - missing `import ...fluid.CaramelLavaFluid;`
* `SweetapocalpysebiomeModFluidTypes` - missing `import ...fluid.types.CaramelLavaFluidType;`
* `client/model/ModelStrawberry_Vine_Creeper` - raw Blockbench export left in the tree with
  `<T extends creeper>` (now `T extends Creeper`). It is registered in
  `SweetapocalpysebiomeModModels` but no renderer uses it; the entity uses
  `ModelStrawberryVineCreeper`. Delete it in MCreator if it was pasted by accident.

`gradlew compileJava --offline` succeeds after these changes.

## Still worth doing (design level, needs MCreator)

The chamber is still keyed **per player and per server run**, not per block: two players cannot share
one Purifier, contents are not saved to the world and the Purifier block has no block entity/container
of its own. Converting it into a `BaseContainerBlockEntity` (with `ContainerData` for progress) would
remove the last "hidden global storage" oddity. That is a bigger change because the LDLib GUI binds
`IItemHandler`s, but `LDLibGUIContext.getBoundItemHandler` / `Capabilities.ItemHandler.BLOCK` already
support binding to a real block container - see `docs/multi-loader-support.md`.


