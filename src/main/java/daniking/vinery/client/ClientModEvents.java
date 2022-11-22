package daniking.vinery.client;

import daniking.vinery.Vinery;
import daniking.vinery.block.entity.chair.ChairRenderer;
import daniking.vinery.client.gui.CookingPotGui;
import daniking.vinery.client.gui.FermentationBarrelGui;
import daniking.vinery.client.gui.StoveGui;
import daniking.vinery.client.render.block.WineRackRenderer;
import daniking.vinery.client.render.entity.SimpleGeoRenderer;
import daniking.vinery.client.render.entity.WanderingWinemakerRenderer;
import daniking.vinery.client.render.feature.StrawHatRenderer;
import daniking.vinery.item.StrawHatItem;
import daniking.vinery.registry.VineryBlockEntityTypes;
import daniking.vinery.registry.VineryEntites;
import daniking.vinery.registry.VineryScreenHandlerTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Vinery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public void onRenderTypeSetup(FMLClientSetupEvent event) {



        /*SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ObjectRegistry.CHERRY_SIGN.getTexture()));
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ObjectRegistry.RED_GRAPE_BUSH, ObjectRegistry.WHITE_GRAPE_BUSH,
                                               ObjectRegistry.CHERRY_DOOR, ObjectRegistry.STACKABLE_LOG, ObjectRegistry.COOKING_POT,
                                               ObjectRegistry.CHERRY_JAM, ObjectRegistry.CHERRY_JAR, ObjectRegistry.FERMENTATION_BARREL,
                                                ObjectRegistry.MELLOHI_WINE, ObjectRegistry.CLARK_WINE, ObjectRegistry.BOLVAR_WINE, ObjectRegistry.CHERRY_WINE,
                                              ObjectRegistry.KING_DANIS_WINE, ObjectRegistry.CHERRY_JAR, ObjectRegistry.CHENET_WINE, ObjectRegistry.MELLOHI_WINE,
                                                ObjectRegistry.NOIR_WINE, ObjectRegistry.WINE_BOTTLE, ObjectRegistry.TABLE


        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ObjectRegistry.GRAPEVINE_STEM, ObjectRegistry.WINE_BOTTLE,
                                               ObjectRegistry.RED_GRAPEJUICE_WINE_BOTTLE, ObjectRegistry.WHITE_GRAPEJUICE_WINE_BOTTLE,
                                               ObjectRegistry.WINE_BOX, ObjectRegistry.FLOWER_BOX_ALLIUM, ObjectRegistry.FLOWER_BOX_AZURE_BLUET,
                                               ObjectRegistry.FLOWER_BOX, ObjectRegistry.FLOWER_BOX_BLUE_ORCHID, ObjectRegistry.FLOWER_BOX_BLUE_DANDELION,
                                               ObjectRegistry.FLOWER_BOX_BLUE_CORNFLOWER, ObjectRegistry.FLOWER_BOX_BLUE_LILY_OF_THE_VALLEY,
                                               ObjectRegistry.FLOWER_BOX_BLUE_ORANGE_TULIP, ObjectRegistry.FLOWER_BOX_BLUE_OXEYE_DAISY,
                                               ObjectRegistry.FLOWER_BOX_BLUE_PINK_TULIP, ObjectRegistry.FLOWER_BOX_BLUE_POPPY,
                                               ObjectRegistry.FLOWER_BOX_BLUE_RED_TULIP, ObjectRegistry.FLOWER_BOX_BLUE_WHITE_TULIP,
                                               ObjectRegistry.FLOWER_BOX_BLUE_WHITER_ROSE, ObjectRegistry.FLOWER_POT,
                                               ObjectRegistry.CHAIR,
                                               ObjectRegistry.WINE_PRESS, ObjectRegistry.GRASS_SLAB, ObjectRegistry.CHERRY_JAR,
                                               ObjectRegistry.CHERRY_SAPLING, ObjectRegistry.OLD_CHERRY_SAPLING, ObjectRegistry.KITCHEN_SINK, ObjectRegistry.STACKABLE_LOG
                                              );

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), ObjectRegistry.WINDOW, ObjectRegistry.WINE_BOX, ObjectRegistry.STACKABLE_LOG);

         *
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getAverageGrassColor(world, pos), ObjectRegistry.GRASS_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(1.0, 0.5), ObjectRegistry.GRASS_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return -1;
            }
            return BiomeColors.getAverageWaterColor(world, pos);
            }, ObjectRegistry.KITCHEN_SINK);
         */

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(VineryBlockEntityTypes.WINE_RACK_GECKO_ENTITY.get(),
                (BlockEntityRendererProvider.Context rendererDispatcherIn) -> new WineRackRenderer());
        event.registerEntityRenderer(VineryEntites.MULE.get(), mgr -> new SimpleGeoRenderer<>(mgr, Vinery.MODID, "wandering_mule"));
        event.registerEntityRenderer(VineryEntites.WANDERING_WINEMAKER.get(), WanderingWinemakerRenderer::new);
        event.registerEntityRenderer(VineryBlockEntityTypes.CHAIR.get(), ChairRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(StrawHatItem.class, new StrawHatRenderer());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(VineryScreenHandlerTypes.STOVE_GUI_HANDLER.get(), StoveGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.FERMENTATION_BARREL_GUI_HANDLER.get(), FermentationBarrelGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.COOKING_POT_SCREEN_HANDLER.get(), CookingPotGui::new);

        //TerraformBoatClientHelper.registerModelLayers(new VineryIdentifier("cherry"));
    }
    
    public static Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
    
}