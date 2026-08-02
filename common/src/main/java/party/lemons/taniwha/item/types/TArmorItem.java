package party.lemons.taniwha.item.types;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class TArmorItem extends ArmorItem
{
    private final ItemAttributeModifiers attributes;
    private final int protection;
    private final float toughness;

    public TArmorItem(Holder<ArmorMaterial> material, ItemAttributeModifiers attributes, int protection, float toughness, Type type, Properties properties)
    {
        super(material, type, properties);

        this.attributes = attributes;
        this.protection = protection;
        this.toughness = toughness;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return attributes;
    }

    @Override
    public float getToughness()
    {
        return toughness;
    }

    @Override
    public int getDefense()
    {
        return this.protection;
    }
}