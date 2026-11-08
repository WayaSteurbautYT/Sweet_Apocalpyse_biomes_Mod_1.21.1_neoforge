# Sweet Apocalypse Biomes 2.6.3

Minecraft 1.21.1 | NeoForge 21.1.232

## Changes

- Fixed duplicated Purifier inputs by switching the block to a standard server-side container menu, so placing items consumes them from the player's inventory correctly.
- One Purify Potion processes the full corrupted-food stack, one item at a time. The chamber shows batch progress and reports purification status.
- Purification continues in the background after the GUI closes and stops when the food stack is finished or the output cannot accept more items.
- Breaking the Purifier cancels its active process and drops its chamber contents; stored Purify Potions become empty bottles.
- Added Purifier discovery and first-purification advancements with a toast, plus a 16-item purification milestone.
- Restored the Purifier Block to the Sweet Apocalypse creative tab and corrected its recipe unlock.
- Restored Chocolate Bar recipe-book unlocks for Chocolate Fragments.
- Added names and descriptions for custom enchantments and implemented their gameplay effects.