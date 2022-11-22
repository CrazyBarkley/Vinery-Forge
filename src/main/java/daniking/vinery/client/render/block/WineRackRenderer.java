package daniking.vinery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import daniking.vinery.block.DisplayRackBlock;
import daniking.vinery.block.WineBoxBlock;
import daniking.vinery.block.entity.GeckoStorageBlockEntity;
import daniking.vinery.item.DrinkBlockItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.geckolib3.util.RenderUtils;

public class WineRackRenderer extends GeoBlockRenderer<GeckoStorageBlockEntity> {
    GeckoStorageBlockEntity entity;
    MultiBufferSource renderTypeBuffer;

    public WineRackRenderer() {
        super(null, new WineRackGeckoModel());
    }

    @Override
    public RenderType getRenderType(GeckoStorageBlockEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        entity = animatable;
        this.renderTypeBuffer = renderTypeBuffer;
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack matrixStack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("ref_points")) {
            matrixStack.pushPose();
            matrixStack.translate(-0.46f, -0.455f, 0.035f);
            for (GeoBone b : bone.childBones) {
                matrixStack.pushPose();
                RenderUtils.translate(b, matrixStack);
                RenderUtils.moveToPivot(b, matrixStack);
                matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90));
                matrixStack.scale(0.9f, 0.9f, 0.9f); // Some mess
                if (entity.getBlockState().getBlock() instanceof WineBoxBlock) {
                    matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90));
                    matrixStack.translate(-0.43f, -0.92f, -0.1f);
                    matrixStack.scale(0.87f, 0.87f, 0.87f);
                } else if (entity.getBlockState().getBlock() instanceof DisplayRackBlock) {
                    matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
                    matrixStack.translate(0.56f, 0.45f, 0f);
                } // some more mess
                ItemStack itemStack = entity.getItem(Integer.parseInt(String.valueOf(b.getName().charAt(4))) - 1);
                if (!itemStack.isEmpty()) {
                    if (itemStack.getItem() instanceof  DrinkBlockItem) {
                        if (entity.getBlockState().getBlock() instanceof DisplayRackBlock) {
                            matrixStack.translate(-0.5f, -0.1f, -0.6f);
                        }
                        BlockState bs = ((DrinkBlockItem) itemStack.getItem()).getBlock().defaultBlockState();
                        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(bs, matrixStack, renderTypeBuffer, packedLightIn, packedOverlayIn);
                    } else {
                        if (entity.getBlockState().getBlock() instanceof DisplayRackBlock && itemStack.getItem() instanceof BlockItem) {
                            matrixStack.translate(0f, -0.14f, 0f);
                        }
                        Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, matrixStack, renderTypeBuffer, 0);
                    }
                }
                // Restart render buffer to continue rendering the rest of the wine rack
                renderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(entity)));
                matrixStack.popPose();
            }
            matrixStack.popPose();
            return;
        }
        super.renderRecursively(bone, matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}