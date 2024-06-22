package net.hibiscus.naturespirit.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Map;

public final class HibiscusBoatEntityRenderer extends BoatEntityRenderer {
   public HibiscusBoatEntityRenderer(EntityRendererFactory.Context context, boolean chest, HibiscusBoatEntity.HibiscusBoat boatData) {
      super(context, chest);
      var id = boatData.id();
      var texture = Identifier.of(id.getNamespace(), "textures/entity/" + (chest ? "chest_boat/" : "boat/") + id.getPath() + ".png");
      var rootPart = context.getPart(getModelLayer(boatData, chest));
      var model = chest ? new ChestBoatEntityModel(rootPart) : new BoatEntityModel(rootPart);
      texturesAndModels = texturesAndModels.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> Pair.of(texture, model)));
   }

   public static EntityModelLayer getModelLayer(HibiscusBoatEntity.HibiscusBoat boat, boolean chest) {
      var id = boat.id();
      return new EntityModelLayer(Identifier.of(id.getNamespace(), (chest ? "chest_boat/" : "boat/") + id.getPath()), "main");
   }
}