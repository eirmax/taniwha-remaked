package party.lemons.taniwha.level.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import org.jetbrains.annotations.Nullable;

public class IgnoreAirProcessor extends StructureProcessor
{
	public static final MapCodec<IgnoreAirProcessor> CODEC = MapCodec.unit((() -> IgnoreAirProcessor.INSTANCE));
	public static final IgnoreAirProcessor INSTANCE = new IgnoreAirProcessor();


	private IgnoreAirProcessor()
	{}

	@Nullable
	@Override
	public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos blockPos, BlockPos blockPos2, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo input, StructurePlaceSettings structurePlaceSettings)
	{
		BlockState blockState = input.state();
		if(blockState.isAir())
			return null;

		return input;
	}

	@Override
	protected StructureProcessorType<?> getType()
	{
		return TStructureProcessors.IGNORE_AIR.get();
	}
}
