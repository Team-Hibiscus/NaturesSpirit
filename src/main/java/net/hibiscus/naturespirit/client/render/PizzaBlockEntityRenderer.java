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
         switch (shape) {
            case 0 -> model = this.topping_0;
            case 1 -> model = this.topping_1;
            case 2 -> model = this.topping_2;
            default -> model = this.topping_3;
         }
         if (entity.TOPPINGS != null) {
            for(Identifier identifier : entity.TOPPINGS) {
               String string = identifier.toString();
               VertexConsumer vertexConsumer = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(MOD_ID, "block/pizza/" + string.replace(":", "_") + "_topping")).getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
               model.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      }
   }
}
