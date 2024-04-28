package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.blocks.CoconutBlock;
import net.hibiscus.naturespirit.blocks.YoungCoconutBlock;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.world.HibiscusWorldGen;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Iterator;
import java.util.List;

public class CoconutTreeDecorator extends TreeDecorator {
   public static final MapCodec <CoconutTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CoconutTreeDecorator::new, (decorator) -> {
      return decorator.probability;
   });
   private final float probability;

   public CoconutTreeDecorator(float probability) {
      this.probability = probability;
   }

   protected TreeDecoratorType <?> getType() {
      return HibiscusWorldGen.COCONUT_TREE_DECORATOR;
   }

   public void generate(TreeDecorator.Generator generator) {
      Random random = generator.getRandom();
      if (!(random.nextFloat() >= this.probability)) {
         List <BlockPos> list = generator.getLogPositions();
         list.stream().filter((pos) -> pos.getY() >= list.get(list.size() - 1).getY() - 2).forEach((pos) -> {

            for(Direction direction : Direction.Type.HORIZONTAL) {
               if(random.nextFloat() <= 0.5F) {
                  Direction direction2 = direction.getOpposite();
                  BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());
                  if(generator.isAir(blockPos)) {
                     if (random.nextBoolean()) {
                        generator.replace(blockPos, HibiscusWoods.COCONUT_BLOCK.getDefaultState().with(
                                CoconutBlock.FACING,
                                direction.getOpposite()
                        ));
                     } else {
                        generator.replace(blockPos, HibiscusWoods.YOUNG_COCONUT_BLOCK.getDefaultState().with(
                                YoungCoconutBlock.FACING,
                                direction.getOpposite()
                        ));
                     }
                  }
               }
            }

         });
      }
   }
}

