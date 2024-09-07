package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.registration.HibiscusDataComponents;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.HibiscusTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PizzaBlockEntity extends BlockEntity {
   public ArrayList <PizzaToppingVariant> TOPPINGS = new ArrayList <>();
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

   public boolean canPlaceTopping(ItemStack itemStack, World world, PizzaBlockEntity pizzaBlockEntity) {
      Identifier itemId = Registries.ITEM.getId(itemStack.getItem());
      PizzaToppingVariant toppingVariant = getVariantFromItem(itemId, world);
      boolean bl = pizzaBlockEntity.getCachedState().get(PizzaBlock.BITES) == 0 && pizzaBlockEntity.TOPPING_NUMBER < 4 && !(itemStack.isIn(HibiscusTags.Items.DISABLED_PIZZA_TOPPINGS)) && toppingVariant != null && !TOPPINGS.contains(toppingVariant);
      if (bl) {
         TOPPINGS.add(toppingVariant);
      }
      this.markDirty();
     return bl;
   }

   @Nullable
   public static PizzaToppingVariant getVariantFromItem(Identifier itemId, World world) {
      for(PizzaToppingVariant pizzaToppingVariant: world.getRegistryManager().get(NatureSpirit.PIZZA_TOPPING_VARIANT)) {
         if (pizzaToppingVariant.itemId().equals(itemId)) return pizzaToppingVariant;
      }
      return null;
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
