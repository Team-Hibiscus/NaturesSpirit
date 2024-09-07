package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusMiscBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class DesertTurnipBlock extends FacingBlock {
   public DesertTurnipBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
   }

   @Override protected MapCodec <? extends FacingBlock> getCodec() {
      return null;
   }

   public BlockState rotate(BlockState state, BlockRotation rotation) {
      return state.with(FACING, rotation.rotate(state.get(FACING)));
   }

   public BlockState getPlacementState(ItemPlacementContext ctx) {
      Direction direction = ctx.getSide();
      return this.getDefaultState().with(FACING, direction);
   }

   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(FACING);
   }

   public DesertTurnipStemBlock getStem() {
      return (DesertTurnipStemBlock) HibiscusMiscBlocks.DESERT_TURNIP_STEM;
   }
}
