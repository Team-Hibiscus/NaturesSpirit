//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class CalciteBubbleParticle extends TextureSheetParticle {
   private final SpriteSet spriteProvider;

   CalciteBubbleParticle(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteProvider) {
      super(world, x, y, z, 0.0, 0.0, 0.0);
      this.friction = 0.96F;
      this.spriteProvider = spriteProvider;
      float f = 2.5F;
      this.xd *= 0.02000000149011612;
      this.yd *= 0.02000000149011612;
      this.zd *= 0.02000000149011612;
      this.xd += velocityX;
      this.yd += velocityY;
      this.zd += velocityZ;
      float g = 1.0F - (float)(Math.random() * 0.30000001192092896);
      this.rCol = g;
      this.gCol = g;
      this.bCol = g;
      this.quadSize *= .9525F;
      int i = (int)(8.0 / (Math.random() * 0.8 + 0.3));
      this.lifetime = (int)Math.max((float)i * 2.5F, 1.0F);
      this.hasPhysics = false;
      this.setSpriteFromAge(spriteProvider);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
   }

   public float getQuadSize(float tickDelta) {
      return this.quadSize * Mth.clamp(((float)this.age + tickDelta) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
   }

   public void tick() {
      super.tick();
      if (!this.removed) {
         this.setSpriteFromAge(this.spriteProvider);
         Player playerEntity = this.level.getNearestPlayer(this.x, this.y, this.z, 2.0, false);
         if (playerEntity != null) {
            double d = playerEntity.getY();
            if (this.y > d) {
               this.y += (d - this.y) * 0.005;
               this.yd += (playerEntity.getDeltaMovement().y - this.yd) * 0.005;
               this.setPos(this.x, this.y, this.z);
            }
         }
      }

   }


   @Environment(EnvType.CLIENT)
   public static class BubbleFactory implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteProvider;

      public BubbleFactory(SpriteSet spriteProvider) {
         this.spriteProvider = spriteProvider;
      }

      public Particle createParticle(SimpleParticleType defaultParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
         return new CalciteBubbleParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
      }
   }
}
