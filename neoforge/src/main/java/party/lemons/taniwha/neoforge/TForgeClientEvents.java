package party.lemons.taniwha.neoforge;


import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.TaniwhaClient;
import party.lemons.taniwha.client.model.RenderLayerInjector;


@EventBusSubscriber(modid = TConstants.MOD_ID, value = Dist.CLIENT)
public class TForgeClientEvents
{
    public static void initClient()
    {
        TaniwhaClient.init();
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event)
    {
        for(RenderLayerInjector.LayerInject inject : RenderLayerInjector.injects)
        {
            LivingEntityRenderer renderer = (LivingEntityRenderer) event.getRenderer(inject.type());

            renderer.addLayer(inject.layerFunction().apply(new RenderLayerInjector.Context(renderer, event.getEntityModels())));
        }
    }
}
