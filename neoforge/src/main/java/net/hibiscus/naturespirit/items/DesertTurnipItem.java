package net.hibiscus.naturespirit.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class DesertTurnipItem extends ItemNameBlockItem {
  public DesertTurnipItem(Block block, Properties settings) {
    super(block, settings);
  }

  @Override
  public int getUseDuration(ItemStack stack, LivingEntity user) {
    return 24;
  }
}