package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.registration.HibiscusDataComponents;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PizzaBlockEntity extends BlockEntity {
   public ArrayList <Identifier> TOPPINGS = new ArrayList <>();
   public int TOPPING_NUMBER = 0;
   public PizzaBlockEntity(BlockPos pos, BlockState state) {
      super(HibiscusMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE, pos, state);
   }
   protected void readComponents(BlockEntity.ComponentsAccess components) {
      super.readComponents(components);
      if (components.get(HibiscusDataComponents.TOPPINGS) != null) {
         TOPPINGS = new ArrayList <>(components.get(HibiscusDataComponents.TOPPINGS));
      }
      this.TOPPING_NUMBER = TOPPINGS != null ? TOPPINGS.size() : 0;
      this.markDirty();
   }

   protected void addComponents(ComponentMap.Builder componentMapBuilder) {
      super.addComponents(componentMapBuilder);
      componentMapBuilder.add(HibiscusDataComponents.TOPPINGS, TOPPINGS);
      this.markDirty();
   }

   public boolean canPlaceTopping(ItemStack itemStack, PizzaBlockEntity pizzaBlockEntity) {
      Identifier itemId = Registries.ITEM.getId(itemStack.getItem());
      boolean bl = pizzaBlockEntity.getCachedState().get(PizzaBlock.BITES) == 0 && pizzaBlockEntity.TOPPING_NUMBER < 4 && !(itemStack.isIn(HibiscusTags.Items.DISABLED_PIZZA_TOPPINGS)) && itemStack.isIn(HibiscusTags.Items.PIZZA_TOPPINGS) && !TOPPINGS.contains(itemId);
      if (bl) {
         TOPPINGS.add(itemId);
      }
      this.markDirty();
     return bl;
   }
   @Nullable
   @Override
   public Packet <ClientPlayPacketListener> toUpdatePacket() {
      return BlockEntityUpdateS2CPacket.create(this);
   }

   @Override
   public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
      return createNbt(registryLookup);
   }
}
