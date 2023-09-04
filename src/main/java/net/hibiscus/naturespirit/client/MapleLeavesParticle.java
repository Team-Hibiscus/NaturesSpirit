//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT) public class MapleLeavesParticle extends SpriteBillboardParticle {
   private static final float field_43372 = 0.0025F;
   private static final int field_43373 = 300;
   private static final int field_43366 = 300;
   private static final float field_43367 = 0.25F;
   private static final float field_43368 = 2.0F;
   private float field_43369;
   private final float field_43370;
   private final float field_43371;

   protected MapleLeavesParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
      super(world, x, y, z);
      this.setSprite(spriteProvider.getSprite(this.random.nextInt(12), 12));
      this.field_43369 = (float) Math.toRadians(this.random.nextBoolean() ? -30.0D : 30.0D);
      this.field_43370 = this.random.nextFloat();
      this.field_43371 = (float) Math.toRadians(this.random.nextBoolean() ? -5.0D : 5.0D);
      this.maxAge = 300;
      this.gravityStrength = 7.5E-4F;
      float f = this.random.nextBoolean() ? 0.133333F : 0.19999999995F;
      this.scale = f;
      this.setBoundingBoxSpacing(f, f);
      this.velocityMultiplier = 0.98F;
   }

   public ParticleTextureSheet getType() {
      return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      this.prevPosX = this.x;
      this.prevPosY = this.y;
      this.prevPosZ = this.z;
      if(this.maxAge-- <= 0) {
         this.markDead();
      }

      if(!this.dead) {
         float f = (float) (300 - this.maxAge);
         float g = Math.min(f / 300.0F, 1.0F);
         double d = Math.cos(Math.toRadians(this.field_43370 * 60.0F)) * 1.85D * Math.pow(g, 1.25D);
         double e = Math.sin(Math.toRadians(this.field_43370 * 60.0F)) * 1.85D * Math.pow(g, 1.25D);
         this.velocityX += d * 0.0024999999441206455D;
         this.velocityZ += e * 0.0024999999441206455D;
         this.velocityY -= this.gravityStrength;
         this.field_43369 += this.field_43371 / 20.0F;
         this.prevAngle = this.angle;
         this.angle += this.field_43369 / 20.0F;
         this.move(this.velocityX, this.velocityY, this.velocityZ);
         if(this.onGround || this.maxAge < 299 && (this.velocityX == 0.0D || this.velocityZ == 0.0D)) {
            this.markDead();
         }

         if(!this.dead) {
            this.velocityX *= this.velocityMultiplier;
            this.velocityY *= this.velocityMultiplier;
            this.velocityZ *= this.velocityMultiplier;
         }
      }
   }
}
