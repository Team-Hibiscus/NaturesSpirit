package net.hibiscus.naturespirit.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class AzollaItem extends BlockItem {

  public AzollaItem(Block block, Properties settings) {
    super(block, settings);
  }

  @Override
  public InteractionResult useOn(@NotNull UseOnContext ctx) {
    BlockPos pos = ctx.getClickedPos();
    Level world = ctx.getLevel();
    FluidState sideFluid = world.getFluidState(pos.relative(ctx.getClickedFace()));
    FluidState aboveFluid = world.getFluidState(pos.above());
    if (sideFluid.is(Fluids.WATER) || aboveFluid.is(Fluids.WATER)) {
      return InteractionResult.PASS;
    }
    return this.place(new BlockPlaceContext(ctx));
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
    return new InteractionResultHolder<>(super.useOn(waterContext(world, user, hand)), user.getItemInHand(hand));
  }

  // Finds the block above the first water source or block intercepted.
  protected UseOnContext waterContext(Level world, Player user, InteractionHand hand) {
    BlockHitResult blockHitResult = PlaceOnWaterBlockItem.getPlayerPOVHitResult(world, user, ClipContext.Fluid.SOURCE_ONLY);
    BlockHitResult aboveHitResult = blockHitResult.withPosition(blockHitResult.getBlockPos().above());
    return new UseOnContext(user, hand, aboveHitResult);
  }
}
