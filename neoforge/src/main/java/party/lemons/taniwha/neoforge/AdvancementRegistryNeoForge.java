package party.lemons.taniwha.neoforge;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import party.lemons.taniwha.data.criterion.TAdvancements;

@EventBusSubscriber
public class AdvancementRegistryNeoForge {

    @SubscribeEvent
    public static void registerTriggers(RegisterEvent event)
    {
        if (event.getRegistryKey().equals(Registries.TRIGGER_TYPE))
        {
            event.register(Registries.TRIGGER_TYPE, TAdvancements.WEAR_ARMOUR.getId(), () -> TAdvancements.WEAR_ARMOUR);
        }
    }
}
