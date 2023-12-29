package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralParentBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SucculentBlock extends CoralParentBlock {
   public static final MapCodec<SucculentBlock> CODEC = SucculentBlock.createCodec(SucculentBlock::new);
   private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);

   public MapCodec<? extends SucculentBlock> getCodec() {
      return CODEC;
   }

   public SucculentBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(WATERLOGGED, false));
   }

   @Override
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return SHAPE;
   }

   @Override
   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
      if (!(entity instanceof LivingEntity) || entity.isSneaking() || entity.getType().isIn(HibiscusTags.EntityTypes.CANT_SUCCULENT_SLOWED)) {
         return;
      }
      entity.slowMovement(state, new Vec3d(0.8f, 0.75, 0.8f));
   }

}

