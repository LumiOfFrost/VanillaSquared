package net.runelit.vanillasquared.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.runelit.vanillasquared.BlockInit;

import java.util.Random;

import static java.lang.Math.max;

public class DesertPalm extends Feature<DefaultFeatureConfig> {
    public DesertPalm(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos,
                            DefaultFeatureConfig config) {
        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        int height;
        int y;
        int x;
        if (Math.random() > 0.4) {
            height = 5;
        } else {
            height = 6;
        }
        for (y = 0; y <= height; y++) {
            world.setBlockState(topPos.up(y), Blocks.JUNGLE_LOG.getDefaultState(), 3);
        }
        world.setBlockState(topPos.up(y + 1), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);

        for (x = 1; x <= 2; x++) {
            world.setBlockState(topPos.north(x).up(y), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);
        }
        world.setBlockState(topPos.north(x).up(y - 1), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);

        for (x = 1; x <= 2; x++) {
            world.setBlockState(topPos.south(x).up(y), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);
        }
        world.setBlockState(topPos.south(x).up(y - 1), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);

        for (x = 1; x <= 2; x++) {
            world.setBlockState(topPos.east(x).up(y), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);
        }
        world.setBlockState(topPos.east(x).up(y - 1), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);

        for (x = 1; x <= 2; x++) {
            world.setBlockState(topPos.west(x).up(y), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);
        }
        world.setBlockState(topPos.west(x).up(y - 1), BlockInit.DESERT_PALM_LEAVES.getDefaultState(), 3);

        if(Math.random() > 4) {
            world.setBlockState(topPos.north(1).up(y - 1), BlockInit.COCONUT.getDefaultState(), 3);
        } else {
            world.setBlockState(topPos.south(1).up(y - 1), BlockInit.COCONUT.getDefaultState(), 3);
        }

        if(Math.random() > 4) {
            world.setBlockState(topPos.west(1).up(y - 1), BlockInit.COCONUT.getDefaultState(), 3);
        } else {
            world.setBlockState(topPos.east(1).up(y - 1), BlockInit.COCONUT.getDefaultState(), 3);
        }

        return true;
    }
}