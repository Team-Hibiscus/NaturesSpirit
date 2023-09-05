package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

import static net.minecraft.block.cauldron.CauldronBehavior.*;

public interface HibiscusCauldronBehavior {

   Map <Item, CauldronBehavior> MILK_CAULDRON_BEHAVIOR = createMap();
   CauldronBehavior FILL_WITH_MILK = (state, world, pos, player, hand, stack) -> fillCauldron(
           world,
           pos,
           player,
           hand,
           stack,
           HibiscusBlocksAndItems.MILK_CAULDRON.getDefaultState(),
           SoundEvents.ITEM_BUCKET_EMPTY
   );
   Map <Item, CauldronBehavior> CHEESE_CAULDRON_BEHAVIOR = createMap();
   CauldronBehavior FILL_WITH_CHEESE = (state, world, pos, player, hand, stack) -> fillCauldron(
           world,
           pos,
           player,
           hand,
           stack,
           HibiscusBlocksAndItems.CHEESE_CAULDRON.getDefaultState(),
           SoundEvents.ITEM_BUCKET_EMPTY
   );

   static void registerBehavior() {
      MILK_CAULDRON_BEHAVIOR.put(
              Items.BUCKET,
              (state, world, pos, player, hand, stack) -> emptyCauldron(state,
                      world,
                      pos,
                      player,
                      hand,
                      stack,
                      new ItemStack(Items.MILK_BUCKET),
                      (statex) -> {return true;},
                      SoundEvents.ENTITY_COW_MILK
              )
      );
      registerBucketBehavior(MILK_CAULDRON_BEHAVIOR);
      CHEESE_CAULDRON_BEHAVIOR.put(
              Items.BUCKET,
              (state, world, pos, player, hand, stack) -> emptyCauldron(state,
                      world,
                      pos,
                      player,
                      hand,
                      stack,
                      new ItemStack(HibiscusBlocksAndItems.CHEESE_BUCKET),
                      (statex) -> { return true;},
                      SoundEvents.ITEM_BUCKET_FILL
              )
      );
      registerBucketBehavior(CHEESE_CAULDRON_BEHAVIOR);
   }


}
