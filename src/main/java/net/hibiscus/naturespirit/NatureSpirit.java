package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.lithostitched.NSRegions;
import net.hibiscus.naturespirit.registration.NSDataComponents;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.compat.NSArtsAndCraftsCompat;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.hibiscus.naturespirit.blocks.NSCauldronBehavior;
import net.hibiscus.naturespirit.registration.NSVillagers;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Mod(NatureSpirit.MOD_ID)
public class NatureSpirit {

    public static final String MOD_ID = "natures_spirit";
    public static final ResourceKey<Registry<PizzaToppingVariant>> PIZZA_TOPPING_VARIANT = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "pizza_topping_variant"));

    public NatureSpirit() {
        IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        modEventBus.addListener(this::commonSetup);
        NSBlocks.BLOCKS.register(modEventBus);
        NSDataComponents.DATA_COMPONENTS.register(modEventBus);
        NSBlocks.ITEMS.register(modEventBus);
        NSBlocks.BLOCK_ENTITIES.register(modEventBus);
        NSEntityTypes.ENTITIES.register(modEventBus);
        NSItemGroups.CREATIVE_MODE_TABS.register(modEventBus);
        NSParticleTypes.PARTICLES.register(modEventBus);
        NSStatTypes.CUSTOM_STATS.register(modEventBus);
        NSSounds.SOUND_EVENTS.register(modEventBus);
        NSWorldGen.FOLIAGE_PLACERS.register(modEventBus);
        NSWorldGen.TREE_DECORATORS.register(modEventBus);
        NSWorldGen.TRUNK_PLACERS.register(modEventBus);
        NSWorldGen.CARVERS.register(modEventBus);
        NSWorldGen.FEATURES.register(modEventBus);
        NSVillagers.VILLAGER_TYPES.register(modEventBus);
        modEventBus.addListener(this::creativeInventory);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::blockTypes);
        modEventBus.addListener(this::datapackRegistries);
        NSCriteria.CRITERIA.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::wandererTrades);
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, NSConfig.SPEC);


        if(ModList.get().isLoaded("arts_and_crafts")) {
            NSArtsAndCraftsCompat.ANC_BLOCKS.register(modEventBus);
            NSArtsAndCraftsCompat.ANC_ITEMS.register(modEventBus);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NSRegions.init();
        //Regions.register(new TerraFeraxRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_ferax"), NSConfig.terraFeraxWeight));
        //Regions.register(new TerraSolarisRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_solaris"), NSConfig.terraSolarisWeight));
        //Regions.register(new TerraFlavaRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_flava"), NSConfig.terraFlavaWeight));
        //Regions.register(new TerraMaterRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_mater"), NSConfig.terraMaterWeight));
        //Regions.register(new TerraLaetaRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_laeta"), NSConfig.terraLaetaWeight));
        //SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());

        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SCORCHED_GRASS.getId(), NSBlocks.POTTED_SCORCHED_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BEACH_GRASS.getId(), NSBlocks.POTTED_BEACH_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SEDGE_GRASS.getId(), NSBlocks.POTTED_SEDGE_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FLAXEN_FERN.getId(), NSBlocks.POTTED_FLAXEN_FERN);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.OAT_GRASS.getId(), NSBlocks.POTTED_OAT_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MELIC_GRASS.getId(), NSBlocks.POTTED_MELIC_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.LUSH_FERN.getId(), NSBlocks.POTTED_LUSH_FERN);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FRIGID_GRASS.getId(), NSBlocks.POTTED_FRIGID_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.GREEN_BEARBERRIES.getId(), NSBlocks.POTTED_GREEN_BEARBERRIES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.RED_BEARBERRIES.getId(), NSBlocks.POTTED_RED_BEARBERRIES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PURPLE_BEARBERRIES.getId(), NSBlocks.POTTED_PURPLE_BEARBERRIES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.ORNATE_SUCCULENT.getId(), NSBlocks.POTTED_ORNATE_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.DROWSY_SUCCULENT.getId(), NSBlocks.POTTED_DROWSY_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.AUREATE_SUCCULENT.getId(), NSBlocks.POTTED_AUREATE_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SAGE_SUCCULENT.getId(), NSBlocks.POTTED_SAGE_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FOAMY_SUCCULENT.getId(), NSBlocks.POTTED_FOAMY_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.IMPERIAL_SUCCULENT.getId(), NSBlocks.POTTED_IMPERIAL_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.REGAL_SUCCULENT.getId(), NSBlocks.POTTED_REGAL_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SHIITAKE_MUSHROOM.getId(), NSBlocks.POTTED_SHIITAKE_MUSHROOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MARIGOLD.getFlowerBlock().getId(), NSBlocks.MARIGOLD.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BLUEBELL.getFlowerBlock().getId(), NSBlocks.BLUEBELL.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.TIGER_LILY.getFlowerBlock().getId(), NSBlocks.TIGER_LILY.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().getId(), NSBlocks.PURPLE_WILDFLOWER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().getId(), NSBlocks.YELLOW_WILDFLOWER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.RED_HEATHER.getFlowerBlock().getId(), NSBlocks.RED_HEATHER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WHITE_HEATHER.getFlowerBlock().getId(), NSBlocks.WHITE_HEATHER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PURPLE_HEATHER.getFlowerBlock().getId(), NSBlocks.PURPLE_HEATHER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.ANEMONE.getFlowerBlock().getId(), NSBlocks.ANEMONE.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().getId(), NSBlocks.DWARF_BLOSSOMS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PROTEA.getFlowerBlock().getId(), NSBlocks.PROTEA.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.HIBISCUS.getFlowerBlock().getId(), NSBlocks.HIBISCUS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BLUE_IRIS.getFlowerBlock().getId(), NSBlocks.BLUE_IRIS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BLACK_IRIS.getFlowerBlock().getId(), NSBlocks.BLACK_IRIS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().getId(), NSBlocks.RUBY_BLOSSOMS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.REDWOOD.getSapling().getId(), NSBlocks.REDWOOD.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SUGI.getSapling().getId(), NSBlocks.SUGI.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getPurpleSapling().getId(), NSBlocks.WISTERIA.getPottedPurpleSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getWhiteSapling().getId(), NSBlocks.WISTERIA.getPottedWhiteSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getBlueSapling().getId(), NSBlocks.WISTERIA.getPottedBlueSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getPinkSapling().getId(), NSBlocks.WISTERIA.getPottedPinkSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FIR.getSapling().getId(), NSBlocks.FIR.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WILLOW.getSapling().getId(), NSBlocks.WILLOW.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.ASPEN.getSapling().getId(), NSBlocks.ASPEN.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAPLE.getRedSapling().getId(), NSBlocks.MAPLE.getPottedRedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAPLE.getOrangeSapling().getId(), NSBlocks.MAPLE.getPottedOrangeSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAPLE.getYellowSapling().getId(), NSBlocks.MAPLE.getPottedYellowSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.CYPRESS.getSapling().getId(), NSBlocks.CYPRESS.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.OLIVE.getSapling().getId(), NSBlocks.OLIVE.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.JOSHUA.getSapling().getId(), NSBlocks.JOSHUA.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.GHAF.getSapling().getId(), NSBlocks.GHAF.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PALO_VERDE.getSapling().getId(), NSBlocks.PALO_VERDE.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.CEDAR.getSapling().getId(), NSBlocks.CEDAR.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.LARCH.getSapling().getId(), NSBlocks.LARCH.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAHOGANY.getSapling().getId(), NSBlocks.MAHOGANY.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SAXAUL.getSapling().getId(), NSBlocks.SAXAUL.getPottedSapling());
            NSCauldronBehavior.registerBehavior();

            HashMap<Block, Block> strippables = new HashMap<>(AxeItem.STRIPPABLES);
            strippables.put(NSBlocks.REDWOOD.getLog().get(), NSBlocks.REDWOOD.getStrippedLog().get());
            strippables.put(NSBlocks.SUGI.getLog().get(), NSBlocks.SUGI.getStrippedLog().get());
            strippables.put(NSBlocks.WISTERIA.getLog().get(), NSBlocks.WISTERIA.getStrippedLog().get());
            strippables.put(NSBlocks.FIR.getLog().get(), NSBlocks.FIR.getStrippedLog().get());
            strippables.put(NSBlocks.WILLOW.getLog().get(), NSBlocks.WILLOW.getStrippedLog().get());
            strippables.put(NSBlocks.ASPEN.getLog().get(), NSBlocks.ASPEN.getStrippedLog().get());
            strippables.put(NSBlocks.MAPLE.getLog().get(), NSBlocks.MAPLE.getStrippedLog().get());
            strippables.put(NSBlocks.CYPRESS.getLog().get(), NSBlocks.CYPRESS.getStrippedLog().get());
            strippables.put(NSBlocks.OLIVE.getLog().get(), NSBlocks.OLIVE.getStrippedLog().get());
            strippables.put(NSBlocks.GHAF.getLog().get(), NSBlocks.GHAF.getStrippedLog().get());
            strippables.put(NSBlocks.PALO_VERDE.getLog().get(), NSBlocks.PALO_VERDE.getStrippedLog().get());
            strippables.put(NSBlocks.CEDAR.getLog().get(), NSBlocks.CEDAR.getStrippedLog().get());
            strippables.put(NSBlocks.COCONUT.getLog().get(), NSBlocks.COCONUT.getStrippedLog().get());
            strippables.put(NSBlocks.LARCH.getLog().get(), NSBlocks.LARCH.getStrippedLog().get());
            strippables.put(NSBlocks.MAHOGANY.getLog().get(), NSBlocks.MAHOGANY.getStrippedLog().get());
            strippables.put(NSBlocks.SAXAUL.getLog().get(), NSBlocks.SAXAUL.getStrippedLog().get());
            strippables.put(NSBlocks.REDWOOD.getWood().get(), NSBlocks.REDWOOD.getStrippedWood().get());
            strippables.put(NSBlocks.SUGI.getWood().get(), NSBlocks.SUGI.getStrippedWood().get());
            strippables.put(NSBlocks.WISTERIA.getWood().get(), NSBlocks.WISTERIA.getStrippedWood().get());
            strippables.put(NSBlocks.FIR.getWood().get(), NSBlocks.FIR.getStrippedWood().get());
            strippables.put(NSBlocks.WILLOW.getWood().get(), NSBlocks.WILLOW.getStrippedWood().get());
            strippables.put(NSBlocks.ASPEN.getWood().get(), NSBlocks.ASPEN.getStrippedWood().get());
            strippables.put(NSBlocks.MAPLE.getWood().get(), NSBlocks.MAPLE.getStrippedWood().get());
            strippables.put(NSBlocks.CYPRESS.getWood().get(), NSBlocks.CYPRESS.getStrippedWood().get());
            strippables.put(NSBlocks.OLIVE.getWood().get(), NSBlocks.OLIVE.getStrippedWood().get());
            strippables.put(NSBlocks.GHAF.getWood().get(), NSBlocks.GHAF.getStrippedWood().get());
            strippables.put(NSBlocks.PALO_VERDE.getWood().get(), NSBlocks.PALO_VERDE.getStrippedWood().get());
            strippables.put(NSBlocks.CEDAR.getWood().get(), NSBlocks.CEDAR.getStrippedWood().get());
            strippables.put(NSBlocks.COCONUT.getWood().get(), NSBlocks.COCONUT.getStrippedWood().get());
            strippables.put(NSBlocks.LARCH.getWood().get(), NSBlocks.LARCH.getStrippedWood().get());
            strippables.put(NSBlocks.MAHOGANY.getWood().get(), NSBlocks.MAHOGANY.getStrippedWood().get());
            strippables.put(NSBlocks.SAXAUL.getWood().get(), NSBlocks.SAXAUL.getStrippedWood().get());
            strippables.put(NSBlocks.ALLUAUDIA_BUNDLE.get(), NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE.get());
            strippables.put(NSBlocks.JOSHUA.getBundle().get(), NSBlocks.JOSHUA.getStrippedBundle().get());
            AxeItem.STRIPPABLES = strippables;
        });
    }

    public void datapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                PIZZA_TOPPING_VARIANT,
                PizzaToppingVariant.CODEC,
                PizzaToppingVariant.CODEC,
                builder -> builder.maxId(256).sync(true)
        );
    }

    public void wandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(NSBlocks.RED_MOSS_BLOCK.get(), 2), 5, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(NSBlocks.HIBISCUS.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(NSBlocks.BLUE_IRIS.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(NSBlocks.BLACK_IRIS.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(NSBlocks.ANEMONE.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(NSBlocks.LOTUS_FLOWER_ITEM.get(), 2), 12, 1, 0.5F));

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.REDWOOD.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.SUGI.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getPurpleSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getWhiteSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getBlueSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getPinkSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.FIR.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.WILLOW.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.ASPEN.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.MAPLE.getRedSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.MAPLE.getOrangeSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.MAPLE.getYellowSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.CYPRESS.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.OLIVE.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.JOSHUA.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.GHAF.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.PALO_VERDE.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.CEDAR.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.LARCH.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.MAHOGANY.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(NSBlocks.SAXAUL.getSapling().get(), 5), 8, 1, 0.5F));
    }

    public void blockTypes(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
            NSBlocks.REDWOOD.getSign().get(),
            NSBlocks.REDWOOD.getWallSign().get(),
            NSBlocks.SUGI.getSign().get(),
            NSBlocks.SUGI.getWallSign().get(),
            NSBlocks.WISTERIA.getSign().get(),
            NSBlocks.WISTERIA.getWallSign().get(),
            NSBlocks.FIR.getSign().get(),
            NSBlocks.FIR.getWallSign().get(),
            NSBlocks.WILLOW.getSign().get(),
            NSBlocks.WILLOW.getWallSign().get(),
            NSBlocks.ASPEN.getSign().get(),
            NSBlocks.ASPEN.getWallSign().get(),
            NSBlocks.MAPLE.getSign().get(),
            NSBlocks.MAPLE.getWallSign().get(),
            NSBlocks.CYPRESS.getSign().get(),
            NSBlocks.CYPRESS.getWallSign().get(),
            NSBlocks.OLIVE.getSign().get(),
            NSBlocks.OLIVE.getWallSign().get(),
            NSBlocks.JOSHUA.getSign().get(),
            NSBlocks.JOSHUA.getWallSign().get(),
            NSBlocks.GHAF.getSign().get(),
            NSBlocks.GHAF.getWallSign().get(),
            NSBlocks.PALO_VERDE.getSign().get(),
            NSBlocks.PALO_VERDE.getWallSign().get(),
            NSBlocks.COCONUT.getSign().get(),
            NSBlocks.COCONUT.getWallSign().get(),
            NSBlocks.CEDAR.getSign().get(),
            NSBlocks.CEDAR.getWallSign().get(),
            NSBlocks.LARCH.getSign().get(),
            NSBlocks.LARCH.getWallSign().get(),
            NSBlocks.MAHOGANY.getSign().get(),
            NSBlocks.MAHOGANY.getWallSign().get(),
            NSBlocks.SAXAUL.getSign().get(),
            NSBlocks.SAXAUL.getWallSign().get(),
            NSBlocks.PAPER_SIGN.get(),
            NSBlocks.PAPER_WALL_SIGN.get()
        );
        event.modify(BlockEntityType.HANGING_SIGN,
                NSBlocks.REDWOOD.getHangingSign().get(),
                NSBlocks.REDWOOD.getHangingWallSign().get(),
                NSBlocks.SUGI.getHangingSign().get(),
                NSBlocks.SUGI.getHangingWallSign().get(),
                NSBlocks.WISTERIA.getHangingSign().get(),
                NSBlocks.WISTERIA.getHangingWallSign().get(),
                NSBlocks.FIR.getHangingSign().get(),
                NSBlocks.FIR.getHangingWallSign().get(),
                NSBlocks.WILLOW.getHangingSign().get(),
                NSBlocks.WILLOW.getHangingWallSign().get(),
                NSBlocks.ASPEN.getHangingSign().get(),
                NSBlocks.ASPEN.getHangingWallSign().get(),
                NSBlocks.MAPLE.getHangingSign().get(),
                NSBlocks.MAPLE.getHangingWallSign().get(),
                NSBlocks.CYPRESS.getHangingSign().get(),
                NSBlocks.CYPRESS.getHangingWallSign().get(),
                NSBlocks.OLIVE.getHangingSign().get(),
                NSBlocks.OLIVE.getHangingWallSign().get(),
                NSBlocks.JOSHUA.getHangingSign().get(),
                NSBlocks.JOSHUA.getHangingWallSign().get(),
                NSBlocks.GHAF.getHangingSign().get(),
                NSBlocks.GHAF.getHangingWallSign().get(),
                NSBlocks.PALO_VERDE.getHangingSign().get(),
                NSBlocks.PALO_VERDE.getHangingWallSign().get(),
                NSBlocks.COCONUT.getHangingSign().get(),
                NSBlocks.COCONUT.getHangingWallSign().get(),
                NSBlocks.CEDAR.getHangingSign().get(),
                NSBlocks.CEDAR.getHangingWallSign().get(),
                NSBlocks.LARCH.getHangingSign().get(),
                NSBlocks.LARCH.getHangingWallSign().get(),
                NSBlocks.MAHOGANY.getHangingSign().get(),
                NSBlocks.MAHOGANY.getHangingWallSign().get(),
                NSBlocks.SAXAUL.getHangingSign().get(),
                NSBlocks.SAXAUL.getHangingWallSign().get(),
                NSBlocks.PAPER_HANGING_SIGN.get(),
                NSBlocks.PAPER_WALL_HANGING_SIGN.get()
        );
    }

    public void creativeInventory(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            ArrayList<ItemLike> wood = new ArrayList<>();
            wood.add(Items.BAMBOO_BUTTON);
            for(WoodSet woodset: NSBlocks.getWoodSets()) {
                wood.add(woodset.getLog().get());
                if (woodset.getWoodPreset() == WoodSet.WoodPreset.JOSHUA) {
                    wood.addAll(List.of(woodset.getBundle().get(), woodset.getStrippedLog().get(), woodset.getStrippedBundle().get(), woodset.getPlanks().get()));
                } else if (!woodset.hasBark()) {
                    wood.addAll(List.of(woodset.getStrippedLog().get(), woodset.getPlanks().get()));
                } else {
                    wood.addAll(List.of(woodset.getWood().get(), woodset.getStrippedLog().get(), woodset.getStrippedWood().get(), woodset.getPlanks().get()));
                }
                if (woodset.hasMosaic()) {
                    wood.addAll(List.of(woodset.getMosaic().get(), woodset.getStairs().get(), woodset.getMosaicStairs().get(), woodset.getSlab().get(), woodset.getMosaicSlab().get(),
                            woodset.getFence().get(), woodset.getFenceGate().get(),
                            woodset.getDoor().get(), woodset.getTrapDoor().get(),
                            woodset.getPressurePlate().get(), woodset.getButton().get()));
                } else {
                    wood.addAll(List.of(woodset.getStairs().get(), woodset.getSlab().get(),
                            woodset.getFence().get(), woodset.getFenceGate().get(),
                            woodset.getDoor().get(), woodset.getTrapDoor().get(),
                            woodset.getPressurePlate().get(), woodset.getButton().get()));
                }
            }
            addAllAfterFirst(event, wood, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBaseStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBaseStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBaseSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBaseSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getCobbled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getCobbledStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getCobbledSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getCobbledWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbledStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbledSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbledWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getChiseled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getChiseled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolished().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getPolished().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getPolishedStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getPolishedSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getPolishedWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCrackedBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getCrackedBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getBricksWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getMossyBricksWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getTiles().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCrackedTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getCrackedTiles().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getTilesStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getTilesSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TRAVERTINE.getTilesWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBaseStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBaseStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBaseSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBaseSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getChiseled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getChiseled().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolished().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getPolished().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getPolishedStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getPolishedSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getPolishedWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBricks().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getCrackedBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getCrackedBricks().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getBricksWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getTiles().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getCrackedTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getCrackedTiles().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getTilesStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHERT.getTilesSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_WALL.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_SANDSTONE_WALL.get().asItem().getDefaultInstance(), NSBlocks.CHISELED_PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CHISELED_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SMOOTH_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SMOOTH_PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SMOOTH_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CUT_PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CUT_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.CUT_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(Items.CHERRY_LOG.getDefaultInstance(), NSBlocks.REDWOOD.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.REDWOOD.getLog().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.SUGI.getLog().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.WISTERIA.getLog().get().asItem().getDefaultInstance(), NSBlocks.FIR.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.FIR.getLog().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.WILLOW.getLog().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.ASPEN.getLog().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.MAPLE.getLog().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.CYPRESS.getLog().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.OLIVE.getLog().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.JOSHUA.getLog().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.GHAF.getLog().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.PALO_VERDE.getLog().get().asItem().getDefaultInstance(), NSBlocks.COCONUT.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.COCONUT.getLog().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.CEDAR.getLog().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.LARCH.getLog().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.MAHOGANY.getLog().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

            event.insertAfter(Items.GOLD_ORE.getDefaultInstance(), NSBlocks.CHERT_GOLD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.IRON_ORE.getDefaultInstance(), NSBlocks.CHERT_IRON_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.COAL_ORE.getDefaultInstance(), NSBlocks.CHERT_COAL_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.LAPIS_ORE.getDefaultInstance(), NSBlocks.CHERT_LAPIS_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.DIAMOND_ORE.getDefaultInstance(), NSBlocks.CHERT_DIAMOND_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.REDSTONE_ORE.getDefaultInstance(), NSBlocks.CHERT_REDSTONE_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.EMERALD_ORE.getDefaultInstance(), NSBlocks.CHERT_EMERALD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.COPPER_ORE.getDefaultInstance(), NSBlocks.CHERT_COPPER_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.ANDESITE.getDefaultInstance(), NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

            event.insertAfter(Items.RED_SANDSTONE.getDefaultInstance(), NSBlocks.PINK_SAND.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_SAND.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

            event.insertAfter(Items.BAMBOO.getDefaultInstance(), NSBlocks.OLIVE_BRANCH.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SWEET_BERRIES.getDefaultInstance(), NSBlocks.COCONUT_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.RED_MUSHROOM.getDefaultInstance(), NSBlocks.SHIITAKE_MUSHROOM.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SHIITAKE_MUSHROOM.get().asItem().getDefaultInstance(), NSBlocks.GRAY_POLYPORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.RED_MUSHROOM_BLOCK.getDefaultInstance(), NSBlocks.SHIITAKE_MUSHROOM_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SHIITAKE_MUSHROOM_BLOCK.get().asItem().getDefaultInstance(), NSBlocks.GRAY_POLYPORE_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.SHROOMLIGHT.getDefaultInstance(), NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get().asItem().getDefaultInstance(), NSBlocks.DESERT_TURNIP_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.VINE.getDefaultInstance(), NSBlocks.WILLOW.getVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getWhiteVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getBlueVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPinkVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CACTUS.getDefaultInstance(), NSBlocks.ALLUAUDIA.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ALLUAUDIA.get().asItem().getDefaultInstance(), NSBlocks.ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), NSBlocks.STRIPPED_ALLUAUDIA.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.STRIPPED_ALLUAUDIA.get().asItem().getDefaultInstance(), NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.LILY_PAD.getDefaultInstance(), NSBlocks.HELVOLA_PAD_ITEM.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.HELVOLA_PAD_ITEM.get().asItem().getDefaultInstance(), NSBlocks.HELVOLA_FLOWER_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.HELVOLA_FLOWER_ITEM.get().asItem().getDefaultInstance(), NSBlocks.LOTUS_FLOWER_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LOTUS_FLOWER_ITEM.get().asItem().getDefaultInstance(), NSBlocks.LOTUS_STEM.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LOTUS_STEM.get().asItem().getDefaultInstance(), NSBlocks.AZOLLA_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.FARMLAND.getDefaultInstance(), NSBlocks.SANDY_SOIL.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.MOSS_BLOCK.getDefaultInstance(), NSBlocks.RED_MOSS_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.MOSS_CARPET.getDefaultInstance(), NSBlocks.RED_MOSS_CARPET.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.PITCHER_PLANT.getDefaultInstance(), NSBlocks.ORNATE_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORNATE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.DROWSY_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.DROWSY_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.AUREATE_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.AUREATE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.SAGE_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SAGE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.FOAMY_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FOAMY_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.IMPERIAL_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.IMPERIAL_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.REGAL_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.PEONY.getDefaultInstance(), NSBlocks.LAVENDER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LAVENDER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLEEDING_HEART.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLEEDING_HEART.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUE_BULBS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_BULBS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.CARNATION.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CARNATION.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.GARDENIA.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GARDENIA.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.SNAPDRAGON.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SNAPDRAGON.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.FOXGLOVE.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FOXGLOVE.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BEGONIA.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.LILY_OF_THE_VALLEY.getDefaultInstance(), NSBlocks.MARIGOLD.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MARIGOLD.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUEBELL.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUEBELL.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.TIGER_LILY.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TIGER_LILY.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.RED_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.WHITE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PURPLE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.ANEMONE.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ANEMONE.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PROTEA.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PROTEA.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.HIBISCUS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.HIBISCUS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUE_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLACK_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.SILVERBUSH.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.LARGE_FERN.getDefaultInstance(), NSBlocks.CATTAIL.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CATTAIL.get().asItem().getDefaultInstance(), NSBlocks.TALL_FRIGID_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TALL_FRIGID_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_SCORCHED_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TALL_SCORCHED_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_BEACH_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TALL_BEACH_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_SEDGE_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TALL_SEDGE_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LARGE_FLAXEN_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARGE_FLAXEN_FERN.get().asItem().getDefaultInstance(), NSBlocks.TALL_OAT_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TALL_OAT_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LARGE_LUSH_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARGE_LUSH_FERN.get().asItem().getDefaultInstance(), NSBlocks.TALL_MELIC_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.TALL_MELIC_GRASS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_BEARBERRIES.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.RED_BEARBERRIES.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_BEARBERRIES.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.GREEN_BITTER_SPROUTS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_BITTER_SPROUTS.get().asItem().getDefaultInstance(), NSBlocks.RED_BITTER_SPROUTS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_BITTER_SPROUTS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_BITTER_SPROUTS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.FERN.getDefaultInstance(), NSBlocks.BEACH_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BEACH_GRASS.get().asItem().getDefaultInstance(), NSBlocks.SCORCHED_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SCORCHED_GRASS.get().asItem().getDefaultInstance(), NSBlocks.SEDGE_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SEDGE_GRASS.get().asItem().getDefaultInstance(), NSBlocks.FLAXEN_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FLAXEN_FERN.get().asItem().getDefaultInstance(), NSBlocks.OAT_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OAT_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LUSH_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LUSH_FERN.get().asItem().getDefaultInstance(), NSBlocks.MELIC_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MELIC_GRASS.get().asItem().getDefaultInstance(), NSBlocks.FRIGID_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CHERRY_LEAVES.getDefaultInstance(), NSBlocks.REDWOOD.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.REDWOOD.getFrostyLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getFrostyLeaves().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SUGI.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPurpleLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getWhiteLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getBlueLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPinkLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartPurpleLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPartPurpleLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartWhiteLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPartWhiteLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartBlueLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPartBlueLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartPinkLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPartPinkLeaves().get().asItem().getDefaultInstance(), NSBlocks.FIR.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.FIR.getFrostyLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getFrostyLeaves().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getYellowLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getYellowLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getRedLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getRedLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getOrangeLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getOrangeLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getYellowLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getYellowLeaves().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYPRESS.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OLIVE.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.JOSHUA.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GHAF.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PALO_VERDE.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CEDAR.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.COCONUT.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.COCONUT.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARCH.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAHOGANY.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.CHERRY_SAPLING.getDefaultInstance(), NSBlocks.REDWOOD.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getSapling().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SUGI.getSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPurpleSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getWhiteSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getBlueSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getPinkSapling().get().asItem().getDefaultInstance(), NSBlocks.FIR.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getSapling().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getSapling().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getRedSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getRedSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getOrangeSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getOrangeSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getYellowSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getYellowSapling().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYPRESS.getSapling().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OLIVE.getSapling().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.JOSHUA.getSapling().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GHAF.getSapling().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PALO_VERDE.getSapling().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CEDAR.getSapling().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARCH.getSapling().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAHOGANY.getSapling().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.insertAfter(Items.BAMBOO_HANGING_SIGN.getDefaultInstance(), NSBlocks.REDWOOD.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getSignItem().get().getDefaultInstance(), NSBlocks.REDWOOD.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getHangingSignItem().get().getDefaultInstance(), NSBlocks.SUGI.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SUGI.getSignItem().get().getDefaultInstance(), NSBlocks.SUGI.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SUGI.getHangingSignItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getSignItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getHangingSignItem().get().getDefaultInstance(), NSBlocks.FIR.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getSignItem().get().getDefaultInstance(), NSBlocks.FIR.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getHangingSignItem().get().getDefaultInstance(), NSBlocks.WILLOW.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getSignItem().get().getDefaultInstance(), NSBlocks.WILLOW.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getHangingSignItem().get().getDefaultInstance(), NSBlocks.ASPEN.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getSignItem().get().getDefaultInstance(), NSBlocks.ASPEN.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getHangingSignItem().get().getDefaultInstance(), NSBlocks.MAPLE.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getSignItem().get().getDefaultInstance(), NSBlocks.MAPLE.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYPRESS.getSignItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYPRESS.getHangingSignItem().get().getDefaultInstance(), NSBlocks.OLIVE.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OLIVE.getSignItem().get().getDefaultInstance(), NSBlocks.OLIVE.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OLIVE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.JOSHUA.getSignItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.JOSHUA.getHangingSignItem().get().getDefaultInstance(), NSBlocks.GHAF.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GHAF.getSignItem().get().getDefaultInstance(), NSBlocks.GHAF.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GHAF.getHangingSignItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PALO_VERDE.getSignItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PALO_VERDE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.COCONUT.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.COCONUT.getSignItem().get().getDefaultInstance(), NSBlocks.COCONUT.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.COCONUT.getHangingSignItem().get().getDefaultInstance(), NSBlocks.CEDAR.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CEDAR.getSignItem().get().getDefaultInstance(), NSBlocks.CEDAR.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CEDAR.getHangingSignItem().get().getDefaultInstance(), NSBlocks.LARCH.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARCH.getSignItem().get().getDefaultInstance(), NSBlocks.LARCH.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARCH.getHangingSignItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAHOGANY.getSignItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAHOGANY.getHangingSignItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SAXAUL.getSignItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.insertAfter(NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PINK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(Items.BAMBOO_CHEST_RAFT.getDefaultInstance(), NSBlocks.REDWOOD.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getBoatItem().get().getDefaultInstance(), NSBlocks.REDWOOD.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.REDWOOD.getChestBoatItem().get().getDefaultInstance(), NSBlocks.SUGI.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SUGI.getBoatItem().get().getDefaultInstance(), NSBlocks.SUGI.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SUGI.getChestBoatItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getBoatItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WISTERIA.getChestBoatItem().get().getDefaultInstance(), NSBlocks.FIR.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getBoatItem().get().getDefaultInstance(), NSBlocks.FIR.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.FIR.getChestBoatItem().get().getDefaultInstance(), NSBlocks.WILLOW.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getBoatItem().get().getDefaultInstance(), NSBlocks.WILLOW.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WILLOW.getChestBoatItem().get().getDefaultInstance(), NSBlocks.ASPEN.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getBoatItem().get().getDefaultInstance(), NSBlocks.ASPEN.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ASPEN.getChestBoatItem().get().getDefaultInstance(), NSBlocks.MAPLE.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getBoatItem().get().getDefaultInstance(), NSBlocks.MAPLE.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAPLE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYPRESS.getBoatItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYPRESS.getChestBoatItem().get().getDefaultInstance(), NSBlocks.OLIVE.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OLIVE.getBoatItem().get().getDefaultInstance(), NSBlocks.OLIVE.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.OLIVE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.JOSHUA.getBoatItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.JOSHUA.getChestBoatItem().get().getDefaultInstance(), NSBlocks.GHAF.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GHAF.getBoatItem().get().getDefaultInstance(), NSBlocks.GHAF.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GHAF.getChestBoatItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PALO_VERDE.getBoatItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PALO_VERDE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.COCONUT.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.COCONUT.getBoatItem().get().getDefaultInstance(), NSBlocks.COCONUT.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.COCONUT.getChestBoatItem().get().getDefaultInstance(), NSBlocks.CEDAR.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CEDAR.getBoatItem().get().getDefaultInstance(), NSBlocks.CEDAR.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CEDAR.getChestBoatItem().get().getDefaultInstance(), NSBlocks.LARCH.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARCH.getBoatItem().get().getDefaultInstance(), NSBlocks.LARCH.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LARCH.getChestBoatItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAHOGANY.getBoatItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAHOGANY.getChestBoatItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.SAXAUL.getBoatItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS){
            event.insertAfter(Items.PINK_TERRACOTTA.getDefaultInstance(), NSBlocks.KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_CHALK.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_CHALK.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_CHALK.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_CHALK.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_CHALK.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PINK_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            
            event.insertAfter(Items.PINK_CANDLE.getDefaultInstance(), NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PINK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


            if(ModList.get().isLoaded("arts_and_crafts")) {
                event.insertBefore(NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.insertBefore(NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.insertBefore(NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.AMETHYST_SHARD.getDefaultInstance(), NSBlocks.CALCITE_SHARD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.HONEYCOMB.getDefaultInstance(), NSBlocks.CHALK_POWDER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.BOWL.getDefaultInstance(), NSBlocks.COCONUT_SHELL.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(Items.BEETROOT.getDefaultInstance(), NSBlocks.COCONUT_HALF.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.COCONUT_HALF.get().getDefaultInstance(), NSBlocks.DESERT_TURNIP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NSBlocks.DESERT_TURNIP.get().getDefaultInstance(), NSBlocks.OLIVES.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.BREAD.getDefaultInstance(), NSBlocks.WHOLE_PIZZA.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.MILK_BUCKET.getDefaultInstance(), NSBlocks.CHEESE_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
    public static void addAllAfterFirst(BuildCreativeModeTabContentsEvent event, ArrayList<ItemLike> itemStacks, CreativeModeTab.TabVisibility tabVisibility) {
        for (int i = 1; i < itemStacks.size(); i++) {
            event.insertAfter(itemStacks.get(i-1).asItem().getDefaultInstance(), itemStacks.get(i).asItem().getDefaultInstance(), tabVisibility);
        }
    }

    public void addPackFinders(AddPackFindersEvent event) {
        if(ModList.get().isLoaded("arts_and_crafts")) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/arts_and_crafts_res"),
                    PackType.CLIENT_RESOURCES,
                    Component.translatable("pack.natures_spirit.arts_and_crafts"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/arts_and_crafts_dat"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.arts_and_crafts"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        event.addPackFinders(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/plank_consistency"),
                PackType.CLIENT_RESOURCES,
                Component.translatable("pack.natures_spirit.plank_consistency"),
                PackSource.FEATURE,
                false,
                Pack.Position.TOP
        );
        event.addPackFinders(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/emissive_ores_compatibility"),
                PackType.CLIENT_RESOURCES,
                Component.translatable("pack.natures_spirit.emissive_ores_compatibility"),
                PackSource.FEATURE,
                false,
                Pack.Position.TOP
        );
        if (NSConfig.badlandsToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_badlands"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_badlands"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.birchForestToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_birch_forest"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_birch_forest"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.darkForestToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_dark_forest"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_dark_forest"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.desertToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_desert"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_desert"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.flowerForestToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_flower_forest"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_flower_forest"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.jungleToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_jungle"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_jungle"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.mountainBiomesToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_mountain_biomes"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_mountain_biomes"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.savannaToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_savannas"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_savannas"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.swampToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_swamp"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_swamp"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.vanillaTreesToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_vanilla_trees"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_vanilla_trees"),
                    PackSource.BUILT_IN,
                    false,
                    Pack.Position.TOP
            );
        }
        if (NSConfig.windsweptHillsToggle) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/modified_windswept_hills"),
                    PackType.SERVER_DATA,
                    Component.translatable("pack.natures_spirit.modified_windswept_hills"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }

    }

}