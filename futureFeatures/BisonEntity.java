//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.entity;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.hibiscus.naturespirit.client.render.BisonEntityRenderer;
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CamelEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class BisonEntity extends AnimalEntity {
   public static final EntityDimensions LONG_JUMPING_DIMENSIONS = EntityDimensions.changing(0.9F, 1.3F).scaled(0.7F);
   private static final int DEFAULT_ATTACK_DAMAGE = 2;
   private static final int BABY_ATTACK_DAMAGE = 1;
   protected static final ImmutableList <SensorType <? extends Sensor <? super BisonEntity>>> SENSORS;
   protected static final ImmutableList <MemoryModuleType <?>> MEMORY_MODULES;
   public static final int FALL_DAMAGE_SUBTRACTOR = 10;
   public static final double SCREAMING_CHANCE = 0.02;
   public static final double field_39046 = 0.10000000149011612;
   private boolean preparingRam;
   private int headPitch;


   public BisonEntity(EntityType <? extends BisonEntity> entityType, World world) {
      super(entityType, world); this.getNavigation().setCanSwim(true); this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1.0F); this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1.0F);
   }


   protected Brain.Profile <BisonEntity> createBrainProfile() {
      return Brain.createProfile(MEMORY_MODULES, SENSORS);
   }

   protected Brain <?> deserializeBrain(Dynamic <?> dynamic) {
      return BisonBrain.create(this.createBrainProfile().deserialize(dynamic));
   }

   public static DefaultAttributeContainer.Builder createBisonAttributes() {
      return MobEntity
              .createMobAttributes()
              .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
              .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224)
              .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
   }

   protected void onGrowUp() {
      if(this.isBaby()) {
         this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(1.0);
      }
      else {
         this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(2.0);
      }

   }

   protected int computeFallDamage(float fallDistance, float damageMultiplier) {
      return super.computeFallDamage(fallDistance, damageMultiplier) - 10;
   }

   protected SoundEvent getAmbientSound() {
      return SoundEvents.ENTITY_GOAT_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_GOAT_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_GOAT_DEATH;
   }

   protected void playStepSound(BlockPos pos, BlockState state) {
      this.playSound(SoundEvents.ENTITY_GOAT_STEP, 0.15F, 1.0F);
   }

   protected SoundEvent getMilkingSound() {
      return SoundEvents.ENTITY_GOAT_MILK;
   }

   @Nullable public BisonEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
      return HibiscusEntityTypes.BISON.create(serverWorld);
   }

   public Brain <BisonEntity> getBrain() {
      return (Brain <BisonEntity>) super.getBrain();
   }

   protected void mobTick() {
      this.getWorld().getProfiler().push("bisonBrain"); this.getBrain().tick((ServerWorld) this.getWorld(), this); this.getWorld().getProfiler().pop(); this.getWorld().getProfiler().push(
              "bisonActivityUpdate"); BisonBrain.updateActivities(this); this.getWorld().getProfiler().pop(); super.mobTick();
   }


   public SoundEvent getEatSound(ItemStack stack) {
      return SoundEvents.ENTITY_GOAT_EAT;
   }

   public ActionResult interactMob(PlayerEntity player, Hand hand) {
      ItemStack itemStack = player.getStackInHand(hand); if(itemStack.isOf(Items.BUCKET) && !this.isBaby()) {
         player.playSound(this.getMilkingSound(), 1.0F, 1.0F); ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, Items.MILK_BUCKET.getDefaultStack()); player.setStackInHand(
                 hand,
                 itemStack2
         ); return ActionResult.success(this.getWorld().isClient);
      }
      else {
         ActionResult actionResult = super.interactMob(player, hand); if(actionResult.isAccepted() && this.isBreedingItem(itemStack)) {
            this.getWorld().playSoundFromEntity((PlayerEntity) null, this, this.getEatSound(itemStack), SoundCategory.NEUTRAL, 1.0F, MathHelper.nextBetween(this.getWorld().random, 0.8F, 1.2F));
         }

         return actionResult;
      }
   }

   public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
      this.onGrowUp();
      return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
   }

   protected void sendAiDebugData() {
      super.sendAiDebugData(); DebugInfoSender.sendBrainDebugData(this);
   }

   public EntityDimensions getDimensions(EntityPose pose) {
      return super.getDimensions(pose);
   }

   public void readCustomDataFromNbt(NbtCompound nbt) {
      super.readCustomDataFromNbt(nbt);
   }



   public void handleStatus(byte status) {
      if(status == 58) {
         this.preparingRam = true;
      }
      else if(status == 59) {
         this.preparingRam = false;
      }
      else {
         super.handleStatus(status);
      }

   }

   protected void initDataTracker() {
      super.initDataTracker();
   }


   public static boolean canSpawn(EntityType <? extends AnimalEntity> entityType, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
      return world.getBlockState(pos.down()).isIn(BlockTags.GOATS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
   }

   static {
      SENSORS = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.NEAREST_ADULT, SensorType.HURT_BY, HibiscusSensorTypes.BISON_SEPCIFIC_SENSOR, SensorType.GOAT_TEMPTATIONS);
      MEMORY_MODULES = ImmutableList.of(
              MemoryModuleType.LOOK_TARGET,
              MemoryModuleType.VISIBLE_MOBS,
              MemoryModuleType.WALK_TARGET,
              MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
              MemoryModuleType.PATH,
              MemoryModuleType.ATE_RECENTLY,
              MemoryModuleType.BREED_TARGET,
              MemoryModuleType.TEMPTING_PLAYER,
              MemoryModuleType.NEAREST_VISIBLE_ADULT,
              MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.RAM_COOLDOWN_TICKS,
              HibiscusMemoryModuleTypes.NEARBY_ADULT_BISONS, MemoryModuleType.RAM_TARGET, MemoryModuleType.IS_PANICKING
      );
   }
}
