package party.lemons.taniwha.neoforge;


import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.Taniwha;

@Mod(TConstants.MOD_ID)
@EventBusSubscriber(modid = TConstants.MOD_ID, value = Dist.CLIENT)
public class TaniwhaNeoForge {

    public TaniwhaNeoForge(IEventBus modEventBus) {
        Taniwha.init();
        modEventBus.addListener(TForgeEvents::onPlaceEvent);

        EnvExecutor.runInEnv(Env.CLIENT, ()-> TForgeClientEvents::initClient);
    }
}
