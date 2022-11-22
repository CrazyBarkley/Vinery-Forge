package daniking.vinery.world;

public class VineryConfiguredFeatures {
/*
    public static final Feature<SimpleBlockConfiguration> VINES_FEATURE = new VineryVinesFeature(SimpleBlockConfiguration.CODEC);

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> RED_GRAPE_BUSH_PATCH = FeatureUtils.register(VineryIdentifier.asString("red_grape_bush"), Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ObjectRegistry.RED_GRAPE_BUSH.defaultBlockState().setValue(GrapeBush.AGE, 3))), List.of(Blocks.GRASS_BLOCK), 36));
    public static final Holder<PlacedFeature> RED_GRAPE_PATCH_CHANCE = PlacementUtils.register(VineryIdentifier.asString("red_grape_bush_chance"), RED_GRAPE_BUSH_PATCH, RarityFilter.onAverageOnceEvery(92), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WHITE_GRAPE_BUSH_PATCH = FeatureUtils.register(VineryIdentifier.asString("white_grape_bush"), Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ObjectRegistry.WHITE_GRAPE_BUSH.defaultBlockState().setValue(GrapeBush.AGE, 3))), List.of(Blocks.GRASS_BLOCK), 36));
    public static final Holder<PlacedFeature> WHITE_GRAPE_PATCH_CHANCE = PlacementUtils.register(VineryIdentifier.asString("white_grape_bush_chance"), WHITE_GRAPE_BUSH_PATCH, RarityFilter.onAverageOnceEvery(92), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CHERRY = FeatureUtils.register(VineryIdentifier.asString("cherry"), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.CHERRY_LOG), new StraightTrunkPlacer(5, 2, 0), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CHERRY_VARIANT = FeatureUtils.register(VineryIdentifier.asString("cherry_variant"), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.CHERRY_LOG), new StraightTrunkPlacer(5, 2, 0), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.defaultBlockState().setValue(VariantLeavesBlock.VARIANT, 1)), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY = FeatureUtils.register(VineryIdentifier.asString("old_cherry"), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.defaultBlockState()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of()).forceDirt().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY_BEE = FeatureUtils.register(VineryIdentifier.asString("old_cherry_bee"), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.defaultBlockState()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(new BeehiveDecorator(0.5f))).forceDirt().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY_VARIANT = FeatureUtils.register(VineryIdentifier.asString("old_cherry_variant"), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.defaultBlockState().setValue(VariantLeavesBlock.VARIANT, 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of()).forceDirt().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> OLD_CHERRY_VARIANT_WITH_BEE = FeatureUtils.register(VineryIdentifier.asString("old_cherry_variant_with_bee"), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ObjectRegistry.OLD_CHERRY_LOG), new FancyTrunkPlacer(4, 14, 2), SimpleStateProvider.simple(ObjectRegistry.CHERRY_LEAVES.defaultBlockState().setValue(VariantLeavesBlock.VARIANT, 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(new BeehiveDecorator(0.5f))).forceDirt().build());
    
    public static final Holder<PlacedFeature> TREE_CHERRY = PlacementUtils.register("tree_cherry", CHERRY, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1), ObjectRegistry.CHERRY_SAPLING));
    public static final Holder<PlacedFeature> TREE_CHERRY_OLD = PlacementUtils.register("tree_cherry_old", OLD_CHERRY, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1), ObjectRegistry.OLD_CHERRY_SAPLING));
    
    private static RandomPatchConfiguration getFlowerGrassConfig(Block flowerGrass) {
        return new RandomPatchConfiguration(8, 2, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(flowerGrass.defaultBlockState())), createBlockPredicate(List.of(Blocks.GRASS_BLOCK))));
    }
    
    public static void init() {
        Registry.register(Registry.FEATURE, new VineryIdentifier("vines_feature"), VINES_FEATURE);
        BiomeModification world = BiomeModifications.create(new VineryIdentifier("world_features"));
        Predicate<BiomeSelectionContext> bushBiomes = BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.PLAINS, Biomes.TAIGA, Biomes.RIVER, Biomes.SWAMP, Biomes.JUNGLE);
        Predicate<BiomeSelectionContext> grassFlowerBiomes = BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.TAIGA, Biomes.RIVER, Biomes.SWAMP);
        world.add(ModificationPhase.ADDITIONS, bushBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RED_GRAPE_PATCH_CHANCE.value()));
        world.add(ModificationPhase.ADDITIONS, bushBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WHITE_GRAPE_PATCH_CHANCE.value()));

        world.add(ModificationPhase.ADDITIONS, getTreesSelector(), ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TREE_CHERRY.value()));
        world.add(ModificationPhase.ADDITIONS, getTreesSelector(), ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TREE_CHERRY_OLD.value()));
    }
 
	private static Predicate<BiomeSelectionContext> getTreesSelector() {
        return BiomeSelectors.tag(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("vinery:has_structure/tree")));
	}

    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        return !validGround.isEmpty() ? BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(validGround)) : BlockPredicate.ONLY_IN_AIR_PREDICATE;
    }

 */

}

