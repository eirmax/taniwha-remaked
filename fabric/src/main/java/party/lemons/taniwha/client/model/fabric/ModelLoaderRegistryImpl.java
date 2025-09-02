package party.lemons.taniwha.client.model.fabric;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class ModelLoaderRegistryImpl
{
	public static void loadModel(ModelResourceLocation location)
	{
		ModelLoadingPlugin.register(pluginContext -> {
			pluginContext.addModels(location.id());
		});
	}
}