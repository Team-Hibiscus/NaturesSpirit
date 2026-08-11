package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class NSChestBoatEntity extends ChestBoat {

    public NSChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NSChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(NSEntityTypes.NS_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getNSVariant()) {
            case REDWOOD -> NSBlocks.REDWOOD.getChestBoatItem().get();
            case SUGI -> NSBlocks.SUGI.getChestBoatItem().get();
            case WISTERIA -> NSBlocks.WISTERIA.getChestBoatItem().get();
            case FIR -> NSBlocks.FIR.getChestBoatItem().get();
            case WILLOW -> NSBlocks.WILLOW.getChestBoatItem().get();
            case ASPEN -> NSBlocks.ASPEN.getChestBoatItem().get();
            case MAPLE -> NSBlocks.MAPLE.getChestBoatItem().get();
            case CYPRESS -> NSBlocks.CYPRESS.getChestBoatItem().get();
            case OLIVE -> NSBlocks.OLIVE.getChestBoatItem().get();
            case JOSHUA -> NSBlocks.JOSHUA.getChestBoatItem().get();
            case GHAF -> NSBlocks.GHAF.getChestBoatItem().get();
            case PALO_VERDE -> NSBlocks.PALO_VERDE.getChestBoatItem().get();
            case COCONUT -> NSBlocks.COCONUT.getChestBoatItem().get();
            case CEDAR -> NSBlocks.CEDAR.getChestBoatItem().get();
            case LARCH -> NSBlocks.LARCH.getChestBoatItem().get();
            case MAHOGANY -> NSBlocks.MAHOGANY.getChestBoatItem().get();
            case SAXAUL -> NSBlocks.SAXAUL.getChestBoatItem().get();
            default -> super.getDropItem();
        };
    }

    public void setVariant(NSBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData(SynchedEntityData.Builder p_326198_) {
        p_326198_.define(DATA_ID_TYPE, NSBoatEntity.Type.REDWOOD.ordinal());
        p_326198_.define(DATA_ID_PADDLE_LEFT, false);
        p_326198_.define(DATA_ID_PADDLE_RIGHT, false);
        p_326198_.define(DATA_ID_BUBBLE_TIME, 0);
        p_326198_.define(DATA_ID_HURT, 0);
        p_326198_.define(DATA_ID_HURTDIR, 1);
        p_326198_.define(DATA_ID_DAMAGE, 0.0F);
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getNSVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Type", 8)) {
            this.setVariant(NSBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public NSBoatEntity.Type getNSVariant() {
        return NSBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
