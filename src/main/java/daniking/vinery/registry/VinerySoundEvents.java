package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.VineryIdentifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VinerySoundEvents {


    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Vinery.MODID);

    public static final RegistryObject<SoundEvent> BLOCK_GRAPEVINE_POT_SQUEEZE = create("block.grapevine_pot.squeeze");
    public static final RegistryObject<SoundEvent> BLOCK_COOKING_POT_JUICE_BOILING = create("block.cooking_pot.juice_boiling");
    public static final RegistryObject<SoundEvent> BLOCK_FAUCET = create("block.kitchen_sink.faucet");
    public static final RegistryObject<SoundEvent> WINE_RACK_3_OPEN = create("block.wine_rack_3.open");
    public static final RegistryObject<SoundEvent> WINE_RACK_3_CLOSE = create("block.wine_rack_3.close");
    public static final RegistryObject<SoundEvent> WINE_RACK_5_OPEN = create("block.wine_rack_5.open");
    public static final RegistryObject<SoundEvent> WINE_RACK_5_CLOSE = create("block.wine_rack_5.close");

    private static RegistryObject<SoundEvent> create(String name) {
        final ResourceLocation id = new VineryIdentifier(name);
        Supplier<SoundEvent> event = () -> new SoundEvent(id);
        return SOUND_EVENTS.register(name, event);
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
