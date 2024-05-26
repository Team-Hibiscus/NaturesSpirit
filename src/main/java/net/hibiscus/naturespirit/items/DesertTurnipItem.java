package net.hibiscus.naturespirit.items;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;

public class DesertTurnipItem extends AliasedBlockItem {
   public DesertTurnipItem(Block block, Settings settings) {
      super(block, settings);
   }
   @Override public int getMaxUseTime(ItemStack stack) {
      if (stack.getItem().isFood()) {
         return this.getFoodComponent().isSnack() ? 24 : 32;
      } else {
         return 0;
      }
   }
}
