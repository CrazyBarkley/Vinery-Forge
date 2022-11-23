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
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(VineryBlockEntityTypes.WINE_RACK_GECKO_ENTITY.get(),
                (BlockEntityRendererProvider.Context rendererDispatcherIn) -> new WineRackRenderer());
        event.registerEntityRenderer(VineryEntites.MULE.get(), mgr -> new SimpleGeoRenderer<>(mgr, Vinery.MODID, "wandering_mule"));
        event.registerEntityRenderer(VineryEntites.WANDERING_WINEMAKER.get(), WanderingWinemakerRenderer::new);
        event.registerEntityRenderer(VineryBlockEntityTypes.CHAIR.get(), ChairRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(StrawHatItem.class, StrawHatRenderer::new);
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