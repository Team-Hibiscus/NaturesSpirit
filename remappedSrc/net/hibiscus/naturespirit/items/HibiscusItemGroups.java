package net.hibiscus.naturespirit.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusItemGroups {

    public static Identifier NatureSpiritItemGroupResourceLocation = new Identifier(MOD_ID, "nature_spirit");
    public static ItemGroup NatureSpiritItemGroup;

    public static void registerItemGroup() {
        NatureSpiritItemGroup = FabricItemGroup.builder(NatureSpiritItemGroupResourceLocation).icon(() -> new ItemStack(HibiscusBlocks.REDWOOD[4])).build();
        ItemGroupEvents.modifyEntriesEvent(NatureSpiritItemGroup).register(entries -> {entries.add(HibiscusBlocks.ANEMONE.asItem());});
    }
}
