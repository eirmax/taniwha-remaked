package party.lemons.taniwha.data.trade.listing;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;
import party.lemons.taniwha.Taniwha;
import party.lemons.taniwha.util.TagUtil;

import java.util.Optional;

public class StandardItemListing extends TItemListing
{
	public static final Codec<StandardItemListing> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
						ItemStack.CODEC.fieldOf("item1").forGetter(i-> i.item1.itemStack()),
						ItemStack.CODEC.optionalFieldOf("item2").forGetter(i-> Optional.of(i.item2.itemStack())),
						ItemStack.CODEC.fieldOf("result").forGetter(i->i.result),
						Codec.INT.optionalFieldOf("uses", 0).forGetter(i->i.uses),
						Codec.INT.fieldOf("max_uses").forGetter(i->i.maxUses),
						Codec.INT.optionalFieldOf("xp", 1).forGetter(i->i.xp),
						Codec.FLOAT.optionalFieldOf("price_multiplier", 0.05F).forGetter(i->i.priceMultiplier),
						Codec.INT.optionalFieldOf("demand", 0).forGetter(i->i.demand)
					)
					.apply(instance, StandardItemListing::fromCodec));

	private final ItemCost item1;
	private final ItemCost item2;
	private final ItemStack result;
	private final int uses;
	private final int maxUses;
	private final int xp;
	private final float priceMultiplier;
	private final int demand;

	public StandardItemListing(ItemCost item1, ItemCost item2, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier, int demand)
	{
		this.item1 = item1;
		this.item2 = item2;
		this.result = result;
		this.uses = uses;
		this.maxUses = maxUses;
		this.xp = xp;
		this.priceMultiplier = priceMultiplier;
		this.demand = demand;
	}

	public static StandardItemListing fromCodec(ItemStack item1, Optional<ItemStack> item2, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier, int demand)
	{
		return new StandardItemListing(
			new ItemCost(item1.getItem(), item1.getCount()),
			item2.map(stack -> new ItemCost(stack.getItem(), stack.getCount())).orElse(null),
			result, uses, maxUses, xp, priceMultiplier, demand
		);
	}

	@Override
	public TradeTypes.TradeType<?> type()
	{
		return TradeTypes.STANDARD.get();
	}

	@Nullable
	@Override
	public MerchantOffer getOffer(Entity entity, RandomSource randomSource)
	{
		return new MerchantOffer(item1, Optional.ofNullable(item2), result, uses, maxUses, xp, priceMultiplier, demand);
	}
}
