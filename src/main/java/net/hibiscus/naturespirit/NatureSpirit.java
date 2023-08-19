package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.entity.HibiscusEntityTypes;
import net.hibiscus.naturespirit.items.HibiscusBoatDispensorBehavior;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.*;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.util.HibiscusEvents;
import net.hibiscus.naturespirit.util.HibiscusVillagers;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NatureSpirit implements ModInitializer {

   public static final String MOD_ID = "natures_spirit";
   public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
   public static final Identifier EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice",
           StatFormatter.DEFAULT
   );
   public static final DefaultParticleType RED_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final DefaultParticleType ORANGE_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final DefaultParticleType YELLOW_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);

   @Override public void onInitialize() {

      Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "red_maple_leaves"), RED_MAPLE_LEAVES_PARTICLE);
      Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "orange_maple_leaves"),
              ORANGE_MAPLE_LEAVES_PARTICLE
      );
      Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "yellow_maple_leaves"),
              YELLOW_MAPLE_LEAVES_PARTICLE
      );
      HibiscusVillagers.registerVillagers();
      HibiscusBiomes.registerBiomes();
      HibiscusBlocks.registerHibiscusBlocks();
      HibiscusEvents.registerEvents();
      HibiscusWorldGen.registerWorldGen();
      HibiscusItemGroups.registerItemGroup();
      HibiscusEntityTypes.registerEntityTypes();

      CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.DESERT_TURNIP_BLOCK, 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK, 0.4F);

      for (HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
         DispenserBlock.registerBehavior(boat.boat(), new HibiscusBoatDispensorBehavior(boat, false));
         DispenserBlock.registerBehavior(boat.chestBoat(), new HibiscusBoatDispensorBehavior(boat, true));
      }

      Registry.register(Registries.CAT_VARIANT,
              "trans",
              new CatVariant(new Identifier("textures/entity/cat/trans" + ".png"))
      );
   }
}
