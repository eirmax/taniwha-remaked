package party.lemons.taniwha.entity.boat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;

public class VanillaBoatType extends BoatType
{
    private final Boat.Type vanillaType;

    public VanillaBoatType(ResourceLocation id, BoatShape shape, Boat.Type type, Item boatitem, Item chestItem)
    {
        super(id, shape, ()->boatitem, ()->chestItem);
        this.vanillaType = type;
    }

    public Boat.Type getVanillaType()
    {
        return vanillaType;
    }

    @Override
    public ResourceLocation getTexture(boolean chest)
    {
        if(chest)
            return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/chest_boat/" + vanillaType.getName() + ".png");

        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/boat/" + vanillaType.getName() + ".png");
    }
}