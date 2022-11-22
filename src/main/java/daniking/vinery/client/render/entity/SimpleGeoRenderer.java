package daniking.vinery.client.render.entity;

import daniking.vinery.client.model.SimpleGeoModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SimpleGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
	public SimpleGeoRenderer(EntityRendererProvider.Context mgr, String modId, String modelName) {
		super(mgr, new SimpleGeoModel<>(modId, modelName));
	}
}