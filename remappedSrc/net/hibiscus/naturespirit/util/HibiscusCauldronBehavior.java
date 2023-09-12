package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public interface HibiscusCauldronBehavior {

   Map <Item, CauldronInteraction> MILK_CAULDRON_BEHAVIOR = newInteractionMap();
   CauldronInteraction FILL_WITH_MILK = (state, world, pos, player, hand, stack) -> emptyBucket(
           world,
           pos,
           player,
           hand,
           stack,
           HibiscusBlocksAndItems.MILK_CAULDRON.defaultBlockState(),
           SoundEvents.BUCKET_EMPTY
   );
   Map <Item, CauldronInteraction> CHEESE_CAULDRON_BEHAVIOR = newInteractionMap();
   CauldronInteraction FILL_WITH_CHEESE = (state, world, pos, player, hand, stack) -> emptyBucket(
           world,
           pos,
           player,
           hand,
           stack,
           HibiscusBlocksAndItems.CHEESE_CAULDRON.defaultBlockState(),
           SoundEvents.BUCKET_EMPTY
   );

   static void registerBehavior() {
      MILK_CAULDRON_BEHAVIOR.put(
              Items.BUCKET,
              (state, world, pos, player, hand, stack) -> fillBucket(state,
                      world,
                      pos,
                      player,
                      hand,
                      stack,
                      new ItemStack(Items.MILK_BUCKET),
                      (statex) -> {return true;},
                      SoundEvents.COW_MILK
              )
      );
      addDefaultInteractions(MILK_CAULDRON_BEHAVIOR);
      CHEESE_CAULDRON_BEHAVIOR.put(
              Items.BUCKET,
              (state, world, pos, player, hand, stack) -> fillBucket(state,
                      world,
                      pos,
                      player,
                      hand,
                      stack,
                      new ItemStack(HibiscusBlocksAndItems.CHEESE_BUCKET),
                      (statex) -> { return true;},
                      SoundEvents.BUCKET_FILL
              )
      );
      addDefaultInteractions(CHEESE_CAULDRON_BEHAVIOR);
   }


}
