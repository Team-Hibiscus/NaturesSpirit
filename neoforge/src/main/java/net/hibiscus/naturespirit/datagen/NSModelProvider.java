package net.hibiscus.naturespirit.datagen;

import java.util.Set;
import java.util.stream.Stream;
import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.client.color.item.Constant;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;

public class NSModelProvider extends ModelProvider {

  private static final Set<Identifier> FOLIAGE_TINTED = Set.of(
      NSBlocks.SUGI.getLeaves().getId(),
      NSBlocks.LARCH.getLeaves().getId(),
      NSBlocks.MAHOGANY.getLeaves().getId(),
      NSBlocks.ASPEN.getLeaves().getId());

  private static final Set<Identifier> GRASS_TINTED = Set.of(
      NSBlocks.LUSH_FERN.getId(),
      NSBlocks.LARGE_LUSH_FERN.getId());

  public NSModelProvider(PackOutput output) {
    super(output, NaturesSpirit.MOD_ID);
  }

  // Item definitions delegate to the hand-authored models/item JSONs; no super call,
  // the vanilla generators would emit all vanilla content.
  @Override
  protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
    getKnownItems().map(Holder::value).forEach(item -> {
      Identifier id = BuiltInRegistries.ITEM.getKey(item);
      Identifier model = ModelLocationUtils.getModelLocation(item);
      ItemModel.Unbaked unbaked;
      if (FOLIAGE_TINTED.contains(id)) {
        unbaked = ItemModelUtils.tintedModel(model, new Constant(FoliageColor.FOLIAGE_DEFAULT));
      } else if (GRASS_TINTED.contains(id)) {
        unbaked = ItemModelUtils.tintedModel(model, new GrassColorSource(0.5F, 1.0F));
      } else {
        unbaked = ItemModelUtils.plainModel(model);
      }
      blockModels.itemModelOutput.accept(item, unbaked);
    });
  }

  // Blockstates are hand-authored; an empty stream skips their datagen completeness check.
  @Override
  protected Stream<? extends Holder<Block>> getKnownBlocks() {
    return Stream.empty();
  }
}
