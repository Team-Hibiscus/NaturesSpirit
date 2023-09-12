//// Made with Blockbench 4.8.3
//// Exported for Minecraft version 1.17+ for Yarn
//// Paste this class into your mod and generate all required imports
//public class turkey extends EntityModel<BisonEntity> {
//	private final ModelPart body;
//	private final ModelPart cube_r1;
//	private final ModelPart head;
//	private final ModelPart wingRight;
//	private final ModelPart wingLeft;
//	private final ModelPart legRight;
//	private final ModelPart legLeft;
//	public turkey(ModelPart root) {
//		this.body = root.getChild("body");
//	}
//	public static TexturedModelData getTexturedModelData() {
//		ModelData modelData = new ModelData();
//		ModelPartData modelPartData = modelData.getRoot();
//		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -13.0F, -8.0F, 14.0F, 10.0F, 15.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
//
//		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 25).cuboid(-9.0F, -25.6F, -1.7F, 18.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
//
//		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 37).cuboid(-2.0F, -17.0F, -10.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
//		.uv(0, 45).cuboid(0.0F, -13.0F, -11.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
//		.uv(0, 8).cuboid(-2.0F, -15.0F, -12.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData wingRight = body.addChild("wingRight", ModelPartBuilder.create().uv(25, 26).cuboid(-9.0F, -13.0F, -6.0F, 2.0F, 7.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData wingLeft = body.addChild("wingLeft", ModelPartBuilder.create().uv(25, 26).mirrored().cuboid(-9.0F, -13.0F, -6.0F, 2.0F, 7.0F, 11.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(16.0F, 0.0F, 0.0F));
//
//		ModelPartData legRight = body.addChild("legRight", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-5.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData legLeft = body.addChild("legLeft", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 0.0F, 0.0F));
//		return TexturedModelData.of(modelData, 64, 64);
//	}
//	@Override
//	public void setAngles(BisonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//	}
//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
//		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
//	}
//}