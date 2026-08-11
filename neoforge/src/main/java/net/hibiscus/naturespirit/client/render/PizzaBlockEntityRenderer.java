package net.hibiscus.naturespirit.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.client.NSClient;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class PizzaBlockEntityRenderer implements BlockEntityRenderer<PizzaBlockEntity> {

  private final PizzaToppingModel pizzaToppingModel;

  public PizzaBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    this.pizzaToppingModel = new PizzaToppingModel(ctx.bakeLayer(NSClient.PIZZA_TOPPING));
  }

  @Override
  public void render(PizzaBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
    BlockState cachedState = entity.getBlockState();
    if (cachedState.getBlock() instanceof PizzaBlock) {
      int shape = cachedState.getValue(PizzaBlock.BITES);
      ModelPart model;
      switch (shape) {
        case 0 -> model = pizzaToppingModel.slice0;
        case 1 -> model = pizzaToppingModel.slice1;
        case 2 -> model = pizzaToppingModel.slice2;
        default -> model = pizzaToppingModel.slice3;
      }
      for (PizzaToppingVariant pizzaToppingVariant : entity.toppings) {
        VertexConsumer vertexConsumer = new Material(TextureAtlas.LOCATION_BLOCKS, pizzaToppingVariant.texturePath()).buffer(vertexConsumers,
                RenderType::entityCutout);
        model.render(matrices, vertexConsumer, light, overlay, -1);
      }
    }
  }
}
