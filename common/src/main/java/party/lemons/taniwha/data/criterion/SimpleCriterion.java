package party.lemons.taniwha.data.criterion;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class SimpleCriterion extends SimpleCriterionTrigger<SimpleCriterion.TriggerInstance>
{
    private final ResourceLocation ID;

    public SimpleCriterion(ResourceLocation location)
    {
        ID = location;
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player)
    {
        this.trigger(player, (conditions)->true);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
            ).apply(instance, TriggerInstance::new)
        );

        public static TriggerInstance simple() {
            return new TriggerInstance(Optional.empty());
        }
    }
}
