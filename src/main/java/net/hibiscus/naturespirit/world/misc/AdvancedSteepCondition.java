package net.hibiscus.naturespirit.world.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;

public record AdvancedSteepCondition(int heightDifference, int sampleDistance)
        implements SurfaceRules.ConditionSource {

    public static final KeyDispatchDataCodec<AdvancedSteepCondition> CODEC =
            KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Codec.intRange(1, 512).fieldOf("height_difference")
                            .forGetter(AdvancedSteepCondition::heightDifference),
                    Codec.intRange(1, 15).optionalFieldOf("sample_distance", 1)
                            .forGetter(AdvancedSteepCondition::sampleDistance)
            ).apply(inst, AdvancedSteepCondition::new)));

    @Override
    public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
        return CODEC;
    }

    @Override
    public SurfaceRules.Condition apply(SurfaceRules.Context ctx) {
        return new Cond(ctx);
    }

    private final class Cond implements SurfaceRules.Condition {
        private final SurfaceRules.Context ctx;
        private long lastUpdate = Long.MIN_VALUE;
        private boolean result;

        private Cond(SurfaceRules.Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public boolean test() {
            if (ctx.lastUpdateXZ != lastUpdate) {
                lastUpdate = ctx.lastUpdateXZ;
                result = compute();
            }
            return result;
        }

        private boolean compute() {
            int x = ctx.blockX & 15;
            int z = ctx.blockZ & 15;
            int d = sampleDistance;
            ChunkAccess chunk = ctx.chunk;

            int north = height(chunk, x, Math.max(z - d, 0));
            int south = height(chunk, x, Math.min(z + d, 15));
            int west  = height(chunk, Math.max(x - d, 0), z);
            int east  = height(chunk, Math.min(x + d, 15), z);

            return Math.abs(south - north) >= heightDifference || Math.abs(east - west) >= heightDifference;
        }

        private static int height(ChunkAccess chunk, int x, int z) {
            return chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
        }
    }
}