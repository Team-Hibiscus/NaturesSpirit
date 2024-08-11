package net.hibiscus.naturespirit.util;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.world.HibiscusBiomes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerType;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.minecraft.village.VillagerType.BIOME_TO_TYPE;
import static net.minecraft.village.VillagerType.DESERT;

public class HibiscusVillagers {
   public static final VillagerType WISTERIA = VillagerTypeHelper.register(Identifier.of(MOD_ID, "wisteria"));
   public static final VillagerType CYPRESS = VillagerTypeHelper.register(Identifier.of(MOD_ID, "cypress"));
   public static final VillagerType ADOBE = VillagerTypeHelper.register(Identifier.of(MOD_ID, "adobe"));
   public static final VillagerType COCONUT = VillagerTypeHelper.register(Identifier.of(MOD_ID, "coconut"));

   public static void registerVillagers() {
      BIOME_TO_TYPE.put(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
      BIOME_TO_TYPE.put(HibiscusBiomes.CYPRESS_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.CARNATION_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.LAVENDER_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.STRATIFIED_DESERT, ADOBE);
      BIOME_TO_TYPE.put(HibiscusBiomes.LIVELY_DUNES, ADOBE);
      BIOME_TO_TYPE.put(HibiscusBiomes.BLOOMING_DUNES, ADOBE);
      BIOME_TO_TYPE.put(HibiscusBiomes.DRYLANDS, DESERT);
      BIOME_TO_TYPE.put(HibiscusBiomes.WOODED_DRYLANDS, DESERT);
      BIOME_TO_TYPE.put(HibiscusBiomes.TROPICAL_SHORES, COCONUT);


      TradeOfferHelper.registerWanderingTraderOffers(1,
              factories -> {
                 factories.add(new TradeOffers.SellItemFactory(HibiscusMiscBlocks.RED_MOSS_BLOCK, 1, 2, 5, 1));
                 factories.add(new TradeOffers.SellItemFactory(HibiscusMiscBlocks.BLUEBELL.getFlowerBlock(), 1, 1, 12, 1));
                 factories.add(new TradeOffers.SellItemFactory(HibiscusMiscBlocks.ANEMONE.getFlowerBlock(), 1, 1, 12, 1));
                 factories.add(new TradeOffers.SellItemFactory(HibiscusMiscBlocks.LOTUS_FLOWER_ITEM, 1, 1, 12, 1));
              }
      );

   }

}
