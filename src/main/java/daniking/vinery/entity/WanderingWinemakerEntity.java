package daniking.vinery.entity;

import daniking.vinery.registry.ObjectRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

import java.util.Map;

public class WanderingWinemakerEntity extends WanderingTrader {
	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(Map.of(1, new VillagerTrades.ItemListing[] {
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_GRAPE_SEEDS, 1, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.WHITE_GRAPE_SEEDS, 1, 1, 8, 1),
			//new VillagerTrades.ItemsForEmeralds(ObjectRegistry.CHERRY_SAPLING, 3, 1, 8, 1),
			//new VillagerTrades.ItemsForEmeralds(ObjectRegistry.OLD_CHERRY_SAPLING, 5, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_GRAPE, 2, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_GRAPEJUICE_WINE_BOTTLE, 2, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.WHITE_GRAPEJUICE_WINE_BOTTLE, 2, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.COARSE_DIRT_SLAB, 1, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.GRASS_SLAB, 1, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.CHERRY_PLANKS, 2, 1, 8, 1),
			new VillagerTrades.ItemsForEmeralds(ObjectRegistry.CHERRY_WINE, 1, 1, 8, 1)
	}));
	
	public WanderingWinemakerEntity(EntityType<? extends WanderingWinemakerEntity> entityType, Level world) {
		super(entityType, world);
	}
	
	@Override
	protected void updateTrades() {
		if (this.offers == null) {
			this.offers = new MerchantOffers();
		}
		this.addOffersFromItemListings(this.offers, TRADES.get(1), 8);
	}
	
}