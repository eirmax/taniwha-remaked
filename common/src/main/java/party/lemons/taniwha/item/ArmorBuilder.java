package party.lemons.taniwha.item;

import com.google.common.collect.LinkedListMultimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.item.types.TArmorItem;


import java.util.function.Supplier;

public class ArmorBuilder
{
	private static final String ARMOR_MODIFIER = "armor";
	private static final String ARMOR_TOUGHNESS_MODIFIER = "armor_toughness";
	private static final String KNOCKBACK_RESISTANCE_MODIFIER = "knockback_resistance";
	private static final String ARMOR_SLOT_MODIFIER = "armor_";


	private final LinkedListMultimap<Attribute, AttributeModifier> attributes = LinkedListMultimap.create();
	private final ArmorMaterial material;
	private int protection;
	private boolean overrideProtection = false;
	private float toughness;
	private float knockbackResistance;

	public static ArmorBuilder create(ArmorMaterial material)
	{
		return new ArmorBuilder(material);
	}

	public ArmorBuilder protection(int amount)
	{
		this.protection = amount;
		this.overrideProtection = true;
		return this;
	}

	public ArmorBuilder toughness(float amount)
	{
		this.toughness = amount;
		return this;
	}

	public ArmorBuilder knockbackResistance(float amount)
	{
		this.knockbackResistance = amount;
		return this;
	}

	public ArmorBuilder attribute(ResourceLocation name, Attribute attribute, double value, AttributeModifier.Operation operation)
	{
		attributes.put(attribute, new AttributeModifier(name, value, operation));
		return this;
	}

	public Supplier<Item> build(ArmorItem.Type type, Item.Properties properties)
	{
		if(!overrideProtection)
			protection = material.getDefense(type);

		attributes.removeAll(Attributes.ARMOR.value());
		attributes.removeAll(Attributes.ARMOR_TOUGHNESS.value());
		attributes.removeAll(Attributes.KNOCKBACK_RESISTANCE.value());

		attributes.put(Attributes.ARMOR.value(), new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, ARMOR_MODIFIER), (double) this.protection, AttributeModifier.Operation.ADD_VALUE));
		attributes.put(Attributes.ARMOR_TOUGHNESS.value(), new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, ARMOR_TOUGHNESS_MODIFIER), (double) this.toughness, AttributeModifier.Operation.ADD_VALUE));
		if (this.knockbackResistance != 0.0F)
		{
			attributes.put(Attributes.KNOCKBACK_RESISTANCE.value(), new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, KNOCKBACK_RESISTANCE_MODIFIER), (double) this.knockbackResistance, AttributeModifier.Operation.ADD_VALUE));
		}

		LinkedListMultimap<Attribute, AttributeModifier> builtAttributes = buildAttributes(type);
		return ()->new TArmorItem(Holder.direct(material), builtAttributes, protection, toughness, type, properties);
	}

	public LinkedListMultimap<Attribute, AttributeModifier> buildAttributes(ArmorItem.Type type)
	{
		LinkedListMultimap<Attribute, AttributeModifier> atts = LinkedListMultimap.create();
		for(Attribute attribute : attributes.keys())
		{
			for(AttributeModifier modifier : attributes.get(attribute))
			{
				atts.put(attribute, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, ARMOR_SLOT_MODIFIER + type.getSlot().getIndex()), modifier.amount(), modifier.operation()));
			}
		}
		return atts;
	}

	private ArmorBuilder(ArmorMaterial material)
	{
		this.material = material;
		this.toughness = material.toughness();
		this.knockbackResistance = material.knockbackResistance();
	}
}