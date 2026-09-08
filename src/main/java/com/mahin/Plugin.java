package com.mahin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;
public class Plugin extends JavaPlugin implements Listener {
    private CommandSender console;
    private ItemStack CreateItem(Material material, String name, String lore, String CMDS) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        meta.lore(new ArrayList<>(List.of(Component.text(lore))));
        CustomModelDataComponent CMD = meta.getCustomModelDataComponent();
        CMD.setStrings(List.of(CMDS));
        meta.setCustomModelDataComponent(CMD);
        item.setItemMeta(meta);
        return item;
    }
    @Override
    public void onEnable() {
        ShapedRecipe FireCoreRecipe = new ShapedRecipe(new NamespacedKey(this, "fire_core"), CreateItem(Material.ENCHANTED_BOOK, "Fire Core", "Right click to unlock fire bending", "fire_core"));
        FireCoreRecipe.shape("BCM", "PNF", "RSL");
        FireCoreRecipe.setIngredient('B', Material.BLAZE_ROD);
        FireCoreRecipe.setIngredient('C', Material.CAMPFIRE);
        FireCoreRecipe.setIngredient('M', Material.MAGMA_BLOCK);
        FireCoreRecipe.setIngredient('P', Material.BLAZE_POWDER);
        FireCoreRecipe.setIngredient('N', Material.NETHER_STAR);
        FireCoreRecipe.setIngredient('F', Material.FIRE_CHARGE);
        FireCoreRecipe.setIngredient('R', Material.NETHERRACK);
        FireCoreRecipe.setIngredient('S', Material.SOUL_CAMPFIRE);
        FireCoreRecipe.setIngredient('L', Material.LAVA_BUCKET);
        Bukkit.addRecipe(FireCoreRecipe);
        ShapedRecipe WaterCoreRecipe = new ShapedRecipe(new NamespacedKey(this, "water_core"), CreateItem(Material.ENCHANTED_BOOK, "Water Core", "Right click to unlock water bending", "water_core"));
        WaterCoreRecipe.shape("BPB", "HNH", "BPB");
        WaterCoreRecipe.setIngredient('B', Material.BLUE_ICE);
        WaterCoreRecipe.setIngredient('P', Material.PRISMARINE);
        WaterCoreRecipe.setIngredient('H', Material.HEART_OF_THE_SEA);
        WaterCoreRecipe.setIngredient('N', Material.NETHER_STAR);
        Bukkit.addRecipe(WaterCoreRecipe);
        ShapedRecipe EarthCoreRecipe = new ShapedRecipe(new NamespacedKey(this, "earth_core"), CreateItem(Material.ENCHANTED_BOOK, "Earth Core", "Right click to unlock earth bending", "earth_core"));
        EarthCoreRecipe.shape("DRC", "MNT", "GBU");
        EarthCoreRecipe.setIngredient('D', Material.DEEPSLATE);
        EarthCoreRecipe.setIngredient('R', Material.DRIPSTONE_BLOCK);
        EarthCoreRecipe.setIngredient('C', Material.CALCITE);
        EarthCoreRecipe.setIngredient('M', Material.MUD);
        EarthCoreRecipe.setIngredient('N', Material.NETHER_STAR);
        EarthCoreRecipe.setIngredient('T', Material.TUFF);
        EarthCoreRecipe.setIngredient('G', Material.SUSPICIOUS_GRAVEL);
        EarthCoreRecipe.setIngredient('B', Material.MOSS_BLOCK);
        EarthCoreRecipe.setIngredient('U', Material.ROOTED_DIRT);
        Bukkit.addRecipe(EarthCoreRecipe);
        ShapedRecipe AirCoreRecipe = new ShapedRecipe(new NamespacedKey(this, "air_core"), CreateItem(Material.ENCHANTED_BOOK, "Air Core", "Right click to unlock air bending", "air_core"));
        AirCoreRecipe.shape("FBF", "WNW", "FBF");
        AirCoreRecipe.setIngredient('F', Material.FEATHER);
        AirCoreRecipe.setIngredient('B', Material.BREEZE_ROD);
        AirCoreRecipe.setIngredient('W', Material.WIND_CHARGE);
        AirCoreRecipe.setIngredient('N', Material.NETHER_STAR);
        Bukkit.addRecipe(AirCoreRecipe);
        this.console = Bukkit.getConsoleSender();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Bending Core has been enabled");
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack ItemInHand = event.getItem();
            if (ItemInHand != null) {
                List<Component> ItemInHandLore = ItemInHand.lore();
                if (ItemInHandLore != null) {
                    String ItemInHandLoreString = PlainTextComponentSerializer.plainText().serialize(ItemInHandLore.get(0));
                    if (ItemInHandLoreString.endsWith("bending")) {
                        ItemInHand.setAmount(ItemInHand.getAmount() - 1);
                        String element = ItemInHandLoreString.replace("Right click to unlock ", "").replace(" bending", "");
                        Player player = event.getPlayer();
                        Location player_location = player.getLocation();
                        World player_world = player.getWorld();
                        String ElementPermission = "bending.command.choose." + element;
                        if (player.hasPermission(ElementPermission)) {
                            player.sendMessage(Component.text("You already have " + element + " bending abilities").color(NamedTextColor.RED));
                            player_world.spawnParticle(Particle.EXPLOSION_EMITTER, player_location.clone().add(0, 1, 0), 1);
                        }
                        else {
                            String player_name = player.getName();
                            Bukkit.dispatchCommand(console, "lp user " + player_name + " permission set " + ElementPermission + " true");
                            if (!player.hasPermission("bending.command.choose")) {
                                Bukkit.dispatchCommand(console, "lp user " + player_name + " permission set bending.command.choose true");
                                Bukkit.dispatchCommand(console, "lp user " + player_name + " permission set bending.command.bind true");
                                Bukkit.dispatchCommand(console, "lp user " + player_name + " permission set bending.command.help true");
                                Bukkit.dispatchCommand(console, "lp user " + player_name + " permission set bending.command.toggle true");
                                Bukkit.dispatchCommand(console, "lp user " + player_name + " permission set bending.command.board true");
                            }
                            player.sendMessage(Component.text("You now have the ability to use " + element + " bending!").color(NamedTextColor.GREEN));
                            player.sendMessage(Component.text("Use '/bending choose' to select your ability").color(NamedTextColor.YELLOW));
                            player.sendMessage(Component.text("Use '/bending bind <slot>' to bind an ability").color(NamedTextColor.YELLOW));
                            player.sendMessage(Component.text("Use '/bending help <ability name>' to learn how to use an ability").color(NamedTextColor.YELLOW));
                            player.sendMessage(Component.text("Use '/bending toggle' to enable or disable your bending abilities").color(NamedTextColor.YELLOW));
                            Particle particle = switch (element) {
                                case "fire" -> Particle.FLAME;
                                case "water" -> Particle.SCULK_CHARGE_POP;
                                case "earth" -> Particle.SNEEZE;
                                default -> Particle.SNOWFLAKE;
                            };
                            for (int i = 0; i < 24; i++) {
                                double angle = 2 * Math.PI * i / 24;
                                double x = Math.cos(angle) * 2.5;
                                double z = Math.sin(angle) * 2.5;
                                player_world.spawnParticle(particle, player_location.clone().add(x, 1, z), 40, 0.2, 0.2, 0.2, 0.25);
                            }
                        }
                    }
                }
            }
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Bending Core has been disabled");
    }
}