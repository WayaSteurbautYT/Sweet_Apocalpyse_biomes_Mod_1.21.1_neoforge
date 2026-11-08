# Git workflow

This workspace uses `main` on GitHub as its shared copy. On Linux, clone it
with GitHub authentication enabled, then run:

```sh
git clone https://github.com/WayaSteurbautYT/Sweet_Apocalpyse_biomes_Mod_1.21.1_neoforge.git
cd Sweet_Apocalpyse_biomes_Mod_1.21.1_neoforge
./sync-before-launch.sh
```

To update automatically before launching MCreator, configure the MCreator
desktop shortcut to run the updater followed by the MCreator launcher command:

```sh
/absolute/path/to/Sweet_Apocalpyse_biomes_Mod_1.21.1_neoforge/sync-before-launch.sh /absolute/path/to/mcreator-launcher
```

Replace the launcher path with the one used by the Linux installation. The
updater fetches GitHub and fast-forwards only. It stops without changing files
if there are local edits, non-ignored untracked files, local commits that are
not on GitHub, or no upstream branch. Commit or stash local work before
updating; it never resets or auto-commits.
