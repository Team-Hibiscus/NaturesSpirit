package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
//import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.minecraft.block.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.item.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.HashMap;

public class HibiscusRegistryHelper {

   public static HashMap <String, Block[]> WoodHashMap = new HashMap <>();
   public static HashMap <String, Block[]> SaplingHashMap = new HashMap <>();
   public static HashMap <String, Block> LeavesHashMap = new HashMap <>();

   private static Boolean canSpawnUponLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType <?> type) {
      return type == EntityType.OCELOT || type == EntityType.PARROT;
   }

   private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
      return false;
   }


   public static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType <?> type) {
      return false;
   }

   public static Block[] registerWoodBlocks(String name, MapColor topMaterialColor, MapColor sideMaterialColor, Block buttonPlacement, Block logPlacement, Block[] signPlacement, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[17];
      Array[0] = registerBlock(name + "_wood",
              new RotatedPillarBlock(FabricBlockSettings.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(
                      sideMaterialColor).strength(2.0F).sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[1] = registerBlock("stripped_" + name + "_wood",
              new RotatedPillarBlock(FabricBlockSettings.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(
                      topMaterialColor).strength(2.0F).sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[2] = registerBlock(name + "_log",
              new RotatedPillarBlock(FabricBlockSettings.of()
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor)
                      .strength(2.0F)
                      .sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[3] = registerBlock("stripped_" + name + "_log",
              new RotatedPillarBlock(FabricBlockSettings.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(
                      topMaterialColor).strength(2.0F).sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[4] = registerBlock(name + "_planks",
              new Block(FabricBlockSettings.of()
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0F, 3.0F)
                      .sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[5] = registerBlock(name + "_stairs",
              new StairBlock(Array[4].defaultBlockState(), FabricBlockSettings.copy(Array[4])),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[6] = registerBlock(name + "_slab",
              new SlabBlock(FabricBlockSettings.of()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0f, 3.0f)
                      .sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[7] = registerBlock(name + "_door",
              new DoorBlock(FabricBlockSettings.copy(Array[4]).noOcclusion(), blockSetType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[8] = registerBlock(name + "_trapdoor", new TrapDoorBlock(FabricBlockSettings.of()
              .mapColor(topMaterialColor)
              .ignitedByLava()
              .instrument(NoteBlockInstrument.BASS)
              .strength(3.0f)
              .sound(SoundType.WOOD)
              .isValidSpawn(HibiscusRegistryHelper::never)
              .noOcclusion(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[9] = registerBlock(name + "_fence",
              new FenceBlock(FabricBlockSettings.copy(Array[4]).noOcclusion().forceSolidOn()),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[10] = registerBlock(name + "_fence_gate",
              new FenceGateBlock(FabricBlockSettings.copy(Array[4]).noOcclusion().forceSolidOn(), woodType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[11] = registerBlock(name + "_pressure_plate",
              new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                      FabricBlockSettings.of()
                              .forceSolidOn()
                              .mapColor(topMaterialColor)
                              .noCollission()
                              .strength(0.5f)
                              .sound(SoundType.WOOD),
                      blockSetType
              ),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[12] = registerBlock(name + "_button",
              new ButtonBlock(FabricBlockSettings.of()
                      .mapColor(topMaterialColor)
                      .strength(0.5F).noCollission()
                      .sound(SoundType.WOOD), blockSetType, 30, true),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[13] = registerBlock(
              name + "_sign",
              new StandingSignBlock(FabricBlockSettings.of()
                      .mapColor(topMaterialColor)
                      .forceSolidOn()
                      .instrument(NoteBlockInstrument.BASS)
                      .noCollission()
                      .strength(1.0F)
                      .ignitedByLava(), woodType)
      );
      Array[14] = registerBlock(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.of().forceSolidOn().instrument(
              NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(Array[13]), woodType));
      Array[15] = registerBlock(name + "_hanging_sign", new CeilingHangingSignBlock(FabricBlockSettings.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .sound(SoundType.HANGING_SIGN), woodType));
      Array[16] = registerBlock(
              name + "_wall_hanging_sign",
              new WallHangingSignBlock(FabricBlockSettings.of()
                      .mapColor(topMaterialColor)
                      .forceSolidOn()
                      .instrument(NoteBlockInstrument.BASS)
                      .noCollission()
                      .strength(1.0F)
                      .ignitedByLava()
                      .sound(SoundType.HANGING_SIGN)
                      .dropsLike(Array[15]), woodType)
      );

      registerItem(name + "_sign",
              new SignItem(new FabricItemSettings().stacksTo(16), Array[13], Array[14]),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      registerItem(name + "_hanging_sign",
              new HangingSignItem(Array[15], Array[16], new FabricItemSettings().stacksTo(16)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );

      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> entries.addAfter(
              buttonPlacement,
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
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(logPlacement,
              Array[2]
      ));
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(signPlacement[15],
              Array[13].asItem(),
              Array[15].asItem()
      ));

      BlockRenderLayerMap.INSTANCE.putBlock(Array[7], RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(Array[8], RenderType.cutout());

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
              new JoshuaTrunkBlock(FabricBlockSettings.of()
                      .ignitedByLava()
                      .mapColor(MapColor.COLOR_GRAY)
                      .instrument(NoteBlockInstrument.BASS)
                      .requiresCorrectToolForDrops()
                      .strength(2.0F)
                      .sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[1] = registerBlock("stripped_" + name + "_log", new JoshuaTrunkBlock(FabricBlockSettings.of()
              .ignitedByLava()
              .mapColor(MapColor.COLOR_GRAY)
              .instrument(NoteBlockInstrument.BASS)
              .requiresCorrectToolForDrops()
              .strength(2.0F)
              .sound(SoundType.WOOD)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[2] = registerBlock(name + "_planks",
              new Block(FabricBlockSettings.of()
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0F, 3.0F)
                      .sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[3] = registerBlock(name + "_stairs",
              new StairBlock(Array[2].defaultBlockState(), FabricBlockSettings.copy(Array[2])),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[4] = registerBlock(name + "_slab",
              new SlabBlock(FabricBlockSettings.of()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0f, 3.0f)
                      .sound(SoundType.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[5] = registerBlock(name + "_door",
              new DoorBlock(FabricBlockSettings.copy(Array[2]).noOcclusion(), blockSetType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[6] = registerBlock(name + "_trapdoor", new TrapDoorBlock(FabricBlockSettings.of()
              .mapColor(topMaterialColor)
              .ignitedByLava()
              .instrument(NoteBlockInstrument.BASS)
              .strength(3.0f)
              .sound(SoundType.WOOD)
              .isValidSpawn(HibiscusRegistryHelper::never)
              .noOcclusion(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[7] = registerBlock(name + "_fence",
              new FenceBlock(FabricBlockSettings.copy(Array[2]).noOcclusion().forceSolidOn()),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[8] = registerBlock(name + "_fence_gate",
              new FenceGateBlock(FabricBlockSettings.copy(Array[2]).noOcclusion().forceSolidOn(), woodType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[9] = registerBlock(name + "_pressure_plate",
              new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                      FabricBlockSettings.of()
                              .forceSolidOn()
                              .mapColor(topMaterialColor)
                              .noCollission()
                              .strength(0.5f)
                              .sound(SoundType.WOOD),
                      blockSetType
              ),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[10] = registerBlock(name + "_button",
              new ButtonBlock(FabricBlockSettings.of()
                      .mapColor(topMaterialColor).noCollission()
                      .strength(0.5F)
                      .sound(SoundType.WOOD), blockSetType, 30, true),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[11] = registerBlock(
              name + "_sign",
              new StandingSignBlock(FabricBlockSettings.of()
                      .mapColor(topMaterialColor)
                      .forceSolidOn()
                      .instrument(NoteBlockInstrument.BASS)
                      .noCollission()
                      .strength(1.0F)
                      .ignitedByLava(), woodType)
      );
      Array[12] = registerBlock(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.of().forceSolidOn().instrument(
              NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(Array[11]), woodType));
      Array[13] = registerBlock(name + "_hanging_sign", new CeilingHangingSignBlock(FabricBlockSettings.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .sound(SoundType.HANGING_SIGN), woodType));
      Array[14] = registerBlock(
              name + "_wall_hanging_sign",
              new WallHangingSignBlock(FabricBlockSettings.of()
                      .mapColor(topMaterialColor)
                      .forceSolidOn()
                      .instrument(NoteBlockInstrument.BASS)
                      .noCollission()
                      .strength(1.0F)
                      .ignitedByLava()
                      .sound(SoundType.HANGING_SIGN)
                      .dropsLike(Array[13]), woodType)
      );

      registerItem(name + "_sign",
              new SignItem(new FabricItemSettings().stacksTo(16), Array[11], Array[12]),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      registerItem(name + "_hanging_sign",
              new HangingSignItem(Array[13], Array[14], new FabricItemSettings().stacksTo(16)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );

      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> entries.addAfter(
              buttonPlacement,
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
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(logPlacement,
              Array[2]
      ));
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(signPlacement[15],
              Array[11].asItem(),
              Array[13].asItem()
      ));

      BlockRenderLayerMap.INSTANCE.putBlock(Array[5], RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(Array[6], RenderType.cutout());

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
              new LeavesBlock(FabricBlockSettings.of()
                      .strength(0.2F)
                      .randomTicks()
                      .noOcclusion()
                      .sound(SoundType.AZALEA_LEAVES)
                      .mapColor(color)
                      .ignitedByLava()
                      .isValidSpawn(HibiscusRegistryHelper::canSpawnUponLeaves)
                      .isSuffocating(HibiscusRegistryHelper::never)
                      .isViewBlocking(HibiscusRegistryHelper::never)
                      .pushReaction(PushReaction.DESTROY)
                      .isRedstoneConductor(HibiscusRegistryHelper::never)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      BlockRenderLayerMap.INSTANCE.putBlock(Leaves, RenderType.cutout());
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blockBefore,
              Leaves.asItem()
      ));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block registerMapleLeafBlock(String name, MapColor color, ParticleOptions particle, Block blockBefore) {
      Block Leaves = registerBlock(
              name + "_leaves",
              new MapleLeavesBlock(FabricBlockSettings.of()
                      .strength(0.2F)
                      .randomTicks()
                      .noOcclusion()
                      .sound(SoundType.AZALEA_LEAVES)
                      .mapColor(color)
                      .ignitedByLava()
                      .isValidSpawn(HibiscusRegistryHelper::canSpawnUponLeaves)
                      .isSuffocating(HibiscusRegistryHelper::never)
                      .isViewBlocking(HibiscusRegistryHelper::never)
                      .pushReaction(PushReaction.DESTROY)
                      .isRedstoneConductor(HibiscusRegistryHelper::never), particle),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      BlockRenderLayerMap.INSTANCE.putBlock(Leaves, RenderType.cutout());
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blockBefore,
              Leaves.asItem()
      ));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block registerLeafBlock(String name, MapColor color, Block blockBefore, boolean bl) {
      Block Leaves;
      if(bl) {
         Leaves = registerBlock(name + "_leaves",
                 new WisteriaLeaves(FabricBlockSettings.of()
                         .mapColor(color)
                         .strength(0.2F)
                         .randomTicks()
                         .sound(SoundType.GRASS)
                         .noOcclusion()
                         .isValidSpawn(HibiscusRegistryHelper::canSpawnUponLeaves)
                         .isSuffocating(HibiscusRegistryHelper::never)
                         .isViewBlocking(HibiscusRegistryHelper::never)
                         .ignitedByLava()
                         .pushReaction(PushReaction.DESTROY)
                         .isRedstoneConductor(HibiscusRegistryHelper::never)),
                 HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
         );
      }
      else {
         Leaves = registerBlock(name + "_leaves",
                 new WillowLeaves(FabricBlockSettings.of()
                         .mapColor(color)
                         .strength(0.2F)
                         .randomTicks()
                         .sound(SoundType.GRASS)
                         .noOcclusion()
                         .isValidSpawn(HibiscusRegistryHelper::canSpawnUponLeaves)
                         .isSuffocating(HibiscusRegistryHelper::never)
                         .isViewBlocking(HibiscusRegistryHelper::never)
                         .ignitedByLava()
                         .pushReaction(PushReaction.DESTROY)
                         .isRedstoneConductor(HibiscusRegistryHelper::never)),
                 HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
         );
      }
      BlockRenderLayerMap.INSTANCE.putBlock(Leaves, RenderType.cutout());
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blockBefore,
              Leaves.asItem()
      ));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block[] registerSapling(String name, AbstractTreeGrower saplingGenerator, Block blockBefore) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling",
              new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      BlockRenderLayerMap.INSTANCE.putBlock(Plant[0], RenderType.cutout());
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blockBefore,
              Plant[0].asItem()
      ));
      SaplingHashMap.put(name, Plant);
      return Plant;
   }

   public static Block[] registerJoshuaSapling(String name, AbstractTreeGrower saplingGenerator, Block blockBefore) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling",
              new JoshuaSapling(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      BlockRenderLayerMap.INSTANCE.putBlock(Plant[0], RenderType.cutout());
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blockBefore,
              Plant[0].asItem()
      ));
      SaplingHashMap.put(name, Plant);
      return Plant;
   }

   public static Block registerPottedPlant(String name, Block plant) {
      Block pottedPlant = registerBlock("potted_" + name,
              new FlowerPotBlock(plant,
                      FabricBlockSettings.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)
              )
      );
      BlockRenderLayerMap.INSTANCE.putBlock(pottedPlant, RenderType.cutout());
      return pottedPlant;
   }

   public static Block registerBlock(String name, Block block) {
      return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, name), block);
   }

   public static Block registerBlock(String name, Block block, ResourceKey <CreativeModeTab> tab) {
      registerBlockItem(name, block, tab);
      return registerBlock(name, block);
   }

   public static Block registerBlock(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore, ResourceKey <CreativeModeTab> secondaryTab) {
      Block block1 = registerBlock(name, block, tab);
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore,
              block1.asItem()
      ));
      return block1;
   }

   public static Block registerSecondaryDoorBlock(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore) {
      Block block1 = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, name), block);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
      registerBlockItem(name, block, tab, blockBefore, CreativeModeTabs.BUILDING_BLOCKS);
      return block1;
   }

   public static Block registerPlantBlock(String name, Block block) {
      Block Plant = registerBlock(name, block);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
      return Plant;
   }

   public static Block registerPlantBlock(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore, float compost) {
      Block Plant = registerBlock(name, block, tab, blockBefore, CreativeModeTabs.NATURAL_BLOCKS);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
      CompostingChanceRegistry.INSTANCE.add(block, compost);
      return Plant;
   }

   public static Block registerPlantBlockWithoutItem(String name, Block block, float compost) {
      Block Plant = registerBlock(name, block);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
      return Plant;
   }

   public static Item registerBlockItem(String name, Block block, ResourceKey <CreativeModeTab> tab) {
      return registerItem(name, new BlockItem(block, new FabricItemSettings()), tab);
   }

   public static Item registerBlockItem(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore, ResourceKey <CreativeModeTab> secondaryTab) {
      Item item = registerItem(name, new BlockItem(block, new FabricItemSettings()));
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore,
              item.asItem()
      ));
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
      return item;
   }

   public static Item registerItem(String name, Item item) {
      return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, name), item);
   }

   public static Item registerItem(String name, Item item, ResourceKey <CreativeModeTab> tab) {
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.accept(item.asItem()));
      return registerItem(name, item);
   }

   public static Item registerItem(String name, Item item, ResourceKey <CreativeModeTab> tab, Item itemBefore, ResourceKey <CreativeModeTab> secondaryTab) {
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(itemBefore, item));
      return registerItem(name, item, tab);
   }

   public static Item registerPlantItem(String name, Item item, ResourceKey <CreativeModeTab> tab, Item itemBefore, ResourceKey <CreativeModeTab> secondaryTab, float compost) {
      CompostingChanceRegistry.INSTANCE.add(item, compost);
      return registerItem(name, item, tab, itemBefore, secondaryTab);
   }
}
