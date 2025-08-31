package party.lemons.taniwha.forge;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.EventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.Taniwha;

@Mod(TConstants.MOD_ID)
@EventBusSubscriber(modid = TConstants.MOD_ID, value = Dist.CLIENT)
public class TaniwhaForge {

    public TaniwhaForge(EventBus event) {
        event.registerModEventBus(TConstants.MOD_ID, event.get().getModEventBus());
        Taniwha.init();
        MinecraftForge.EVENT_BUS.addListener(TForgeEvents::onPlaceEvent);

        EnvExecutor.runInEnv(Env.CLIENT, ()-> TForgeClientEvents::initClient);
    }
}
