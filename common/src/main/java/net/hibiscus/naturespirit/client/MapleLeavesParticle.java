package net.hibiscus.naturespirit.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class MapleLeavesParticle extends FallingLeavesParticle {

  public MapleLeavesParticle(ClientLevel world, double x, double y, double z, TextureAtlasSprite sprite) {
    super(world, x, y, z, sprite, 0.25F, 2.0F, false, true, 1.0F, 0.0F);
    float f = this.random.nextBoolean() ? 0.133333F : 0.19999999995F;
    this.quadSize = f;
    this.setSize(f, f);
    this.friction = 0.78F;
  }
}
