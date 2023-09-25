package net.hibiscus.naturespirit.world.tree_decorator;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;

public class MapleGroundTreeDecorator extends TreeDecorator {
   public static final Codec <MapleGroundTreeDecorator> CODEC;
   private final BlockStateProvider provider;
   private final BlockStateProvider provider2;

   public MapleGroundTreeDecorator(BlockStateProvider provider, BlockStateProvider provider2) {
      this.provider = provider;
      this.provider2 = provider2;
   }

   protected TreeDecoratorType <?> type() {
      return HibiscusWorldGen.MAPLE_GROUND_TREE_DECORATOR;
   }

   public void place(Context generator) {
      List <BlockPos> list = Lists.newArrayList();
      List <BlockPos> list3 = generator.logs();
      list.addAll(list3);
      if(!list.isEmpty()) {
         int i = list.get(0).getY();
         list.stream().filter((pos) -> pos.getY() == i).forEach((pos) -> {
            this.setArea(generator, pos);
         });
      }
   }

   private void setArea(Context generator, BlockPos origin) {
      for(int i = -2; i <= 2; ++i) {
         for(int j = -2; j <= 2; ++j) {
            if(Math.abs(i) != 2 || Math.abs(j) != 2) {
               if(Math.abs(i) == 2 || Math.abs(j) == 2) {
                  this.setColumn2(generator, origin.offset(i, 0, j));
               }
               else {this.setColumn(generator, origin.offset(i, 0, j));}
            }
         }
      }

   }

   private void setColumn(Context generator, BlockPos origin) {
      for(int i = 2; i >= -3; --i) {
         BlockPos blockPos = origin.above(i);
         if(Feature.isGrassOrDirt(generator.level(), blockPos)) {
            generator.setBlock(blockPos, this.provider.getState(generator.random(), origin));
            break;
         }

         if(!generator.isAir(blockPos) && i < 0) {
            break;
         }
      }

   }

   private void setColumn2(Context generator, BlockPos origin) {
      for(int i = 2; i >= -3; --i) {
         BlockPos blockPos = origin.above(i);
         if(Feature.isGrassOrDirt(generator.level(), blockPos)) {

            generator.setBlock(blockPos, this.provider2.getState(generator.random(), origin));

            if(generator.isAir(blockPos.above(1)) && generator.random().nextInt(50) == 0) {
               generator.setBlock(blockPos.above(1), HibiscusBlocksAndItems.SHIITAKE_MUSHROOM.defaultBlockState());
            }
            break;
         }

         if(!generator.isAir(blockPos) && i < 0) {
            break;
         }
      }

   }

   static {
      CODEC = RecordCodecBuilder.create((instance) -> instance.group(
              BlockStateProvider.CODEC.fieldOf("inner_block").forGetter((treeDecorator) -> treeDecorator.provider),
              BlockStateProvider.CODEC.fieldOf("outer_block").forGetter((treeDecorator) -> treeDecorator.provider2)
      ).apply(instance, MapleGroundTreeDecorator::new));
   }
}
