package net.hibiscus.naturespirit.mixin;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void isValid(BlockState state, CallbackInfoReturnable <Boolean> info) {
        if (BlockEntityType.SIGN.equals(this) && (state.getBlock() instanceof AbstractSignBlock || state.getBlock() instanceof WallSignBlock)) {
            info.setReturnValue(true);
        }
    }
}