package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.client.gui.handler.CookingPotGuiHandler;
import daniking.vinery.client.gui.handler.FermentationBarrelGuiHandler;
import daniking.vinery.client.gui.handler.StoveGuiHandler;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VineryScreenHandlerTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Vinery.MODID);

    public static final MenuType<StoveGuiHandler> STOVE_GUI_HANDLER = create("stove_gui_handler", new MenuType<>(StoveGuiHandler::new));
    public static final MenuType<FermentationBarrelGuiHandler> FERMENTATION_BARREL_GUI_HANDLER = create("fermentation_barrel_gui_handler", new MenuType<>(FermentationBarrelGuiHandler::new));
    public static final MenuType<CookingPotGuiHandler> COOKING_POT_SCREEN_HANDLER = create("cooking_pot_gui_handler", new MenuType<>(CookingPotGuiHandler::new));

    private static <T extends MenuType<?>> T create(String name, T type){
        MENU_TYPES.register(name, () -> type);
        return type;
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
