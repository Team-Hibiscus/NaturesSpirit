package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DesertTurnipBlock extends FacingBlock {
   public DesertTurnipBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.UP));
   }

   public BlockState rotate(BlockState state, BlockRotation rotation) {
      return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
   }
   public BlockState getPlacementState(ItemPlacementContext ctx) {
      Direction direction = ctx.getSide();
      return (BlockState)this.getDefaultState().with(FACING, direction);
   }

   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(new Property[]{FACING});
   }

   public DesertPlantBlock getStem() {
      return (DesertPlantBlock) HibiscusBlocks.DESERT_TURNIP_STEM;
   }
}
