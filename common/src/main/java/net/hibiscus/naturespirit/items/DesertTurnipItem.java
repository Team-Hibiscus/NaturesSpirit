package net.hibiscus.naturespirit.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class DesertTurnipItem extends BlockItem {
  public DesertTurnipItem(Block block, Properties settings) {
    super(block, settings.useItemDescriptionPrefix());
  }

  @Override
  public int getUseDuration(ItemStack stack, LivingEntity user) {
    return 24;
  }
}
