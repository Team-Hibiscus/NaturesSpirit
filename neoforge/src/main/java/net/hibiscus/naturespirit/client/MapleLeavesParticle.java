//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.particle.SpriteSet;

public class MapleLeavesParticle extends CherryParticle {

  public MapleLeavesParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
    super(world, x, y, z, spriteProvider);
    float f = this.random.nextBoolean() ? 0.133333F : 0.19999999995F;
    this.quadSize = f;
    this.setSize(f, f);
    this.friction = 0.78F;
  }
}
