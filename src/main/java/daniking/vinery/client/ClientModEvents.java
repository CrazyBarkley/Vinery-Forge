package daniking.vinery.client;

import daniking.vinery.Vinery;
import daniking.vinery.block.entity.chair.ChairRenderer;
import daniking.vinery.client.gui.CookingPotGui;
import daniking.vinery.client.gui.FermentationBarrelGui;
import daniking.vinery.client.gui.StoveGui;
import daniking.vinery.client.gui.WinePressGui;
import daniking.vinery.client.render.block.WineRackRenderer;
import daniking.vinery.client.render.entity.SimpleGeoRenderer;
import daniking.vinery.client.render.entity.WanderingWinemakerRenderer;
import daniking.vinery.client.render.feature.StrawHatRenderer;
import daniking.vinery.item.StrawHatItem;
import daniking.vinery.item.WinemakerArmorItem;
import daniking.vinery.registry.ObjectRegistry;
import daniking.vinery.registry.VineryBlockEntityTypes;
import daniking.vinery.registry.VineryEntites;
import daniking.vinery.registry.VineryScreenHandlerTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.List;

@Mod.EventBusSubscriber(modid = Vinery.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
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
        MenuScreens.register(VineryScreenHandlerTypes.WINE_PRESS_SCREEN_HANDLER.get(), WinePressGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.FERMENTATION_BARREL_GUI_HANDLER.get(), FermentationBarrelGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.COOKING_POT_SCREEN_HANDLER.get(), CookingPotGui::new);

        //TerraformBoatClientHelper.registerModelLayers(new VineryIdentifier("cherry"));
    }

    @SubscribeEvent
    public static void colorHandlerBlockEvent(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return -1;
            }
            return BiomeColors.getAverageGrassColor(world, pos);
        }, ObjectRegistry.GRASS_SLAB.get());
        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return -1;
            }
            return BiomeColors.getAverageWaterColor(world, pos);
        }, ObjectRegistry.KITCHEN_SINK.get());
    }

    @SubscribeEvent
    public static void colorHandlerItemEvent(RegisterColorHandlersEvent.Item event) {
        event.register((p_92672_, p_92673_) -> GrassColor.get(1.0, 0.5), ObjectRegistry.GRASS_SLAB.get().asItem());
    }

    public static void appendToolTip(List<Component> tooltip){
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.nullToEmpty(ChatFormatting.AQUA + I18n.get("vinery.tooltip.winemaker_armor")));
        tooltip.add(Component.nullToEmpty((helmet != null && helmet.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.STRAW_HAT.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((chestplate != null && chestplate.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.VINEMAKER_APRON.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((leggings != null && leggings.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.VINEMAKER_LEGGINGS.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((boots != null && boots.getItem() instanceof WinemakerArmorItem ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.VINEMAKER_BOOTS.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.nullToEmpty(ChatFormatting.GRAY + I18n.get("vinery.tooltip.winemaker_armor2")));
        tooltip.add(Component.nullToEmpty(((helmet != null && helmet.getItem() instanceof WinemakerArmorItem &&
                chestplate != null && chestplate.getItem() instanceof WinemakerArmorItem &&
                leggings != null && leggings.getItem() instanceof WinemakerArmorItem &&
                boots != null && boots.getItem() instanceof WinemakerArmorItem) ? ChatFormatting.DARK_GREEN.toString() : ChatFormatting.GRAY.toString()) + I18n.get("vinery.tooltip.winemaker_armor3")));
    }
    
}