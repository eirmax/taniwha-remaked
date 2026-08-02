package party.lemons.taniwha.hooks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;

import java.util.HashMap;
import java.util.Map;

public class PotteryPatternHooks
{
	private static final Map<Item, ResourceKey<DecoratedPotPattern>> EXTRA_PATTERNS = new HashMap<>();

	public static void addPotteryPatternItem(Item item, ResourceLocation pattern)
	{
		EXTRA_PATTERNS.put(item, ResourceKey.create(Registries.DECORATED_POT_PATTERN, pattern));
	}

	public static ResourceKey<DecoratedPotPattern> getPotteryPatternItem(Item item)
	{
		return EXTRA_PATTERNS.get(item);
	}
}
