package party.lemons.taniwha.neoforge;


import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.Taniwha;

@Mod(TConstants.MOD_ID)
public class TaniwhaNeoForge {

    public TaniwhaNeoForge(IEventBus bus) {
        Taniwha.init();

        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> {
            bus.addListener(TForgeClientEvents::initClient);
            bus.addListener(TForgeClientEvents::registerModelLayers);
            bus.addListener(TForgeClientEvents::registerRenderers);
            bus.addListener(TForgeClientEvents::addLayers);
        });
    }
}
