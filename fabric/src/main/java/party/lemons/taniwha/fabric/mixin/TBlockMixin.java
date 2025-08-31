package party.lemons.taniwha.fabric.mixin;

import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathType;
import org.spongepowered.asm.mixin.Mixin;
import party.lemons.taniwha.block.TBlockExtension;
import party.lemons.taniwha.block.types.TBlock;

@Mixin(TBlock.class)
public abstract class TBlockMixin implements TBlockExtension
{
	@Override
	public void onRegister()
	{
		PathType nodeType = getNodePathType();
		if(nodeType != null)
			LandPathNodeTypesRegistry.register((Block)(Object)this, (state, neighbor) -> nodeType);
	}
}
