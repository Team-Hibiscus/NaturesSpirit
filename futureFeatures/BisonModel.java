package net.hibiscus.naturespirit.client.entity;

import net.hibiscus.naturespirit.client.entity.animations.BisonAnimations;
import net.hibiscus.naturespirit.entity.BisonEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.CamelAnimations;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BisonModel extends SinglePartEntityModel <BisonEntity> {
	private final ModelPart base;
	private final ModelPart body;
	private final ModelPart legs;
	private final ModelPart frontLeft;
	private final ModelPart frontRight;
	private final ModelPart backLeft;
	private final ModelPart backRight;
	private final ModelPart head;
	private final ModelPart cube_r1;
	public BisonModel(ModelPart root) {
		this.base = root.getChild("base");
		this.body = base.getChild("body");
		this.legs = base.getChild("legs");
		this.frontLeft = legs.getChild("frontLeft");
		this.frontRight = legs.getChild("frontRight");
		this.backLeft = legs.getChild("backLeft");
		this.backRight = legs.getChild("backRight");
		this.head = base.getChild("head");
		this.cube_r1 = head.getChild("cube_r1");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 13.0F, -0.025F));

		ModelPartData body = base.addChild("body", ModelPartBuilder.create().uv(68, 70).cuboid(-8.0F, -21.0F, 4.0F, 16.0F, 12.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 63).mirrored().cuboid(-8.0F, -24.0F, -10.0F, 16.0F, 15.0F, 14.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 10.0F, 0.0F));

		ModelPartData legs = base.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData frontLeft = legs.addChild("frontLeft", ModelPartBuilder.create().uv(40, 0).mirrored().cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.1F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 1.0F, -4.0F));

		ModelPartData frontRight = legs.addChild("frontRight", ModelPartBuilder.create().uv(40, 0).mirrored().cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.1F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 1.0F, -4.0F));

		ModelPartData backLeft = legs.addChild("backLeft", ModelPartBuilder.create().uv(40, 0).mirrored().cuboid(-3.0F, -2.0F, -5.0F, 4.0F, 12.0F, 4.1F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 1.0F, 14.0F));

		ModelPartData backRight = legs.addChild("backRight", ModelPartBuilder.create().uv(40, 0).mirrored().cuboid(-1.0F, -2.0F, -5.0F, 4.0F, 12.0F, 4.1F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 1.0F, 14.0F));

		ModelPartData head = base.addChild("head", ModelPartBuilder.create().uv(18, 46).cuboid(-3.0F, -7.0F, -16.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.0F, -22.0F, -15.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).mirrored().cuboid(4.0F, -22.0F, -15.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 9.0F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(36, 34).cuboid(-3.0F, -16.0F, 6.0F, 6.0F, 6.0F, 12.0F, new Dilation(0.0F))
		.uv(46, 20).cuboid(-4.0F, -18.0F, 13.0F, 8.0F, 9.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(BisonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.backRight.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.backLeft.pitch = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
		this.frontRight.pitch = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
		this.frontLeft.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	public ModelPart getPart() {
		return this.base;
	}
}