package net.hibiscus.naturespirit.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.hibiscus.naturespirit.client.entity.BisonModel;
import net.minecraft.client.model.TexturedModelData;

public class BisonTexturedModelDataProvider implements EntityModelLayerRegistry.TexturedModelDataProvider {


   @Override public TexturedModelData createModelData() {
      return BisonModel.getTexturedModelData();
   }
}
