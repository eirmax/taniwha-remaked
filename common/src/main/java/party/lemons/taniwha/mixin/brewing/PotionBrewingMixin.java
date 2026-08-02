package party.lemons.taniwha.mixin.brewing;

import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import party.lemons.taniwha.hooks.block.BrewingStandHooks;

@Mixin(PotionBrewing.class)
public class PotionBrewingMixin {

	@Inject(method = "addVanillaMixes", at = @At("TAIL"))
	private static void taniwha$addExtraMixes(PotionBrewing.Builder builder, CallbackInfo ci) {
		BrewingStandHooks.addQueuedMixes((PotionBrewingInvoker)(Object)builder);
	}
}
