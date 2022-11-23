package daniking.vinery;

import daniking.vinery.mixin.AxeItemAccess;
import daniking.vinery.registry.*;
import daniking.vinery.world.VineryConfiguredFeatures;
import dev.architectury.registry.fuel.FuelRegistry;
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

import java.util.IdentityHashMap;
import java.util.Map;

@Mod(Vinery.MODID)
public class Vinery{

    public static final String MODID = "vinery";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("vinery.creative_tab") {
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
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        VineryEntites.register(modEventBus);
        ObjectRegistry.register(modEventBus);
        VineryBlockEntityTypes.register(modEventBus);
        VineryScreenHandlerTypes.register(modEventBus);
        VineryRecipeTypes.register(modEventBus);
        VinerySoundEvents.register(modEventBus);
        VineryVillagers.register(modEventBus);
        VineryConfiguredFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->{
            VineryVillagers.registerPOIs();
            FuelRegistry.register(300, ObjectRegistry.CHERRY_FENCE.get(), ObjectRegistry.CHERRY_FENCE_GATE.get(), ObjectRegistry.STACKABLE_LOG.get(), ObjectRegistry.FERMENTATION_BARREL.get());
        });

        Map<Block, Block> strippables = new IdentityHashMap<>(AxeItemAccess.getStrippables());
        strippables.put(ObjectRegistry.CHERRY_LOG.get(), ObjectRegistry.STRIPPED_CHERRY_LOG.get());
        strippables.put(ObjectRegistry.CHERRY_WOOD.get(), ObjectRegistry.STRIPPED_CHERRY_WOOD.get());
        strippables.put(ObjectRegistry.OLD_CHERRY_LOG.get(), ObjectRegistry.STRIPPED_OLD_CHERRY_LOG.get());
        strippables.put(ObjectRegistry.OLD_CHERRY_WOOD.get(), ObjectRegistry.STRIPPED_OLD_CHERRY_WOOD.get());
        AxeItemAccess.setStripables(strippables);
    }
}

