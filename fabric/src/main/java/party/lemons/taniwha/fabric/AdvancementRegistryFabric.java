package party.lemons.taniwha.fabric;

import net.minecraft.advancements.CriteriaTriggers;
import party.lemons.taniwha.data.criterion.TAdvancements;

public class AdvancementRegistryFabric {

    public static void init(){
        CriteriaTriggers.register(TAdvancements.WEAR_ARMOUR.getId().toString(), TAdvancements.WEAR_ARMOUR);
    }
}

