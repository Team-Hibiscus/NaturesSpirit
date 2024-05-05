package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PizzaBlockEntity extends BlockEntity {
   public ArrayList<String> TOPPINGS = new ArrayList<String>();
   public int TOPPING_NUMBER = TOPPINGS != null ? TOPPINGS.size() : 0;
   public int BITES = 0;
   public PizzaBlockEntity(BlockPos pos, BlockState state) {
      super(HibiscusMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE, pos, state);
   }
   @Override
   public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
      NbtList nbtElement = new NbtList();
      for(String string : TOPPINGS) {
         nbtElement.add(NbtString.of(string));
      }
      nbt.put("topping_types", nbtElement);
      nbt.putInt("toppings_number", this.TOPPING_NUMBER);
      nbt.putInt("pizza_bites", this.BITES);
      this.markDirty();

      super.writeNbt(nbt, registryLookup);
   }
   @Override
   public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
      NbtList nbt2 = ((NbtList)nbt.get("topping_types"));
      if (nbt2 != null) {
         TOPPINGS.clear();
         for(int i = 0; i < nbt2.size(); i++) {
            TOPPINGS.add(i, nbt2.getString(i));
         }
      }
      this.TOPPING_NUMBER = nbt.getInt("toppings_number");
      this.BITES = nbt.getInt("pizza_bites");

      super.readNbt(nbt, registryLookup);
   }
   public boolean checkTopping(ItemStack itemStack) {
      return itemStack.isIn(HibiscusTags.Items.PIZZA_TOPPINGS);
   }
   public boolean canPlaceTopping(ItemStack itemStack, PizzaBlockEntity pizzaBlockEntity) {
      boolean pizzaTopping = checkTopping(itemStack);
      String itemId = Registries.ITEM.getId(itemStack.getItem()).toString();
      boolean bl = pizzaBlockEntity.BITES == 0 && pizzaBlockEntity.TOPPING_NUMBER < 4 && !(itemStack.isIn(HibiscusTags.Items.DISABLED_PIZZA_TOPPINGS)) && pizzaTopping && !TOPPINGS.contains(itemId);
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
