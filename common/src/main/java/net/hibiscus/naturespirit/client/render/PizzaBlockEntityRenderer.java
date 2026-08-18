package net.hibiscus.naturespirit.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.client.NSClient;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class PizzaBlockEntityRenderer implements BlockEntityRenderer<PizzaBlockEntity, PizzaBlockEntityRenderer.PizzaRenderState> {

  private final PizzaToppingModel pizzaToppingModel;
  private final SpriteGetter sprites;

  public PizzaBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    this.pizzaToppingModel = new PizzaToppingModel(ctx.bakeLayer(NSClient.PIZZA_TOPPING));
    this.sprites = ctx.sprites();
  }

  @Override
  public PizzaRenderState createRenderState() {
    return new PizzaRenderState();
  }

  @Override
  public void extractRenderState(PizzaBlockEntity entity, PizzaRenderState state, float partialTicks, Vec3 cameraPosition,
          ModelFeatureRenderer.CrumblingOverlay breakProgress) {
    BlockEntityRenderer.super.extractRenderState(entity, state, partialTicks, cameraPosition, breakProgress);
    BlockState cachedState = entity.getBlockState();
    state.isPizza = cachedState.getBlock() instanceof PizzaBlock;
    state.bites = state.isPizza ? cachedState.getValue(PizzaBlock.BITES) : 0;
    state.toppings.clear();
    state.toppings.addAll(entity.toppings);
  }

  @Override
  public void submit(PizzaRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
    if (!state.isPizza) {
      return;
    }
    ModelPart model;
    switch (state.bites) {
      case 0 -> model = pizzaToppingModel.slice0;
      case 1 -> model = pizzaToppingModel.slice1;
      case 2 -> model = pizzaToppingModel.slice2;
      default -> model = pizzaToppingModel.slice3;
    }
    for (PizzaToppingVariant pizzaToppingVariant : state.toppings) {
      SpriteId spriteId = new SpriteId(TextureAtlas.LOCATION_BLOCKS, pizzaToppingVariant.texturePath());
      submitNodeCollector.submitModelPart(model, poseStack, spriteId.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY,
              this.sprites.get(spriteId), -1, state.breakProgress);
    }
  }

  public static class PizzaRenderState extends BlockEntityRenderState {
    public boolean isPizza;
    public int bites;
    public final List<PizzaToppingVariant> toppings = new ArrayList<>();
  }
}
