package daniking.vinery.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SimpleGeoModel<T extends LivingEntity & IAnimatable> extends AnimatedGeoModel<T> {
    private final ResourceLocation texture;
    private final ResourceLocation model;
    private final ResourceLocation animation;

    public SimpleGeoModel(String modId, String name) {
        this.texture = new ResourceLocation(modId, "textures/entity/" + name + ".png");
        this.model = new ResourceLocation(modId, "geo/" + name + ".geo.json");
        this.animation = new ResourceLocation(modId, "animations/" + name + ".animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return animation;
    }

}