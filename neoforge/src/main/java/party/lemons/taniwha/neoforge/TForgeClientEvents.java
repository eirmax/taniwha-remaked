package party.lemons.taniwha.neoforge;


import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import party.lemons.taniwha.TaniwhaClient;
import party.lemons.taniwha.client.model.RenderLayerInjector;
import party.lemons.taniwha.entity.TEntities;
import party.lemons.taniwha.entity.boat.BoatShapeModels;
import party.lemons.taniwha.entity.boat.TBoatRender;


public class TForgeClientEvents
{
    public static void initClient(FMLClientSetupEvent event)
    {
        TaniwhaClient.init();
    }

    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        BoatShapeModels.registerModelLayers(event::registerLayerDefinition);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(TEntities.T_BOAT.get(), context -> new TBoatRender(context, false));
        event.registerEntityRenderer(TEntities.T_CHEST_BOAT.get(), context -> new TBoatRender(context, true));
    }

    public static void addLayers(EntityRenderersEvent.AddLayers event)
    {
        for(RenderLayerInjector.LayerInject inject : RenderLayerInjector.injects)
        {
            LivingEntityRenderer renderer = (LivingEntityRenderer) event.getRenderer(inject.type());
            if(renderer == null)
                continue;

            renderer.addLayer(inject.layerFunction().apply(new RenderLayerInjector.Context(renderer, event.getEntityModels())));
        }
    }
}
