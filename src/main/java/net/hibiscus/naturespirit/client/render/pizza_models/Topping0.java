package net.hibiscus.naturespirit.client.render.pizza_models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class Topping0 extends Model {
	private final ModelPart bb_main;

   public Topping0(ModelPart root) {
      super(RenderLayer::getEntityCutout);
      this.bb_main = root.getChild("bb_main");
   }

   public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(-7, 11).cuboid(3.0F, 2.04F, 3.0F, 10.0F, 0.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int integer) {
		bb_main.render(matrices, vertexConsumer, light, overlay, integer);
	}
}