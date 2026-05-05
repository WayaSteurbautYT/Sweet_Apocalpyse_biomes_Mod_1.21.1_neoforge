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
- `Enhanced Candy Titanium Sword` - Epic sword with healing abilities (12 damage, 3.0 knockback)
- `Candy Compressor` - Advanced crafting station
- `Chocolatecorrupted`, `Chocolatefragment` - Corruption system items
- `Corrupted Cookie` - Harmful food item that triggers corruption effects
- `Chocolate Boat`, `Chocolate Boat with Chest`
- `Candy Dimension Portal` - Portal item for accessing Candy Dimension
- `Candy Trader` - New villager profession for candy items

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
| **Enhanced Candy Sword Master** | Craft enhanced candy titanium sword | 50 XP |
| **Candy Trader Found** | Find and trade with Candy Trader | 25 XP |
| **Dimension Explorer** | Enter Candy Dimension | 30 XP + Candy Dimension Portal |
| **Corrupted Feaster** | Custom challenge | 50 XP + corrupted chocolate loot |
| **Titanium Candy Tools Master** | Obtain all four titanium candy tools | 100 XP |
| **Dimension Loot Master** | Open 10 Candy Dimension chests | 75 XP + Enhanced Candy Ingot |

### 🌍 Candy Dimension
- **New dimension** accessible via Candy Dimension Portal item
- **Portal system**: Use Candy Dimension Portal on obsidian to create portal
- **Dimension effects**: Saturation + Regeneration while in Candy Dimension
- **Exclusive structures**: Candy structures only spawn in this dimension

### ⚔️ Enhanced Candy Titanium Tools
- **Enhanced Candy Titanium Sword**: Epic-tier weapon with special abilities:
  - 12 damage (stronger than netherite)
  - 3.0 knockback (high knockback)
  - 1.8 attack speed (fast attacks)
  - **Healing ability**: Regenerates player when hitting enemies
  - **Candy corruption**: Applies poison and slowness to targets
- **Crafting**: Requires Enhanced Candy Titanium Ingot

### 🏪 Candy Trader Villager
- **New profession**: Candy Trader found in villages
- **Workstation**: Uses Candy Compressor block
- **Trading system**: Comprehensive candy item economy:
  - Titanium Candy Ingots (1 emerald, 10 uses)
  - Enhanced Candy Titanium Ingots (3 emeralds, 5 uses)
  - Chocolate Bars (2 emeralds, 12 uses)
  - Chocolate Corrupted (5 emeralds, 8 uses)
  - Corrupted Cookies (1 emerald, 20 uses)
  - Purify Potions (8 emeralds, 3 uses)

### 🎁 New Loot System
- **Candy Dimension Chests**: Rich loot with weighted drops:
  - 15% Titanium Candy Ingots
  - 10% Enhanced Candy Titanium Ingots
  - 20% Chocolate Bars
  - 25% Corrupted Cookies
  - 12% Purify Potions
  - 8% Chocolate Corrupted
  - 5% Emeralds
  - 3% Diamonds

- **Piglin Bartering**: Candy items available for bartering:
  - Chocolate Corrupted (20% weight)
  - Corrupted Cookies (15% weight)
  - Chocolate Fragments (10% weight)
  - Titanium Candy Ingots (8% weight)
  - Enhanced Candy Titanium Ingots (5% weight)
  - Chocolate Bars (3% weight)

### 🧪 Enhanced Brewing
| Output | Recipe |
|--------|--------|
| `Corrupted Brew` | `Potion + Chocolatecorrupted` |
| `Sugar Rush` | `Potion + Sugar + Titanium Candy Ingot` |
| `Sweet Aura` | `Potion + Titanium Candy Ingot` |
| `Purify Potion` | `Potion + Chocolatefragment` |
| `Enhanced Candy Brew` | `Potion + Enhanced Candy Titanium Ingot` |

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
| `Enhanced Candy Brew` | `Potion + Enhanced Candy Titanium Ingot` |

## 📅 Release Planning

### 🎯 Current Version: `1.5.0`

**Major Features Added:**
- 🌍 **Candy Dimension** - Complete new dimension with portal system
- ⚔️ **Enhanced Candy Titanium Tools** - Epic-tier weapons with special abilities
- 🏪 **Candy Trader Villager** - New profession with comprehensive trading
- 🎁 **Rich Loot System** - Candy Dimension chests and piglin bartering
- 🧪 **Enhanced Brewing** - New potions with Enhanced Candy Titanium Ingots

**Mod Requirements:**
- **Minecraft**: `1.21.1`
- **NeoForge**: `21.1.190+`
- **Java**: `17+`
- **Memory**: `4GB+ RAM recommended`

**Installation:**
1. Download the latest `Sweet_Apocalpyse_biomes_Mod_1.21.1_neoforge.jar`
2. Place in your `mods` folder
3. Launch Minecraft with NeoForge
4. Create new world to explore all features

**Quick Start Guide:**
1. Find **Candy Swamp** biome: `/locate biome sweetapocalpysebiome:candy_swamp`
2. Mine **Candy Titanium Ore** for ingots
3. Craft **Candy Compressor** for advanced recipes
4. Trade with **Candy Trader** villagers for rare items
5. Build **Candy Dimension Portal** to access the Candy Dimension
6. Explore **Candy Dimension** for rich loot and unique structures

**Getting Started:**
- **Early Game**: Mine chocolate wood → smelt → craft basic tools
- **Mid Game**: Find Candy Swamp → mine titanium ore → craft enhanced tools
- **Late Game**: Access Candy Dimension → trade for ultimate gear

## 🚀 What's Next

### 🎯 Good Fit for `1.6.0`
- **Candy Dimension Structures**: Unique buildings and dungeons
- **Enhanced Candy Armor Set**: Complete armor progression
- **More Custom Potions**: Additional effects and brewing recipes
- **World Integration**: Better biome distribution and worldgen features

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
