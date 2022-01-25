package net.runelit.vanillasquared.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class DesertPalmSaplingBlock extends SaplingBlock {
    public DesertPalmSaplingBlock(SaplingGenerator generator, AbstractBlock.Settings settings) {
        super(generator, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.SAND) || floor.isOf(Blocks.RED_SAND) || floor.isOf(Blocks.COARSE_DIRT);
    }
}