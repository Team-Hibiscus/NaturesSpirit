package net.hibiscus.naturespirit.client.entity.animations;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class BisonAnimations {

   public static final Animation BISON_WALK = Animation.Builder.create(1.92f).looping().addBoneAnimation("base", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 2.5f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.96f, AnimationHelper.createRotationalVector(0f, 0f, -2.5f), Transformation.Interpolations.CUBIC),
           new Keyframe(1.92f, AnimationHelper.createRotationalVector(0f, 0f, 2.5f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("frontLeft", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(20f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.96f, AnimationHelper.createRotationalVector(-20f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(1.92f, AnimationHelper.createRotationalVector(20f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("frontRight", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(-20f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.96f, AnimationHelper.createRotationalVector(20f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(1.92f, AnimationHelper.createRotationalVector(-20f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("backLeft", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.96f, AnimationHelper.createRotationalVector(20f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(1.92f, AnimationHelper.createRotationalVector(-20f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("backRight", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.48f, AnimationHelper.createRotationalVector(-20f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(1.92f, AnimationHelper.createRotationalVector(20f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).build();

   public static final Animation BISON_RUN = Animation.Builder.create(0.48f).looping().addBoneAnimation("base", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 3.5f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.24f, AnimationHelper.createRotationalVector(0f, 0f, -3.5f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.48f, AnimationHelper.createRotationalVector(0f, 0f, 3.5f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("frontLeft", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.24f, AnimationHelper.createRotationalVector(-40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.48f, AnimationHelper.createRotationalVector(40f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("frontRight", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(-40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.24f, AnimationHelper.createRotationalVector(40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.48f, AnimationHelper.createRotationalVector(-40f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("backLeft", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(-40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.24f, AnimationHelper.createRotationalVector(40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.48f, AnimationHelper.createRotationalVector(-40f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).addBoneAnimation("backRight", new Transformation(Transformation.Targets.ROTATE,
           new Keyframe(0f, AnimationHelper.createRotationalVector(40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.24f, AnimationHelper.createRotationalVector(-40f, 0f, 0f), Transformation.Interpolations.CUBIC),
           new Keyframe(0.48f, AnimationHelper.createRotationalVector(40f, 0f, 0f), Transformation.Interpolations.CUBIC)
   )).build();
   public static final Animation BISON_IDLE = Animation.Builder.create(2.88f).looping().addBoneAnimation("body", new Transformation(Transformation.Targets.SCALE,
           new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC),
           new Keyframe(1.2f, AnimationHelper.createScalingVector(1.01f, 1.01f, 1.02f), Transformation.Interpolations.CUBIC),
           new Keyframe(2.88f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.CUBIC)
   )).build();
   public static final Animation BISONMODEL_NEW = Animation.Builder.create(0f).build();
}
