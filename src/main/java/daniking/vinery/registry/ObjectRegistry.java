package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.block.FlowerPotBlock;
import daniking.vinery.block.*;
import daniking.vinery.item.*;
import daniking.vinery.mixin.WoodTypeAccessor;
import daniking.vinery.util.GrapevineType;
import daniking.vinery.world.VineryConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vinery.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vinery.MODID);

    // Red Grapes
    public static final RegistryObject<Block> RED_GRAPE_BUSH = register("red_grape_bush", () -> new GrapeBush(getBushSettings(), GrapevineType.RED));

    public static final RegistryObject<Item> RED_GRAPE = registerItem("red_grape", () -> new GrapeItem(getSettings().food(Foods.SWEET_BERRIES), GrapevineType.RED));

    public static final RegistryObject<Block> WHITE_GRAPE_BUSH = register("white_grape_bush", () -> new GrapeBush(getBushSettings(), GrapevineType.WHITE));
    public static final RegistryObject<Item> WHITE_GRAPE = registerItem("white_grape", () -> new GrapeItem(getSettings().food(Foods.SWEET_BERRIES), GrapevineType.WHITE));

    public static final RegistryObject<Item> CHERRY = registerItem("cherry", () -> new CherryItem(getSettings().food(Foods.COOKIE)));


    public static final RegistryObject<Block> CHERRY_SAPLING = register("cherry_sapling", () -> new SaplingBlock(new AbstractTreeGrower() {
        @Override
        protected @NotNull Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean bees) {
            if (random.nextBoolean()) return VineryConfiguredFeatures.CHERRY.getHolder().get();
            return VineryConfiguredFeatures.CHERRY_VARIANT.getHolder().get();
        }
    }, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> OLD_CHERRY_SAPLING = register("old_cherry_sapling", () -> new SaplingBlock(new AbstractTreeGrower() {
        @Override
        protected @NotNull Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean bees) {
            if (random.nextBoolean()) {
                if (bees) return VineryConfiguredFeatures.OLD_CHERRY_BEE.getHolder().get();
                return VineryConfiguredFeatures.OLD_CHERRY.getHolder().get();
            } else {
                if (bees) return VineryConfiguredFeatures.OLD_CHERRY_VARIANT_WITH_BEE.getHolder().get();
                return VineryConfiguredFeatures.OLD_CHERRY_VARIANT.getHolder().get();
            }
        }
    }, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));


    public static final RegistryObject<Block> GRAPEVINE_LEAVES = register("grapevine_leaves", () -> new GrapevineLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Item>  GRAPEVINE_LEAVES_ITEM = registerItem("grapevine_leaves", () -> new BlockItem(GRAPEVINE_LEAVES.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_LEAVES = register("cherry_leaves", VariantLeavesBlock::new);
    public static final RegistryObject<Item>  CHERRY_LEAVES_ITEM = registerItem("cherry_leaves", () -> new BlockItem(CHERRY_LEAVES.get(), getSettings()));
    public static final RegistryObject<Block> WHITE_GRAPE_CRATE = register("white_grape_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item>  WHITE_GRAPE_CRATE_ITEM = registerItem("white_grape_crate", () -> new BlockItem(WHITE_GRAPE_CRATE.get(), getSettings()));
    public static final RegistryObject<Block> RED_GRAPE_CRATE = register("red_grape_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item>  RED_GRAPE_CRATE_ITEM = registerItem("red_grape_crate", () -> new BlockItem(RED_GRAPE_CRATE.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_CRATE = register("cherry_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item>  CHERRY_CRATE_ITEM = registerItem("cherry_crate", () -> new BlockItem(CHERRY_CRATE.get(), getSettings()));
    public static final RegistryObject<Block> GRAPEVINE_STEM = register("grapevine_stem", () -> new GrapevineStemBlock(getGrapevineSettings()));
    public static final RegistryObject<Item>  GRAPEVINE_STEM_ITEM = registerItem("grapevine_stem", () -> new BlockItem(GRAPEVINE_STEM.get(), getSettings()));
    public static final RegistryObject<Block> GRAPEVINE_POT = register("grapevine_pot", () -> new GrapevinePotBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
    public static final RegistryObject<Item>  GRAPEVINE_POT_ITEM = registerItem("grapevine_pot", () -> new BlockItem(GRAPEVINE_POT.get(), getSettings()));
    public static final RegistryObject<Block> FERMENTATION_BARREL = register("fermentation_barrel", () -> new FermentationBarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Item>  FERMENTATION_BARREL_ITEM = registerItem("fermentation_barrel", () -> new BlockItem(FERMENTATION_BARREL.get(), getSettings()));
    public static final RegistryObject<Block> WINE_PRESS = register("wine_press", () -> new WinePressBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Item>  WINE_PRESS_ITEM = registerItem("wine_press", () -> new BlockItem(WINE_PRESS.get(), getSettings()));
    public static final RegistryObject<Block> CHAIR = register("chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Item>  CHAIR_ITEM = registerItem("chair", () -> new BlockItem(CHAIR.get(), getSettings()));
    public static final RegistryObject<Block> TABLE = register("table", () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Item>  TABLE_ITEM = registerItem("table", () -> new BlockItem(TABLE.get(), getSettings()));
    public static final RegistryObject<Block> WOOD_FIRED_OVEN = register("wood_fired_oven", () -> new WoodFiredOvenBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(WoodFiredOvenBlock.LIT) ? 13 : 0)));
    public static final RegistryObject<Item>  WOOD_FIRED_OVEN_ITEM = registerItem("wood_fired_oven", () -> new BlockItem(WOOD_FIRED_OVEN.get(), getSettings()));
    public static final RegistryObject<Block> STOVE = register("stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(block -> 12)));
    public static final RegistryObject<Item>  STOVE_ITEM = registerItem("stove", () -> new BlockItem(STOVE.get(), getSettings()));
    public static final RegistryObject<Block> KITCHEN_SINK = register("kitchen_sink", () -> new KitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Item>  KITCHEN_SINK_ITEM = registerItem("kitchen_sink", () -> new BlockItem(KITCHEN_SINK.get(), getSettings()));
    public static final RegistryObject<Block> WINE_RACK_1 = register("wine_rack_1", () -> new WineRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), 9, 1));
    public static final RegistryObject<Item>  WINE_RACK_1_ITEM = registerItem("wine_rack_1", () -> new BlockItem(WINE_RACK_1.get(), getSettings()));
    public static final RegistryObject<Block> WINE_RACK_2 = register("wine_rack_2", () -> new WineRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion(), 3, 2));
    public static final RegistryObject<Item>  WINE_RACK_2_ITEM = registerItem("wine_rack_2", () -> new BlockItem(WINE_RACK_2.get(), getSettings()));
    public static final RegistryObject<Block> WINE_RACK_3 = register("wine_rack_3", () -> new WineRackStorageBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN.get(), VinerySoundEvents.WINE_RACK_3_CLOSE.get()));
    public static final RegistryObject<Item>  WINE_RACK_3_ITEM = registerItem("wine_rack_3", () -> new BlockItem(WINE_RACK_3.get(), getSettings()));
    public static final RegistryObject<Block> WINE_RACK_5 = register("wine_rack_5", () -> new WineRackStorageBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN.get(), VinerySoundEvents.WINE_RACK_5_CLOSE.get()));
    public static final RegistryObject<Item>  WINE_RACK_5_ITEM = registerItem("wine_rack_5", () -> new BlockItem(WINE_RACK_5.get(), getSettings()));
    public static final RegistryObject<Block> BARREL = register("barrel", () -> new BarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
    public static final RegistryObject<Item>  BARREL_ITEM = registerItem("barrel", () -> new BlockItem(BARREL.get(), getSettings()));
    public static final RegistryObject<Block> STORAGE_POT = register("storage_pot", () -> new StoragePotBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.DYE_USE, SoundEvents.DYE_USE));
    public static final RegistryObject<Item>  STORAGE_POT_ITEM = registerItem("storage_pot", () -> new BlockItem(STORAGE_POT.get(), getSettings()));


    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = registerLog("stripped_cherry_log");
    public static final RegistryObject<Item>  STRIPPED_CHERRY_LOG_ITEM = registerItem("stripped_cherry_log", () -> new BlockItem(STRIPPED_CHERRY_LOG.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_LOG = registerLog("cherry_log");
    public static final RegistryObject<Item>  CHERRY_LOG_ITEM = registerItem("cherry_log", () -> new BlockItem(CHERRY_LOG.get(), getSettings()));
    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = registerLog("stripped_cherry_wood");
    public static final RegistryObject<Item>  STRIPPED_CHERRY_WOOD_ITEM = registerItem("stripped_cherry_wood", () -> new BlockItem(STRIPPED_CHERRY_WOOD.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_WOOD = registerLog("cherry_wood");
    public static final RegistryObject<Item>  CHERRY_WOOD_ITEM = registerItem("cherry_wood", () -> new BlockItem(CHERRY_WOOD.get(), getSettings()));
    public static final RegistryObject<Block> STRIPPED_OLD_CHERRY_LOG = registerLog("stripped_old_cherry_log");
    public static final RegistryObject<Item>  STRIPPED_OLD_CHERRY_LOG_ITEM = registerItem("stripped_old_cherry_log", () -> new BlockItem(STRIPPED_OLD_CHERRY_LOG.get(), getSettings()));
    public static final RegistryObject<Block> OLD_CHERRY_LOG = registerLog("old_cherry_log");
    public static final RegistryObject<Item>  OLD_CHERRY_LOG_ITEM = registerItem("old_cherry_log", () -> new BlockItem(OLD_CHERRY_LOG.get(), getSettings()));
    public static final RegistryObject<Block> STRIPPED_OLD_CHERRY_WOOD = registerLog("stripped_old_cherry_wood");
    public static final RegistryObject<Item>  STRIPPED_OLD_CHERRY_WOOD_ITEM = registerItem("stripped_old_cherry_wood", () -> new BlockItem(STRIPPED_OLD_CHERRY_WOOD.get(), getSettings()));
    public static final RegistryObject<Block> OLD_CHERRY_WOOD = registerLog("old_cherry_wood");
    public static final RegistryObject<Item>  OLD_CHERRY_WOOD_ITEM = registerItem("old_cherry_wood", () -> new BlockItem(OLD_CHERRY_WOOD.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_BEAM = registerLog("cherry_beam");
    public static final RegistryObject<Item>  CHERRY_BEAM_ITEM = registerItem("cherry_beam", () -> new BlockItem(CHERRY_BEAM.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_PLANKS = register("cherry_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }
        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Item> CHERRY_PLANK_ITEM = registerItem("cherry_planks", () -> new BlockItem(CHERRY_PLANKS.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_FLOORBOARD = register("cherry_floorboard", () -> new Block(BlockBehaviour.Properties.copy(CHERRY_PLANKS.get())));
    public static final RegistryObject<Item>  CHERRY_FLOORBOARD_ITEM = registerItem("cherry_floorboard", () -> new BlockItem(CHERRY_FLOORBOARD.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_STAIRS = register("cherry_stairs", () -> new StairBlock(CHERRY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CHERRY_PLANKS.get())));
    public static final RegistryObject<Item>  CHERRY_STAIRS_ITEM = registerItem("cherry_stairs", () -> new BlockItem(CHERRY_STAIRS.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_SLAB = register("cherry_slab", () -> new SlabBlock(getSlabSettings()));
    public static final RegistryObject<Item>  CHERRY_SLAB_ITEM = registerItem("cherry_slab", () -> new BlockItem(CHERRY_SLAB.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_FENCE = register("cherry_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Item>  CHERRY_FENCE_ITEM = registerItem("cherry_fence", () -> new BlockItem(CHERRY_FENCE.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = register("cherry_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Item>  CHERRY_FENCE_GATE_ITEM = registerItem("cherry_fence_gate", () -> new BlockItem(CHERRY_FENCE_GATE.get(), getSettings()));

    public static final RegistryObject<Block> CHERRY_BUTTON = register("cherry_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Item>  CHERRY_BUTTON_ITEM = registerItem("cherry_button", () -> new BlockItem(CHERRY_BUTTON.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = register("cherry_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<Item>  CHERRY_PRESSURE_PLATE_ITEM = registerItem("cherry_pressure_plate", () -> new BlockItem(CHERRY_PRESSURE_PLATE.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_DOOR = register("cherry_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Item>  CHERRY_DOOR_ITEM = registerItem("cherry_door", () -> new BlockItem(CHERRY_DOOR.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = register("cherry_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Item>  CHERRY_TRAPDOOR_ITEM = registerItem("cherry_trapdoor", () -> new BlockItem(CHERRY_TRAPDOOR.get(), getSettings()));

    public static final WoodType CHERRY_WOOD_TYPE = WoodTypeAccessor.callRegister(WoodTypeAccessor.callCreate("cherry"));
    public static final RegistryObject<Block> CHERRY_SIGN = register("cherry_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0f).sound(SoundType.WOOD), CHERRY_WOOD_TYPE));
    public static final RegistryObject<Block> CHERRY_WALL_SIGN = register("cherry_wall_sign",
            () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0f).sound(SoundType.WOOD).dropsLike(CHERRY_SIGN.get()), CHERRY_WOOD_TYPE));
    public static final RegistryObject<Item> CHERRY_SIGN_ITEM = registerItem("cherry_sign", () -> new SignItem(getSettings().stacksTo(16), CHERRY_SIGN.get(), CHERRY_WALL_SIGN.get()));

    public static final RegistryObject<Block> WINDOW = register("window", () -> new WindowBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)));
    public static final RegistryObject<Item>  WINDOW_ITEM = registerItem("window", () -> new BlockItem(WINDOW.get(), getSettings()));
    public static final RegistryObject<Block> LOAM = register("loam", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final RegistryObject<Item>  LOAM_ITEM = registerItem("loam", () -> new BlockItem(LOAM.get(), getSettings()));
    public static final RegistryObject<Block> LOAM_STAIRS = register("loam_stairs", () -> new StairBlock(LOAM.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final RegistryObject<Item>  LOAM_STAIRS_ITEM = registerItem("loam_stairs", () -> new BlockItem(LOAM_STAIRS.get(), getSettings()));
    public static final RegistryObject<Block> LOAM_SLAB = register("loam_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0F, 3.0F).sound(SoundType.MUD)));
    public static final RegistryObject<Item>  LOAM_SLAB_ITEM = registerItem("loam_slab", () -> new BlockItem(LOAM_SLAB.get(), getSettings()));
    public static final RegistryObject<Block> COARSE_DIRT_SLAB = register("coarse_dirt_slab", () -> new VariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Item>  COARSE_DIRT_SLAB_ITEM = registerItem("coarse_dirt_slab", () -> new BlockItem(COARSE_DIRT_SLAB.get(), getSettings()));
    public static final RegistryObject<Block> DIRT_SLAB = register("dirt_slab", () -> new VariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Item>  DIRT_SLAB_ITEM = registerItem("dirt_slab", () -> new BlockItem(DIRT_SLAB.get(), getSettings()));
    public static final RegistryObject<Block> GRASS_SLAB = register("grass_slab", () -> new SnowyVariantSlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Item>  GRASS_SLAB_ITEM = registerItem("grass_slab", () -> new BlockItem(GRASS_SLAB.get(), getSettings()));
    public static final RegistryObject<Block> WINE_BOTTLE = register("wine_bottle", () -> new EmptyWineBottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instabreak().noOcclusion()));
    public static final RegistryObject<Item>  WINE_BOTTLE_ITEM = registerItem("wine_bottle", () -> new BlockItem(WINE_BOTTLE.get(), getSettings()));
    public static final RegistryObject<Block> RED_GRAPEJUICE_WINE_BOTTLE = register("red_grapejuice_wine_bottle", () -> new RedGrapejuiceWineBottle(getWineSettings()));
    public static final RegistryObject<Item>  RED_GRAPEJUICE_WINE_BOTTLE_ITEM = registerItem("red_grapejuice_wine_bottle", () -> new BlockItem(RED_GRAPEJUICE_WINE_BOTTLE.get(), getSettings()));
    public static final RegistryObject<Block> WHITE_GRAPEJUICE_WINE_BOTTLE = register("white_grapejuice_wine_bottle", () -> new WhiteGrapejuiceWineBottle(getWineSettings()));
    public static final RegistryObject<Item>  WHITE_GRAPEJUICE_WINE_BOTTLE_ITEM = registerItem("white_grapejuice_wine_bottle", () -> new BlockItem(WHITE_GRAPEJUICE_WINE_BOTTLE.get(), getSettings()));
    public static final RegistryObject<Block> CHENET_WINE = register("chenet_wine", () -> new ChenetBottleBlock(getWineSettings())/*, MobEffects.JUMP*/);
    public static final RegistryObject<Item>  CHENET_WINE_ITEM = registerItem("chenet_wine", () -> new BlockItem(CHENET_WINE.get(), getSettings()));
    public static final RegistryObject<Block> KING_DANIS_WINE = register("king_danis_wine", () -> new KingDanisBottleBlock(getWineSettings())/*, MobEffects.LUCK*/);
    public static final RegistryObject<Item>  KING_DANIS_WINE_ITEM = registerItem("king_danis_wine", () -> new BlockItem(KING_DANIS_WINE.get(), getSettings()));
    public static final RegistryObject<Block> NOIR_WINE = register("noir_wine", () -> new WineBottleBlock(getWineSettings())/*, MobEffects.WATER_BREATHING*/);
    public static final RegistryObject<Item>  NOIR_WINE_ITEM = registerItem("noir_wine", () -> new BlockItem(NOIR_WINE.get(), getSettings()));
    public static final RegistryObject<Block> CLARK_WINE = register("clark_wine", () -> new WineBottleBlock(getWineSettings())/*, MobEffects.FIRE_RESISTANCE*/);
    public static final RegistryObject<Item>  CLARK_WINE_ITEM = registerItem("clark_wine", () -> new BlockItem(CLARK_WINE.get(), getSettings()));
    public static final RegistryObject<Block> MELLOHI_WINE = register("mellohi_wine", () -> new MellohiWineBlock(getWineSettings())/*, MobEffects.DAMAGE_BOOST*/);
    public static final RegistryObject<Item>  MELLOHI_WINE_ITEM = registerItem("mellohi_wine", () -> new BlockItem(MELLOHI_WINE.get(), getSettings()));
    public static final RegistryObject<Block> BOLVAR_WINE = register("bolvar_wine", () -> new WineBottleBlock(getWineSettings())/*, MobEffects.HEALTH_BOOST*/);
    public static final RegistryObject<Item>  BOLVAR_WINE_ITEM = registerItem("bolvar_wine", () -> new BlockItem(BOLVAR_WINE.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_WINE = register("cherry_wine", () -> new CherryWineBlock(getWineSettings())/*, MobEffects.MOVEMENT_SPEED*/);
    public static final RegistryObject<Item>  CHERRY_WINE_ITEM = registerItem("cherry_wine", () -> new BlockItem(CHERRY_WINE.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_JAR = register("cherry_jar", () -> new CherryJarBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final RegistryObject<Item>  CHERRY_JAR_ITEM= registerItem("cherry_jar", () -> new BlockItem(CHERRY_JAR.get(), getSettings()));
    public static final RegistryObject<Block> CHERRY_JAM = register("cherry_jam", () -> new CherryJamBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final RegistryObject<Item>  CHERRY_JAM_ITEM = registerItem("cherry_jam", () -> new BlockItem(CHERRY_JAM.get(), getSettings()));
    public static final RegistryObject<Block> WINE_BOX = register("wine_box", () -> new WineBoxBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion()));
    public static final RegistryObject<Item>  WINE_BOX_ITEM = registerItem("wine_box", () -> new BlockItem(WINE_BOX.get(), getSettings()));
    public static final RegistryObject<Block> BIG_TABLE = register("big_table", () -> new BigTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 2.0F)));
    public static final RegistryObject<Item>  BIG_TABLE_ITEM = registerItem("big_table", () -> new BlockItem(BIG_TABLE.get(), getSettings()));
    public static final RegistryObject<Block> WINE_RACK_4 = register("wine_rack_4", () -> new DisplayRackBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Item>  WINE_RACK_4_ITEM = registerItem("wine_rack_4", () -> new BlockItem(WINE_RACK_4.get(), getSettings()));
    public static final RegistryObject<Block> FLOWER_BOX = register("flower_box", () -> new FlowerBoxBlock(Blocks.AIR,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Item>  FLOWER_BOX_ITEM = registerItem("flower_box", () -> new BlockItem(FLOWER_BOX.get(), getSettings()));
    public static final RegistryObject<Block> FLOWER_BOX_ALLIUM = register("flower_box_allium", () -> new FlowerBoxBlock(Blocks.ALLIUM,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_AZURE_BLUET = register("flower_box_azure_bluet", () -> new FlowerBoxBlock(Blocks.AZURE_BLUET,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_ORCHID = register("flower_box_blue_orchid", () -> new FlowerBoxBlock(Blocks.BLUE_ORCHID,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_CORNFLOWER = register("flower_box_cornflower", () -> new FlowerBoxBlock(Blocks.CORNFLOWER,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_DANDELION = register("flower_box_dandelion", () -> new FlowerBoxBlock(Blocks.DANDELION,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_LILY_OF_THE_VALLEY = register("flower_box_lily_of_the_valley", () -> new FlowerBoxBlock(Blocks.LILY_OF_THE_VALLEY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_ORANGE_TULIP = register("flower_box_orange_tulip", () -> new FlowerBoxBlock(Blocks.ORANGE_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_OXEYE_DAISY = register("flower_box_oxeye_daisy", () -> new FlowerBoxBlock(Blocks.OXEYE_DAISY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_PINK_TULIP = register("flower_box_pink_tulip", () -> new FlowerBoxBlock(Blocks.PINK_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_POPPY = register("flower_box_poppy", () -> new FlowerBoxBlock(Blocks.POPPY,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_RED_TULIP = register("flower_box_red_tulip", () -> new FlowerBoxBlock(Blocks.RED_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_WHITE_TULIP = register("flower_box_white_tulip", () -> new FlowerBoxBlock(Blocks.WHITE_TULIP,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FLOWER_BOX_BLUE_WHITER_ROSE = register("flower_box_whiter_rose", () -> new FlowerBoxBlock(Blocks.WITHER_ROSE,BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<Block> FLOWER_POT = register("flower_pot", () -> new FlowerPotBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Item>  FLOWER_POT_ITEM = registerItem("flower_pot", () -> new BlockItem(FLOWER_POT.get(), getSettings()));

    public static final RegistryObject<Block> BASKET = register("basket", () -> new BasketBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion(), 1));
    public static final RegistryObject<Item>  BASKET_ITEM = registerItem("basket", () -> new BlockItem(BASKET.get(), getSettings()));
    public static final RegistryObject<Block> COOKING_POT = register("cooking_pot", () -> new CookingPotBlock(BlockBehaviour.Properties.of(Material.STONE).instabreak().noOcclusion()));
    public static final RegistryObject<Item>  COOKING_POT_ITEM = registerItem("cooking_pot", () -> new BlockItem(COOKING_POT.get(), getSettings()));
    public static final RegistryObject<Block> STACKABLE_LOG = register("stackable_log", () -> new StackableLogBlock(getLogBlockSettings().noOcclusion().lightLevel(state -> state.getValue(StackableLogBlock.FIRED) ? 13 : 0)));
    public static final RegistryObject<Item>  STACKABLE_LOG_ITEM = registerItem("stackable_log", () -> new BlockItem(STACKABLE_LOG.get(), getSettings()));
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
    public static final RegistryObject<Item>  CRUSTY_BREAD_ITEM = registerItem("crusty_bread", () -> new BlockItem(CRUSTY_BREAD.get(), getSettings()));


    public static final RegistryObject<Item> MULE_SPAWN_EGG = registerItem("mule_spawn_egg", () -> new ForgeSpawnEggItem(VineryEntites.MULE, 0x8b7867, 0x5a4e43, getSettings()));
    public static final RegistryObject<Item> WANDERING_WINEMAKER_SPAWN_EGG = registerItem("wandering_winemaker_spawn_egg", () -> new ForgeSpawnEggItem(VineryEntites.WANDERING_WINEMAKER, 0xb78272, 0x3c4a73, getSettings()));

    private static <T extends Block> RegistryObject<T> register(String path, Supplier<T> block) {
        return BLOCKS.register(path, block);
    }

    private static RegistryObject<Item> registerBlockItem(String path, Block block, Item.Properties properties) {
        return ITEMS.register(path, () -> new BlockItem(block, properties));
    }

    private static RegistryObject<Block> registerLog(String path) {
        return register(path, () -> new FlammableRotatedPillarBlock(getLogBlockSettings()));
    }

    private static <T extends Item> RegistryObject<T> registerItem(String path, Supplier<T> item) {
        return ITEMS.register(path, item);
    }

    /*
    private static <T extends Block> RegistryObject<T> registerWine(String path, Supplier<T> block, MobEffect effect) {
        ITEMS.register(path, () -> new DrinkBlockItem(block.get(), new Item.Properties().tab(Vinery.CREATIVE_TAB).food(wineFoodComponent(effect))));
        return register(path, block);
    }

    private static <T extends Block> RegistryObject<T> registerBigWine(String path, Supplier<T> block, MobEffect effect) {
        ITEMS.register(path, () -> new DrinkBlockBigItem(block.get(), new Item.Properties().tab(Vinery.CREATIVE_TAB).food(wineFoodComponent(effect))));
        return register(path, block);
    }
    */


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
        flammableRegistry.add(CHERRY_SLAB, 5, 20);
        flammableRegistry.add(CHERRY_STAIRS, 5, 20);
        flammableRegistry.add(CHERRY_FENCE, 5, 20);
        flammableRegistry.add(CHERRY_FENCE_GATE, 5, 20);
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
