package net.bobyjones.epicserveradditions.items;

import eu.pb4.polymer.api.item.PolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class RubyItem extends Item implements PolymerItem {
    public RubyItem(Settings settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {

        return Items.RED_DYE;
    }

}
