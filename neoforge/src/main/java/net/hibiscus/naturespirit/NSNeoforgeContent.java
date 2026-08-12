package net.hibiscus.naturespirit;

import java.util.function.Supplier;
import net.hibiscus.naturespirit.blocks.NSCauldronBehavior;
import net.hibiscus.naturespirit.registration.NSFlammables;
import net.hibiscus.naturespirit.util.NSEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.TriState;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCauldronInteractionEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = NaturesSpirit.MOD_ID)
public final class NSNeoforgeContent {

    private NSNeoforgeContent() {
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(NSNeoforgeContent::registerFlowerPots);
        event.enqueueWork(NSNeoforgeContent::registerFlammables);
    }

    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        for (NSCommonHooks.BuiltinPack pack : NSCommonHooks.BUILTIN_PACKS) {
            if (!pack.condition().getAsBoolean()) {
                continue;
            }
            event.addPackFinders(NaturesSpirit.id("resourcepacks/" + pack.name()), pack.type(), pack.displayName(), pack.source(), pack.alwaysActive(), pack.position());
        }
    }

    @SubscribeEvent
    public static void onAddBlockEntityBlocks(BlockEntityTypeAddBlocksEvent event) {
        for (NSCommonHooks.BlockEntityBlocks entry : NSCommonHooks.BLOCK_ENTITY_BLOCKS) {
            for (Supplier<? extends Block> block : entry.blocks()) {
                event.modify(entry.type().get(), block.get());
            }
        }
    }

    @SubscribeEvent
    public static void onBuildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        for (NSCommonHooks.CreativeTabEntries entry : NSCommonHooks.CREATIVE_TAB_ENTRIES) {
            if (event.getTabKey().equals(entry.tab())) {
                entry.populate().accept(adapt(event));
            }
        }
    }

    @SubscribeEvent
    public static void onDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        for (NSCommonHooks.DatapackRegistry<?> registry : NSCommonHooks.DATAPACK_REGISTRIES) {
            register(event, registry);
        }
    }

    @SubscribeEvent
    public static void onRegisterCauldronDispatchers(RegisterCauldronInteractionEvent.Dispatcher event) {
        for (NSCauldronBehavior.DispatcherEntry entry : NSCauldronBehavior.DISPATCHERS) {
            event.register(entry.id(), entry.dispatcher());
        }
    }

    @SubscribeEvent
    public static void onRegisterCauldronInteractions(RegisterCauldronInteractionEvent.Interaction event) {
        for (NSCauldronBehavior.InteractionEntry entry : NSCauldronBehavior.INTERACTIONS) {
            event.register(entry.dispatcherId(), entry.item(), entry.interaction());
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        InteractionResult result = NSEvents.onCauldronBucketUse(event.getEntity(), level, event.getHand(), pos, level.getBlockState(pos), event.getItemStack());
        if (result != InteractionResult.PASS) {
            event.setUseItem(TriState.FALSE);
            event.setCancellationResult(result);
            event.setCanceled(true);
        }
    }

    private static void registerFlowerPots() {
        FlowerPotBlock emptyPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        for (NSCommonHooks.PottedPlant entry : NSCommonHooks.FLOWER_POTS) {
            emptyPot.addPlant(BuiltInRegistries.BLOCK.getKey(entry.plant().get()), entry.potted());
        }
    }

    private static void registerFlammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        for (NSFlammables.Entry entry : NSFlammables.FLAMMABLES) {
            fire.setFlammable(entry.block().get(), entry.igniteOdds(), entry.burnOdds());
        }
    }

    private static <T> void register(DataPackRegistryEvent.NewRegistry event, NSCommonHooks.DatapackRegistry<T> registry) {
        if (registry.networkCodec() == null) {
            event.dataPackRegistry(registry.key(), registry.codec());
            return;
        }
        event.dataPackRegistry(registry.key(), registry.codec(), registry.networkCodec(), builder -> {
            if (registry.maxId() > 0) {
                builder.maxId(registry.maxId());
            }
            builder.sync(true);
        });
    }

    private static NSCommonHooks.TabOutput adapt(BuildCreativeModeTabContentsEvent event) {
        return new NSCommonHooks.TabOutput() {

            @Override
            public void accept(ItemStack stack, NSCommonHooks.TabVisibility visibility) {
                event.accept(stack, visibility(visibility));
            }

            @Override
            public void insertAfter(ItemStack anchor, ItemStack stack, NSCommonHooks.TabVisibility visibility) {
                event.insertAfter(anchor, stack, visibility(visibility));
            }

            @Override
            public void insertBefore(ItemStack anchor, ItemStack stack, NSCommonHooks.TabVisibility visibility) {
                event.insertBefore(anchor, stack, visibility(visibility));
            }
        };
    }

    private static CreativeModeTab.TabVisibility visibility(NSCommonHooks.TabVisibility visibility) {
        return switch (visibility) {
            case PARENT_AND_SEARCH_TABS -> CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;
            case PARENT_TAB_ONLY -> CreativeModeTab.TabVisibility.PARENT_TAB_ONLY;
            case SEARCH_TAB_ONLY -> CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY;
        };
    }
}
