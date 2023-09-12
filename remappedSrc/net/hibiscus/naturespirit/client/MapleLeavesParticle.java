//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

@Environment(EnvType.CLIENT) public class MapleLeavesParticle extends TextureSheetParticle {
   private static final float field_43372 = 0.0025F;
   private static final int field_43373 = 300;
   private static final int field_43366 = 300;
   private static final float field_43367 = 0.25F;
   private static final float field_43368 = 2.0F;
   private float field_43369;
   private final float field_43370;
   private final float field_43371;

   protected MapleLeavesParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
      super(world, x, y, z);
      this.setSprite(spriteProvider.get(this.random.nextInt(12), 12));
      this.field_43369 = (float) Math.toRadians(this.random.nextBoolean() ? -30.0D : 30.0D);
      this.field_43370 = this.random.nextFloat();
      this.field_43371 = (float) Math.toRadians(this.random.nextBoolean() ? -5.0D : 5.0D);
      this.lifetime = 300;
      this.gravity = 7.5E-4F;
      float f = this.random.nextBoolean() ? 0.133333F : 0.19999999995F;
      this.quadSize = f;
      this.setSize(f, f);
      this.friction = 0.98F;
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      this.xo = this.x;
      this.yo = this.y;
      this.zo = this.z;
      if(this.lifetime-- <= 0) {
         this.remove();
      }

      if(!this.removed) {
         float f = (float) (300 - this.lifetime);
         float g = Math.min(f / 300.0F, 1.0F);
         double d = Math.cos(Math.toRadians(this.field_43370 * 60.0F)) * 1.85D * Math.pow(g, 1.25D);
         double e = Math.sin(Math.toRadians(this.field_43370 * 60.0F)) * 1.85D * Math.pow(g, 1.25D);
         this.xd += d * 0.0024999999441206455D;
         this.zd += e * 0.0024999999441206455D;
         this.yd -= this.gravity;
         this.field_43369 += this.field_43371 / 20.0F;
         this.oRoll = this.roll;
         this.roll += this.field_43369 / 20.0F;
         this.move(this.xd, this.yd, this.zd);
         if(this.onGround || this.lifetime < 299 && (this.xd == 0.0D || this.zd == 0.0D)) {
            this.remove();
         }

         if(!this.removed) {
            this.xd *= this.friction;
            this.yd *= this.friction;
            this.zd *= this.friction;
         }
      }
   }
}
