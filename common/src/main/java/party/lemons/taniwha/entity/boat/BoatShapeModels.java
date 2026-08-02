package party.lemons.taniwha.entity.boat;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import party.lemons.taniwha.TConstants;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class BoatShapeModels
{
	private BoatShapeModels()
	{
	}

	public static void registerModelLayers()
	{
		registerModelLayers(EntityModelLayerRegistry::register);
	}

	public static void registerModelLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registrar)
	{
		for(BoatType type : BoatTypes.TYPES)
		{
			registrar.accept(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, type.getModelLocation()), "main"), getLayerDefinition(type.shape));
			registrar.accept(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, type.getChestModelLocation()), "main"), getChestLayerDefinition(type.shape));
		}
	}

	public static Supplier<LayerDefinition> getLayerDefinition(BoatShape shape)
	{
		if(shape instanceof RaftBoatShape)
			return RaftModel::createBodyModel;
		return BoatModel::createBodyModel;
	}

	public static Supplier<LayerDefinition> getChestLayerDefinition(BoatShape shape)
	{
		if(shape instanceof RaftBoatShape)
			return ChestRaftModel::createBodyModel;
		return ChestBoatModel::createBodyModel;
	}

	public static ListModel<Boat> createModel(EntityRendererProvider.Context context, BoatType type, boolean chest)
	{
		if(type.shape instanceof RaftBoatShape)
		{
			if(chest)
				return new ChestRaftModel(context.bakeLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, type.getChestModelLocation()), "main")));
			return new RaftModel(context.bakeLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, type.getModelLocation()), "main")));
		}

		if(chest)
			return new ChestBoatModel(context.bakeLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, type.getChestModelLocation()), "main")));
		return new BoatModel(context.bakeLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, type.getModelLocation()), "main")));
	}
}
