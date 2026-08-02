package party.lemons.taniwha.item;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import party.lemons.taniwha.TConstants;
import party.lemons.taniwha.item.types.TArmorItem;

import java.util.List;
import java.util.function.Supplier;

public class ArmorBuilder
{
    private static final String ARMOR_MODIFIER = "armor";
    private static final String ARMOR_TOUGHNESS_MODIFIER = "armor_toughness";
    private static final String KNOCKBACK_RESISTANCE_MODIFIER = "knockback_resistance";

    private final List<ArmorAttribute> attributes = Lists.newArrayList();
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
        return attribute(name, BuiltInRegistries.ATTRIBUTE.wrapAsHolder(attribute), value, operation);
    }

    public ArmorBuilder attribute(ResourceLocation name, Holder<Attribute> attribute, double value, AttributeModifier.Operation operation)
    {
        attributes.add(new ArmorAttribute(attribute, new AttributeModifier(name, value, operation)));
        return this;
    }

    public Supplier<Item> build(ArmorItem.Type type, Item.Properties properties)
    {
        if(!overrideProtection)
            protection = material.getDefense(type);

        ItemAttributeModifiers builtAttributes = buildAttributes(type);
        return ()->new TArmorItem(Holder.direct(material), builtAttributes, protection, toughness, type, properties);
    }

    public ItemAttributeModifiers buildAttributes(ArmorItem.Type type)
    {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        EquipmentSlotGroup slot = EquipmentSlotGroup.bySlot(type.getSlot());

        builder.add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, ARMOR_MODIFIER), this.protection, AttributeModifier.Operation.ADD_VALUE), slot);
        builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, ARMOR_TOUGHNESS_MODIFIER), this.toughness, AttributeModifier.Operation.ADD_VALUE), slot);
        if (this.knockbackResistance != 0.0F)
        {
            builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, KNOCKBACK_RESISTANCE_MODIFIER), this.knockbackResistance, AttributeModifier.Operation.ADD_VALUE), slot);
        }

        for(ArmorAttribute attribute : attributes)
        {
            builder.add(attribute.attribute(), attribute.modifier(), slot);
        }

        return builder.build();
    }

    private ArmorBuilder(ArmorMaterial material)
    {
        this.material = material;
        this.toughness = material.toughness();
        this.knockbackResistance = material.knockbackResistance();
    }

    private record ArmorAttribute(Holder<Attribute> attribute, AttributeModifier modifier)
    {
    }
}
