# Sweet Apocalypse Biomes Mod

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-brightgreen.svg)
![NeoForge](https://img.shields.io/badge/NeoForge-21.1.190+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

> A deliciously dangerous Minecraft mod that adds candy-themed biomes, chocolate wood structures, and corruption mechanics to Minecraft 1.21.1 NeoForge.

`SweetApocalpyseBiome` is a Minecraft `1.21.1` NeoForge mod built in MCreator that adds a candy-themed swamp biome, chocolate wood building blocks, titanium candy progression, corruption-themed food systems, custom potions, and advancement-based progression.

## 🚀 Platform Support

| Component | Version |
|-----------|---------|
| **Minecraft** | `1.21.1` |
| **NeoForge** | `21.1.190+` |
| **Mod ID** | `sweetapocalpysebiome` |
| **Current Version** | `1.0.1` (ready for `1.1.0` content update) |

## 🌍 Main Features

### 🏞️ Biome and Worldgen
- **Candy Swamp** biome - A sweet but dangerous swamp environment
- **Chocolate House** world structure - Automated chocolate buildings
- **Candy Titanium Ore** worldgen - Rare candy titanium deposits
- Candy-themed grass and dirt generation

### 🧱 New Blocks
#### Building Materials
- `Cooky Cobble Stone`, `Cooky Stone`, `Cooky Smooth Stone`
- `Candy Titanium Ore`, `Candy Titanium Block`
- `Candy Grass`, `Candy Dirt`

#### Chocolate Wood Set
- `Chocolate Log`, `Chocolate Wood`, `Stripped Chocolate Log`, `Stripped Chocolate Wood`
- `Chocolate Planks`, `Stripped Chocolate Planks`, `Chocolate Leaves`
- `Chocolate Stairs`, `Chocolate Slab`, `Chocolate Fence`, `Chocolate Fence Gate`
- `Chocolate Door`, `Chocolate Trapdoor`, `Chocolate Pressure Plate`, `Chocolate Button`
- `Chocolate Sign`, `Chocolate Hanging Sign`

### ⚔️ New Items and Gear
- `Titanium Candy Ingot`, `Enhanced Candy Ingot`
- `Titanium Candy Sword`, `Titanium Candy Pickaxe`, `Titanium Candy Axe`, `Titanium Candy Shovel`
- `Candy Compressor` - Advanced crafting station
- `Chocolatecorrupted`, `Chocolatefragment` - Corruption system items
- `Corrupted Cookie` - Harmful food item that triggers corruption effects
- `Chocolate Boat`, `Chocolate Boat with Chest`

### ☠️ Corruption System
- Normal `Cookies` are automatically flagged as corrupted unless purified first
- Eating corrupted food applies `Poison` and `Hunger` effects
- `Chocolatecorrupted` tracks a timer and transforms into `Chocolatefragment` after ~60 seconds
- `Chocolatefragment` is always stored as purified
- Drinking the custom `Purify` potion cleanses corrupted items in player inventory

### 🧪 Potions and Effects
#### Corrupted Brew
- `Poison II` for 20s
- `Weakness I` for 30s
- `Blindness` for 10s
- Applies custom `It's Not Good For The Skin` effect

#### Sugar Rush
- `Speed II` for 90s
- `Haste I` for 60s

#### Sweet Aura
- `Saturation I` for 30s
- `Luck II` for 60s
- Applies custom `A Cookie God Effect`

### ✨ Custom Effects
#### A Cookie God Effect
- Triggered by `Sweet Aura` potion
- Lets player consume a cookie to purge harmful custom effects
- Grants temporary buffs: `Saturation`, `Health Boost`, `Strength`, `Haste`
- Custom reward behavior tied to death while affected

#### A Sticky Situation Effect
- Heavy movement and mining penalties
- Applies darkness and direct damage
- Can lead to `Diabetes Type 1` advancement path

#### It's Not Good For The Skin Effect
- Harmful corruption-linked custom effect
- Used by `Corrupted Brew`

### 🏆 Advancements
| Advancement | Requirement | Reward |
|-------------|-------------|--------|
| **A Cookie God Miner** | Custom progression around `Cooky Stone` | 20 XP |
| **The Liberator Of Candy** | Enter `Candy Swamp` biome | Candy biome loot |
| **Corrupted Cookie Crafter** | Craft first corrupted cookie | 10 XP + Purify potion recipe |
| **A Sticky Situation** | Custom challenge | - |
| **Thats Not Good For Skin** | Custom task | - |
| **Diabetes Type 1** | Death from sticky-effect path | - |
| **Corrupted Feaster** | Custom challenge | 50 XP + corrupted chocolate loot |
| **Titanium Candy Tools Master** | Obtain all four titanium candy tools | 100 XP |

## 📋 Crafting Recipes

### ⚡ Titanium and Candy Progression
| Output | Recipe |
|--------|--------|
| `Titanium Candy Ingot x9` | `2x2 Candy Titanium Block` |
| `Candy Titanium Block x1` | `3x3 Titanium Candy Ingot` |
| `Enhanced Candy Ingot x1` | `Titanium Candy Ingot + Glistering Melon Slice + Ghast Tear` |
| `Titanium Candy Sword x1` | `2 Titanium Candy Ingots + 1 Stick` |
| `Titanium Candy Pickaxe x1` | `3 Titanium Candy Ingots + 2 Sticks` |
| `Titanium Candy Axe x1` | `3 Titanium Candy Ingots + 2 Sticks` |
| `Titanium Candy Shovel x1` | `1 Titanium Candy Ingot + 2 Sticks` |
| `Candy Compressor x1` | `4 Candy Titanium Blocks + 4 Titanium Candy Ingots + 1 Redstone Block` |

### 🔄 Corruption and Food Loop
| Output | Recipe |
|--------|--------|
| `Chocolatecorrupted x1` | `4 Chocolatefragment` |
| `Chocolatefragment x2` | `2 Chocolatecorrupted` stacked vertically |
| `Chocolatecorrupted x1` | Smelt `Chocolatefragment` |
| `Chocolatecorrupted x1` | Smelt any chocolate log |
| `Corrupted Cookie x4` | Any item in the `cookie_stones` tag |

### 🔥 Cooky Stone Processing
| Output | Recipe |
|--------|--------|
| `Cooky Stone x1` | Smelt `Cooky Cobble Stone` |
| `Cooky Stone x1` | Blast `Cooky Cobble Stone` |
| `Cooky Smooth Stone x1` | Smelt `Cooky Stone` |

### 🌳 Chocolate Wood Set
| Output | Recipe |
|--------|--------|
| `Chocolate Planks x4` | Any item in the `chocolate_logs` tag |
| `Chocolate Wood x3` | `2x2 Chocolate Log` |
| `Stripped Chocolate Wood x3` | `2x2 Stripped Chocolate Log` |
| `Chocolate Stairs x4` | Standard stair recipe using `Chocolate Planks` |
| `Chocolate Slab x6` | `3 Chocolate Planks` |
| `Chocolate Fence x3` | `4 Chocolate Planks + 2 Sticks` |
| `Chocolate Fence Gate x1` | `2 Chocolate Planks + 4 Sticks` |
| `Chocolate Door x3` | `6 Chocolate Planks` |
| `Chocolate Trapdoor x2` | `6 Chocolate Planks` |
| `Chocolate Pressure Plate x1` | `2 Chocolate Planks` |
| `Chocolate Button x1` | `1 Chocolate Plank` |
| `Chocolate Sign x3` | `6 Chocolate Planks + 1 Stick` |
| `Chocolate Hanging Sign x6` | `6 Stripped Chocolate Logs + 2 Chains` |
| `Chocolate Boat x1` | Standard boat recipe using `Chocolate Planks` |
| `Chocolate Boat with Chest x1` | `Chocolate Boat + Chest` |

### 🧪 Brewing
| Output | Recipe |
|--------|--------|
| `Corrupted Brew` | `Potion + Chocolatecorrupted` |
| `Sugar Rush` | `Potion + Sugar + Titanium Candy Ingot` |
| `Sweet Aura` | `Potion + Titanium Candy Ingot` |
| `Purify Potion` | `Potion + Chocolatefragment` |

## 📅 Release Planning

### 🎯 Good Fit for `1.1.0`
- New `Candy Swamp` biome and structure content
- Full chocolate wood block family
- Titanium candy material progression
- Titanium candy tool set
- Corruption system and corrupted chocolate loop
- Custom potions and advancement chain

### 🚀 Good Fit for `1.2.0`
- Unique candy titanium tool textures
- GitHub-ready documentation pass
- Additional balancing and polish around progression
- Follow-up release after the first big content milestone

## ⚠️ Known Issues & WIP

- `Candy Compressor` has an item and recipe, but actual use logic is still marked `TODO` in code
- `Titanium Candy Armor` exists in code but is not part of documented crafting progression yet
- Some item names and advancement text use rough placeholder wording and can be polished later

## 📁 Repository Structure

- **Texture prompts** for the titanium candy item set are stored in [`TEXTURE_PROMPTS.md`](TEXTURE_PROMPTS.md)
- Local Git history includes null-safety fixes and new titanium tool texture wiring
- Mod metadata is located in [`src/main/resources/META-INF/neoforge.mods.toml`](src/main/resources/META-INF/neoforge.mods.toml)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📞 Support

If you encounter any issues or have suggestions, please:
1. Check the [Issues](../../issues) page
2. Create a new issue with detailed information
3. Join our community discussions

---

**Enjoy your sweet apocalypse!** 🍭⚔️
