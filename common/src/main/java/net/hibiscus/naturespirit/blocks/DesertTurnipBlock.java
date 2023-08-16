package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

public class DesertTurnipBlock extends DirectionalBlock {
   public DesertTurnipBlock(BlockBehaviour.Properties settings) {
      super(settings);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.UP));
   }

   public BlockState rotate(BlockState state, Rotation rotation) {
      return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
   }
   public BlockState getStateForPlacement(BlockPlaceContext ctx) {
      Direction direction = ctx.getClickedFace();
      return (BlockState)this.defaultBlockState().setValue(FACING, direction);
   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(new Property[]{FACING});
   }

   public DesertPlantBlock getStem() {
      return (DesertPlantBlock) HibiscusBlocks.DESERT_TURNIP_STEM;
   }
}
