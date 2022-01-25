package net.runelit.vanillasquared;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.runelit.vanillasquared.world.features.DesertPalm;
import org.spongepowered.asm.mixin.Shadow;

public class WorldInit implements ModInitializer {

    public static final Feature<DefaultFeatureConfig> DESERT_PALM = new DesertPalm(DefaultFeatureConfig.CODEC);

    public static final ConfiguredFeature<?, ?> DESERT_PALM_CONFIGURED = DESERT_PALM.configure(FeatureConfig.DEFAULT)
            .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(66)));

    @Override
    public void onInitialize() {
        Registry.register(Registry.FEATURE, new Identifier("vanillasquared", "desert_palm"), DESERT_PALM);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vanillasquared", "desert_palm"), DESERT_PALM_CONFIGURED);
    }

}
