package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusItemGroups {

   public static final ResourceKey <CreativeModeTab> NATURES_SPIRIT_ITEM_GROUP = register("natures_spirit");

   public static void registerItemGroup() {
      build(NATURES_SPIRIT_ITEM_GROUP,
              FabricItemGroup.builder()
                      .title(Component.translatable("itemGroup" + ".natures_spirit" + ".natures_spirit_item_group"))
                      .icon(() -> new ItemStack(HibiscusWoods.REDWOOD[4]))
                      .build()
      );
   }

   private static ResourceKey <CreativeModeTab> register(String id) {
      return ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, id));
   }

   private static void build(ResourceKey <CreativeModeTab> key, CreativeModeTab itemGroup) {
      Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, itemGroup);
   }

}