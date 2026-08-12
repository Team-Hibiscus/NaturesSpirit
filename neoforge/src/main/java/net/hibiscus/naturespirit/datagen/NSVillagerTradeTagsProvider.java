package net.hibiscus.naturespirit.datagen;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class NSVillagerTradeTagsProvider extends KeyTagProvider<VillagerTrade> {

  public NSVillagerTradeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(output, Registries.VILLAGER_TRADE, lookupProvider, NaturesSpirit.MOD_ID);
  }

  @Override
  protected void addTags(HolderLookup.Provider registries) {
    TagAppender<ResourceKey<VillagerTrade>, VillagerTrade> common = tag(VillagerTradeTags.WANDERING_TRADER_COMMON);
    NSVillagerTrades.WANDERING_TRADER_COMMON.forEach(trade -> common.add(trade.key()));
  }
}
