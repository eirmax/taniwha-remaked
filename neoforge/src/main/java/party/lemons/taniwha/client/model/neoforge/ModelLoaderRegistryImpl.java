package party.lemons.taniwha.client.model.neoforge;

import com.google.common.collect.Lists;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;
import party.lemons.taniwha.TConstants;

import java.util.List;

@Mod(TConstants.MOD_ID)
public class ModelLoaderRegistryImpl
{
	private static final List<ModelResourceLocation> additionalModels = Lists.newArrayList();

	public static void loadModel(ModelResourceLocation location)
	{
		additionalModels.add(location);
	}

	@SubscribeEvent
	public static void onModelEvent(ModelEvent.RegisterAdditional event)
	{
		additionalModels.forEach(event::register);
	}
}