package net.hibiscus.naturespirit.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrushItem.class)
public class BrushItemMixin {

  @Unique
  public long naturespirit$nextDustTime = 0;

  @Inject(method = "onUseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBlockEntity(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;"))
  private void injectCalciteClusterBrushing(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks, CallbackInfo ci, @Local BlockState blockState, @Local BlockPos blockPos, @Local BlockHitResult blockHitResult, @Local
      Player playerEntity) {
    if (blockState.is(NSBlocks.SMALL_CALCITE_BUD.get()) || blockState.is(NSBlocks.LARGE_CALCITE_BUD.get()) || blockState.is(NSBlocks.CALCITE_CLUSTER.get())) {

      if (world.getGameTime() > naturespirit$nextDustTime) {
        naturespirit$nextDustTime = world.getGameTime() + 20L;
        ItemEntity itemEntity = naturespirit$getChalkPowder(world, blockPos);
        itemEntity.setDeltaMovement(Vec3.ZERO);
        world.addFreshEntity(itemEntity);
        if (world.getRandom().nextFloat() < .3) {
          if (blockState.is(NSBlocks.SMALL_CALCITE_BUD.get())) world.setBlockAndUpdate(blockPos, blockState.getFluidState().createLegacyBlock());
          if (blockState.is(NSBlocks.LARGE_CALCITE_BUD.get())) world.setBlockAndUpdate(blockPos, NSBlocks.SMALL_CALCITE_BUD.get().withPropertiesOf(blockState));
          if (blockState.is(NSBlocks.CALCITE_CLUSTER.get())) world.setBlockAndUpdate(blockPos, NSBlocks.LARGE_CALCITE_BUD.get().withPropertiesOf(blockState));
          EquipmentSlot equipmentSlot = stack.equals(playerEntity.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
          stack.hurtAndBreak(1, user, equipmentSlot);
        }
      }
    }
  }

  @Unique
  private ItemEntity naturespirit$getChalkPowder(Level world, BlockPos blockPos) {
    double d = EntityType.ITEM.getWidth();
    double e = 1.0 - d;
    double f = d / 2.0;
    double g = (double) blockPos.getX() + 0.5 * e + f;
    double h = (double) blockPos.getY() + 0.25 + (double)(EntityType.ITEM.getHeight() / 2.0F);
    double i = (double) blockPos.getZ() + 0.5 * e + f;
    return new ItemEntity(world, g, h, i, new ItemStack(NSBlocks.CHALK_POWDER.get(), world.getRandom().nextIntBetweenInclusive(1, 3)));
  }

  @Inject(method = "useOn", at = @At("HEAD"))
  private void useOnBlock(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
    naturespirit$nextDustTime = context.getLevel().getGameTime() + 10L;
  }

}
