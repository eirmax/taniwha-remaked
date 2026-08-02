package party.lemons.taniwha.mixin.brewing;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PotionBrewing.Builder.class)
public interface PotionBrewingInvoker {

	@Invoker("addMix")
	void taniwha$callAddMix(Holder<Potion> potion, Item item, Holder<Potion> potion2);

	@Invoker("addContainerRecipe")
	void taniwha$callAddContainerRecipe(Item item, Item item2, Item item3);

	@Invoker("addContainer")
	void taniwha$callAddContainer(Item item);
}
