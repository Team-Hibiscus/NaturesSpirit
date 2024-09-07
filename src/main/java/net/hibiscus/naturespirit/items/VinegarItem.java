package net.hibiscus.naturespirit.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PotionItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VinegarItem extends PotionItem {

   public VinegarItem(Settings settings) {
      super(settings);
   }

   public ItemStack getDefaultStack() {
      return new ItemStack(this);
   }


   public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
      return this.isFood() ? user.eatFood(world, stack) : stack;
   }

   public ActionResult useOnBlock(ItemUsageContext context) {
      BlockPos pos = context.getBlockPos();
      World world = context.getWorld();
      BlockState blockState = world.getBlockState(pos);
      PlayerEntity player = context.getPlayer();
      Hand hand = context.getHand();
//      if (player != null && blockState.isOf(Blocks.CALCITE) && player.getStackInHand(hand).isOf(HibiscusMiscBlocks.VINEGAR_BOTTLE) && HibiscusConfig.vinegar_duplication) {
//
//         Vec3d vec3d = Vec3d.add(pos, 0.5, 1.01, 0.5).addRandom(world.random, 0.2F);
//         ItemStack itemStack = new ItemStack(HibiscusMiscBlocks.CHALK_POWDER);
//         itemStack.setCount(world.getRandom().nextBetween(1, 5));
//         ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), itemStack);
//         itemEntity.setToDefaultPickupDelay();
//         world.spawnEntity(itemEntity);
//         world.playSound(null, pos, SoundEvents.BLOCK_CALCITE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.8F);
//         double d = blockState.getOutlineShape(world, pos).getEndingCoord(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
//         Random random = world.getRandom();
//
//         for(int i = 0; i < 10; ++i) {
//            double g = random.nextGaussian() * 0.01D;
//            double h = random.nextGaussian() * 0.02D;
//            double j = random.nextGaussian() * 0.01D;
//            float xOffset = context.getSide() == Direction.WEST ? -.75F : (context.getSide() == Direction.EAST ? .75F : 0F);
//            float zOffset = context.getSide() == Direction.NORTH ? -.75F : (context.getSide() == Direction.SOUTH ? .75F : 0F);
//            float yOffset = context.getSide() == Direction.UP ? 0F : (context.getSide() == Direction.DOWN ? -1F : -.5F);
//            world.addParticle(NatureSpirit.CALCITE_BUBBLE_PARTICLE,
//                    (double)pos.getX() + xOffset + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(),
//                    (double)pos.getY() + yOffset + d + (double)random.nextFloat() * (1.0D - d),
//                    (double)pos.getZ() + zOffset + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(),
//                    g, h, j);
//         }
//         if (!player.isCreative() && !player.isSpectator())
//         {
//            ItemStack itemStack2 = player.getStackInHand(hand).getRecipeRemainder();
//            player.getStackInHand(hand).decrement(1);
//            if (player.getStackInHand(hand).isEmpty()) {
//               player.setStackInHand(hand, itemStack2);
//            } else {
//               if(player.getInventory().insertStack(itemStack2)) {
//                  player.dropItem(itemStack2, false);
//               }
//            }
//         }
//         return ActionResult.SUCCESS;
//      }
      return ActionResult.PASS;
   }

   public int getMaxUseTime(ItemStack stack) {
      if (stack.getItem().isFood()) {
         return this.getFoodComponent().isSnack() ? 16 : 32;
      } else {
         return 0;
      }
   }

   public UseAction getUseAction(ItemStack stack) {
      return stack.getItem().isFood() ? UseAction.EAT : UseAction.NONE;
   }

   public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
      if (this.isFood()) {
         ItemStack itemStack = user.getStackInHand(hand);
         if (user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
         } else {
            return TypedActionResult.fail(itemStack);
         }
      } else {
         return TypedActionResult.pass(user.getStackInHand(hand));
      }
   }

   public String getTranslationKey() {
      return this.getOrCreateTranslationKey();
   }

   public String getTranslationKey(ItemStack stack) {
      return this.getTranslationKey();
   }

   public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
   }
}
