package party.lemons.taniwha.neoforge.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import party.lemons.taniwha.block.WoodTypeHolder;

@Mixin(FenceGateBlock.class)
public class FenceGateBlockMixin implements WoodTypeHolder
{

	@Mutable
	@Final
	private SoundEvent openSound;


	@Mutable
	@Final
	private SoundEvent closeSound;

    public FenceGateBlockMixin(SoundEvent openSound, SoundEvent closeSound) {
        this.openSound = openSound;
        this.closeSound = closeSound;
    }

    @Override
	public WoodType getWoodType()
	{
		return new WoodType("", null, null, null, openSound, closeSound);
	}
}