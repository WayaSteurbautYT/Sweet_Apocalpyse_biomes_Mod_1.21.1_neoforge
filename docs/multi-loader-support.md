# Fabric / Forge (multi-loader) support: feasibility

## Where this project stands today

* This is an **MCreator workspace** (`sweetapocalpysebiome.mcreator`) generating a **single-loader
  NeoForge 1.21.1** mod. `build.gradle` is an MCreator-generated NeoForge MDK (NeoForge
  `21.1.x`, `mcreator_link.jar`, NeoForge access transformers). There is no multi-loader layout
  (no `common/` + per-loader modules, no Architectury/Loom, no MultiLoader template).
* MCreator regenerates code on every workspace save, so hand-written Java in `src` is only safe in
  the places MCreator treats as custom code. A second loader cannot be "toggled on" in the workspace.
* The mod uses NeoForge APIs throughout: `DeferredRegister`/NeoForge registries, `@EventBusSubscriber`,
  `Capabilities.ItemHandler`/`IItemHandler`/`ItemStackHandler`, `FluidType` + `IFluidHandler`,
  `PacketDistributor`/`IPayloadContext` custom payloads, NeoForge event bus, NeoForge mixins
  (`BiomeSourcePresetMixin`, `NoiseGeneratorSettingsMixin`), and the `capability` package
  (`PlayerDnaCapability`, `PlayerSugarCapability`) built on NeoForge capability providers.
* **LDLib2 is NeoForge-only.** The dependency is
  `com.lowdragmc.ldlib2:ldlib2-neoforge-1.21.1:...:all` and the LDLib2 README states its
  compatibility work "focuses on modern NeoForge". There is no Fabric/Forge artifact. It is used by
  `PurifyLDLibGUIUI`, `client/gui/ldlib/LDLibGUIContext`, `client/gui/ldlib/LDLibUITemplateLoader`
  and the open-check in `PurifyGUICraftPacket`.
* MCreator itself: for 1.21.1 it targets NeoForge (community Fabric generator plugins exist but cover
  a subset of features; there is no MCreator Forge 1.21.1 generator - MCreator's Forge generator
  stops at 1.20.1).

## Hard blockers for "just build it for another loader"

1. **LDLib2 GUI** - the fancy purify window (`PurifyLDLibGUIUI`, `ModularUIContainerMenu`,
   `WindowDragHelper`, Taffy layout, `PlayerUIMenuType`) cannot load on Fabric/Forge. Either drop it
   (the vanilla `PurifyGUIScreen`/`PurifyGUIMenu` already exists and works) or maintain a fork of
   LDLib2 for the other loader (large, LGPL, upstream unlikely to merge).
2. **Registration / events / networking / capabilities / fluids** all differ per loader - NeoForge
   `DeferredRegister`, `PacketDistributor`, `Capabilities.*`, `FluidType` have no direct Fabric or
   legacy-Forge equivalents (Fabric: `Registry.register`, `PayloadTypeRegistry`, `ItemStorage`
   component-style APIs, tag-driven fluids; Forge 1.21.1: same shape as NeoForge but different
   packages/artifacts).
3. **Two mixins** target worldgen internals; mixin configs, refmaps and ATs are loader-specific.
4. **MCreator workflow** - after going multi-loader, MCreator can no longer regenerate the shipped
   jar; the generated resources (recipes, tags, loot tables, lang, models, blockstates - all JSON)
   are loader-agnostic and stay usable, but the Java becomes a hand-maintained project.

## Options

| # | Option | Effort | Keeps MCreator? | Result |
| --- | --- | --- | --- | --- |
| A | **Stay NeoForge-only** (status quo) | 0 | Yes | One jar for NeoForge 1.21.1. This is what the project builds and ships today. |
| B | **Manual multi-loader refactor** (Architectury Loom or MultiLoader template: `common` + `neoforge` + `fabric`) | Weeks, then permanent upkeep | No - standalone Gradle repo, MCreator only as a resource exporter | Real `neoforge` + `fabric` jars from one source. |
| C | **MCreator Fabric generator plugin** | Days-weeks, but partial | Yes (with plugin) | Fabric jar only, and only if every feature used by the workspace is supported by the plugin; LDLib GUI must still be replaced. |
| D | **Forge 1.21.1 jar** | Weeks (manual) | No | Forge 1.21.1 exists upstream but is not what MCreator generates; closest to NeoForge but still a full package/API swap. |

## Recommended path (if a second loader is a real requirement)

1. **Make the game logic loader-agnostic first, while still on NeoForge only.** Introduce a thin
   seam layer in this workspace so the blast radius of a port is small:
   * `platform/PlatformHelper` - mod loader detection, isModLoaded, sided work.
   * `platform/Networking` - send payload helpers wrapping `PacketDistributor`.
   * `platform/Inventories` - wrap `IItemHandler`/`ItemStackHandler` behind a 3-slot interface used
     by the purify code (the LDLib `ItemSlot`s already talk to `IItemHandler`, so only this seam
     needs a Fabric `ItemStorage`/`Inventory` implementation).
   * Keep NeoForge-only imports out of `procedures/`, `FoodCorruptionHelper`, `PurifyGUIElementCustom`
     and the network handlers.
2. **Single GUI for all loaders: use the vanilla `PurifyGUIScreen` + `PurifyGUIMenu` path** and keep
   `PurifyLDLibGUIUI` as an optional NeoForge-only enhancement behind `isModLoaded("ldlib2")`.
   This removes blocker #1 entirely (the LDLib UI has no Fabric build).
3. **Replace NeoForge-only subsystems** in the port: `PlayerDnaCapability`/`PlayerSugarCapability` ->
   data components or a small per-loader attachment wrapper; `FluidType` -> tag-driven fluid handling
   plus a per-loader fluid render handler; NeoForge capabilities -> `ItemStorage` on Fabric.
4. **Split the build**: keep this MCreator workspace as the NeoForge source of truth; create a
   multi-loader repo (Architectury Loom if you want Fabric+NeoForge from one tree, or the
   MultiLoader template if you want NeoForge+Forge) that consumes the exported resources and the
   loader-agnostic `common` Java. CI: `gradlew build` on both loaders, plus a test matrix
   (singleplayer, dedicated server, LAN) for the purify GUI flows.
5. **Do the port only after the duplication fix is shipped and verified** - the fix changes packet
   layouts (`OpenPurifyGUIPacket` now uses an optional position) and every loader must ship the same
   protocol version.

## Honest bottom line

* **Today: NeoForge 1.21.1 only.** There is no switch to flip; nothing in the workspace produces a
  Fabric or Forge jar.
* A **Fabric port is feasible but is a rewrite of the platform layer, not a rebuild**: drop LDLib2,
  replace capabilities/fluid APIs, keep the JSON resources and the vanilla purify GUI. Budget it as a
  dedicated project (weeks), and expect to maintain it outside MCreator.
* If the goal is "more players can install the mod", the cheapest honest step is **not** a port but
  shipping a well-tested NeoForge build (plus an optional "no-LDLib" build) - and, if you do want a
  second loader, starting with **Fabric** (largest non-NeoForge player base) rather than Forge.

