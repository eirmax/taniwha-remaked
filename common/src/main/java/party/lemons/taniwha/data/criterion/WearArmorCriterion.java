package party.lemons.taniwha.data.criterion;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class WearArmorCriterion extends SimpleCriterionTrigger<WearArmorCriterion.TriggerInstance>
{
    public static final List<WearArmorCriterion> CRITERION = Lists.newArrayList();

    private final ResourceLocation ID;

    public WearArmorCriterion(String modid)
    {
        ID = ResourceLocation.fromNamespaceAndPath(modid, "wear_armor");

        CRITERION.add(this);
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player)
    {
        this.trigger(player, (conditions)->
                conditions.matches(player.getArmorSlots()));
    }

    public ResourceLocation getId() {
        return ID;
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, ItemPredicate item) implements SimpleCriterionTrigger.SimpleInstance {
        
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                ItemPredicate.CODEC.fieldOf("item").forGetter(TriggerInstance::item)
            ).apply(instance, TriggerInstance::new)
        );

        public static TriggerInstance wearingItem(ItemPredicate item) {
            return new TriggerInstance(Optional.empty(), item);
        }

        public boolean matches(Iterable<ItemStack> armorItems)
        {
            for(ItemStack st : armorItems)
            {
                if(item.test(st)) return true;
            }
            return false;
        }
    }
}
