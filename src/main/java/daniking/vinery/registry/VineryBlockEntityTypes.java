package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.VineryIdentifier;
import daniking.vinery.block.entity.*;
import daniking.vinery.block.entity.chair.ChairEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VineryBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Vinery.MODID);



    public static final RegistryObject<BlockEntityType<WoodFiredOvenBlockEntity>> WOOD_FIRED_OVEN_BLOCK_ENTITY = create("wood_fired_oven", () -> BlockEntityType.Builder.of(WoodFiredOvenBlockEntity::new, ObjectRegistry.WOOD_FIRED_OVEN.get()).build(null));
    public static final RegistryObject<BlockEntityType<CookingPotEntity>> COOKING_POT_BLOCK_ENTITY = create("cooking_pot", () -> BlockEntityType.Builder.of(CookingPotEntity::new, ObjectRegistry.COOKING_POT.get()).build(null));
    public static final RegistryObject<BlockEntityType<FermentationBarrelBlockEntity>> FERMENTATION_BARREL_ENTITY = create("fermentation_barrel", () -> BlockEntityType.Builder.of(FermentationBarrelBlockEntity::new, ObjectRegistry.FERMENTATION_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<WineRackBlockEntity>> WINE_RACK_ENTITY = create("wine_rack", () -> BlockEntityType.Builder.of(WineRackBlockEntity::new, ObjectRegistry.WINE_RACK_3.get(), ObjectRegistry.WINE_RACK_5.get()).build(null));

    public static final RegistryObject<BlockEntityType<WineRackStorageBlockEntity>> WINE_RACK_STORAGE_ENTITY = create("wine_rack_storage", () -> BlockEntityType.Builder.of(WineRackStorageBlockEntity::new, ObjectRegistry.WINE_RACK_3.get(), ObjectRegistry.WINE_RACK_5.get()).build(null));
    public static final RegistryObject<BlockEntityType<GeckoStorageBlockEntity>> WINE_RACK_GECKO_ENTITY = create("wine_rack_gecko", () -> BlockEntityType.Builder.of(
            GeckoStorageBlockEntity::new, ObjectRegistry.WINE_RACK_1.get(), ObjectRegistry.WINE_RACK_2.get(), ObjectRegistry.WINE_BOX.get(), ObjectRegistry.WINE_RACK_4.get()).build(null));

    public static final RegistryObject<EntityType<ChairEntity>> CHAIR = VineryEntites.create("chair",
            () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).sized(0.001F, 0.001F).build(new VineryIdentifier("chair").toString())
    );



    private static <T extends BlockEntityType<?>> RegistryObject<T> create(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPE.register(path, type);
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPE.register(eventBus);
    }
}
