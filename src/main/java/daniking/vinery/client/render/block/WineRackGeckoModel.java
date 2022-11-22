package daniking.vinery.client.render.block;

import daniking.vinery.Vinery;
import daniking.vinery.block.DisplayRackBlock;
import daniking.vinery.block.WineBoxBlock;
import daniking.vinery.block.entity.GeckoStorageBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WineRackGeckoModel extends AnimatedGeoModel<GeckoStorageBlockEntity> {

    @Override
    public ResourceLocation getAnimationResource(GeckoStorageBlockEntity e) {
        return new ResourceLocation(Vinery.MODID, "animations/empty.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(GeckoStorageBlockEntity e) {
        return new ResourceLocation(Vinery.MODID, "geo/" + e.getModelName() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeckoStorageBlockEntity e) {
        if (e.getBlockState().getBlock() instanceof WineBoxBlock) {
            return new ResourceLocation(Vinery.MODID, "textures/block/wine_box.png");
        } else if (e.getBlockState().getBlock() instanceof DisplayRackBlock) {
            return new ResourceLocation(Vinery.MODID, "textures/block/shelf.png");
        }
        return new ResourceLocation(Vinery.MODID, "textures/block/wine_rack_ref.png");
    }

}