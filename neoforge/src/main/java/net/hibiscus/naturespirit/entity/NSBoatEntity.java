package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class NSBoatEntity extends Boat {
    public NSBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NSBoatEntity(Level level, double pX, double pY, double pZ) {
        this(NSEntityTypes.NS_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getNSVariant()) {
            case REDWOOD -> NSBlocks.REDWOOD.getBoatItem().get();
            case SUGI -> NSBlocks.SUGI.getBoatItem().get();
            case WISTERIA -> NSBlocks.WISTERIA.getBoatItem().get();
            case FIR -> NSBlocks.FIR.getBoatItem().get();
            case WILLOW -> NSBlocks.WILLOW.getBoatItem().get();
            case ASPEN -> NSBlocks.ASPEN.getBoatItem().get();
            case MAPLE -> NSBlocks.MAPLE.getBoatItem().get();
            case CYPRESS -> NSBlocks.CYPRESS.getBoatItem().get();
            case OLIVE -> NSBlocks.OLIVE.getBoatItem().get();
            case JOSHUA -> NSBlocks.JOSHUA.getBoatItem().get();
            case GHAF -> NSBlocks.GHAF.getBoatItem().get();
            case PALO_VERDE -> NSBlocks.PALO_VERDE.getBoatItem().get();
            case COCONUT -> NSBlocks.COCONUT.getBoatItem().get();
            case CEDAR -> NSBlocks.CEDAR.getBoatItem().get();
            case LARCH -> NSBlocks.LARCH.getBoatItem().get();
            case MAHOGANY -> NSBlocks.MAHOGANY.getBoatItem().get();
            case SAXAUL -> NSBlocks.SAXAUL.getBoatItem().get();
            default -> super.getDropItem();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getNSVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData(SynchedEntityData.Builder p_326198_) {
        p_326198_.define(DATA_ID_TYPE, Type.REDWOOD.ordinal());
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
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public static enum Type implements StringRepresentable {
        REDWOOD(NSBlocks.REDWOOD.getPlanks().get(), "redwood"),
        SUGI(NSBlocks.SUGI.getPlanks().get(), "sugi"),
        WISTERIA(NSBlocks.WISTERIA.getPlanks().get(), "wisteria"),
        FIR(NSBlocks.FIR.getPlanks().get(), "fir"),
        WILLOW(NSBlocks.WILLOW.getPlanks().get(), "willow"),
        ASPEN(NSBlocks.ASPEN.getPlanks().get(), "aspen"),
        MAPLE(NSBlocks.MAPLE.getPlanks().get(), "maple"),
        CYPRESS(NSBlocks.CYPRESS.getPlanks().get(), "cypress"),
        OLIVE(NSBlocks.OLIVE.getPlanks().get(), "olive"),
        JOSHUA(NSBlocks.JOSHUA.getPlanks().get(), "joshua"),
        GHAF(NSBlocks.GHAF.getPlanks().get(), "ghaf"),
        PALO_VERDE(NSBlocks.PALO_VERDE.getPlanks().get(), "palo_verde"),
        COCONUT(NSBlocks.COCONUT.getPlanks().get(), "coconut"),
        CEDAR(NSBlocks.CEDAR.getPlanks().get(), "cedar"),
        LARCH(NSBlocks.LARCH.getPlanks().get(), "larch"),
        MAHOGANY(NSBlocks.MAHOGANY.getPlanks().get(), "mahogany"),
        SAXAUL(NSBlocks.SAXAUL.getPlanks().get(), "saxaul");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<NSBoatEntity.Type> CODEC = StringRepresentable.fromEnum(NSBoatEntity.Type::values);
        private static final IntFunction<NSBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by its enum ordinal
         */
        public static NSBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static NSBoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, REDWOOD);
        }
    }
}
