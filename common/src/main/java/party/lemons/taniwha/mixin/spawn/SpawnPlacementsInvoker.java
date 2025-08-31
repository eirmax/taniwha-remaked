package party.lemons.taniwha.mixin.spawn;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpawnPlacements.class)
public interface SpawnPlacementsInvoker
{
	static <T extends Mob> void callRegister(EntityType<T> entityType, SpawnPlacements type, Heightmap.Types types, SpawnPlacements.SpawnPredicate<T> spawnPredicate) {

	}
}