package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class PizzaBlock extends Block {
    public static final int MAX_BITES = 3;
    public static final int MAX_TOPPINGS = 4;
    public static final IntProperty BITES = IntProperty.of("pizza_bites", 0, 3);
    public static final IntProperty TOPPINGS = IntProperty.of("toppings", 0, 4);
    public static final BooleanProperty MUSHROOM_TOPPING = BooleanProperty.of("mushroom_topping");
    public static final BooleanProperty GREEN_OLIVES_TOPPING = BooleanProperty.of("green_olives_topping");
    public static final BooleanProperty BLACK_OLIVES_TOPPING = BooleanProperty.of("black_olives_topping");
    public static final BooleanProperty CARROT_TOPPING = BooleanProperty.of("carrot_topping");
    public static final BooleanProperty BEETROOT_TOPPING = BooleanProperty.of("beetroot_topping");
    public static final BooleanProperty CHICKEN_TOPPING = BooleanProperty.of("chicken_topping");
    public static final BooleanProperty COD_TOPPING = BooleanProperty.of("cod_topping");
    public static final BooleanProperty PORK_TOPPING = BooleanProperty.of("pork_topping");
    public static final BooleanProperty RABBIT_TOPPING = BooleanProperty.of("rabbit_topping");
    public static final int DEFAULT_COMPARATOR_OUTPUT;
    protected static final float field_31047 = 1.0F;
    protected static final float field_31048 = 2.0F;
    protected static final VoxelShape[] BITES_TO_SHAPE;

    public PizzaBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState())
                .with(BITES, 0)
                .with(GREEN_OLIVES_TOPPING, false)
                .with(BLACK_OLIVES_TOPPING, false)
                .with(BEETROOT_TOPPING, false)
                .with(CARROT_TOPPING, false)
                .with(CHICKEN_TOPPING, false)
                .with(COD_TOPPING, false)
                .with(PORK_TOPPING, false)
                .with(RABBIT_TOPPING, false)
                .with(MUSHROOM_TOPPING, false)
                .with(TOPPINGS, 0));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        int BITE_STATE = state.get(BITES);
        Item item = BITE_STATE == 0 ? HibiscusBlocks.WHOLE_PIZZA : BITE_STATE == 1 ? HibiscusBlocks.THREE_QUARTERS_PIZZA : BITE_STATE == 2 ? HibiscusBlocks.HALF_PIZZA : HibiscusBlocks.QUARTER_PIZZA;
        ItemStack itemStack = new ItemStack(item);

        int TOPPINGS_STATE = state.get(TOPPINGS);
        NbtCompound nbtCompound = itemStack.getOrCreateSubNbt("BlockStateTag");
        assert nbtCompound != null;

        String tomatoBoolean = state.get(MUSHROOM_TOPPING).toString();
        String greenOlivesBoolean = state.get(GREEN_OLIVES_TOPPING).toString();
        String blackOlivesBoolean = state.get(BLACK_OLIVES_TOPPING).toString();
        String beetrootBoolean = state.get(BEETROOT_TOPPING).toString();
        String carrotBoolean = state.get(CARROT_TOPPING).toString();
        String codBoolean = state.get(COD_TOPPING).toString();
        String chickenBoolean = state.get(CHICKEN_TOPPING).toString();
        String porkBoolean = state.get(PORK_TOPPING).toString();
        String rabbitBoolean = state.get(RABBIT_TOPPING).toString();

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

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[(Integer)state.get(BITES)];
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        BooleanProperty pizzaTopping;
        switch (itemStack.getItem().getTranslationKey()) {
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
        if (itemStack.isIn(HibiscusTags.Items.PIZZA_TOPPINGS) && (Integer)state.get(BITES) == 0 && state.get(TOPPINGS) < 4 && !(itemStack.isIn(HibiscusTags.Items.DISABLED_PIZZA_TOPPINGS)) && !state.get(pizzaTopping)) {
            Block block = Block.getBlockFromItem(item);
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }


                world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_MOSS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, (BlockState)state.with(TOPPINGS, state.get(TOPPINGS) + 1).with(
                        pizzaTopping, true), 3);
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                player.incrementStat(Stats.USED.getOrCreateStat(item));

                return ActionResult.SUCCESS;
        }
        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.incrementStat(NatureSpirit.EAT_PIZZA_SLICE);
            int foodAmount = 2;
            float saturationModifier = 0.2F;
            if (state.get(CARROT_TOPPING)) {
                foodAmount = foodAmount + 1;
                saturationModifier = saturationModifier + 0.1F;
            }
            if (state.get(PORK_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.2F;
            }
            if (state.get(BLACK_OLIVES_TOPPING)) {
                foodAmount = foodAmount + 1;
                saturationModifier = saturationModifier + 0.1F;
            }
            if (state.get(GREEN_OLIVES_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.1F;
            }
            if (state.get(BEETROOT_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.1F;
            }
            if (state.get(CHICKEN_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.2F;
            }
            if (state.get(RABBIT_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.2F;
            }
            if (state.get(COD_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.2F;
            }
            if (state.get(MUSHROOM_TOPPING)) {
                foodAmount = foodAmount + 2;
                saturationModifier = saturationModifier + 0.1F;
            }

            player.getHungerManager().add(foodAmount, saturationModifier);

            int i = (Integer)state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 3) {
                world.setBlockState(pos, (BlockState)state.with(BITES, i + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

    protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
        builder.add(BITES, TOPPINGS, COD_TOPPING, BEETROOT_TOPPING, PORK_TOPPING, RABBIT_TOPPING, MUSHROOM_TOPPING, BLACK_OLIVES_TOPPING, GREEN_OLIVES_TOPPING, CARROT_TOPPING, CHICKEN_TOPPING);
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getComparatorOutput((Integer)state.get(BITES));
    }

    public static int getComparatorOutput(int bites) {
        return (7 - bites) * 2;
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
        BITES_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 5.0D, 15.0D),
                VoxelShapes.union(Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 5.0D, 8.0D), Block.createCuboidShape(1.0D, 0.0D, 1.0D, 8.0D, 5.0D, 15.0D)),
                Block.createCuboidShape(1.0D, 0.0D, 1.0D, 8.0D, 5.0D, 15.0D),
                Block.createCuboidShape(1.0D, 0.0D, 8.0D, 8.0D, 5.0D, 15.0D)};
    }
}
