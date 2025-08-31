package party.lemons.taniwha.item.types;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class TArmorItem extends ArmorItem
{
	private final Multimap<Attribute, AttributeModifier> attributes;
	private final int protection;
	private final float toughness;

	public TArmorItem(Holder<ArmorMaterial> material, Multimap<Attribute, AttributeModifier> attributes, int protection, float toughness, Type type, Properties properties)
	{
		super(material, type, properties);

		this.attributes = attributes;
		this.protection = protection;
		this.toughness = toughness;
	}


	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		return super.getDefaultAttributeModifiers();
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