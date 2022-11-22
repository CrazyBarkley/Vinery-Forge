package daniking.vinery.registry;

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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vinery.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vinery.MODID);


    // Red Grapes
    public static final RegistryObject<Block> RED_GRAPE_BUSH = registerWithoutItem("red_grape_bush", () -> new GrapeBush(getBushSettings(), GrapevineType.RED));
    public static final RegistryObject<Item> RED_GRAPE_SEEDS = registerItem("red_grape_seeds", () -> new GrapeBushSeedItem(RED_GRAPE_BUSH.get(), getSettings(), GrapevineType.RED));

    public static final RegistryObject<Item> RED_GRAPE = registerItem("red_grape", () -> new GrapeItem(getSettings().food(Foods.SWEET_BERRIES), GrapevineType.RED));

    public static final RegistryObject<Block> WHITE_GRAPE_BUSH = registerWithoutItem("white_grape_bush", () -> new GrapeBush(getBushSettings(), GrapevineType.WHITE));
    public static final RegistryObject<Item> WHITE_GRAPE_SEEDS = registerItem("white_grape_seeds", () -> new GrapeBushSeedItem(WHITE_GRAPE_BUSH.get(), getSettings(), GrapevineType.WHITE));
    public static final RegistryObject<Item> WHITE_GRAPE = registerItem("white_grape", () -> new GrapeItem(getSettings().food(Foods.SWEET_BERRIES), GrapevineType.WHITE));

    public static final RegistryObject<Item> CHERRY = registerItem("cherry", () -> new CherryItem(getSettings().food(Foods.COOKIE)));

    /*
    public static final RegistryObject<Block> CHERRY_SAPLING = register("cherry_sapling", new SaplingBlock(new AbstractTreeGrower() {
        @Nullable
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
            if (random.nextBoolean()) return VineryConfiguredFeatures.CHERRY;
            return VineryConfiguredFeatures.CHERRY_VARIANT;
        }
    }, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> OLD_CHERRY_SAPLING = register("old_cherry_sapling", new SaplingBlock(new AbstractTreeGrower() {
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

    public static final RegistryObject<Block> GRAPEVINE_LEAVES = register("grapevine_leaves", () -> new GrapevineLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> CHERRY_LEAVES = register("cherry_leaves", VariantLeavesBlock::new);

    public static final RegistryObject<Block> WHITE_GRAPE_CRATE = register("white_grape_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RED_GRAPE_CRATE = register("red_grape_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHERRY_CRATE = register("cherry_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> GRAPEVINE_STEM = register("grapevine_stem", () -> new GrapevineStemBlock(getGrapevineSettings()));

    public static final RegistryObject<Block> GRAPEVINE_POT = register("grapevine_pot", () -> new GrapevinePotBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));

    public static final RegistryObject<Block> FERMENTATION_BARREL = register("fermentation_barrel", () -> new FermentationBarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));

    public static final RegistryObject<Block> WINE_PRESS = register("wine_press", () -> new WinePressBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CHAIR = register("chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> TABLE = register("table", () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> WOOD_FIRED_OVEN = register("wood_fired_oven", () -> new WoodFiredOvenBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(WoodFiredOvenBlock.LIT) ? 13 : 0)));
    public static final RegistryObject<Block> STOVE = register("stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(block -> 12)));

    public static final RegistryObject<Block> KITCHEN_SINK = register("kitchen_sink", () -> new KitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static final RegistryObject<Block> WINE_RACK_1 = register("wine_rack_1", () -> new WineRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), 9, 1));
    public static final RegistryObject<Block> WINE_RACK_2 = register("wine_rack_2", () -> new WineRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), 3, 2));
    public static final RegistryObject<Block> WINE_RACK_3 = register("wine_rack_3", () -> new WineRackStorageBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN.get(), VinerySoundEvents.WINE_RACK_3_CLOSE.get()));
    public static final RegistryObject<Block> WINE_RACK_5 = register("wine_rack_5", () -> new WineRackStorageBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN.get(), VinerySoundEvents.WINE_RACK_5_CLOSE.get()));

    public static final RegistryObject<Block> BARREL = register("barrel", () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));

    public static final RegistryObject<Block> STORAGE_POT = register("storage_pot", () -> new StoragePotBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.DYE_USE, SoundEvents.DYE_USE));
    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = registerLog("stripped_cherry_log");
    //public static final RegistryObject<Block> CHERRY_LOG = register("cherry_log", new StrippableLogBlock(() -> STRIPPED_CHERRY_LOG, MaterialColor.WOOD, getLogBlockSettings()));
    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = registerLog("stripped_cherry_wood");
    //public static final RegistryObject<Block> CHERRY_WOOD = register("cherry_wood", new StrippableLogBlock(() -> STRIPPED_CHERRY_WOOD, MaterialColor.WOOD, getLogBlockSettings()));
    public static final RegistryObject<Block> STRIPPED_OLD_CHERRY_LOG = registerLog("stripped_old_cherry_log");
    //public static final RegistryObject<Block> OLD_CHERRY_LOG = register("old_cherry_log", new StrippableLogBlock(() -> STRIPPED_OLD_CHERRY_LOG, MaterialColor.WOOD, getLogBlockSettings()));
    public static final RegistryObject<Block> STRIPPED_OLD_CHERRY_WOOD = registerLog("stripped_old_cherry_wood");
    //public static final RegistryObject<Block> OLD_CHERRY_WOOD = register("old_cherry_wood", new StrippableLogBlock(() -> STRIPPED_OLD_CHERRY_WOOD, MaterialColor.WOOD, getLogBlockSettings()));

    public static final RegistryObject<Block> CHERRY_BEAM = registerLog("cherry_beam");

    public static final RegistryObject<Block> CHERRY_PLANKS = register("cherry_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_FLOORBOARD = register("cherry_floorboard", () -> new Block(BlockBehaviour.Properties.copy(CHERRY_PLANKS.get())));
    public static final RegistryObject<Block> CHERRY_STAIRS = register("cherry_stairs", () -> new StairBlock(CHERRY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CHERRY_PLANKS.get())));
    public static final RegistryObject<Block> CHERRY_SLAB = register("cherry_slab", () -> new SlabBlock(getSlabSettings()));
    public static final RegistryObject<Block> CHERRY_FENCE = register("cherry_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = register("cherry_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> CHERRY_BUTTON = register("cherry_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = register("cherry_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<Block> CHERRY_DOOR = register("cherry_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = register("cherry_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    private static final ResourceLocation CHERRY_SIGN_TEXTURE = new VineryIdentifier("entity/sign/cherry");

    //public static final TerraformSignBlock CHERRY_SIGN = register("cherry_sign", new TerraformSignBlock(CHERRY_SIGN_TEXTURE, BlockBehaviour.Properties.copy(Blocks.OAK_SIGN)), false);
    //public static final RegistryObject<Block> CHERRY_WALL_SIGN = register("cherry_wall_sign", new TerraformWallSignBlock(CHERRY_SIGN_TEXTURE, BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)), false);
    //public static final RegistryObject<Item> CHERRY_SIGN_ITEM = register("cherry_sign", new SignItem(getSettings().stacksTo(16), CHERRY_SIGN, CHERRY_WALL_SIGN));
    public static final RegistryObject<Block> WINDOW = register("window", () -> new WindowBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)));

    public static final RegistryObject<Block> LOAM = register("loam", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final RegistryObject<Block> LOAM_STAIRS = register("loam_stairs", () -> new StairBlock(LOAM.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final RegistryObject<Block> LOAM_SLAB = register("loam_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final RegistryObject<Block> COARSE_DIRT_SLAB = register("coarse_dirt_slab", () -> new VariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> DIRT_SLAB = register("dirt_slab", () -> new VariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> GRASS_SLAB = register("grass_slab", () -> new SnowyVariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<Block> WINE_BOTTLE = register("wine_bottle", () -> new EmptyWineBottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instabreak().noOcclusion()));
    public static final RegistryObject<Block> RED_GRAPEJUICE_WINE_BOTTLE = registerWine("red_grapejuice_wine_bottle", () -> new RedGrapejuiceWineBottle(getWineSettings()), null);
    public static final RegistryObject<Block> WHITE_GRAPEJUICE_WINE_BOTTLE = registerWine("white_grapejuice_wine_bottle", () -> new WhiteGrapejuiceWineBottle(getWineSettings()), null);

    public static final RegistryObject<Block> CHENET_WINE = registerBigWine("chenet_wine", () -> new ChenetBottleBlock(getWineSettings()), MobEffects.JUMP);
    public static final RegistryObject<Block> KING_DANIS_WINE = registerBigWine("king_danis_wine", () -> new KingDanisBottleBlock(getWineSettings()), MobEffects.LUCK);
    public static final RegistryObject<Block> NOIR_WINE = registerWine("noir_wine", () -> new WineBottleBlock(getWineSettings()), MobEffects.WATER_BREATHING);
    public static final RegistryObject<Block> CLARK_WINE = registerWine("clark_wine", () -> new WineBottleBlock(getWineSettings()), MobEffects.FIRE_RESISTANCE);
    public static final RegistryObject<Block> MELLOHI_WINE = registerBigWine("mellohi_wine", () -> new MellohiWineBlock(getWineSettings()), MobEffects.DAMAGE_BOOST);
    public static final RegistryObject<Block> BOLVAR_WINE = registerWine("bolvar_wine", () -> new WineBottleBlock(getWineSettings()), MobEffects.HEALTH_BOOST);
    public static final RegistryObject<Block> CHERRY_WINE = registerWine("cherry_wine", () -> new CherryWineBlock(getWineSettings()), MobEffects.MOVEMENT_SPEED);
    public static final RegistryObject<Block> CHERRY_JAR = register("cherry_jar", () -> new CherryJarBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final RegistryObject<Block> CHERRY_JAM = register("cherry_jam", () -> new CherryJamBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final RegistryObject<Block> WINE_BOX = register("wine_box", () -> new WineBoxBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion()));
    public static final RegistryObject<Block> BIG_TABLE = register("big_table", () -> new BigTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 2.0F)));

    public static final RegistryObject<Block> WINE_RACK_4 = register("wine_rack_4", () -> new DisplayRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> FLOWER_BOX = register("flower_box", () -> new FlowerBoxBlock(Blocks.AIR,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_ALLIUM = registerWithoutItem("flower_box_allium", () -> new FlowerBoxBlock(Blocks.ALLIUM,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_AZURE_BLUET = registerWithoutItem("flower_box_azure_bluet", () -> new FlowerBoxBlock(Blocks.AZURE_BLUET,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_ORCHID = registerWithoutItem("flower_box_blue_orchid", () -> new FlowerBoxBlock(Blocks.BLUE_ORCHID,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_CORNFLOWER = registerWithoutItem("flower_box_cornflower", () -> new FlowerBoxBlock(Blocks.CORNFLOWER,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_DANDELION = registerWithoutItem("flower_box_dandelion", () -> new FlowerBoxBlock(Blocks.DANDELION,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_LILY_OF_THE_VALLEY = registerWithoutItem("flower_box_lily_of_the_valley", () -> new FlowerBoxBlock(Blocks.LILY_OF_THE_VALLEY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_ORANGE_TULIP = registerWithoutItem("flower_box_orange_tulip", () -> new FlowerBoxBlock(Blocks.ORANGE_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_OXEYE_DAISY = registerWithoutItem("flower_box_oxeye_daisy", () -> new FlowerBoxBlock(Blocks.OXEYE_DAISY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_PINK_TULIP = registerWithoutItem("flower_box_pink_tulip", () -> new FlowerBoxBlock(Blocks.PINK_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_POPPY = registerWithoutItem("flower_box_poppy", () -> new FlowerBoxBlock(Blocks.POPPY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_RED_TULIP = registerWithoutItem("flower_box_red_tulip", () -> new FlowerBoxBlock(Blocks.RED_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_WHITE_TULIP = registerWithoutItem("flower_box_white_tulip", () -> new FlowerBoxBlock(Blocks.WHITE_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_WHITER_ROSE = registerWithoutItem("flower_box_whiter_rose", () -> new FlowerBoxBlock(Blocks.WITHER_ROSE,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<Block> FLOWER_POT = register("flower_pot", () -> new FlowerPotBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<Block> BASKET = register("basket", () -> new BasketBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), 1));
    public static final RegistryObject<Block> COOKING_POT = register("cooking_pot", () -> new CookingPotBlock(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion()));
    public static final RegistryObject<Block> STACKABLE_LOG = register("stackable_log", () -> new StackableLogBlock(getLogBlockSettings().noOcclusion().lightLevel(state -> state.getValue(StackableLogBlock.FIRED) ? 13 : 0)));
    public static final RegistryObject<Item> FAUCET = registerItem("faucet", () -> new FaucetItem(getSettings()));

    public static final RegistryObject<Item> STRAW_HAT = registerItem("straw_hat", () -> new StrawHatItem(getSettings().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VINEMAKER_APRON = registerItem("vinemaker_apron", () -> new WinemakerArmorItem(VineryMaterials.VINEMAKER_ARMOR, EquipmentSlot.CHEST, getSettings().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> VINEMAKER_LEGGINGS = registerItem("vinemaker_leggings", () -> new WinemakerArmorItem(VineryMaterials.VINEMAKER_ARMOR, EquipmentSlot.LEGS, getSettings().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VINEMAKER_BOOTS = registerItem("vinemaker_boots", () -> new WinemakerArmorItem(VineryMaterials.VINEMAKER_ARMOR, EquipmentSlot.FEET, getSettings().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GLOVES = registerItem("gloves", () -> new GlovesItem(getSettings().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> CHOCOLATE_BREAD = registerItem("chocolate_bread", () -> new ChocolateBreadItem(getSettings().food(Foods.BREAD)));
    public static final RegistryObject<Item> TOAST = registerItem("toast", () -> new ToastItem(getSettings().food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> DONUT = registerItem("donut", () -> new DoughnutItem(getSettings().food(Foods.CARROT)));
    public static final RegistryObject<Item> MILK_BREAD = registerItem("milk_bread", () -> new MilkBreadItem(getSettings().food(Foods.COOKIE)));
    public static final RegistryObject<Block> CRUSTY_BREAD = register("crusty_bread", () -> new BreadBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion()));

    //public static final RegistryObject<Item> MULE_SPAWN_EGG = register("mule_spawn_egg", new SpawnEggItem(MULE, 0x8b7867, 0x5a4e43, getSettings()));
    //public static final RegistryObject<Item> WANDERING_WINEMAKER_SPAWN_EGG = register("wandering_winemaker_spawn_egg", new SpawnEggItem(WANDERING_WINEMAKER, 0xb78272, 0x3c4a73, getSettings()));


    /*
    private static RotatedPillarBlock registerLog(String path) {
        return register(path, () -> new RotatedPillarBlock(getLogBlockSettings()));
    }

     */

    private static <T extends Block> RegistryObject<T> registerWithoutItem(String path, Supplier<T> block) {
        return BLOCKS.register(path, block);
    }

    private static <T extends Block> RegistryObject<T> register(String path, Supplier<T> block) {
        //ITEMS.register(path, () -> new BlockItem(block.get(), getSettings()));
        return BLOCKS.register(path, block);
    }

    private static RegistryObject<Block> registerLog(String path) {
        return register(path, () -> new RotatedPillarBlock(getLogBlockSettings()));
    }

    private static <T extends Item> RegistryObject<T> registerItem(String path, Supplier<T> item) {
        return ITEMS.register(path, item);
    }

    private static <T extends Block> RegistryObject<T> registerWine(String path, Supplier<T> block, MobEffect effect) {
        //ITEMS.register(path, () -> new DrinkBlockItem(block.get(), new Item.Properties().tab(Vinery.CREATIVE_TAB).food(wineFoodComponent(effect))));
        return registerWithoutItem(path, block);
    }

    private static <T extends Block> RegistryObject<T> registerBigWine(String path, Supplier<T> block, MobEffect effect) {
        //ITEMS.register(path, () -> new DrinkBlockBigItem(block.get(), new Item.Properties().tab(Vinery.CREATIVE_TAB).food(wineFoodComponent(effect))));
        return registerWithoutItem(path, block);
    }


    private static FoodProperties wineFoodComponent(MobEffect effect) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(1);
        if(effect != null) component.effect(new MobEffectInstance(effect, 45 * 20), 1.0f);
        return component.build();
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
