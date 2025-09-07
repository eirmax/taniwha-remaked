package party.lemons.taniwha.neoforge;


import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.Taniwha;

@Mod(TConstants.MOD_ID)
public class TaniwhaNeoForge {

    public TaniwhaNeoForge() {
        Taniwha.init();

        EnvExecutor.runInEnv(Env.CLIENT, ()-> TForgeClientEvents::initClient);
    }
}
