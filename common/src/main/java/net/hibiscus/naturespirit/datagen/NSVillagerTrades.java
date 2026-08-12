package net.hibiscus.naturespirit.datagen;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NSVillagerTrades {

  public record WanderingTrade(ResourceKey<VillagerTrade> key, ItemLike gives, int givesCount, int emeralds, int maxUses) {
  }

  public static final List<WanderingTrade> WANDERING_TRADER_COMMON = commonTrades();

  private static List<WanderingTrade> commonTrades() {
    List<WanderingTrade> trades = new ArrayList<>();

    trades.add(trade("red_moss_block", NSBlocks.RED_MOSS_BLOCK, 2, 1, 5));
    trades.add(trade("hibiscus", NSBlocks.HIBISCUS.getFlowerBlock(), 2, 1, 12));
    trades.add(trade("blue_iris", NSBlocks.BLUE_IRIS.getFlowerBlock(), 2, 1, 12));
    trades.add(trade("black_iris", NSBlocks.BLACK_IRIS.getFlowerBlock(), 2, 1, 12));
    trades.add(trade("anemone", NSBlocks.ANEMONE.getFlowerBlock(), 2, 1, 12));
    trades.add(trade("lotus_flower", NSBlocks.LOTUS_FLOWER_ITEM, 2, 1, 12));

    trades.add(sapling("redwood_sapling", NSBlocks.REDWOOD.getSapling()));
    trades.add(sapling("sugi_sapling", NSBlocks.SUGI.getSapling()));
    trades.add(sapling("purple_wisteria_sapling", NSBlocks.WISTERIA.getPurpleSapling()));
    trades.add(sapling("white_wisteria_sapling", NSBlocks.WISTERIA.getWhiteSapling()));
    trades.add(sapling("blue_wisteria_sapling", NSBlocks.WISTERIA.getBlueSapling()));
    trades.add(sapling("pink_wisteria_sapling", NSBlocks.WISTERIA.getPinkSapling()));
    trades.add(sapling("fir_sapling", NSBlocks.FIR.getSapling()));
    trades.add(sapling("willow_sapling", NSBlocks.WILLOW.getSapling()));
    trades.add(sapling("aspen_sapling", NSBlocks.ASPEN.getSapling()));
    trades.add(sapling("red_maple_sapling", NSBlocks.MAPLE.getRedSapling()));
    trades.add(sapling("orange_maple_sapling", NSBlocks.MAPLE.getOrangeSapling()));
    trades.add(sapling("yellow_maple_sapling", NSBlocks.MAPLE.getYellowSapling()));
    trades.add(sapling("cypress_sapling", NSBlocks.CYPRESS.getSapling()));
    trades.add(sapling("olive_sapling", NSBlocks.OLIVE.getSapling()));
    trades.add(sapling("joshua_sapling", NSBlocks.JOSHUA.getSapling()));
    trades.add(sapling("ghaf_sapling", NSBlocks.GHAF.getSapling()));
    trades.add(sapling("palo_verde_sapling", NSBlocks.PALO_VERDE.getSapling()));
    trades.add(sapling("cedar_sapling", NSBlocks.CEDAR.getSapling()));
    trades.add(sapling("larch_sapling", NSBlocks.LARCH.getSapling()));
    trades.add(sapling("mahogany_sapling", NSBlocks.MAHOGANY.getSapling()));
    trades.add(sapling("saxaul_sapling", NSBlocks.SAXAUL.getSapling()));

    return List.copyOf(trades);
  }

  private static WanderingTrade sapling(String path, ItemLike gives) {
    return trade(path, gives, 5, 5, 8);
  }

  private static WanderingTrade trade(String path, ItemLike gives, int givesCount, int emeralds, int maxUses) {
    ResourceKey<VillagerTrade> key = ResourceKey.create(Registries.VILLAGER_TRADE, NaturesSpirit.id("wandering_trader/" + path));
    return new WanderingTrade(key, gives, givesCount, emeralds, maxUses);
  }

  public static void bootstrap(BootstrapContext<VillagerTrade> context) {
    for (WanderingTrade trade : WANDERING_TRADER_COMMON) {
      context.register(trade.key(), new VillagerTrade(
          new TradeCost(Items.EMERALD, trade.emeralds()),
          new ItemStackTemplate(trade.gives().asItem(), trade.givesCount()),
          trade.maxUses(),
          1,
          0.5F,
          Optional.empty(),
          List.of()));
    }
  }
}
