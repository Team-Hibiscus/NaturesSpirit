package net.hibiscus.naturespirit.client.render;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.client.NatureSpiritClient;
import net.hibiscus.naturespirit.client.entity.BisonModel;
import net.hibiscus.naturespirit.entity.BisonEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BisonEntityRenderer extends MobEntityRenderer<BisonEntity, BisonModel> {
   public static final Identifier TEXTURE = new Identifier(NatureSpirit.MOD_ID, "textures/entity/bison/bison.png");

   public BisonEntityRenderer(EntityRendererFactory.Context ctx) {
      super(ctx, new BisonModel(ctx.getPart(NatureSpiritClient.BISON_MODEL_LAYER)), 0.3F);
   }

   @Override public Identifier getTexture(BisonEntity entity) {
      return TEXTURE;
   }
}
