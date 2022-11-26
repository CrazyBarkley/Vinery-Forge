package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.VineryIdentifier;
import daniking.vinery.effect.EmptyEffect;
import daniking.vinery.effect.JellieEffect;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VineryEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Vinery.MODID);

    public static final RegistryObject<MobEffect> EMPTY = registerEffekt("empty", () -> new EmptyEffect());

    public static final RegistryObject<MobEffect> JELLIE = registerEffekt("jellie", () -> new JellieEffect());

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

    private static RegistryObject<MobEffect> registerEffekt(String name, Supplier<MobEffect> effect){
        return MOB_EFFECTS.register(name, effect);
    }
}
