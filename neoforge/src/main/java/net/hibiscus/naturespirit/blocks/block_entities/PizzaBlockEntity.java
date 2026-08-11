package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSDataComponents;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PizzaBlockEntity extends BlockEntity {

  public ArrayList<PizzaToppingVariant> toppings = new ArrayList<>();
  public int toppingCount = 0;

  public PizzaBlockEntity(BlockPos pos, BlockState state) {
    super(NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get(), pos, state);
  }

  @Override
  protected void applyImplicitComponents(BlockEntity.DataComponentInput components) {
    super.applyImplicitComponents(components);
    if (components.get(NSDataComponents.TOPPINGS) != null) {
      toppings = new ArrayList<>(components.get(NSDataComponents.TOPPINGS.get()));
    }
    this.toppingCount = toppings != null ? toppings.size() : 0;
  }

  @Override
  protected void collectImplicitComponents(DataComponentMap.Builder componentMapBuilder) {
    super.collectImplicitComponents(componentMapBuilder);
    componentMapBuilder.set(NSDataComponents.TOPPINGS.get(), toppings);
  }

  @Override
  public void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
    super.saveAdditional(nbt, registryLookup);
    nbt.put("toppings", (Tag)NSDataComponents.TOPPINGS.get().codec().encodeStart(NbtOps.INSTANCE, this.toppings).getOrThrow());
  }

  @Override
  public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
    super.loadAdditional(nbt, registryLookup);
    this.toppings.clear();
    if (nbt.contains("toppings")) {
      NSDataComponents.TOPPINGS.get().codec().parse(NbtOps.INSTANCE, nbt.get("toppings")).resultOrPartial().ifPresent((list) -> toppings.addAll(list));
    }
  }

  public boolean canPlaceTopping(ItemStack itemStack, Level world, PizzaBlockEntity pizzaBlockEntity) {
    ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
    PizzaToppingVariant toppingVariant = getVariantFromItem(itemId, world);
    boolean bl = pizzaBlockEntity.getBlockState().getValue(PizzaBlock.BITES) == 0 && pizzaBlockEntity.toppingCount < 4 && !(itemStack.is(NSTags.Items.DISABLED_PIZZA_TOPPINGS))
            && toppingVariant != null && !toppings.contains(toppingVariant);
    if (bl) {
      toppings.add(toppingVariant);
    }
    return bl;
  }

  @Nullable
  public static PizzaToppingVariant getVariantFromItem(ResourceLocation itemId, Level world) {
    for (PizzaToppingVariant pizzaToppingVariant : world.registryAccess().registryOrThrow(NatureSpirit.PIZZA_TOPPING_VARIANT)) {
      if (pizzaToppingVariant.itemId().equals(itemId)) {
        return pizzaToppingVariant;
      }
    }
    return null;
  }

  @Nullable
  @Override
  public Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
    return saveWithoutMetadata(registryLookup);
  }
}
