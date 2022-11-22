package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.entity.TraderMuleEntity;
import daniking.vinery.entity.WanderingWinemakerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VineryEntites {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Vinery.MODID);


	public static final EntityType<TraderMuleEntity> MULE = create(
			"mule",
			EntityType.Builder.of(TraderMuleEntity::new, MobCategory.CREATURE)
					.sized(0.9f, 1.87f)
					.setTrackingRange(10)
					.build(null)
	);
	
	public static final EntityType<WanderingWinemakerEntity> WANDERING_WINEMAKER =create("wandering_winemaker",
			EntityType.Builder.of(WanderingWinemakerEntity::new, MobCategory.CREATURE)
			                       .sized(0.6f, 1.95f)
			                       .setTrackingRange(10)
			                       .build(null)
	);

	public static <T extends EntityType<?>> T create(final String path, final T type) {
		ENTITY_TYPE.register(path, () -> type);
		return type;
	}

	public static void register(IEventBus eventBus) {
		ENTITY_TYPE.register(eventBus);
	}
}