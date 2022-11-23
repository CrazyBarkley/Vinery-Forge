package daniking.vinery.world;

import com.google.common.collect.ImmutableList;
import daniking.vinery.Vinery;
import daniking.vinery.VineryIdentifier;
import daniking.vinery.block.GrapeBush;
import daniking.vinery.block.VariantLeavesBlock;
import daniking.vinery.registry.ObjectRegistry;
import daniking.vinery.world.feature.VineryVinesFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class VineryConfiguredFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Vinery.MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Vinery.MODID);

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Vinery.MODID);

    public static final RegistryObject<Feature<SimpleBlockConfiguration>> VINES_FEATURE = FEATURES.register("vines_feature", () -> new VineryVinesFeature(SimpleBlockConfiguration.CODEC));


    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_GRAPE_BUSH_PATCH = registerConfiguredFeature("red_grape_bush", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ObjectRegistry.RED_GRAPE_BUSH.get().defaultBlockState().setValue(GrapeBush.AGE, 3))), List.of(Blocks.GRASS_BLOCK), 36)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> WHITE_GRAPE_BUSH_PATCH = registerConfiguredFeature("white_grape_bush", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ObjectRegistry.WHITE_GRAPE_BUSH.get().defaultBlockState().setValue(GrapeBush.AGE, 3))), List.of(Blocks.GRASS_BLOCK), 36)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CHERRY = registerConfiguredFeature("cherry", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.CHERRY_LOG.get()), new StraightTrunkPlacer(5, 2, 0), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));
    public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> CHERRY_VARIANT = registerConfiguredFeature("cherry_variant", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.CHERRY_LOG.get()), new StraightTrunkPlacer(5, 2, 0), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.get().defaultBlockState().setValue(VariantLeavesBlock.VARIANT, 1)), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> OLD_CHERRY = registerConfiguredFeature("old_cherry", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG.get()), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.get().defaultBlockState()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of()).forceDirt().build()));
    public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY_BEE = registerConfiguredFeature("old_cherry_bee", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG.get()), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.get().defaultBlockState()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(new BeehiveDecorator(0.5f))).forceDirt().build()));
    public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY_VARIANT = registerConfiguredFeature("old_cherry_variant", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG.get()), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.get().defaultBlockState().setValue(VariantLeavesBlock.VARIANT, 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of()).forceDirt().build()));
    public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY_VARIANT_WITH_BEE = registerConfiguredFeature("old_cherry_variant_with_bee", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG.get()), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.get().defaultBlockState().setValue(VariantLeavesBlock.VARIANT, 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(new BeehiveDecorator(0.5f))).forceDirt().build()));



    public static final RegistryObject<PlacedFeature> RED_GRAPE_PATCH_CHANCE = registerPlacedFeature("red_grape_bush_chance", () -> new PlacedFeature(RED_GRAPE_BUSH_PATCH.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(92), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> WHITE_GRAPE_PATCH_CHANCE = registerPlacedFeature("white_grape_bush_chance", () -> new PlacedFeature(WHITE_GRAPE_BUSH_PATCH.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(92), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));


    public static final RegistryObject<PlacedFeature> TREE_CHERRY = registerPlacedFeature("tree_cherry", () -> new PlacedFeature(CHERRY.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1), ObjectRegistry.CHERRY_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> TREE_CHERRY_OLD = registerPlacedFeature("tree_cherry_old", () -> new PlacedFeature(OLD_CHERRY.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1), ObjectRegistry.OLD_CHERRY_SAPLING.get())));

    /*
    private static RandomPatchConfiguration getFlowerGrassConfig(Block flowerGrass) {
        return new RandomPatchConfiguration(8, 2, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(flowerGrass.defaultBlockState())), createBlockPredicate(List.of(Blocks.GRASS_BLOCK))));
    }

     */
    
    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
        CONFIGURED_FEATURES.register(eventBus);
        PLACED_FEATURES.register(eventBus);
    }

    private static <T extends ConfiguredFeature<?, ?>> RegistryObject<T> registerConfiguredFeature(String path, Supplier<T> supplier){
        return CONFIGURED_FEATURES.register(path, supplier);
    }

    private static <T extends PlacedFeature> RegistryObject<T> registerPlacedFeature(String path, Supplier<T> supplier){
        return PLACED_FEATURES.register(path, supplier);
    }
 /*
	private static Predicate<BiomeSelectionContext> getTreesSelector() {
        return BiomeSelectors.tag(TagKey.create(Registry.BIOME_REGISTRY, new VineryIdentifier("has_structure/tree")));
	}

    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        return !validGround.isEmpty() ? BlockPredicate.allOf(BlockPredicate, BlockPredicate.matchesBlocks(validGround)) : BlockPredicate.ONLY_IN_AIR_PREDICATE;
    }

  */
    

}

