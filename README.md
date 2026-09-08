Bending Core is an add-on for the Bending plugin that changes how players gain access to bending. Normally with Bending, players can freely pick whichever element they want. Bending Core removes that freedom and turns each element into something you have to earn by crafting a special item first. This makes unlocking an element feel like a real progression goal rather than a menu choice

There are four element cores: fire core, water core, earth core, and air core. Each one is a crafted enchanted book made from materials that match its element. The fire core uses things like blaze rods, campfires, magma blocks, and a lava bucket. The water core uses blue ice, prismarine, and a heart of the sea. The earth core uses deepslate, dripstone, tuff, moss, and other earthy blocks. The air core uses feathers, breeze rods, and wind charges. Every single recipe also requires a Nether Star in the center, so obtaining any element is deliberately expensive and something a player has to work toward

Once a player has crafted a core, they simply right click it while holding it. This consumes the core regardless of the outcome. If the player does not yet have that element, they are permanently granted those bending abilities, shown a short set of instructions on how to choose, bind, and use them, and a burst of element-themed particles plays around them to celebrate the unlock. If the player already has that element, the core is still consumed but nothing is granted, so players should be careful not to use a core for an element they already have

Under the hood, the cores are identified by their lore text rather than by name or a data tag. When a player right clicks an item, the plugin reads the first line of its lore, and if it ends with the word "bending" it treats the item as a core and extracts the element name from that line. Unlocking is handled entirely through LuckPerms. The plugin runs console commands to grant the player the permission for their chosen element, and if this is the player's first ever element, it also grants them the shared bending command permissions (choose, bind, help, toggle, and board) so they can actually use the system. Because everything is permission based, a player's unlocked elements persist properly and are managed by LuckPerms rather than the plugin storing its own data. Each core also has a custom model data string assigned to it (fire_core, water_core, earth_core, and air_core respectively), so server owners can apply custom textures to them through a resource pack if they wish

This plugin has no config file. Any custom behaviour requires modifying the source code directly and rebuilding the jar from scratch

This plugin was built for Minecraft 26.2 running on PaperMC. It may or may not work on older or newer versions of Minecraft or other server software

This plugin depends on the Bending plugin and LuckPerms

- Bending: https://modrinth.com/plugin/bending
- LuckPerms: https://modrinth.com/plugin/luckperms

- In order to work correctly, this plugin assumes that LuckPerms' default group has '*' set to false
- LuckPerms' default group must be configured so that 'bending.board' and 'bending.ability.*' permissions are set to true

Licensed under the Apache License 2.0
