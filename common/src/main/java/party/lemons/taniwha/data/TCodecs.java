package party.lemons.taniwha.data;

import com.mojang.serialization.Codec;
import net.minecraft.world.item.crafting.Ingredient;

public class TCodecs
{
    public static final Codec<Ingredient> INGREDIENT = Ingredient.CODEC;
}
