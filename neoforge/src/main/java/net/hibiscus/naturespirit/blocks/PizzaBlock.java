package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSStatTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PizzaBlock extends Block implements EntityBlock {

  public static final int DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
  protected static final VoxelShape[] BITES_TO_SHAPE;
  public static final IntegerProperty BITES = IntegerProperty.create("pizza_bites", 0, 3);

  static {
    BITES_TO_SHAPE = new VoxelShape[]{
            Block.box(1D, 0D, 1D, 15D, 3D, 15D), Shapes.or(
            Block.box(1D, 0D, 1D, 15D, 3D, 8D),
            Block.box(1D, 0D, 1D, 8D, 3D, 15D)
    ), Block.box(1D, 0D, 1D, 8D, 3D, 15D), Block.box(1D, 0D, 8D, 8D, 3D, 15D)
    };
  }

  public PizzaBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
  }

  protected static InteractionResult tryEat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
    if (player.canEat(false)) {
      Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
      if (optionalPizzaBlockEntity.isPresent()) {
        PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
        player.awardStat(NSStatTypes.EAT_PIZZA_SLICE.get());
        int foodAmount = 2;
        float saturationModifier = 0.2F;
        for (PizzaToppingVariant pizzaToppingVariant : pizzaBlockEntity.toppings) {
          foodAmount += pizzaToppingVariant.hunger();
          saturationModifier += pizzaToppingVariant.saturation();
        }

        player.getFoodData().eat(foodAmount, saturationModifier);

        int i = state.getValue(BITES);
        world.gameEvent(player, GameEvent.EAT, pos);
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        if (i < 3) {
          if (!world.isClientSide()) {
            world.getServer().overworld().sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
          }
          world.setBlock(pos, state.setValue(BITES, state.getValue(BITES) + 1), 2);
          pizzaBlockEntity.setChanged();
        } else {
          world.removeBlock(pos, false);
          world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return InteractionResult.SUCCESS;
      }

    }
    return InteractionResult.PASS;
  }

  public static int getComparatorOutput(int bites) {
    return (7 - bites) * 2;
  }

  @Override
  public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) {
    Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
    if (optionalPizzaBlockEntity.isPresent()) {
      PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
      int BITE_STATE = state.getValue(BITES);
      Item item =
              BITE_STATE == 0 ? NSBlocks.WHOLE_PIZZA.get() : BITE_STATE == 1 ? NSBlocks.THREE_QUARTERS_PIZZA.get() : BITE_STATE == 2 ? NSBlocks.HALF_PIZZA.get() : NSBlocks.QUARTER_PIZZA.get();
      ItemStack itemStack = new ItemStack(item);
      pizzaBlockEntity.saveToItem(itemStack, world.registryAccess());
      return itemStack;
    }
    return super.getCloneItemStack(world, pos, state);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    int BITE_STATE = state.getValue(BITES);
    return BITES_TO_SHAPE[BITE_STATE];
  }

  @Override
  public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
    Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
    if (optionalPizzaBlockEntity.isPresent()) {
      PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
      ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
      Item item = itemStack.getItem();
      if (pizzaBlockEntity.canPlaceTopping(itemStack, world, pizzaBlockEntity)) {
        if (!player.isCreative()) {
          itemStack.shrink(1);
        }
        if (!world.isClientSide()) {
          world.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
        }

        world.playSound(null, pos, SoundEvents.MOSS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        pizzaBlockEntity.toppingCount++;
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.awardStat(Stats.ITEM_USED.get(item));

        return InteractionResult.SUCCESS;
      }
      if (world.isClientSide) {
        if (tryEat(world, pos, state, player).consumesAction()) {
          return InteractionResult.SUCCESS;
        }

        if (itemStack.isEmpty()) {
          return InteractionResult.CONSUME;
        }
      }

      return tryEat(world, pos, state, player);
    }
    return super.useWithoutItem(state, world, pos, player, hit);
  }

  @Override
  public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
    return direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState()
            : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(BITES);
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    BlockPos pos1 = pos.below();
    return world.getBlockState(pos1).isRedstoneConductor(world, pos1);
  }

  @Override
  public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
    int BITE_STATE = state.getValue(BITES);
    return getComparatorOutput(BITE_STATE);
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new PizzaBlockEntity(pos, state);
  }
}
