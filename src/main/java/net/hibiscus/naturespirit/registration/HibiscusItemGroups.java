package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
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

   public static final RegistryKey <ItemGroup> NS_MISC_ITEM_GROUP = register("misc");
   public static final RegistryKey <ItemGroup> NS_WOOD_ITEM_GROUP = register("wood");

   public static void registerItemGroup() {
      build(
              NS_MISC_ITEM_GROUP,
              FabricItemGroup.builder().displayName(Text.translatable("itemGroup" + ".natures_spirit" + ".misc_item_group")).icon(() -> new ItemStack(HibiscusMiscBlocks.PINK_SANDSTONE)).build()
      );
      build(
              NS_WOOD_ITEM_GROUP,
              FabricItemGroup.builder().displayName(Text.translatable("itemGroup" + ".natures_spirit" + ".wood_item_group")).icon(() -> new ItemStack(HibiscusMiscBlocks.FRAMED_PAPER_DOOR)).build()
      );
   }

   private static RegistryKey <ItemGroup> register(String id) {
      return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, id));
   }

   private static void build(RegistryKey <ItemGroup> key, ItemGroup itemGroup) {
      Registry.register(Registries.ITEM_GROUP, key, itemGroup);
   }

}