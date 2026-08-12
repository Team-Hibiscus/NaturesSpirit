package net.hibiscus.naturespirit;

import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class NSCommonHooks {

    public static final List<BuiltinPack> BUILTIN_PACKS = new ArrayList<>();
    public static final List<DatapackRegistry<?>> DATAPACK_REGISTRIES = new ArrayList<>();
    public static final List<BlockEntityBlocks> BLOCK_ENTITY_BLOCKS = new ArrayList<>();
    public static final List<PottedPlant> FLOWER_POTS = new ArrayList<>();
    public static final List<CreativeTabEntries> CREATIVE_TAB_ENTRIES = new ArrayList<>();
    public static final List<VillagerBiomeType> VILLAGER_BIOME_TYPES = new ArrayList<>();

    private NSCommonHooks() {
    }

    public record BuiltinPack(String name, PackType type, Component displayName, PackSource source, boolean alwaysActive, Pack.Position position, BooleanSupplier condition) {
    }

    public record DatapackRegistry<T>(ResourceKey<Registry<T>> key, Codec<T> codec, Codec<T> networkCodec, int maxId) {
    }

    public record BlockEntityBlocks(Supplier<BlockEntityType<?>> type, List<Supplier<? extends Block>> blocks) {
    }

    public record PottedPlant(Supplier<? extends Block> plant, Supplier<? extends Block> potted) {
    }

    public record CreativeTabEntries(ResourceKey<CreativeModeTab> tab, Consumer<TabOutput> populate) {
    }

    public record VillagerBiomeType(ResourceKey<Biome> biome, ResourceKey<VillagerType> villagerType) {
    }

    public enum TabVisibility {
        PARENT_AND_SEARCH_TABS,
        PARENT_TAB_ONLY,
        SEARCH_TAB_ONLY
    }

    public interface TabOutput {

        void accept(ItemStack stack, TabVisibility visibility);

        void insertAfter(ItemStack anchor, ItemStack stack, TabVisibility visibility);

        void insertBefore(ItemStack anchor, ItemStack stack, TabVisibility visibility);
    }
}
