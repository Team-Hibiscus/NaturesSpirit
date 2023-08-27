package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

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
      return (DesertPlantBlock) HibiscusBlocksAndItems.DESERT_TURNIP_STEM;
   }
}
