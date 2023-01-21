package net.hibiscus.naturespirit.world.feature.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class SakuraTrunkPlacer extends TrunkPlacer {
    public static final Codec <SakuraTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return trunkPlacerParts(instance).apply(instance, SakuraTrunkPlacer::new);
    });

    public SakuraTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    private static float treeShape(int i, int j) {
        if ((float) j < (float) i * 0.675F) {
            return -1.0F;
        } else {
            float f = (float) i / 1.25F;
            float g = f - (float) j;
            float h = Mth.sqrt(f * f - g * g);
            if (g == 0.0F) {
                h = f;
            } else if (Math.abs(g) >= f) {
                return 0.0F;
            }

            return h * 0.5F;
        }
    }

    protected TrunkPlacerType <?> type() {
        return TrunkPlacerType.FANCY_TRUNK_PLACER;
    }

    public List <FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer <BlockPos, BlockState> biConsumer, RandomSource randomSource, int i, BlockPos blockPos, TreeConfiguration treeConfiguration) {
        int j = 1;
        int k = i + 3;
        int l = Mth.floor((double) k * 0.618D);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);
        double d = 1.0D;
        int m = Math.min(1, Mth.floor(1.382D + Math.pow(1.0D * (double) k / 13.0D, 2.0D)));
        int n = blockPos.getY() + l;
        int o = k - 5;
        List <SakuraTrunkPlacer.FoliageCoords> list = Lists.newArrayList();
        list.add(new SakuraTrunkPlacer.FoliageCoords(blockPos.above(o), n));

        for (; o >= -2; --o) {
            float f = treeShape(k, o);
            if (!(f < 0.0F)) {
                for (int p = 0; p < m; ++p) {
                    double g = 1.6D * (double) f * ((double) randomSource.nextFloat() + 1.328D);
                    double h = (double) (randomSource.nextFloat() * 2.5F) * 3.141592653589793D;
                    double q = g * Math.sin(h) + 1.5D;
                    double r = g * Math.cos(h) + 1.5D;
                    BlockPos blockPos2 = blockPos.offset(q, o - 1, r);
                    BlockPos blockPos3 = blockPos2.above(5);
                    if (this.makeLimb(levelSimulatedReader, biConsumer, randomSource, blockPos2, blockPos3, false, treeConfiguration)) {
                        int s = blockPos.getX() - blockPos2.getX();
                        int t = blockPos.getZ() - blockPos2.getZ();
                        double u = (double) blockPos2.getY() - Math.sqrt(s * s + t * t) * 0.381D;
                        int v = u > (double) n ? n : (int) u;
                        BlockPos blockPos4 = new BlockPos(blockPos.getX(), v, blockPos.getZ());
                        if (this.makeLimb(levelSimulatedReader, biConsumer, randomSource, blockPos4, blockPos2, false, treeConfiguration)) {
                            list.add(new SakuraTrunkPlacer.FoliageCoords(blockPos2, blockPos4.getY()));
                        }
                    }
                }
            }
        }

        this.makeLimb(levelSimulatedReader, biConsumer, randomSource, blockPos, blockPos.above(l), true, treeConfiguration);
        this.makeBranches(levelSimulatedReader, biConsumer, randomSource, k, blockPos, list, treeConfiguration);
        List <FoliagePlacer.FoliageAttachment> list2 = Lists.newArrayList();
        Iterator var37 = list.iterator();

        while (var37.hasNext()) {
            SakuraTrunkPlacer.FoliageCoords foliageCoords = (SakuraTrunkPlacer.FoliageCoords) var37.next();
            if (this.trimBranches(k, foliageCoords.getBranchBase() - blockPos.getY())) {
                list2.add(foliageCoords.attachment);
            }
        }

        return list2;
    }

    private boolean makeLimb(LevelSimulatedReader levelSimulatedReader, BiConsumer <BlockPos, BlockState> biConsumer, RandomSource randomSource, BlockPos blockPos, BlockPos blockPos2, boolean bl, TreeConfiguration treeConfiguration) {
        if (!bl && Objects.equals(blockPos, blockPos2)) {
            return true;
        } else {
            BlockPos blockPos3 = blockPos2.offset(-blockPos.getX(), -blockPos.getY(), -blockPos.getZ());
            int i = this.getSteps(blockPos3);
            float f = (float) blockPos3.getX() / (float) i;
            float g = (float) blockPos3.getY() / (float) i;
            float h = (float) blockPos3.getZ() / (float) i;

            for (int j = 0; j <= i; ++j) {
                BlockPos blockPos4 = blockPos.offset(0.5F + (float) j * f, 0.5F + (float) j * g, 0.5F + (float) j * h);
                if (bl) {
                    this.placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos4, treeConfiguration, (blockState) -> {
                        return blockState.setValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos, blockPos4));
                    });
                } else if (!this.isFree(levelSimulatedReader, blockPos4)) {
                    return false;
                }
            }

            return true;
        }
    }

    private int getSteps(BlockPos pos) {
        int i = Mth.abs(pos.getX());
        int j = Mth.abs(pos.getY());
        int k = Mth.abs(pos.getZ());
        return Math.max(i, Math.max(j, k));
    }

    private Direction.Axis getLogAxis(BlockPos pos, BlockPos otherPos) {
        Direction.Axis axis = Direction.Axis.Y;
        int i = Math.abs(otherPos.getX() - pos.getX());
        int j = Math.abs(otherPos.getZ() - pos.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }

    private boolean trimBranches(int i, int j) {
        return (double) j <= (double) i * 0.165D;
    }

    private void makeBranches(LevelSimulatedReader levelSimulatedReader, BiConsumer <BlockPos, BlockState> biConsumer, RandomSource randomSource, int i, BlockPos blockPos, List <SakuraTrunkPlacer.FoliageCoords> list, TreeConfiguration treeConfiguration) {
        Iterator var8 = list.iterator();

        while (var8.hasNext()) {
            SakuraTrunkPlacer.FoliageCoords foliageCoords = (SakuraTrunkPlacer.FoliageCoords) var8.next();
            int j = foliageCoords.getBranchBase();
            BlockPos blockPos2 = new BlockPos(blockPos.getX(), j, blockPos.getZ());
            if (!blockPos2.equals(foliageCoords.attachment.pos()) && this.trimBranches(i, j - blockPos.getY())) {
                this.makeLimb(levelSimulatedReader, biConsumer, randomSource, blockPos2, foliageCoords.attachment.pos(), true, treeConfiguration);
            }
        }

    }

    static class FoliageCoords {
        final FoliagePlacer.FoliageAttachment attachment;
        private final int branchBase;

        public FoliageCoords(BlockPos blockPos, int i) {
            this.attachment = new FoliagePlacer.FoliageAttachment(blockPos, 0, false);
            this.branchBase = i;
        }

        public int getBranchBase() {
            return this.branchBase;
        }
    }
}
