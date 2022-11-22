package daniking.vinery;

import daniking.vinery.registry.*;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Vinery.MODID)
public class Vinery{

    public static final String MODID = "vinery";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(new VineryIdentifier("creative_tab").toString()) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ObjectRegistry.RED_GRAPE.get());
        }
    };
    public static final TagKey<Block> ALLOWS_COOKING_ON_POT = TagKey.create(Registry.BLOCK_REGISTRY, new VineryIdentifier("allows_cooking_on_pot"));
    public static final TagKey<Block> CAN_NOT_CONNECT = TagKey.create(Registry.BLOCK_REGISTRY, new VineryIdentifier("can_not_connect"));
    public static final TagKey<Item> CHERRY_LOGS = TagKey.create(Registry.ITEM_REGISTRY, new VineryIdentifier("cherry_logs"));
    public static final TagKey<Block> WINE_RACK = TagKey.create(Registry.BLOCK_REGISTRY, new VineryIdentifier("wine_racks"));

    public Vinery() {
        Vinery.LOGGER.error("mod head");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        VineryEntites.register(modEventBus);
        ObjectRegistry.register(modEventBus);
        VineryBlockEntityTypes.register(modEventBus);
        VineryScreenHandlerTypes.register(modEventBus);
        VineryRecipeTypes.register(modEventBus);
        VinerySoundEvents.register(modEventBus);
        VineryVillagers.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->{
            VineryVillagers.registerPOIs();
        });
    }
    /*
    public void onInitialize() {

        ObjectRegistry.init();
        VineryBlockEntityTypes.init();
        AdditionalHouses.registerNewVillageStructures();
        VineryScreenHandlerTypes.init();
        VineryRecipeTypes.init();
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            final ResourceLocation resourceLocation = new VineryIdentifier("inject/seeds");
            if (Blocks.GRASS.getLootTable().equals(id) || Blocks.TALL_GRASS.getLootTable().equals(id) || Blocks.FERN.getLootTable().equals(id)) {
                supplier.pool(LootPool.lootPool().add(LootTableReference.lootTableReference(resourceLocation).setWeight(1)).build());
            }
        });
        VineryBoatTypes.init();
        VineryConfiguredFeatures.init();
        VinerySoundEvents.init();
        VineryVillagers.init();
        VineryEntites.init();
    }

     */
}

