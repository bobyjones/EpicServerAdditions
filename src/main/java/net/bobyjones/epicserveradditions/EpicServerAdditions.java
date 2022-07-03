package net.bobyjones.epicserveradditions;

import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.bobyjones.epicserveradditions.Events.ZombieEvent;
import net.bobyjones.epicserveradditions.items.RubyItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.logging.Logger;

public class EpicServerAdditions implements ModInitializer {
    public static final String MOD_ID = "epicservereadditions";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);
    public static final RubyItem RUBY_ITEM = new RubyItem(new FabricItemSettings().rarity(Rarity.RARE).group(ItemGroup.MISC));
    @Override
    public void onInitialize() {

        PolymerRPUtils.addAssetSource(MOD_ID);

        //register items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ruby_item"), RUBY_ITEM);

        //register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, two, env) -> {
            dispatcher.register(CommandManager.literal("StartEvent").then(CommandManager.literal("zombie").executes(context -> {
                if (context.getSource().hasPermissionLevel(4) && context.getSource().isExecutedByPlayer() && !context.getSource().getWorld().isClient) {
                    ZombieEvent.run(context.getSource().getPlayer());
                    return 1;
                }
                return -1;
            })));
        });
    }
}
