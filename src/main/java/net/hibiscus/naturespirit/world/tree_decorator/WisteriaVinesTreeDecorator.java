package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.world.HibiscusWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WisteriaVinesTreeDecorator extends TreeDecorator {
   public static final Codec <WisteriaVinesTreeDecorator> CODEC = RecordCodecBuilder.create((instance) -> {
      return instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((treeDecorator) -> {
         return treeDecorator.probability;
      }), BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> {
         return treeDecorator.blockProvider;
      }), BlockStateProvider.TYPE_CODEC.fieldOf("block_provider2").forGetter((treeDecorator) -> {
         return treeDecorator.blockProvider2;
      }), BlockStateProvider.TYPE_CODEC.fieldOf("block_provider3").forGetter((treeDecorator) -> {
         return treeDecorator.blockProvider3;
      }), Codec.intRange(0, 10).fieldOf("number").forGetter((treeDecorator) -> {
         return treeDecorator.number;
      })).apply(instance, WisteriaVinesTreeDecorator::new);
   });
   protected final BlockStateProvider blockProvider;
   protected final BlockStateProvider blockProvider2;
   protected final BlockStateProvider blockProvider3;
   private final float probability;
   protected int number;

   public WisteriaVinesTreeDecorator(float probability, BlockStateProvider blockProvider, BlockStateProvider blockProvider2, BlockStateProvider blockProvider3, int number) {
      this.probability = probability;
      this.blockProvider = blockProvider;
      this.blockProvider2 = blockProvider2;
      this.blockProvider3 = blockProvider3;
      this.number = number;
   }

   private static void placeVines(BlockPos pos, BlockStateProvider block, BlockStateProvider block2, BlockStateProvider block3, Generator generator, int number) {
      Random random = generator.getRandom();
      generator.replace(pos, block3.get(random, pos));
      for(pos = pos.down(); number > 0; --number) {
         if(generator.isAir(pos)) {
            if(number == 1 || !generator.isAir(pos.down()) || random.nextBoolean()) {
               generator.replace(pos, block2.get(random, pos));
               break;
            }
            generator.replace(pos, block.get(random, pos));
         }
         pos = pos.down();
      }

   }

   @Override protected TreeDecoratorType <?> getType() {
      return HibiscusWorldGen.WISTERIA_VINES_TREE_DECORATOR;
   }

   public void generate(Generator context) {
      Random randomSource = context.getRandom();
      context.getLeavesPositions().forEach((blockPos) -> {
         BlockPos blockPos2;
         if(randomSource.nextFloat() < this.probability) {
            blockPos2 = blockPos.down();
            if(context.isAir(blockPos2)) {
               if (!context.isAir(blockPos.up())) {
                  context.replace(blockPos, blockProvider3.get(randomSource, blockPos));
                  if(!context.isAir(blockPos.up(2))) {
                     context.replace(blockPos, blockProvider3.get(randomSource, blockPos.up()));
                  }
               }
               placeVines(blockPos2, blockProvider, blockProvider2, blockProvider3, context, this.number);
            }
         }

      });
   }
}