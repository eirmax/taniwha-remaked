package party.lemons.taniwha.neoforge;

import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.world.level.Level;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import party.lemons.taniwha.hooks.TEvents;

public class TForgeEvents {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onPlaceEvent(BlockEvent.Place event) {
		if (event.getLevel() instanceof Level) {
			TEvents.PLACE.invoker().placeBlock((Level) event.getLevel(), event.getPos(), event.getState(), event.getEntity());
		}
	}
}
