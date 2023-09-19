package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.HashMap;

public class PizzaBlockEntity extends BlockEntity {
   public boolean MUSHROOM_BOOLEAN = false;
   public boolean GREEN_OLIVES_BOOLEAN = false;
   public boolean BLACK_OLIVES_BOOLEAN = false;
   public boolean BEETROOT_BOOLEAN = false;
   public boolean CARROT_BOOLEAN = false;
   public boolean COD_BOOLEAN = false;
   public boolean CHICKEN_BOOLEAN = false;
   public boolean PORK_BOOLEAN = false;
   public boolean RABBIT_BOOLEAN = false;
   public int TOPPINGS = 0;
   public int BITES = 0;
   public PizzaBlockEntity(BlockPos pos, BlockState state) {
      super(HibiscusBlocksAndItems.PIZZA_BLOCK_ENTITY_TYPE, pos, state);
   }
   @Override
   public void writeNbt(NbtCompound nbt) {
      nbt.putBoolean("mushroom_topping", this.MUSHROOM_BOOLEAN);
      nbt.putBoolean("green_olives_topping", this.GREEN_OLIVES_BOOLEAN);
      nbt.putBoolean("black_olives_topping", this.BLACK_OLIVES_BOOLEAN);
      nbt.putBoolean("beetroot_topping", this.BEETROOT_BOOLEAN);
      nbt.putBoolean("carrot_topping", this.CARROT_BOOLEAN);
      nbt.putBoolean("cod_topping", this.COD_BOOLEAN);
      nbt.putBoolean("chicken_topping", this.CHICKEN_BOOLEAN);
      nbt.putBoolean("pork_topping", this.PORK_BOOLEAN);
      nbt.putBoolean("rabbit_topping", this.RABBIT_BOOLEAN);
      nbt.putInt("toppings", this.TOPPINGS);
      nbt.putInt("pizza_bites", this.BITES);

      super.writeNbt(nbt);
   }
   @Override
   public void readNbt(NbtCompound nbt) {
      this.MUSHROOM_BOOLEAN = nbt.getBoolean("mushroom_topping");
      this.GREEN_OLIVES_BOOLEAN = nbt.getBoolean("green_olives_topping");
      this.BLACK_OLIVES_BOOLEAN = nbt.getBoolean("black_olives_topping");
      this.BEETROOT_BOOLEAN = nbt.getBoolean("beetroot_topping");
      this.CARROT_BOOLEAN = nbt.getBoolean("carrot_topping");
      this.COD_BOOLEAN = nbt.getBoolean("cod_topping");
      this.CHICKEN_BOOLEAN = nbt.getBoolean("chicken_topping");
      this.PORK_BOOLEAN = nbt.getBoolean("pork_topping");
      this.RABBIT_BOOLEAN = nbt.getBoolean("rabbit_topping");
      this.TOPPINGS = nbt.getInt("toppings");
      this.BITES = nbt.getInt("pizza_bites");

      super.readNbt(nbt);
   }
   public boolean checkTopping(ItemStack itemStack, PizzaBlockEntity pizzaBlockEntity) {
      boolean pizzaTopping = false;
      switch(Registries.ITEM.getId(itemStack.getItem()).toString()) {
         case "minecraft:carrot" -> pizzaTopping = pizzaBlockEntity.CARROT_BOOLEAN;
         case "minecraft:cooked_porkchop" -> pizzaTopping = pizzaBlockEntity.PORK_BOOLEAN;
         case "natures_spirit:black_olives" -> pizzaTopping = pizzaBlockEntity.BLACK_OLIVES_BOOLEAN;
         case "natures_spirit:green_olives" -> pizzaTopping = pizzaBlockEntity.GREEN_OLIVES_BOOLEAN;
         case "minecraft:beetroot" -> pizzaTopping = pizzaBlockEntity.BEETROOT_BOOLEAN;
         case "minecraft:cooked_chicken" -> pizzaTopping = pizzaBlockEntity.CHICKEN_BOOLEAN;
         case "minecraft:cooked_cod" -> pizzaTopping = pizzaBlockEntity.COD_BOOLEAN;
         case "minecraft:cooked_rabbit" -> pizzaTopping = pizzaBlockEntity.RABBIT_BOOLEAN;
         case "minecraft:brown_mushroom" -> pizzaTopping = pizzaBlockEntity.MUSHROOM_BOOLEAN;
      }
      return pizzaTopping;
   }
   public boolean canPlaceTopping(ItemStack itemStack, PizzaBlockEntity pizzaBlockEntity) {
      boolean pizzaTopping = checkTopping(itemStack, pizzaBlockEntity);
      if (!pizzaTopping) {
         switch(Registries.ITEM.getId(itemStack.getItem()).toString()) {
            case "minecraft:carrot" -> pizzaBlockEntity.CARROT_BOOLEAN = true;
            case "minecraft:cooked_porkchop" -> pizzaBlockEntity.PORK_BOOLEAN = true;
            case "natures_spirit:black_olives" -> pizzaBlockEntity.BLACK_OLIVES_BOOLEAN = true;
            case "natures_spirit:green_olives" -> pizzaBlockEntity.GREEN_OLIVES_BOOLEAN = true;
            case "minecraft:beetroot" -> pizzaBlockEntity.BEETROOT_BOOLEAN = true;
            case "minecraft:cooked_chicken" -> pizzaBlockEntity.CHICKEN_BOOLEAN = true;
            case "minecraft:cooked_cod" -> pizzaBlockEntity.COD_BOOLEAN = true;
            case "minecraft:cooked_rabbit" -> pizzaBlockEntity.RABBIT_BOOLEAN = true;
            case "minecraft:brown_mushroom" -> pizzaBlockEntity.MUSHROOM_BOOLEAN = true;
         }
      }
     return itemStack.isIn(HibiscusTags.Items.PIZZA_TOPPINGS) && pizzaBlockEntity.BITES == 0 && pizzaBlockEntity.TOPPINGS < 4 && !(itemStack.isIn(HibiscusTags.Items.DISABLED_PIZZA_TOPPINGS)) && !pizzaTopping;
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
