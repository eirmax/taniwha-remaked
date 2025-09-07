package party.lemons.taniwha.hooks.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;

public interface DispenserBlockHooks {
    public static void removeItemBehaviour(Item item) {
        DispenserBlock.DISPENSER_REGISTRY.remove(item);
    }

    public static boolean hasItemBehaviour(Item item) {
        return DispenserBlock.DISPENSER_REGISTRY.containsKey(item);
    }

    void removeBehaviour(Item item);
    boolean hasBehaviour(Item item);
}