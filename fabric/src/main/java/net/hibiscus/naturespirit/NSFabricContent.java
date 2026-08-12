package net.hibiscus.naturespirit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.hibiscus.naturespirit.blocks.NSCauldronBehavior;
import net.hibiscus.naturespirit.registration.NSCompostables;
import net.hibiscus.naturespirit.registration.NSFlammables;
import net.hibiscus.naturespirit.registration.NSFuels;
import net.hibiscus.naturespirit.registration.NSStrippables;
import net.hibiscus.naturespirit.util.NSEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public final class NSFabricContent {

    private NSFabricContent() {
    }

    public static void run() {
        registerBuiltinPacks();
        registerDatapackRegistries();
        registerBlockEntityBlocks();
        registerCreativeTabEntries();
        registerStrippables();
        registerFlammables();
        registerFuels();
        registerCompostables();
        registerCauldronInteractions();
        registerVillagerBiomeTypes();
        registerEventCallbacks();
    }

    private static void registerBuiltinPacks() {
        ModContainer container = FabricLoader.getInstance().getModContainer(NaturesSpirit.MOD_ID).orElseThrow();
        for (NSCommonHooks.BuiltinPack pack : NSCommonHooks.BUILTIN_PACKS) {
            if (!pack.condition().getAsBoolean()) {
                continue;
            }
            PackActivationType activationType = pack.alwaysActive() ? PackActivationType.ALWAYS_ENABLED : PackActivationType.NORMAL;
            ResourceLoader.registerBuiltinPack(NaturesSpirit.id(pack.name()), container, pack.displayName(), activationType);
        }
    }

    private static void registerDatapackRegistries() {
        for (NSCommonHooks.DatapackRegistry<?> registry : NSCommonHooks.DATAPACK_REGISTRIES) {
            register(registry);
        }
    }

    private static <T> void register(NSCommonHooks.DatapackRegistry<T> registry) {
        if (registry.networkCodec() == null) {
            DynamicRegistries.register(registry.key(), registry.codec());
        } else {
            DynamicRegistries.registerSynced(registry.key(), registry.codec(), registry.networkCodec());
        }
    }

    private static void registerBlockEntityBlocks() {
        for (NSCommonHooks.BlockEntityBlocks entry : NSCommonHooks.BLOCK_ENTITY_BLOCKS) {
            FabricBlockEntityType type = (FabricBlockEntityType) entry.type().get();
            for (Supplier<? extends Block> block : entry.blocks()) {
                type.addValidBlock(block.get());
            }
        }
    }

    private static void registerCreativeTabEntries() {
        for (NSCommonHooks.CreativeTabEntries entry : NSCommonHooks.CREATIVE_TAB_ENTRIES) {
            CreativeModeTabEvents.modifyOutputEvent(entry.tab()).register(output -> entry.populate().accept(adapt(output)));
        }
    }

    private static void registerStrippables() {
        for (NSStrippables.Entry entry : NSStrippables.STRIPPABLES) {
            StrippableBlockRegistry.register(entry.log().get(), entry.stripped().get());
        }
    }

    private static void registerFlammables() {
        for (NSFlammables.Entry entry : NSFlammables.FLAMMABLES) {
            FlammableBlockRegistry.getDefaultInstance().add(entry.block().get(), entry.igniteOdds(), entry.burnOdds());
        }
    }

    private static void registerFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            for (NSFuels.Entry entry : NSFuels.FUELS) {
                builder.add(entry.item().get(), entry.burnTime());
            }
        });
    }

    private static void registerCompostables() {
        for (NSCompostables.Entry entry : NSCompostables.COMPOSTABLES) {
            CompostableRegistry.INSTANCE.add(entry.item().get(), entry.chance());
        }
    }

    private static void registerCauldronInteractions() {
        Map<Identifier, CauldronInteraction.Dispatcher> dispatchers = new HashMap<>();
        for (NSCauldronBehavior.DispatcherEntry entry : NSCauldronBehavior.DISPATCHERS) {
            dispatchers.put(entry.id(), entry.dispatcher());
        }
        for (NSCauldronBehavior.InteractionEntry entry : NSCauldronBehavior.INTERACTIONS) {
            dispatchers.get(entry.dispatcherId()).put(entry.item(), entry.interaction());
        }
    }

    private static void registerVillagerBiomeTypes() {
        for (NSCommonHooks.VillagerBiomeType entry : NSCommonHooks.VILLAGER_BIOME_TYPES) {
            VillagerType.BY_BIOME.put(entry.biome(), entry.villagerType());
        }
    }

    private static void registerEventCallbacks() {
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            return NSEvents.onCauldronBucketUse(player, level, hand, pos, level.getBlockState(pos), player.getItemInHand(hand));
        });
    }

    private static NSCommonHooks.TabOutput adapt(FabricCreativeModeTabOutput output) {
        return new NSCommonHooks.TabOutput() {

            @Override
            public void accept(ItemStack stack, NSCommonHooks.TabVisibility visibility) {
                output.accept(stack, visibility(visibility));
            }

            @Override
            public void insertAfter(ItemStack anchor, ItemStack stack, NSCommonHooks.TabVisibility visibility) {
                output.insertAfter(anchor, List.of(stack), visibility(visibility));
            }

            @Override
            public void insertBefore(ItemStack anchor, ItemStack stack, NSCommonHooks.TabVisibility visibility) {
                output.insertBefore(anchor, List.of(stack), visibility(visibility));
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
