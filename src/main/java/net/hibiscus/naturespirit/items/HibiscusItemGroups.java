package net.hibiscus.naturespirit.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusItemGroups {

    public static final RegistryKey <ItemGroup> NATURES_SPIRIT_ITEM_GROUP = register("natures_spirit");
    public static void registerItemGroup() {
        build(NATURES_SPIRIT_ITEM_GROUP, FabricItemGroup.builder().displayName(Text.translatable("itemGroup.natures_spirit.natures_spirit_item_group")).icon(() -> new ItemStack(HibiscusBlocks.REDWOOD[4])).build());
    }

    private static RegistryKey <ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, id));
    }

    private static void build(RegistryKey<ItemGroup> key, ItemGroup itemGroup) {
        Registry.register(Registries.ITEM_GROUP, key, itemGroup);
    }

}