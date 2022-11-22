package daniking.vinery.client.render.feature;

import daniking.vinery.VineryIdentifier;
import daniking.vinery.item.StrawHatItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StrawHatModel extends AnimatedGeoModel<StrawHatItem> {
	@Override
	public ResourceLocation getModelResource(StrawHatItem object) {
		return new VineryIdentifier("geo/straw_hat.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(StrawHatItem object) {
		return new VineryIdentifier("textures/item/straw_hat.png");
	}

	@Override
	public ResourceLocation getAnimationResource(StrawHatItem animatable) {
		return new VineryIdentifier("animations/straw_hat.animation.json");
	}
}
