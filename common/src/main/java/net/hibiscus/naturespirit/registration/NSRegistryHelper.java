package net.hibiscus.naturespirit.registration;

import java.util.function.Function;
import java.util.function.Supplier;
import net.hibiscus.naturespirit.NSCommonContent;
import net.hibiscus.naturespirit.platform.Services;
import net.hibiscus.naturespirit.registration.compat.NSArtsAndCraftsCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class NSRegistryHelper {

    public static final NSRegistrar<Block> BLOCKS = NSRegistrar.of(Registries.BLOCK);
    public static final NSRegistrar<Item> ITEMS = NSRegistrar.of(Registries.ITEM);

    public static void bootstrap() {
        NSBlocks.bootstrap();
        NSDataComponents.bootstrap();
        NSEntityTypes.bootstrap();
        NSItemGroups.bootstrap();
        NSParticleTypes.bootstrap();
        NSStatTypes.bootstrap();
        NSSounds.bootstrap();
        NSWorldGen.bootstrap();
        NSVillagers.bootstrap();
        NSCriteria.bootstrap();
        if (Services.PLATFORM.isModLoaded("arts_and_crafts")) {
            NSArtsAndCraftsCompat.bootstrap();
        }
        NSCommonContent.bootstrap();
        NSCreativeTabContents.bootstrap();
    }

    public static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    public static Boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    public static boolean always(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }

    public static BlockPos postProcessSelf(BlockState state, BlockGetter world, BlockPos pos) {
        return pos;
    }

    public static Boolean ocelotOrParrot(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    public static <T extends Block> NSBlockHolder<T> registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, ? extends T> block, Supplier<BlockBehaviour.Properties> properties) {
        return BLOCKS.registerBlock(name, block, properties);
    }

    public static <T extends Block> NSBlockHolder<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, Supplier<BlockBehaviour.Properties> properties) {
        NSBlockHolder<T> holder = registerBlockWithoutItem(name, block, properties);
        ITEMS.registerSimpleBlockItem(name, holder);
        return holder;
    }

    public static <T extends Block> NSBlockHolder<T> registerTransparentBlockWithoutItem(String name, Function<BlockBehaviour.Properties, ? extends T> block, Supplier<BlockBehaviour.Properties> properties) {
        return registerBlockWithoutItem(name, block, properties);
    }

    public static <T extends Block> NSBlockHolder<T> registerTransparentBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, Supplier<BlockBehaviour.Properties> properties) {
        return registerBlock(name, block, properties);
    }

    public static <T extends Item> NSItemHolder<T> registerItem(String name, Function<Item.Properties, ? extends T> item, Supplier<Item.Properties> properties) {
        return ITEMS.registerItem(name, item, properties);
    }

    public static <T extends Block> NSBlockHolder<T> registerTallPlantBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, Supplier<BlockBehaviour.Properties> properties) {
        NSBlockHolder<T> plant = registerBlockWithoutItem(name, block, properties);
        ITEMS.registerItem(name, props -> new DoubleHighBlockItem(plant.get(), props), () -> new Item.Properties().useBlockDescriptionPrefix());
        return plant;
    }
}
