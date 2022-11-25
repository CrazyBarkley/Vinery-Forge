package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.client.gui.handler.CookingPotGuiHandler;
import daniking.vinery.client.gui.handler.FermentationBarrelGuiHandler;
import daniking.vinery.client.gui.handler.StoveGuiHandler;
import daniking.vinery.client.gui.handler.WinePressGuiHandler;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VineryScreenHandlerTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Vinery.MODID);

    public static final RegistryObject<MenuType<StoveGuiHandler>> STOVE_GUI_HANDLER = create("stove_gui_handler", () -> new MenuType<>(StoveGuiHandler::new));
    public static final RegistryObject<MenuType<FermentationBarrelGuiHandler>> FERMENTATION_BARREL_GUI_HANDLER = create("fermentation_barrel_gui_handler", () -> new MenuType<>(FermentationBarrelGuiHandler::new));
    public static final RegistryObject<MenuType<CookingPotGuiHandler>> COOKING_POT_SCREEN_HANDLER = create("cooking_pot_gui_handler", () -> new MenuType<>(CookingPotGuiHandler::new));

    public static final RegistryObject<MenuType<WinePressGuiHandler>> WINE_PRESS_SCREEN_HANDLER = create("wine_press_gui_handler", () -> new MenuType<>(WinePressGuiHandler::new));

    private static <T extends MenuType<?>> RegistryObject<T> create(String name, Supplier<T> type){
        return MENU_TYPES.register(name, type);
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
