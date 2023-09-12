package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VinegarItem extends PotionItem {

   public VinegarItem(Properties settings) {
      super(settings);
   }

   public ItemStack getDefaultInstance() {
      return new ItemStack(this);
   }


   public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
      return this.isEdible() ? user.eat(world, stack) : stack;
   }

   public InteractionResult useOn(UseOnContext context) {
      BlockPos pos = context.getClickedPos();
      Level world = context.getLevel();
      BlockState blockState = world.getBlockState(pos);
      Player player = context.getPlayer();
      InteractionHand hand = context.getHand();
      if (player != null && blockState.is(Blocks.CALCITE) && player.getItemInHand(hand).is(HibiscusBlocksAndItems.VINEGAR_BOTTLE)) {

         Vec3 vec3d = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(world.random, 0.2F);
         ItemStack itemStack = new ItemStack(HibiscusBlocksAndItems.CHALK_POWDER);
         itemStack.setCount(world.getRandom().nextIntBetweenInclusive(1, 5));
         ItemEntity itemEntity = new ItemEntity(world, vec3d.x(), vec3d.y(), vec3d.z(), itemStack);
         itemEntity.setDefaultPickUpDelay();
         world.addFreshEntity(itemEntity);
         world.playSound(null, pos, SoundEvents.CALCITE_PLACE, SoundSource.BLOCKS, 1.0F, 1.8F);
         double d = blockState.getShape(world, pos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
         RandomSource random = world.getRandom();

         for(int i = 0; i < 10; ++i) {
            double g = random.nextGaussian() * 0.01D;
            double h = random.nextGaussian() * 0.02D;
            double j = random.nextGaussian() * 0.01D;
            float xOffset = context.getClickedFace() == Direction.WEST ? -.75F : (context.getClickedFace() == Direction.EAST ? .75F : 0F);
            float zOffset = context.getClickedFace() == Direction.NORTH ? -.75F : (context.getClickedFace() == Direction.SOUTH ? .75F : 0F);
            float yOffset = context.getClickedFace() == Direction.UP ? 0F : (context.getClickedFace() == Direction.DOWN ? -1F : -.5F);
            world.addParticle(NatureSpirit.CALCITE_BUBBLE_PARTICLE,
                    (double)pos.getX() + xOffset + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(),
                    (double)pos.getY() + yOffset + d + (double)random.nextFloat() * (1.0D - d),
                    (double)pos.getZ() + zOffset + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(),
                    g, h, j);
         }
         if (!player.isCreative() && !player.isSpectator())
         {
            ItemStack itemStack2 = player.getItemInHand(hand).getRecipeRemainder();
            player.getItemInHand(hand).shrink(1);
            if (player.getItemInHand(hand).isEmpty()) {
               player.setItemInHand(hand, itemStack2);
            } else {
               if(player.getInventory().add(itemStack2)) {
                  player.drop(itemStack2, false);
               }
            }
         }
         return InteractionResult.SUCCESS;
      }
      return InteractionResult.PASS;
   }

   public int getUseDuration(ItemStack stack) {
      if (stack.getItem().isEdible()) {
         return this.getFoodProperties().isFastFood() ? 16 : 32;
      } else {
         return 0;
      }
   }

   public UseAnim getUseAnimation(ItemStack stack) {
      return stack.getItem().isEdible() ? UseAnim.EAT : UseAnim.NONE;
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
      if (this.isEdible()) {
         ItemStack itemStack = user.getItemInHand(hand);
         if (user.canEat(this.getFoodProperties().canAlwaysEat())) {
            user.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
         } else {
            return InteractionResultHolder.fail(itemStack);
         }
      } else {
         return InteractionResultHolder.pass(user.getItemInHand(hand));
      }
   }

   public String getDescriptionId() {
      return this.getOrCreateDescriptionId();
   }

   public String getDescriptionId(ItemStack stack) {
      return this.getDescriptionId();
   }

   public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
   }
}
