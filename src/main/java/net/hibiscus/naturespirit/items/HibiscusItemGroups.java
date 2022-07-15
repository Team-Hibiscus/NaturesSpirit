package net.hibiscus.naturespirit.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class HibiscusItemGroups {
    public static final ItemGroup NatureSpiritItemGroup = FabricItemGroupBuilder.build(new Identifier("nature_spirit"), () -> new ItemStack(HibiscusBlocks.REDWOOD[4]));
}
