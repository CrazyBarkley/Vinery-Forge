package daniking.vinery.registry;

import daniking.vinery.item.GrapeBushSeedItem;
import daniking.vinery.Vinery;
import daniking.vinery.VineryIdentifier;
import daniking.vinery.block.FlowerPotBlock;
import daniking.vinery.block.*;
import daniking.vinery.item.*;
import daniking.vinery.util.GrapevineType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import static daniking.vinery.registry.VineryEntites.MULE;
import static daniking.vinery.registry.VineryEntites.WANDERING_WINEMAKER;

public class ObjectRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vinery.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vinery.MODID);


    // Red Grapes
    public static final Block RED_GRAPE_BUSH = register("red_grape_bush", new GrapeBush(getBushSettings(), GrapevineType.RED), false);
    public static final Item RED_GRAPE_SEEDS = register("red_grape_seeds", new GrapeBushSeedItem(RED_GRAPE_BUSH, getSettings(), GrapevineType.RED));

    public static final Item RED_GRAPE = register("red_grape", new GrapeItem(getSettings().food(Foods.SWEET_BERRIES), GrapevineType.RED));

    public static final Block WHITE_GRAPE_BUSH = register("white_grape_bush", new GrapeBush(getBushSettings(), GrapevineType.WHITE), false);
    public static final Item WHITE_GRAPE_SEEDS = register("white_grape_seeds", new GrapeBushSeedItem(WHITE_GRAPE_BUSH, getSettings(), GrapevineType.WHITE));
    public static final Item WHITE_GRAPE = register("white_grape", new GrapeItem(getSettings().food(Foods.SWEET_BERRIES), GrapevineType.WHITE));

    public static final Item CHERRY = register("cherry", new CherryItem(getSettings().food(Foods.COOKIE)));

    /*
    public static final Block CHERRY_SAPLING = register("cherry_sapling", new SaplingBlock(new AbstractTreeGrower() {
        @Nullable
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
            if (random.nextBoolean()) return VineryConfiguredFeatures.CHERRY;
            return VineryConfiguredFeatures.CHERRY_VARIANT;
        }
    }, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final Block OLD_CHERRY_SAPLING = register("old_cherry_sapling", new SaplingBlock(new AbstractTreeGrower() {
        @Nullable
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
            if (random.nextBoolean()) {
                if (bees) return VineryConfiguredFeatures.OLD_CHERRY_BEE;
                return VineryConfiguredFeatures.OLD_CHERRY;
            } else {
                if (bees) return VineryConfiguredFeatures.OLD_CHERRY_VARIANT_WITH_BEE;
                return VineryConfiguredFeatures.OLD_CHERRY_VARIANT;
            }
        }
    }, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), true);

     */

    public static final Block GRAPEVINE_LEAVES = register("grapevine_leaves", new GrapevineLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final Block CHERRY_LEAVES = register("cherry_leaves", new VariantLeavesBlock());

    public static final Block WHITE_GRAPE_CRATE = register("white_grape_crate", new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block RED_GRAPE_CRATE = register("red_grape_crate", new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    public static final Block CHERRY_CRATE = register("cherry_crate", new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    public static final Block GRAPEVINE_STEM = register("grapevine_stem", new GrapevineStemBlock(getGrapevineSettings()));

    public static final Block GRAPEVINE_POT = register("grapevine_pot", new GrapevinePotBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));

    public static final Block FERMENTATION_BARREL = register("fermentation_barrel", new FermentationBarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));

    public static final Block WINE_PRESS = register("wine_press", new WinePressBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block CHAIR = register("chair", new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final Block TABLE = register("table", new TableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final Block WOOD_FIRED_OVEN = register("wood_fired_oven", new WoodFiredOvenBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(WoodFiredOvenBlock.LIT) ? 13 : 0)));
    public static final Block STOVE = register("stove", new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(block -> 12)));

    public static final Block KITCHEN_SINK = register("kitchen_sink", new KitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static final Block WINE_RACK_1 = register("wine_rack_1", new WineRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), 9, 1));
    public static final Block WINE_RACK_2 = register("wine_rack_2", new WineRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), 3, 2));
    public static final Block WINE_RACK_3 = register("wine_rack_3", new WineRackStorageBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block WINE_RACK_5 = register("wine_rack_5", new WineRackStorageBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));

    public static final Block BARREL = register("barrel", new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));

    public static final Block STORAGE_POT = register("storage_pot", new StoragePotBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.DYE_USE, SoundEvents.DYE_USE));
    public static final Block STRIPPED_CHERRY_LOG = registerLog("stripped_cherry_log");
    //public static final Block CHERRY_LOG = register("cherry_log", new StrippableLogBlock(() -> STRIPPED_CHERRY_LOG, MaterialColor.WOOD, getLogBlockSettings()));
    public static final Block STRIPPED_CHERRY_WOOD = registerLog("stripped_cherry_wood");
    //public static final Block CHERRY_WOOD = register("cherry_wood", new StrippableLogBlock(() -> STRIPPED_CHERRY_WOOD, MaterialColor.WOOD, getLogBlockSettings()));
    public static final Block STRIPPED_OLD_CHERRY_LOG = registerLog("stripped_old_cherry_log");
    //public static final Block OLD_CHERRY_LOG = register("old_cherry_log", new StrippableLogBlock(() -> STRIPPED_OLD_CHERRY_LOG, MaterialColor.WOOD, getLogBlockSettings()));
    public static final Block STRIPPED_OLD_CHERRY_WOOD = registerLog("stripped_old_cherry_wood");
    //public static final Block OLD_CHERRY_WOOD = register("old_cherry_wood", new StrippableLogBlock(() -> STRIPPED_OLD_CHERRY_WOOD, MaterialColor.WOOD, getLogBlockSettings()));

    public static final Block CHERRY_BEAM = registerLog("cherry_beam");

    public static final Block CHERRY_PLANKS = register("cherry_planks", new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block CHERRY_FLOORBOARD = register("cherry_floorboard", new Block(BlockBehaviour.Properties.copy(CHERRY_PLANKS)));
    public static final Block CHERRY_STAIRS = register("cherry_stairs", new StairBlock(CHERRY_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(CHERRY_PLANKS)));
    public static final Block CHERRY_SLAB = register("cherry_slab", new SlabBlock(getSlabSettings()));
    public static final Block CHERRY_FENCE = register("cherry_fence", new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final Block CHERRY_FENCE_GATE = register("cherry_fence_gate", new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final Block CHERRY_BUTTON = register("cherry_button", new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final Block CHERRY_PRESSURE_PLATE = register("cherry_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block CHERRY_DOOR = register("cherry_door", new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final Block CHERRY_TRAPDOOR = register("cherry_trapdoor", new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    private static final ResourceLocation CHERRY_SIGN_TEXTURE = new VineryIdentifier("entity/sign/cherry");

    //public static final TerraformSignBlock CHERRY_SIGN = register("cherry_sign", new TerraformSignBlock(CHERRY_SIGN_TEXTURE, BlockBehaviour.Properties.copy(Blocks.OAK_SIGN)), false);
    //public static final Block CHERRY_WALL_SIGN = register("cherry_wall_sign", new TerraformWallSignBlock(CHERRY_SIGN_TEXTURE, BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)), false);
    //public static final Item CHERRY_SIGN_ITEM = register("cherry_sign", new SignItem(getSettings().stacksTo(16), CHERRY_SIGN, CHERRY_WALL_SIGN));
    public static final Block WINDOW = register("window", new WindowBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)));

    public static final Block LOAM = register("loam", new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final Block LOAM_STAIRS = register("loam_stairs", new StairBlock(LOAM.defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final Block LOAM_SLAB = register("loam_slab", new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final Block COARSE_DIRT_SLAB = register("coarse_dirt_slab", new VariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final Block DIRT_SLAB = register("dirt_slab", new VariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final Block GRASS_SLAB = register("grass_slab", new SnowyVariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final Block WINE_BOTTLE = register("wine_bottle", new EmptyWineBottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instabreak().noOcclusion()));
    public static final Block RED_GRAPEJUICE_WINE_BOTTLE = registerWine("red_grapejuice_wine_bottle", new RedGrapejuiceWineBottle(getWineSettings()), null);
    public static final Block WHITE_GRAPEJUICE_WINE_BOTTLE = registerWine("white_grapejuice_wine_bottle", new WhiteGrapejuiceWineBottle(getWineSettings()), null);

    public static final Block CHENET_WINE = registerBigWine("chenet_wine", new ChenetBottleBlock(getWineSettings()), MobEffects.JUMP);
    public static final Block KING_DANIS_WINE = registerBigWine("king_danis_wine", new KingDanisBottleBlock(getWineSettings()), MobEffects.LUCK);
    public static final Block NOIR_WINE = registerWine("noir_wine", new WineBottleBlock(getWineSettings()), MobEffects.WATER_BREATHING);
    public static final Block CLARK_WINE = registerWine("clark_wine", new WineBottleBlock(getWineSettings()), MobEffects.FIRE_RESISTANCE);
    public static final Block MELLOHI_WINE = registerBigWine("mellohi_wine", new MellohiWineBlock(getWineSettings()), MobEffects.DAMAGE_BOOST);
    public static final Block BOLVAR_WINE = registerWine("bolvar_wine", new WineBottleBlock(getWineSettings()), MobEffects.HEALTH_BOOST);
    public static final Block CHERRY_WINE = registerWine("cherry_wine", new CherryWineBlock(getWineSettings()), MobEffects.MOVEMENT_SPEED);
    public static final Block CHERRY_JAR = register("cherry_jar", new CherryJarBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final Block CHERRY_JAM = register("cherry_jam", new CherryJamBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final Block WINE_BOX = register("wine_box", new WineBoxBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion()));
    public static final Block BIG_TABLE = register("big_table", new BigTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 2.0F)));

    public static final Block WINE_RACK_4 = register("wine_rack_4", new DisplayRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Block FLOWER_BOX = register("flower_box", new FlowerBoxBlock(Blocks.AIR,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final Block FLOWER_BOX_ALLIUM = register("flower_box_allium", new FlowerBoxBlock(Blocks.ALLIUM,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_AZURE_BLUET = register("flower_box_azure_bluet", new FlowerBoxBlock(Blocks.AZURE_BLUET,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_ORCHID = register("flower_box_blue_orchid", new FlowerBoxBlock(Blocks.BLUE_ORCHID,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_CORNFLOWER = register("flower_box_cornflower", new FlowerBoxBlock(Blocks.CORNFLOWER,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_DANDELION = register("flower_box_dandelion", new FlowerBoxBlock(Blocks.DANDELION,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_LILY_OF_THE_VALLEY = register("flower_box_lily_of_the_valley", new FlowerBoxBlock(Blocks.LILY_OF_THE_VALLEY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_ORANGE_TULIP = register("flower_box_orange_tulip", new FlowerBoxBlock(Blocks.ORANGE_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_OXEYE_DAISY = register("flower_box_oxeye_daisy", new FlowerBoxBlock(Blocks.OXEYE_DAISY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_PINK_TULIP = register("flower_box_pink_tulip", new FlowerBoxBlock(Blocks.PINK_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_POPPY = register("flower_box_poppy", new FlowerBoxBlock(Blocks.POPPY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_RED_TULIP = register("flower_box_red_tulip", new FlowerBoxBlock(Blocks.RED_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_WHITE_TULIP = register("flower_box_white_tulip", new FlowerBoxBlock(Blocks.WHITE_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);
    public static final Block FLOWER_BOX_BLUE_WHITER_ROSE = register("flower_box_whiter_rose", new FlowerBoxBlock(Blocks.WITHER_ROSE,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)), false);

    public static final Block FLOWER_POT = register("flower_pot", new FlowerPotBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final Block BASKET = register("basket", new BasketBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), 1));
    public static final Block COOKING_POT = register("cooking_pot", new CookingPotBlock(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion()));
    public static final Block STACKABLE_LOG = register("stackable_log", new StackableLogBlock(getLogBlockSettings().noOcclusion().lightLevel(state -> state.getValue(StackableLogBlock.FIRED) ? 13 : 0)));
    public static final Item FAUCET = register("faucet", new FaucetItem(getSettings()));

    public static final Item STRAW_HAT = register("straw_hat", new StrawHatItem(getSettings().rarity(Rarity.RARE)));
    public static final Item VINEMAKER_APRON = register("vinemaker_apron", new WinemakerArmorItem(VineryMaterials.VINEMAKER_ARMOR, EquipmentSlot.CHEST, getSettings().rarity(Rarity.EPIC)));
    public static final Item VINEMAKER_LEGGINGS = register("vinemaker_leggings", new WinemakerArmorItem(VineryMaterials.VINEMAKER_ARMOR, EquipmentSlot.LEGS, getSettings().rarity(Rarity.RARE)));
    public static final Item VINEMAKER_BOOTS = register("vinemaker_boots", new WinemakerArmorItem(VineryMaterials.VINEMAKER_ARMOR, EquipmentSlot.FEET, getSettings().rarity(Rarity.RARE)));
    public static final Item GLOVES = register("gloves", new GlovesItem(getSettings().rarity(Rarity.RARE)));

    public static final Item CHOCOLATE_BREAD = register("chocolate_bread", new ChocolateBreadItem(getSettings().food(Foods.BREAD)));
    public static final Item TOAST = register("toast", new ToastItem(getSettings().food(Foods.BEETROOT_SOUP)));
    public static final Item DONUT = register("donut", new DoughnutItem(getSettings().food(Foods.CARROT)));
    public static final Item MILK_BREAD = register("milk_bread", new MilkBreadItem(getSettings().food(Foods.COOKIE)));
    public static final Block CRUSTY_BREAD = register("crusty_bread", new BreadBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion()));

    public static final Item MULE_SPAWN_EGG = register("mule_spawn_egg", new SpawnEggItem(MULE, 0x8b7867, 0x5a4e43, getSettings()));
    public static final Item WANDERING_WINEMAKER_SPAWN_EGG = register("wandering_winemaker_spawn_egg", new SpawnEggItem(WANDERING_WINEMAKER, 0xb78272, 0x3c4a73, getSettings()));


    private static RotatedPillarBlock registerLog(String path) {
        return register(path, new RotatedPillarBlock(getLogBlockSettings()));
    }

    private static <T extends Block> T register(String path, T block) {
        return register(path, block, true);
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem) {
        return register(path, block, registerItem, settings -> {});
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem, Consumer<Item.Properties> consumer) {
        return register(path, block, registerItem, BlockItem::new, consumer);
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem, BiFunction<T, Item.Properties, ? extends BlockItem> function,  Consumer<Item.Properties> consumer) {
        BLOCKS.register(path, () -> block);
        if (registerItem) {
            ITEMS.register(path, () -> function.apply(block, getSettings(consumer)));
        }
        return block;
    }

    private static <T extends Block> T registerWine(String path, T block, MobEffect effect) {
        return register(path, block, true, DrinkBlockItem::new, settings -> settings.food(wineFoodComponent(effect)));
    }

    private static <T extends Block> T registerBigWine(String path, T block, MobEffect effect) {
        return register(path, block, true, DrinkBlockBigItem::new, settings -> settings.food(wineFoodComponent(effect)));
    }

    private static FoodProperties wineFoodComponent(MobEffect effect) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(1);
        if(effect != null) component.effect(new MobEffectInstance(effect, 45 * 20), 1.0f);
        return component.build();
    }

    private static <T extends Item> T register(String path, T item) {
        final ResourceLocation id = new VineryIdentifier(path);
        ITEMS.register(path, () -> item);
        return item;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        /*
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(CHERRY_PLANKS, 5, 20);
        flammableRegistry.add(STRIPPED_CHERRY_LOG, 5, 5);
        flammableRegistry.add(STRIPPED_OLD_CHERRY_LOG, 5, 5);
        flammableRegistry.add(CHERRY_LOG, 5, 5);
        flammableRegistry.add(OLD_CHERRY_LOG, 5, 5);
        flammableRegistry.add(STRIPPED_CHERRY_WOOD, 5, 5);
        flammableRegistry.add(CHERRY_WOOD, 5, 5);
        flammableRegistry.add(OLD_CHERRY_WOOD, 5, 5);
        flammableRegistry.add(STRIPPED_OLD_CHERRY_WOOD, 5, 5);
        flammableRegistry.add(CHERRY_SLAB, 5, 20);
        flammableRegistry.add(CHERRY_STAIRS, 5, 20);
        flammableRegistry.add(CHERRY_FENCE, 5, 20);
        flammableRegistry.add(CHERRY_FENCE_GATE, 5, 20);
        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(CHERRY_FENCE, 300);
        fuelRegistry.add(CHERRY_FENCE_GATE, 300);
        fuelRegistry.add(STACKABLE_LOG, 300);
        fuelRegistry.add(FERMENTATION_BARREL, 300);

         */
    }

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties().tab(Vinery.CREATIVE_TAB);
        consumer.accept(settings);
        return settings;
    }

    private static Item.Properties getSettings() {
        return getSettings(settings -> {});
    }

    private static BlockBehaviour.Properties getVineSettings() {
        return BlockBehaviour.Properties.copy(Blocks.VINE);
    }

    private static BlockBehaviour.Properties getBushSettings() {
        return BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH);
    }


    private static BlockBehaviour.Properties getGrassSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).noOcclusion();
    }

    private static BlockBehaviour.Properties getGrapevineSettings() {
        return BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).randomTicks().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties getLogBlockSettings() {
        return BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties getSlabSettings() {
        return getLogBlockSettings().explosionResistance(3.0F);
    }

    private static BlockBehaviour.Properties getWineSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak();
    }

}
