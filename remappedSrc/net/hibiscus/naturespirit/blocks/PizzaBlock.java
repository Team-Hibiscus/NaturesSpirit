package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PizzaBlock extends Block {
   public static final int MAX_BITES = 3;
   public static final int MAX_TOPPINGS = 4;
   public static final IntegerProperty BITES = IntegerProperty.create("pizza_bites", 0, 3);
   public static final IntegerProperty TOPPINGS = IntegerProperty.create("toppings", 0, 4);
   public static final BooleanProperty MUSHROOM_TOPPING = BooleanProperty.create("mushroom_topping");
   public static final BooleanProperty GREEN_OLIVES_TOPPING = BooleanProperty.create("green_olives_topping");
   public static final BooleanProperty BLACK_OLIVES_TOPPING = BooleanProperty.create("black_olives_topping");
   public static final BooleanProperty CARROT_TOPPING = BooleanProperty.create("carrot_topping");
   public static final BooleanProperty BEETROOT_TOPPING = BooleanProperty.create("beetroot_topping");
   public static final BooleanProperty CHICKEN_TOPPING = BooleanProperty.create("chicken_topping");
   public static final BooleanProperty COD_TOPPING = BooleanProperty.create("cod_topping");
   public static final BooleanProperty PORK_TOPPING = BooleanProperty.create("pork_topping");
   public static final BooleanProperty RABBIT_TOPPING = BooleanProperty.create("rabbit_topping");
   public static final int DEFAULT_COMPARATOR_OUTPUT;
   protected static final float field_31047 = 1.0F;
   protected static final float field_31048 = 2.0F;
   protected static final VoxelShape[] BITES_TO_SHAPE;

   static {
      DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
      BITES_TO_SHAPE = new VoxelShape[]{
              Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D), Shapes.or(
              Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 8.0D),
              Block.box(1.0D, 0.0D, 1.0D, 8.0D, 3.0D, 15.0D)
      ), Block.box(1.0D, 0.0D, 1.0D, 8.0D, 3.0D, 15.0D), Block.box(1.0D, 0.0D, 8.0D, 8.0D, 3.0D, 15.0D)
      };
   }

   public PizzaBlock(Properties settings) {
      super(settings);
      this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0).setValue(GREEN_OLIVES_TOPPING, false).setValue(BLACK_OLIVES_TOPPING, false).setValue(BEETROOT_TOPPING, false).setValue(
              CARROT_TOPPING,
              false
      ).setValue(CHICKEN_TOPPING, false).setValue(COD_TOPPING, false).setValue(PORK_TOPPING, false).setValue(RABBIT_TOPPING, false).setValue(MUSHROOM_TOPPING, false).setValue(TOPPINGS, 0));
   }

   protected static InteractionResult tryEat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
      if(!player.canEat(false)) {
         return InteractionResult.PASS;
      }
      else {
         player.awardStat(NatureSpirit.EAT_PIZZA_SLICE);
         int foodAmount = 2;
         float saturationModifier = 0.2F;
         if(state.getValue(CARROT_TOPPING)) {
            foodAmount = foodAmount + 1;
            saturationModifier = saturationModifier + 0.1F;
         }
         if(state.getValue(PORK_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.2F;
         }
         if(state.getValue(BLACK_OLIVES_TOPPING)) {
            foodAmount = foodAmount + 1;
            saturationModifier = saturationModifier + 0.1F;
         }
         if(state.getValue(GREEN_OLIVES_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.1F;
         }
         if(state.getValue(BEETROOT_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.1F;
         }
         if(state.getValue(CHICKEN_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.2F;
         }
         if(state.getValue(RABBIT_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.2F;
         }
         if(state.getValue(COD_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.2F;
         }
         if(state.getValue(MUSHROOM_TOPPING)) {
            foodAmount = foodAmount + 2;
            saturationModifier = saturationModifier + 0.1F;
         }

         player.getFoodData().eat(foodAmount, saturationModifier);

         int i = state.getValue(BITES);
         world.gameEvent(player, GameEvent.EAT, pos);
         if(i < 3) {
            world.setBlock(pos, state.setValue(BITES, i + 1), 3);
         }
         else {
            world.removeBlock(pos, false);
            world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
         }

         return InteractionResult.SUCCESS;
      }
   }

   public static int getComparatorOutput(int bites) {
      return (7 - bites) * 2;
   }

   @Override public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      int BITE_STATE = state.getValue(BITES);
      Item item = BITE_STATE == 0 ? HibiscusBlocksAndItems.WHOLE_PIZZA : BITE_STATE == 1 ? HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA : BITE_STATE == 2 ? HibiscusBlocksAndItems.HALF_PIZZA : HibiscusBlocksAndItems.QUARTER_PIZZA;
      ItemStack itemStack = new ItemStack(item);

      int TOPPINGS_STATE = state.getValue(TOPPINGS);
      CompoundTag nbtCompound = itemStack.getOrCreateTagElement("BlockStateTag");
      assert nbtCompound != null;

      String tomatoBoolean = state.getValue(MUSHROOM_TOPPING).toString();
      String greenOlivesBoolean = state.getValue(GREEN_OLIVES_TOPPING).toString();
      String blackOlivesBoolean = state.getValue(BLACK_OLIVES_TOPPING).toString();
      String beetrootBoolean = state.getValue(BEETROOT_TOPPING).toString();
      String carrotBoolean = state.getValue(CARROT_TOPPING).toString();
      String codBoolean = state.getValue(COD_TOPPING).toString();
      String chickenBoolean = state.getValue(CHICKEN_TOPPING).toString();
      String porkBoolean = state.getValue(PORK_TOPPING).toString();
      String rabbitBoolean = state.getValue(RABBIT_TOPPING).toString();

      nbtCompound.putString("mushroom_topping", tomatoBoolean);
      nbtCompound.putString("green_olives_topping", greenOlivesBoolean);
      nbtCompound.putString("black_olives_topping", blackOlivesBoolean);
      nbtCompound.putString("beetroot_topping", beetrootBoolean);
      nbtCompound.putString("carrot_topping", carrotBoolean);
      nbtCompound.putString("cod_topping", codBoolean);
      nbtCompound.putString("chicken_topping", chickenBoolean);
      nbtCompound.putString("pork_topping", porkBoolean);
      nbtCompound.putString("rabbit_topping", rabbitBoolean);
      nbtCompound.putInt("toppings", TOPPINGS_STATE);

      return itemStack;
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return BITES_TO_SHAPE[state.getValue(BITES)];
   }

   public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
      ItemStack itemStack = player.getItemInHand(hand);
      Item item = itemStack.getItem();
      BooleanProperty pizzaTopping;
      switch(itemStack.getItem().getDescriptionId()) {
         case "item.minecraft.carrot" -> pizzaTopping = CARROT_TOPPING;
         case "item.minecraft.cooked_porkchop" -> pizzaTopping = PORK_TOPPING;
         case "item.natures_spirit.black_olives" -> pizzaTopping = BLACK_OLIVES_TOPPING;
         case "item.natures_spirit.green_olives" -> pizzaTopping = GREEN_OLIVES_TOPPING;
         case "item.minecraft.beetroot" -> pizzaTopping = BEETROOT_TOPPING;
         case "item.minecraft.cooked_chicken" -> pizzaTopping = CHICKEN_TOPPING;
         case "item.minecraft.cooked_cod" -> pizzaTopping = COD_TOPPING;
         case "item.minecraft.cooked_rabbit" -> pizzaTopping = RABBIT_TOPPING;
         default -> pizzaTopping = MUSHROOM_TOPPING;
      }
      if(itemStack.is(HibiscusTags.Items.PIZZA_TOPPINGS) && state.getValue(BITES) == 0 && state.getValue(TOPPINGS) < 4 && !(itemStack.is(HibiscusTags.Items.DISABLED_PIZZA_TOPPINGS)) && !state.getValue(
              pizzaTopping)) {
         Block block = Block.byItem(item);
         if(!player.isCreative()) {
            itemStack.shrink(1);
         }


         world.playSound(null, pos, SoundEvents.MOSS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
         world.setBlock(pos, state.setValue(TOPPINGS, state.getValue(TOPPINGS) + 1).setValue(pizzaTopping, true), 3);
         world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
         player.awardStat(Stats.ITEM_USED.get(item));

         return InteractionResult.SUCCESS;
      }
      if(world.isClientSide) {
         if(tryEat(world, pos, state, player).consumesAction()) {
            return InteractionResult.SUCCESS;
         }

         if(itemStack.isEmpty()) {
            return InteractionResult.CONSUME;
         }
      }

      return tryEat(world, pos, state, player);
   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
      return direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
   }

   public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      BlockPos pos1 = pos.below();
      return world.getBlockState(pos1).isRedstoneConductor(world, pos1);
   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(BITES, TOPPINGS, COD_TOPPING, BEETROOT_TOPPING, PORK_TOPPING, RABBIT_TOPPING, MUSHROOM_TOPPING, BLACK_OLIVES_TOPPING, GREEN_OLIVES_TOPPING, CARROT_TOPPING, CHICKEN_TOPPING);
   }

   public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
      return getComparatorOutput(state.getValue(BITES));
   }

   public boolean hasAnalogOutputSignal(BlockState state) {
      return true;
   }

   public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
      return false;
   }
}
