package net.hibiscus.naturespirit.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class HibiscusItemGroups {
    public static final CreativeModeTab NatureSpiritItemGroup = FabricItemGroupBuilder.build(new ResourceLocation("nature_spirit"), () -> new ItemStack(HibiscusBlocks.REDWOOD[4]));
}
