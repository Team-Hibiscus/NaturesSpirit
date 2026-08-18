package net.hibiscus.naturespirit.client.render;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class CheeseArrowEntityRenderer extends ArrowRenderer<CheeseArrowEntity, ArrowRenderState> {

  public static final Identifier TEXTURE = NaturesSpirit.id("textures/entity/projectiles/cheese_arrow.png");

  public CheeseArrowEntityRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ArrowRenderState createRenderState() {
    return new ArrowRenderState();
  }

  @Override
  protected Identifier getTextureLocation(ArrowRenderState state) {
    return TEXTURE;
  }
}
