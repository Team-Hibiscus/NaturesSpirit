package net.hibiscus.naturespirit.client.render;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class PizzaToppingModel {

  public final ModelPart slice0;
  public final ModelPart slice1;
  public final ModelPart slice2;
  public final ModelPart slice3;

  public PizzaToppingModel(ModelPart root) {
    this.slice0 = root.getChild("slice0");
    this.slice1 = root.getChild("slice1");
    this.slice2 = root.getChild("slice2");
    this.slice3 = root.getChild("slice3");
  }

  public static LayerDefinition getTexturedModelData() {
    MeshDefinition modelData = new MeshDefinition();
    PartDefinition modelPartData = modelData.getRoot();
    modelPartData.addOrReplaceChild("slice0", CubeListBuilder.create().texOffs(-7, 11).addBox(3.0F, 2.04F, 3.0F, 10.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 0.0F, 0.0F));
    modelPartData.addOrReplaceChild("slice1", CubeListBuilder.create().texOffs(-2, 11).addBox(3.0F, 2.04F, 3.0F, 5.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(8, 16)
            .addBox(8.0F, 2.04F, 3.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    modelPartData.addOrReplaceChild("slice2", CubeListBuilder.create().texOffs(-2, 11).addBox(3.0F, 2.04F, 3.0F, 5.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    modelPartData.addOrReplaceChild("slice3", CubeListBuilder.create().texOffs(3, 11).addBox(3.0F, 2.04F, 8.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    return LayerDefinition.create(modelData, 32, 32);
  }
}
