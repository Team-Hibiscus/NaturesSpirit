package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.registration.NSMiscBlocks;
import net.hibiscus.naturespirit.registration.NSTags;
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
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PizzaBlockEntity extends BlockEntity {
   public ArrayList<String> toppings = new ArrayList<String>();
   public int topping_number = toppings != null ? toppings.size() : 0;
   public int bites = 0;
   public PizzaBlockEntity(BlockPos pos, BlockState state) {
      super(NSMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE, pos, state);
   }
   @Override
   public void writeNbt(NbtCompound nbt) {
      NbtList nbtElement = new NbtList();
      for(String string : toppings) {
         nbtElement.add(NbtString.of(string));
      }
      nbt.put("topping_types", nbtElement);
      nbt.putInt("toppings_number", this.topping_number);
      nbt.putInt("pizza_bites", this.bites);
      this.markDirty();

      super.writeNbt(nbt);
   }
   @Override
   public void readNbt(NbtCompound nbt) {
      NbtList nbt2 = ((NbtList)nbt.get("topping_types"));
      if (nbt2 != null) {
         toppings.clear();
         for(int i = 0; i < nbt2.size(); i++) {
            toppings.add(i, nbt2.getString(i));
         }
      }
      this.topping_number = nbt.getInt("toppings_number");
      this.bites = nbt.getInt("pizza_bites");

      super.readNbt(nbt);
   }
   public boolean checkTopping(ItemStack itemStack) {
      return itemStack.isIn(NSTags.Items.PIZZA_TOPPINGS);
   }
   public boolean canPlaceTopping(ItemStack itemStack, PizzaBlockEntity pizzaBlockEntity) {
      boolean pizzaTopping = checkTopping(itemStack);
      String itemId = Registries.ITEM.getId(itemStack.getItem()).toString();
      boolean bl = pizzaBlockEntity.bites == 0 && pizzaBlockEntity.topping_number < 4 && !(itemStack.isIn(NSTags.Items.DISABLED_PIZZA_TOPPINGS)) && pizzaTopping && !toppings.contains(itemId);
      if (bl) {
         toppings.add(itemId);
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
   public NbtCompound toInitialChunkDataNbt() {
      return createNbt();
   }
}
