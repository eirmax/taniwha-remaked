package party.lemons.taniwha.neoforge.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;
import org.spongepowered.asm.mixin.Mixin;
import party.lemons.taniwha.block.TBlockExtension;
import party.lemons.taniwha.block.types.TFarmBlock;

@Mixin(TFarmBlock.class)
public abstract class TFarmBlockMixin extends FarmBlock implements TBlockExtension
{
	public TFarmBlockMixin(Properties arg) {
		super(arg);
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant)
	{
		return TriState.FALSE;
	}

	@Override
	public boolean isFertile(BlockState state, BlockGetter level, BlockPos pos)
	{
		return isFarmlandMoist(state);
	}
}