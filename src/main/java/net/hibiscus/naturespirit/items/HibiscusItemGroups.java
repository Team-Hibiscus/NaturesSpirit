package net.hibiscus.naturespirit.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusItemGroups {

    public static ResourceLocation NatureSpiritItemGroupResourceLocation = new ResourceLocation(MOD_ID, "nature_spirit");
    public static CreativeModeTab NatureSpiritItemGroup;

    public static void registerItemGroup() {
        NatureSpiritItemGroup = FabricItemGroup.builder(NatureSpiritItemGroupResourceLocation).icon(() -> new ItemStack(HibiscusBlocks.REDWOOD[4])).build();
        ItemGroupEvents.modifyEntriesEvent(NatureSpiritItemGroup).register(entries -> {entries.accept(HibiscusBlocks.ANEMONE.asItem());});
    }
}
