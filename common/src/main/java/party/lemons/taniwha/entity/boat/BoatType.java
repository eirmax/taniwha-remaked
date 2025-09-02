package party.lemons.taniwha.entity.boat;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import party.lemons.taniwha.TConstants;

import java.util.function.Supplier;

public class BoatType
{
    public final ResourceLocation id;
    public final Supplier<ItemLike> item;
    public final Supplier<ItemLike> chestItem;
    public final BoatShape shape;


    public BoatType(ResourceLocation id, BoatShape shape, Supplier<ItemLike> item, Supplier<ItemLike> chestBoatItem)
    {
        this.id = id;
        this.item = item;
        this.chestItem = chestBoatItem;
        this.shape = shape;

        BoatTypes.TYPES.add(this);
        BoatTypes.TYPES_MAP.put(id, this);
        EnvExecutor.runInEnv(Env.CLIENT, ()->()->{
            EntityModelLayerRegistry.register(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, getModelLocation()), "main"), shape.getLayerDefinition());
            EntityModelLayerRegistry.register(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TConstants.MOD_ID, getChestModelLocation()), "main"), shape.getChestLayerDefinition());
        });
    }

    public ResourceLocation getTexture(boolean hasChest)
    {
        if(hasChest)
            return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/entity/boat/" + id.getPath() + "_chest.png");
        return  ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/entity/boat/" + id.getPath() + ".png");
    }

    public String getModelLocation()
    {
        return "boat/" + id.getPath();
    }

    public String getChestModelLocation()
    {
        return "chestboat/" + id.getPath();
    }

    public static class BoatDispenseItemBehavior extends DefaultDispenseItemBehavior {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
        private final Supplier<BoatType> type;
        private final boolean isChestBoat;

        public BoatDispenseItemBehavior(Supplier<BoatType> type) {
            this(type, false);
        }

        public BoatDispenseItemBehavior(Supplier<BoatType> type, boolean isChestBoat) {
            this.type = type;
            this.isChestBoat = isChestBoat;
        }

        @Override
        public ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
            Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
            Level level = blockSource.level();
            double xx = blockSource.center().x + (double)((float)direction.getStepX() * 1.125F);
            double yy = blockSource.center().y + (double)((float)direction.getStepY() * 1.125F);
            double zz = blockSource.center().z + (double)((float)direction.getStepZ() * 1.125F);
            BlockPos placePosition = blockSource.pos().relative(direction);
            double yOffset;
            if (level.getFluidState(placePosition).is(FluidTags.WATER)) {
                yOffset = 1.0;
            }
            else
            {
                if (!level.getBlockState(placePosition).isAir() || !level.getFluidState(placePosition.below()).is(FluidTags.WATER)) {
                    return this.defaultDispenseItemBehavior.dispense(blockSource, itemStack);
                }

                yOffset = 0.0;
            }

            TBoat boat = (TBoat) (this.isChestBoat ? new TChestBoat(level, xx, yy + yOffset, zz) : new TBoat(level, xx, yy + yOffset, zz));
            boat.setBoatType(this.type.get());
            boat.setYRot(direction.toYRot());
            level.addFreshEntity(boat);
            itemStack.shrink(1);
            return itemStack;
        }

        @Override
        public void playSound(net.minecraft.core.dispenser.BlockSource blockSource) {
            blockSource.level().levelEvent(1000, blockSource.pos(), 0);
        }
    }

}