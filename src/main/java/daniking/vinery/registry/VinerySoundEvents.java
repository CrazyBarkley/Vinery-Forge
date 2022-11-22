package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.VineryIdentifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VinerySoundEvents {


    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Vinery.MODID);

    public static final SoundEvent BLOCK_GRAPEVINE_POT_SQUEEZE = create("block.grapevine_pot.squeeze");
    public static final SoundEvent BLOCK_COOKING_POT_JUICE_BOILING = create("block.cooking_pot.juice_boiling");
    public static final SoundEvent BLOCK_FAUCET = create("block.kitchen_sink.faucet");
    public static final SoundEvent WINE_RACK_3_OPEN = create("block.wine_rack_3.open");
    public static final SoundEvent WINE_RACK_3_CLOSE = create("block.wine_rack_3.close");
    public static final SoundEvent WINE_RACK_5_OPEN = create("block.wine_rack_5.open");
    public static final SoundEvent WINE_RACK_5_CLOSE = create("block.wine_rack_5.close");

    private static SoundEvent create(String name) {
        final ResourceLocation id = new VineryIdentifier(name);
        final SoundEvent event = new SoundEvent(id);
        SOUND_EVENTS.register(name, () -> event);
        return event;
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
