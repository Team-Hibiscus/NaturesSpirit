package net.hibiscus.naturespirit.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping0;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping1;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping2;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping3;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

@Environment(EnvType.CLIENT)
public class PizzaBlockEntityRenderer implements BlockEntityRenderer<PizzaBlockEntity> {

   public static final SpriteIdentifier GREEN_OLIVES_TOPPING_TEXTURE;
   public static final SpriteIdentifier BLACK_OLIVES_TOPPING_TEXTURE;
   public static final SpriteIdentifier MUSHROOM_TOPPING_TEXTURE;
   public static final SpriteIdentifier CARROT_TOPPING_TEXTURE;
   public static final SpriteIdentifier BEETROOT_TOPPING_TEXTURE;
   public static final SpriteIdentifier CHICKEN_TOPPING_TEXTURE;
   public static final SpriteIdentifier COD_TOPPING_TEXTURE;
   public static final SpriteIdentifier PORK_TOPPING_TEXTURE;
   public static final SpriteIdentifier RABBIT_TOPPING_TEXTURE;
   private final Topping0 topping_0;
   private final Topping1 topping_1;
   private final Topping2 topping_2;
   private final Topping3 topping_3;
   public PizzaBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
      this.topping_0 = new Topping0(ctx.getLayerModelPart(EntityModelLayers.TOPPING_0));
      this.topping_1 = new Topping1(ctx.getLayerModelPart(EntityModelLayers.TOPPING_1));
      this.topping_2 = new Topping2(ctx.getLayerModelPart(EntityModelLayers.TOPPING_2));
      this.topping_3 = new Topping3(ctx.getLayerModelPart(EntityModelLayers.TOPPING_3));
   }

   @Override public void render(PizzaBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
      if (entity.getCachedState().getBlock() instanceof PizzaBlock) {
         int shape = entity.getCachedState().get(PizzaBlock.BITES);
         Model model;
         VertexConsumer[] vertexConsumer = {null, null, null, null, null, null, null, null, null};
         switch (shape) {
            case 0 -> model = this.topping_0;
            case 1 -> model = this.topping_1;
            case 2 -> model = this.topping_2;
            default -> model = this.topping_3;
         }
         if (entity.MUSHROOM_BOOLEAN) {
             vertexConsumer[0] = MUSHROOM_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.GREEN_OLIVES_BOOLEAN) {
            vertexConsumer[1] = GREEN_OLIVES_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.BLACK_OLIVES_BOOLEAN) {
            vertexConsumer[2] = BLACK_OLIVES_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.CARROT_BOOLEAN) {
            vertexConsumer[3] = CARROT_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.BEETROOT_BOOLEAN) {
            vertexConsumer[4] = BEETROOT_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.CHICKEN_BOOLEAN) {
            vertexConsumer[5] = CHICKEN_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.COD_BOOLEAN) {
            vertexConsumer[6] = COD_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.PORK_BOOLEAN) {
            vertexConsumer[7] = PORK_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         if (entity.RABBIT_BOOLEAN) {
            vertexConsumer[8] = RABBIT_TOPPING_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
         }
         for(VertexConsumer vertexConsumer2: vertexConsumer) {
            if (vertexConsumer2 != null) {
               model.render(matrices, vertexConsumer2, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      }
   }
   static {
      GREEN_OLIVES_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/green_olives_topping"));
      BLACK_OLIVES_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/black_olives_topping"));
      MUSHROOM_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/mushroom_topping"));
      CARROT_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/carrot_topping"));
      BEETROOT_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/beetroot_topping"));
      CHICKEN_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/chicken_topping"));
      COD_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/cod_topping"));
      PORK_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/pork_topping"));
      RABBIT_TOPPING_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/rabbit_topping"));
   }
}
