package party.lemons.taniwha.mixin.block.entity;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import party.lemons.taniwha.hooks.PotteryPatternHooks;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin
{
	@Inject(method = "getPatternFromItem", at = @At("HEAD"), cancellable = true)
	private static void taniwha$getPatternFromItem(Item item, CallbackInfoReturnable<ResourceKey<DecoratedPotPattern>> cir)
	{
		ResourceKey<DecoratedPotPattern> pattern = PotteryPatternHooks.getPotteryPatternItem(item);
		if(pattern != null)
			cir.setReturnValue(pattern);
	}
}
