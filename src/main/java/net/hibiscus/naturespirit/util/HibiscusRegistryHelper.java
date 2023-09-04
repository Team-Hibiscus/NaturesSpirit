package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.HashMap;

public class HibiscusRegistryHelper {

   public static HashMap <String, Block[]> WoodHashMap = new HashMap <>();
   public static HashMap <String, Block[]> SaplingHashMap = new HashMap <>();
   public static HashMap <String, Block> LeavesHashMap = new HashMap <>();
   public static HashMap <String, Block> RenderLayerHashMap = new HashMap <>();

   private static Boolean canSpawnUponLeaves(BlockState state, BlockView world, BlockPos pos, EntityType <?> type) {
      return type == EntityType.OCELOT || type == EntityType.PARROT;
   }

   private static boolean never(BlockState state, BlockView world, BlockPos pos) {
      return false;
   }


   public static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType <?> type) {
      return false;
   }

   public static Block[] registerWoodBlocks(String name, MapColor topMaterialColor, MapColor sideMaterialColor, Block buttonPlacement, Block logPlacement, Block[] signPlacement, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[17];
      Array[0] = registerBlock(name + "_wood",
              new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(sideMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[1] = registerBlock("stripped_" + name + "_wood",
              new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(topMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[2] = registerBlock(name + "_log",
              new PillarBlock(FabricBlockSettings
                      .create()
                      .burnable()
                      .instrument(Instrument.BASS)
                      .mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor)
                      .strength(2.0F)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[3] = registerBlock("stripped_" + name + "_log",
              new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(topMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[4] = registerBlock(name + "_planks",
              new Block(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(topMaterialColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[5] = registerBlock(name + "_stairs", new StairsBlock(Array[4].getDefaultState(), FabricBlockSettings.copy(Array[4])), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[6] = registerBlock(name + "_slab",
              new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASS).mapColor(topMaterialColor).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[7] = registerBlock(name + "_door", new DoorBlock(FabricBlockSettings.copy(Array[4]).nonOpaque(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[8] = registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.create().mapColor(topMaterialColor).burnable().instrument(Instrument.BASS).strength(3.0f).sounds(
              BlockSoundGroup.WOOD).allowsSpawning(HibiscusRegistryHelper::never).nonOpaque(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[9] = registerBlock(name + "_fence", new FenceBlock(FabricBlockSettings.copy(Array[4]).nonOpaque().solid()), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[10] = registerBlock(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Array[4]).nonOpaque().solid(), woodType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[11] = registerBlock(
              name + "_pressure_plate",
              new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                      FabricBlockSettings.create().solid().mapColor(topMaterialColor).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD),
                      blockSetType
              ),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[12] = registerBlock(name + "_button",
              new ButtonBlock(FabricBlockSettings.create().mapColor(topMaterialColor).strength(0.5F).noCollision().sounds(BlockSoundGroup.WOOD), blockSetType, 30, true),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[13] = registerBlock(name + "_sign",
              new SignBlock(FabricBlockSettings.create().mapColor(topMaterialColor).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable(), woodType)
      );
      Array[14] = registerBlock(name + "_wall_sign",
              new WallSignBlock(FabricBlockSettings.create().solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable().dropsLike(Array[13]), woodType)
      );
      Array[15] = registerBlock(name + "_hanging_sign",
              new HangingSignBlock(FabricBlockSettings
                      .create()
                      .mapColor(topMaterialColor)
                      .solid()
                      .instrument(Instrument.BASS)
                      .noCollision()
                      .strength(1.0F)
                      .burnable()
                      .sounds(BlockSoundGroup.HANGING_SIGN), woodType)
      );
      Array[16] = registerBlock(name + "_wall_hanging_sign",
              new WallHangingSignBlock(FabricBlockSettings
                      .create()
                      .mapColor(topMaterialColor)
                      .solid()
                      .instrument(Instrument.BASS)
                      .noCollision()
                      .strength(1.0F)
                      .burnable()
                      .sounds(BlockSoundGroup.HANGING_SIGN)
                      .dropsLike(Array[15]), woodType)
      );

      registerItem(name + "_sign", new SignItem(new FabricItemSettings().maxCount(16), Array[13], Array[14]), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      registerItem(name + "_hanging_sign", new HangingSignItem(Array[15], Array[16], new FabricItemSettings().maxCount(16)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

      ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(buttonPlacement,
              Array[2],
              Array[0],
              Array[3],
              Array[1],
              Array[4],
              Array[5],
              Array[6],
              Array[9],
              Array[10],
              Array[7],
              Array[8],
              Array[11],
              Array[12]
      ));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(logPlacement, Array[2]));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(signPlacement[15], Array[13].asItem(), Array[15].asItem()));

      RenderLayerHashMap.put(name + "_door", Array[7]);
      RenderLayerHashMap.put(name + "_trapdoor", Array[8]);

      StrippableBlockRegistry.register(Array[0], Array[1]);
      StrippableBlockRegistry.register(Array[2], Array[3]);

      FlammableBlockRegistry.getDefaultInstance().add(Array[0], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[1], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[2], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[3], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[4], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[5], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[6], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[9], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[10], 5, 20);

      FuelRegistry.INSTANCE.add(Array[9], 300);
      FuelRegistry.INSTANCE.add(Array[10], 300);
      WoodHashMap.put(name, Array);


      return Array;
   }

   public static Block[] registerJoshuaWoodBlocks(String name, MapColor topMaterialColor, Block buttonPlacement, Block logPlacement, Block[] signPlacement, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[15];

      Array[0] = registerBlock(name + "_log",
              new JoshuaTrunkBlock(FabricBlockSettings.create().burnable().mapColor(MapColor.GRAY).instrument(Instrument.BASS).requiresTool().strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[1] = registerBlock("stripped_" + name + "_log",
              new JoshuaTrunkBlock(FabricBlockSettings.create().burnable().mapColor(MapColor.GRAY).instrument(Instrument.BASS).requiresTool().strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[2] = registerBlock(name + "_planks",
              new Block(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(topMaterialColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[3] = registerBlock(name + "_stairs", new StairsBlock(Array[2].getDefaultState(), FabricBlockSettings.copy(Array[2])), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[4] = registerBlock(name + "_slab",
              new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASS).mapColor(topMaterialColor).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[5] = registerBlock(name + "_door", new DoorBlock(FabricBlockSettings.copy(Array[2]).nonOpaque(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[6] = registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.create().mapColor(topMaterialColor).burnable().instrument(Instrument.BASS).strength(3.0f).sounds(
              BlockSoundGroup.WOOD).allowsSpawning(HibiscusRegistryHelper::never).nonOpaque(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[7] = registerBlock(name + "_fence", new FenceBlock(FabricBlockSettings.copy(Array[2]).nonOpaque().solid()), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[8] = registerBlock(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Array[2]).nonOpaque().solid(), woodType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[9] = registerBlock(
              name + "_pressure_plate",
              new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                      FabricBlockSettings.create().solid().mapColor(topMaterialColor).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD),
                      blockSetType
              ),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[10] = registerBlock(name + "_button",
              new ButtonBlock(FabricBlockSettings.create().mapColor(topMaterialColor).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD), blockSetType, 30, true),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[11] = registerBlock(name + "_sign",
              new SignBlock(FabricBlockSettings.create().mapColor(topMaterialColor).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable(), woodType)
      );
      Array[12] = registerBlock(name + "_wall_sign",
              new WallSignBlock(FabricBlockSettings.create().solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable().dropsLike(Array[11]), woodType)
      );
      Array[13] = registerBlock(name + "_hanging_sign",
              new HangingSignBlock(FabricBlockSettings
                      .create()
                      .mapColor(topMaterialColor)
                      .solid()
                      .instrument(Instrument.BASS)
                      .noCollision()
                      .strength(1.0F)
                      .burnable()
                      .sounds(BlockSoundGroup.HANGING_SIGN), woodType)
      );
      Array[14] = registerBlock(name + "_wall_hanging_sign",
              new WallHangingSignBlock(FabricBlockSettings
                      .create()
                      .mapColor(topMaterialColor)
                      .solid()
                      .instrument(Instrument.BASS)
                      .noCollision()
                      .strength(1.0F)
                      .burnable()
                      .sounds(BlockSoundGroup.HANGING_SIGN)
                      .dropsLike(Array[13]), woodType)
      );

      registerItem(name + "_sign", new SignItem(new FabricItemSettings().maxCount(16), Array[11], Array[12]), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      registerItem(name + "_hanging_sign", new HangingSignItem(Array[13], Array[14], new FabricItemSettings().maxCount(16)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

      ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(buttonPlacement,
              Array[0],
              Array[1],
              Array[2],
              Array[3],
              Array[4],
              Array[7],
              Array[8],
              Array[5],
              Array[6],
              Array[9],
              Array[10]
      ));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(logPlacement, Array[2]));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(signPlacement[15], Array[11].asItem(), Array[13].asItem()));

      RenderLayerHashMap.put(name + "_door", Array[5]);
      RenderLayerHashMap.put(name + "_trapdoor", Array[6]);

      FlammableBlockRegistry.getDefaultInstance().add(Array[0], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[1], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[3], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[4], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[5], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[7], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[8], 5, 20);

      FuelRegistry.INSTANCE.add(Array[7], 300);
      FuelRegistry.INSTANCE.add(Array[8], 300);

      return Array;
   }


   public static Block registerLeafBlock(String name, MapColor color, Block blockBefore) {
      Block Leaves = registerBlock(
              name + "_leaves",
              new LeavesBlock(FabricBlockSettings
                      .create()
                      .strength(0.2F)
                      .ticksRandomly()
                      .nonOpaque()
                      .sounds(BlockSoundGroup.AZALEA_LEAVES)
                      .mapColor(color)
                      .burnable()
                      .allowsSpawning(HibiscusRegistryHelper::canSpawnUponLeaves)
                      .suffocates(HibiscusRegistryHelper::never)
                      .blockVision(HibiscusRegistryHelper::never)
                      .pistonBehavior(PistonBehavior.DESTROY)
                      .solidBlock(HibiscusRegistryHelper::never)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      RenderLayerHashMap.put(name + "_leaves", Leaves);
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, Leaves.asItem()));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block registerMapleLeafBlock(String name, MapColor color, ParticleEffect particle, Block blockBefore) {
      Block Leaves = registerBlock(name + "_leaves",
              new MapleLeavesBlock(FabricBlockSettings
                      .create()
                      .strength(0.2F)
                      .ticksRandomly()
                      .nonOpaque()
                      .sounds(BlockSoundGroup.AZALEA_LEAVES)
                      .mapColor(color)
                      .burnable()
                      .allowsSpawning(HibiscusRegistryHelper::canSpawnUponLeaves)
                      .suffocates(HibiscusRegistryHelper::never)
                      .blockVision(HibiscusRegistryHelper::never)
                      .pistonBehavior(PistonBehavior.DESTROY)
                      .solidBlock(HibiscusRegistryHelper::never), particle),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      RenderLayerHashMap.put(name + "_leaves", Leaves);
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, Leaves.asItem()));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block registerLeafBlock(String name, MapColor color, Block blockBefore, boolean bl) {
      Block Leaves;
      if(bl) {
         Leaves = registerBlock(
                 name + "_leaves",
                 new WisteriaLeaves(FabricBlockSettings
                         .create()
                         .mapColor(color)
                         .strength(0.2F)
                         .ticksRandomly()
                         .sounds(BlockSoundGroup.GRASS)
                         .nonOpaque()
                         .allowsSpawning(HibiscusRegistryHelper::canSpawnUponLeaves)
                         .suffocates(HibiscusRegistryHelper::never)
                         .blockVision(HibiscusRegistryHelper::never)
                         .burnable()
                         .pistonBehavior(PistonBehavior.DESTROY)
                         .solidBlock(HibiscusRegistryHelper::never)),
                 HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
         );
      }
      else {
         Leaves = registerBlock(name + "_leaves", new WillowLeaves(FabricBlockSettings
                 .create()
                 .mapColor(color)
                 .strength(0.2F)
                 .ticksRandomly()
                 .sounds(BlockSoundGroup.GRASS)
                 .nonOpaque()
                 .allowsSpawning(HibiscusRegistryHelper::canSpawnUponLeaves)
                 .suffocates(HibiscusRegistryHelper::never)
                 .blockVision(HibiscusRegistryHelper::never)
                 .burnable()
                 .pistonBehavior(PistonBehavior.DESTROY)
                 .solidBlock(HibiscusRegistryHelper::never)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      }
      RenderLayerHashMap.put(name + "_leaves", Leaves);
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, Leaves.asItem()));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block[] registerSapling(String name, SaplingGenerator saplingGenerator, Block blockBefore) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      RenderLayerHashMap.put(name + "_sapling", Plant[0]);
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, Plant[0].asItem()));
      SaplingHashMap.put(name, Plant);
      return Plant;
   }

   public static Block[] registerJoshuaSapling(String name, SaplingGenerator saplingGenerator, Block blockBefore) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling", new JoshuaSapling(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      RenderLayerHashMap.put(name + "_sapling", Plant[0]);
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, Plant[0].asItem()));
      SaplingHashMap.put(name, Plant);
      return Plant;
   }

   public static Block registerPottedPlant(String name, Block plant) {
      Block pottedPlant = registerBlock("potted_" + name, new FlowerPotBlock(plant, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
      RenderLayerHashMap.put("potted_" + name, pottedPlant);
      return pottedPlant;
   }

   public static Block registerBlock(String name, Block block) {
      return Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
   }

   public static Block registerBlock(String name, Block block, RegistryKey <ItemGroup> tab) {
      registerBlockItem(name, block, tab);
      return registerBlock(name, block);
   }

   public static Block registerBlock(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore, RegistryKey <ItemGroup> secondaryTab) {
      Block block1 = registerBlock(name, block, tab);
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, block1.asItem()));
      return block1;
   }

   public static Block registerSecondaryDoorBlock(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore) {
      Block block1 = Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
      RenderLayerHashMap.put(name, block1);
      registerBlockItem(name, block, tab, blockBefore, ItemGroups.BUILDING_BLOCKS);
      return block1;
   }

   public static Block registerPlantBlock(String name, Block block) {
      Block Plant = registerBlock(name, block);
      RenderLayerHashMap.put(name, block);
      return Plant;
   }

   public static Block registerPlantBlock(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore, float compost) {
      Block Plant = registerBlock(name, block, tab, blockBefore, ItemGroups.NATURAL);
      RenderLayerHashMap.put(name, block);
      CompostingChanceRegistry.INSTANCE.add(block, compost);
      return Plant;
   }

   public static Block registerPlantBlockWithoutItem(String name, Block block, float compost) {
      Block Plant = registerBlock(name, block);
      RenderLayerHashMap.put(name, block);
      return Plant;
   }

   public static Item registerBlockItem(String name, Block block, RegistryKey <ItemGroup> tab) {
      return registerItem(name, new BlockItem(block, new FabricItemSettings()), tab);
   }

   public static Item registerBlockItem(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore, RegistryKey <ItemGroup> secondaryTab) {
      Item item = registerItem(name, new BlockItem(block, new FabricItemSettings()));
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
      return item;
   }

   public static Item registerItem(String name, Item item) {
      return Registry.register(Registries.ITEM, new Identifier(NatureSpirit.MOD_ID, name), item);
   }

   public static Item registerItem(String name, Item item, RegistryKey <ItemGroup> tab) {
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item.asItem()));
      return registerItem(name, item);
   }

   public static Item registerItem(String name, Item item, RegistryKey <ItemGroup> tab, Item itemBefore, RegistryKey <ItemGroup> secondaryTab) {
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(itemBefore, item));
      return registerItem(name, item, tab);
   }

   public static Item registerPlantItem(String name, Item item, RegistryKey <ItemGroup> tab, Item itemBefore, RegistryKey <ItemGroup> secondaryTab, float compost) {
      CompostingChanceRegistry.INSTANCE.add(item, compost);
      return registerItem(name, item, tab, itemBefore, secondaryTab);
   }
}
