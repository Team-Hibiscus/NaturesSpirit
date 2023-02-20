//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.feature.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.BiConsumer;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer.TreeNode;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class SugiTrunkPlacer extends TrunkPlacer {
    public static final Codec<SugiTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return fillTrunkPlacerFields(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps").forGetter((trunkPlacer) -> {
            return trunkPlacer.extraBranchSteps;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
            return trunkPlacer.placeBranchPerLogProbability;
        }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
            return trunkPlacer.extraBranchLength;
        }), RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
            return trunkPlacer.canGrowThrough;
        }))).apply(instance, SugiTrunkPlacer::new);
    });
    private final IntProvider extraBranchSteps;
    private final float placeBranchPerLogProbability;
    private final IntProvider extraBranchLength;
    private final RegistryEntryList<Block> canGrowThrough;

    public SugiTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability, IntProvider extraBranchLength, RegistryEntryList<Block> canGrowThrough) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
        this.extraBranchSteps = extraBranchSteps;
        this.placeBranchPerLogProbability = placeBranchPerLogProbability;
        this.extraBranchLength = extraBranchLength;
        this.canGrowThrough = canGrowThrough;
    }

    protected TrunkPlacerType <?> getType() {
        return NatureSpirit.SUGI_TRUNK_PLACER;
    }

    public List<TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        List<TreeNode> list = Lists.newArrayList();
        Mutable mutable = new Mutable();

        for(int i = 0; i < height; ++i) {
            int j = startPos.getY() + i;
            Direction direction = Type.HORIZONTAL.random(random);
            Direction direction2 = Type.HORIZONTAL.random(random);
            Direction direction3 = Type.HORIZONTAL.random(random);
            if (this.getAndSetState(world, replacer, random, mutable.set(startPos.getX(), j, startPos.getZ()), config) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
                int k = this.extraBranchLength.get(random);
                int l = Math.max(0, k - this.extraBranchLength.get(random) - 1);
                int m = height - i - 1;
                if (i > 1) {
                    this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction, l, m);
                }
                if (i > 1 && random.nextFloat() < this.placeBranchPerLogProbability - 0.65) {
                    this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction2, l, Math.round(m/2));
                }
                if (i > 1 && random.nextFloat() < this.placeBranchPerLogProbability - 0.85) {
                    this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction3, l, Math.round(m/2));
                }
            }
            if (i == height - 1) {
                list.add(new TreeNode(mutable.set(startPos.getX(), j + 1, startPos.getZ()), 0, false));
            }
        }

        for(int i = 0; i < height; ++i) {
            int j = startPos.getY() + i;
            Direction direction = Type.HORIZONTAL.random(random);
            Direction direction2 = Type.HORIZONTAL.random(random);
            Direction direction3 = Type.HORIZONTAL.random(random);
            if (this.getAndSetState(world, replacer, random, mutable.set(startPos.getX(), j, startPos.getZ()), config) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
                int k = this.extraBranchLength.get(random);
                int l = Math.max(0, k - this.extraBranchLength.get(random) - 1);
                int m = height - i - 1;
                if (i > 1) {
                    this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction, l, m);
                }
                if (i > 1 && random.nextFloat() < this.placeBranchPerLogProbability - 0.65) {
                    this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction2, l, Math.round(m/2));
                }
                if (i > 1 && random.nextFloat() < this.placeBranchPerLogProbability - 0.85) {
                    this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction3, l, Math.round(m/2));
                }
            }
        }

        return list;
    }

    private void generateExtraBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, TreeFeatureConfig config, List<TreeNode> nodes, Mutable pos, int yOffset, Direction direction, int length, int steps) {
        int i = yOffset + length;
        int j = pos.getX();
        int k = pos.getZ();
        boolean bl = random.nextFloat() < .5F;
        Direction direction2 = random.nextFloat() < .5F ? direction.rotateYClockwise() : direction.rotateYCounterclockwise();

        for(int l = length; l < height && steps > 0 && (steps < (bl ? 7 : 10)); --steps) {
            if (l >= 1) {
                int m = Math.round(yOffset + l/5);
                j += direction.getOffsetX();
                if (bl) {
                    k += Math.max(0, direction2.getOffsetZ() - random.nextInt(2));
                } else {
                    k += direction.getOffsetZ();
                }
                i = m;
                if (this.getAndSetState(world, replacer, random, pos.set(j, m, k), config, blockState -> {
                    return blockState.withIfExists(PillarBlock.AXIS, direction.getAxis());
                })) {
                    i = m + 1;
                }
            }
            ++l;
        }

        if (i - yOffset >= 1) {
            BlockPos blockPos = new BlockPos(j, i, k);
            nodes.add(new TreeNode(blockPos, Math.max(0, height - yOffset - 4), false));
        }

    }

    protected boolean canReplace(TestableWorld world, BlockPos pos) {
        return super.canReplace(world, pos) || world.testBlockState(pos, (state) -> {
            return state.isIn(this.canGrowThrough);
        });
    }
}
