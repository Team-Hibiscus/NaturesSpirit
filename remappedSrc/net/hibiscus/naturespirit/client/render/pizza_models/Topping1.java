package net.hibiscus.naturespirit.client.render.pizza_models;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
public class Topping1 extends Model {
	private final ModelPart bb_main;
	public Topping1(ModelPart root) {
     super(RenderLayer::getEntityCutout);
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(-2, 11).cuboid(3.0F, 2.04F, 3.0F, 5.0F, 0.0F, 10.0F, new Dilation(0.0F))
		.uv(8, 16).cuboid(8.0F, 2.04F, 3.0F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}