package net.hibiscus.naturespirit.client;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.client.render.CheeseArrowEntityRenderer;
import net.hibiscus.naturespirit.client.render.PizzaBlockEntityRenderer;
import net.hibiscus.naturespirit.client.render.PizzaToppingModel;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class NSClient {

  public static final ModelLayerLocation PIZZA_TOPPING = new ModelLayerLocation(NaturesSpirit.id("pizza"), "toppings");

  public static final List<TintEntry> BLOCK_TINTS = new ArrayList<>();
  public static final List<LayerEntry> LAYER_DEFINITIONS = new ArrayList<>();
  public static final List<ParticleEntry<?>> PARTICLE_PROVIDERS = new ArrayList<>();
  public static final List<EntityRendererEntry<?>> ENTITY_RENDERERS = new ArrayList<>();
  public static final List<BlockEntityRendererEntry<?, ?>> BLOCK_ENTITY_RENDERERS = new ArrayList<>();
  public static final List<Supplier<WoodType>> SHEET_WOOD_TYPES = new ArrayList<>();

  static {
    registerBlockTints();
    registerSheetWoodTypes();
    registerParticles();
    registerEntityRenderers();
    registerLayerDefinitions();
  }

  private NSClient() {
  }

  public static ModelLayerLocation boatLayer(String wood) {
    return new ModelLayerLocation(NaturesSpirit.id("boat/" + wood), "main");
  }

  public static ModelLayerLocation chestBoatLayer(String wood) {
    return new ModelLayerLocation(NaturesSpirit.id("chest_boat/" + wood), "main");
  }

  private static void registerBlockTints() {
    tint(BlockTintSources.doubleTallGrass(), NSBlocks.CATTAIL);
    tint(BlockTintSources.foliage(), NSBlocks.SUGI.getLeaves(), NSBlocks.MAHOGANY.getLeaves(), NSBlocks.LARCH.getLeaves(), NSBlocks.ASPEN.getLeaves());
    tint(BlockTintSources.grass(), NSBlocks.LOTUS_STEM, NSBlocks.LARGE_LUSH_FERN, NSBlocks.LUSH_FERN, NSBlocks.POTTED_LUSH_FERN);
    tint(new BlockTintSource() {
      @Override public int color(BlockState state) {
        return GrassColor.getDefaultColor();
      }

      @Override public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        return BiomeColors.getAverageGrassColor(level, new BlockPos(pos.getX(), -64, pos.getZ()));
      }
    }, NSBlocks.LOTUS_FLOWER);
  }

  private static void registerSheetWoodTypes() {
    SHEET_WOOD_TYPES.add(NSBlocks.REDWOOD.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.SUGI.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.WISTERIA.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.FIR.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.WILLOW.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.ASPEN.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.MAPLE.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.CYPRESS.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.OLIVE.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.JOSHUA.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.GHAF.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.PALO_VERDE.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.COCONUT.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.CEDAR.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.LARCH.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.MAHOGANY.getWoodType());
    SHEET_WOOD_TYPES.add(NSBlocks.SAXAUL.getWoodType());
  }

  private static void registerParticles() {
    particle(NSParticleTypes.RED_MAPLE_LEAVES_PARTICLE, NSClient::mapleLeavesProvider);
    particle(NSParticleTypes.ORANGE_MAPLE_LEAVES_PARTICLE, NSClient::mapleLeavesProvider);
    particle(NSParticleTypes.YELLOW_MAPLE_LEAVES_PARTICLE, NSClient::mapleLeavesProvider);
    particle(NSParticleTypes.MILK_PARTICLE, SuspendedTownParticle.ComposterFillProvider::new);
  }

  private static void registerEntityRenderers() {
    blockEntityRenderer(NSBlocks.PIZZA_BLOCK_ENTITY_TYPE, PizzaBlockEntityRenderer::new);
    entityRenderer(NSEntityTypes.CHEESE_ARROW, CheeseArrowEntityRenderer::new);
    for (String wood : NSEntityTypes.BOAT_WOODS) {
      entityRenderer(NSEntityTypes.getBoat(wood), context -> new BoatRenderer(context, boatLayer(wood)));
      entityRenderer(NSEntityTypes.getChestBoat(wood), context -> new BoatRenderer(context, chestBoatLayer(wood)));
    }
  }

  private static void registerLayerDefinitions() {
    layer(PIZZA_TOPPING, PizzaToppingModel::getTexturedModelData);
    for (String wood : NSEntityTypes.BOAT_WOODS) {
      layer(boatLayer(wood), BoatModel::createBoatModel);
      layer(chestBoatLayer(wood), BoatModel::createChestBoatModel);
    }
  }

  private static ParticleProvider<SimpleParticleType> mapleLeavesProvider(SpriteSet spriteSet) {
    return (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> new MapleLeavesParticle(world, x, y, z, spriteSet.get(random));
  }

  @SafeVarargs
  private static void tint(BlockTintSource source, Supplier<? extends Block>... blocks) {
    BLOCK_TINTS.add(new TintEntry(List.of(source), List.of(blocks)));
  }

  private static void layer(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
    LAYER_DEFINITIONS.add(new LayerEntry(location, definition));
  }

  private static <T extends ParticleOptions> void particle(Supplier<? extends ParticleType<T>> type, Function<SpriteSet, ParticleProvider<T>> factory) {
    PARTICLE_PROVIDERS.add(new ParticleEntry<>(type, factory));
  }

  private static <T extends Entity> void entityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
    ENTITY_RENDERERS.add(new EntityRendererEntry<>(type, provider));
  }

  private static <T extends BlockEntity, S extends BlockEntityRenderState> void blockEntityRenderer(Supplier<? extends BlockEntityType<? extends T>> type,
          BlockEntityRendererProvider<T, S> provider) {
    BLOCK_ENTITY_RENDERERS.add(new BlockEntityRendererEntry<>(type, provider));
  }

  public record TintEntry(List<BlockTintSource> layers, List<Supplier<? extends Block>> blocks) {
  }

  public record LayerEntry(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
  }

  public record ParticleEntry<T extends ParticleOptions>(Supplier<? extends ParticleType<T>> type, Function<SpriteSet, ParticleProvider<T>> factory) {
  }

  public record EntityRendererEntry<T extends Entity>(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
  }

  public record BlockEntityRendererEntry<T extends BlockEntity, S extends BlockEntityRenderState>(Supplier<? extends BlockEntityType<? extends T>> type,
          BlockEntityRendererProvider<T, S> provider) {
  }
}
