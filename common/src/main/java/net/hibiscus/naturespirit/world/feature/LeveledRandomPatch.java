package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class LeveledRandomPatch extends Feature<LeveledRandomPatchConfig> {

  public LeveledRandomPatch(Codec<LeveledRandomPatchConfig> codec) {
    super(codec);
  }

  public boolean place(FeaturePlaceContext<LeveledRandomPatchConfig> context) {
    LeveledRandomPatchConfig randomPatchFeatureConfig = context.config();
    RandomSource random = context.random();
    BlockPos blockPos = context.origin();
    WorldGenLevel structureWorldAccess = context.level();
    int i = 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
    int j = randomPatchFeatureConfig.xzSpread() + 1;
    int k = randomPatchFeatureConfig.ySpread() + 1;

    for (int l = 0; l < randomPatchFeatureConfig.tries(); ++l) {
      int y = random.nextInt(k) - random.nextInt(k);
      mutable.setWithOffset(blockPos, random.nextInt(j) - random.nextInt(j), y, random.nextInt(j) - random.nextInt(j));
      if (mutable.getY() > structureWorldAccess.getSeaLevel() + 1) {
        if (randomPatchFeatureConfig.feature().value().place(structureWorldAccess, context.chunkGenerator(), random, mutable)) {
          ++i;
        }
      }
    }

    return i > 0;
  }
}
