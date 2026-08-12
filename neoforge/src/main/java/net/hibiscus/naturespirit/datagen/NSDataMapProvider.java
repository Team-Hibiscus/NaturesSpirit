package net.hibiscus.naturespirit.datagen;

import net.hibiscus.naturespirit.NSCommonHooks;
import net.hibiscus.naturespirit.registration.NSCompostables;
import net.hibiscus.naturespirit.registration.NSFuels;
import net.hibiscus.naturespirit.registration.NSStrippables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.BiomeVillagerType;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

import java.util.concurrent.CompletableFuture;

public class NSDataMapProvider extends DataMapProvider {

  public NSDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(packOutput, lookupProvider);
  }

  @Override
  protected void gather(HolderLookup.Provider provider) {
    Builder<Strippable, Block> strippables = builder(NeoForgeDataMaps.STRIPPABLES);

    for (NSStrippables.Entry entry : NSStrippables.STRIPPABLES) {
      strippables.add(BuiltInRegistries.BLOCK.wrapAsHolder(entry.log().get()), new Strippable(entry.stripped().get()), false);
    }

    Builder<FurnaceFuel, Item> fuels = builder(NeoForgeDataMaps.FURNACE_FUELS);

    for (NSFuels.Entry entry : NSFuels.FUELS) {
      fuels.add(BuiltInRegistries.ITEM.wrapAsHolder(entry.item().get().asItem()), new FurnaceFuel(entry.burnTime()), false);
    }

    Builder<Compostable, Item> compostables = builder(NeoForgeDataMaps.COMPOSTABLES);

    for (NSCompostables.Entry entry : NSCompostables.COMPOSTABLES) {
      compostables.add(BuiltInRegistries.ITEM.wrapAsHolder(entry.item().get().asItem()), new Compostable(entry.chance()), false);
    }

    Builder<BiomeVillagerType, Biome> villagerTypes = builder(NeoForgeDataMaps.VILLAGER_TYPES);

    for (NSCommonHooks.VillagerBiomeType entry : NSCommonHooks.VILLAGER_BIOME_TYPES) {
      villagerTypes.add(entry.biome(), new BiomeVillagerType(entry.villagerType()), false);
    }
  }
}
