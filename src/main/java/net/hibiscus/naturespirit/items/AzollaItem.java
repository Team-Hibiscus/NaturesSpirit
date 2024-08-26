package net.hibiscus.naturespirit.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class AzollaItem extends BlockItem {
   public AzollaItem(Block block, Settings settings) {
      super(block, settings);
   }

   @Override public ActionResult useOnBlock(ItemUsageContext ctx) {
      BlockPos pos = ctx.getBlockPos();
      World world = ctx.getWorld();
      FluidState sideFluid = world.getFluidState(pos.offset(ctx.getSide()));
      FluidState aboveFluid = world.getFluidState(pos.up());
      if(sideFluid.isOf(Fluids.WATER) || aboveFluid.isOf(Fluids.WATER)) return ActionResult.PASS;
      return this.place(new ItemPlacementContext(ctx));
   }

   @Override public TypedActionResult <ItemStack> use(World world, PlayerEntity user, Hand hand) {
      return new TypedActionResult <>(super.useOnBlock(waterContext(world, user, hand)), user.getStackInHand(hand));
   }

   // Finds the block above the first water source or block intercepted.
   protected ItemUsageContext waterContext(World world, PlayerEntity user, Hand hand) {
      BlockHitResult blockHitResult = PlaceableOnWaterItem.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
      BlockHitResult aboveHitResult = blockHitResult.withBlockPos(blockHitResult.getBlockPos().up());
      return new ItemUsageContext(user, hand, aboveHitResult);
   }
}