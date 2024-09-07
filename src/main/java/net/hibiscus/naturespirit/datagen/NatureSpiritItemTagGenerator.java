package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hibiscus.naturespirit.registration.HibiscusRegistryHelper;
import net.hibiscus.naturespirit.registration.HibiscusTags;
import net.hibiscus.naturespirit.registration.sets.StoneSet;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.hibiscus.naturespirit.registration.HibiscusWoods;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.hibiscus.naturespirit.registration.HibiscusMiscBlocks.*;

public class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        for (WoodSet woodSet : HibiscusRegistryHelper.WoodHashMap.values()) {
            this.copy(woodSet.getBlockLogsTag(), woodSet.getItemLogsTag());
            getOrCreateTagBuilder(ItemTags.BOATS).add(woodSet.getBoatItem());
            getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(woodSet.getChestBoatItem());
        }

        for (StoneSet stoneSet : HibiscusRegistryHelper.StoneHashMap.values()) {
            if (stoneSet.hasCobbled()) {
                getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(stoneSet.getCobbled().asItem());
                getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(stoneSet.getCobbled().asItem());
            }
        }
        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(CHERT.getBase().asItem());
        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(CHERT.getBase().asItem());
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(HibiscusTags.Blocks.STRIPPED_LOGS, HibiscusTags.Items.STRIPPED_LOGS);
        this.copy(HibiscusTags.Blocks.ALLUAUDIA_BUNDLES, HibiscusTags.Items.ALLUAUDIA_BUNDLES);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        this.copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
        this.copy(BlockTags.SAND, ItemTags.SAND);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.COAL_ORES, ItemTags.COAL_ORES);
        this.copy(BlockTags.COPPER_ORES, ItemTags.COPPER_ORES);
        this.copy(BlockTags.DIAMOND_ORES, ItemTags.DIAMOND_ORES);
        this.copy(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES);
        this.copy(BlockTags.EMERALD_ORES, ItemTags.EMERALD_ORES);
        this.copy(BlockTags.IRON_ORES, ItemTags.IRON_ORES);
        this.copy(BlockTags.LAPIS_ORES, ItemTags.LAPIS_ORES);
        this.copy(BlockTags.REDSTONE_ORES, ItemTags.REDSTONE_ORES);
        this.getOrCreateTagBuilder(ItemTags.ARROWS).add(CHEESE_ARROW);
        this.getOrCreateTagBuilder(ItemTags.SMELTS_TO_GLASS).add(PINK_SAND.asItem());
        this.getOrCreateTagBuilder(HibiscusTags.Items.EVERGREEN_LEAVES).add(
                HibiscusWoods.FIR.getLeaves().asItem(),
                HibiscusWoods.REDWOOD.getLeaves().asItem(),
                HibiscusWoods.LARCH.getLeaves().asItem(),
                HibiscusWoods.CEDAR.getLeaves().asItem(),
                Items.SPRUCE_LEAVES
        );
        this.getOrCreateTagBuilder(HibiscusTags.Items.XERIC_LEAVES).add(
                HibiscusWoods.GHAF.getLeaves().asItem(),
                HibiscusWoods.OLIVE.getLeaves().asItem(),
                HibiscusWoods.PALO_VERDE.getLeaves().asItem(),
                HibiscusWoods.JOSHUA.getLeaves().asItem(),
                Items.ACACIA_LEAVES
        );
        this.getOrCreateTagBuilder(HibiscusTags.Items.COCONUT_ITEMS).add(
                HibiscusWoods.COCONUT_BLOCK.asItem(),
                HibiscusWoods.COCONUT_HALF,
                HibiscusWoods.COCONUT_SHELL
        );
    }
}
