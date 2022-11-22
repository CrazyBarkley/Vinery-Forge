package daniking.vinery.event;

import daniking.vinery.Vinery;
import daniking.vinery.entity.WanderingWinemakerEntity;
import daniking.vinery.registry.ObjectRegistry;
import daniking.vinery.registry.VineryEntites;
import daniking.vinery.registry.VineryVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Vinery.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType().equals(VineryVillagers.WINEMAKER.get())){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<VillagerTrades.ItemListing> level1 = trades.get(1);
            level1.add(new VineryVillagers.BuyForOneEmeraldFactory(ObjectRegistry.RED_GRAPE, 15, 4, 5));
            level1.add(new VineryVillagers.BuyForOneEmeraldFactory(ObjectRegistry.WHITE_GRAPE, 15, 4, 5));
            //level1.add(new VineryVillagers.SellItemFactory(ObjectRegistry.RED_GRAPE_SEEDS, 2, 1, 5));
            //level1.add(new VineryVillagers.SellItemFactory(ObjectRegistry.WHITE_GRAPE_SEEDS, 2, 1, 5));

            List<VillagerTrades.ItemListing> level2 = trades.get(2);
            level2.add(new VineryVillagers.SellItemFactory(ObjectRegistry.WINE_BOTTLE, 1, 2, 7));

            List<VillagerTrades.ItemListing> level3 = trades.get(3);
            level3.add(new VineryVillagers.SellItemFactory(ObjectRegistry.COOKING_POT, 3, 1, 10));
            level3.add(new VineryVillagers.SellItemFactory(ObjectRegistry.FLOWER_BOX, 3, 1, 10));
            level3.add(new VineryVillagers.SellItemFactory(ObjectRegistry.WHITE_GRAPE_CRATE, 7, 1, 10));
            level3.add(new VineryVillagers.SellItemFactory(ObjectRegistry.RED_GRAPE_CRATE, 7, 1, 10));

            List<VillagerTrades.ItemListing> level4 = trades.get(4);
            level4.add(new VineryVillagers.SellItemFactory(ObjectRegistry.BASKET, 4, 1, 10));
            level4.add(new VineryVillagers.SellItemFactory(ObjectRegistry.FLOWER_POT, 5, 1, 10));
            level4.add(new VineryVillagers.SellItemFactory(ObjectRegistry.WINDOW, 12, 1, 10));
            level4.add(new VineryVillagers.SellItemFactory(ObjectRegistry.CHERRY_BEAM, 6, 1, 10));

            List<VillagerTrades.ItemListing> level5 = trades.get(5);
            level5.add(new VineryVillagers.SellItemFactory(ObjectRegistry.WINE_BOX, 10, 1, 10));
            level5.add(new VineryVillagers.SellItemFactory(ObjectRegistry.KING_DANIS_WINE, 4, 1, 10));
            level5.add(new VineryVillagers.SellItemFactory(ObjectRegistry.GLOVES, 12, 1, 15));
        }
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        event.put(VineryEntites.MULE, Llama.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2f).build());
        event.put(VineryEntites.WANDERING_WINEMAKER, WanderingWinemakerEntity.createMobAttributes().build());
    }
}
