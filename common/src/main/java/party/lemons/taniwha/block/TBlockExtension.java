package party.lemons.taniwha.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathType;

public interface TBlockExtension
{
	default boolean isFarmlandMoist(BlockState state)
	{
		return false;
	}

	default PathType getNodePathType()
	{
		return null;
	}

	default void onRegister()
	{

	}
}
