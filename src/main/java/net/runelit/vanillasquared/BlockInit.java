package net.runelit.vanillasquared;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.Item;
import net.runelit.vanillasquared.blocks.BarrelCactus;
import net.runelit.vanillasquared.blocks.CoconutBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.runelit.vanillasquared.blocks.DesertPalmSaplingBlock;
import net.runelit.vanillasquared.misc.DesertPalmSaplingGenerator;

public class BlockInit implements ModInitializer {



    public static final Block DESERT_PALM_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.JUNGLE_LEAVES).nonOpaque());
    public static final Block BARREL_CACTUS = new BarrelCactus(FabricBlockSettings.copyOf(Blocks.CACTUS).nonOpaque());
    public static final Block COCONUT = new CoconutBlock(FabricBlockSettings.copyOf(Blocks.PUMPKIN).nonOpaque());
    public static final DesertPalmSaplingBlock DESERT_PALM_SAPLING_BLOCK = new DesertPalmSaplingBlock(new DesertPalmSaplingGenerator(WorldInit.DESERT_PALM_CONFIGURED), FabricBlockSettings.copyOf(Blocks.JUNGLE_SAPLING));

    @Override
    public void onInitialize() {

        Registry.register(Registry.BLOCK, new Identifier("vanillasquared", "desert_palm_sapling"), DESERT_PALM_SAPLING_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("vanillasquared", "desert_palm_sapling"), new BlockItem(DESERT_PALM_SAPLING_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        //Decorative
        Registry.register(Registry.BLOCK, new Identifier("vanillasquared", "coconut"), COCONUT);
        Registry.register(Registry.BLOCK, new Identifier("vanillasquared", "barrel_cactus"), BARREL_CACTUS);
        Registry.register(Registry.BLOCK, new Identifier("vanillasquared", "desert_palm_leaves"), DESERT_PALM_LEAVES);

        //Block Items
        Registry.register(Registry.ITEM, new Identifier("vanillasquared", "barrel_cactus"), new BlockItem(BARREL_CACTUS, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("vanillasquared", "coconut"), new BlockItem(COCONUT, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("vanillasquared", "desert_palm_leaves"), new BlockItem(DESERT_PALM_LEAVES, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

    }
}
