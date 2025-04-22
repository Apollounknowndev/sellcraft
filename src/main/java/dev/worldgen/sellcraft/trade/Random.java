package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.MapCodec;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.village.TradeOffer;

import java.util.List;

public record Random(RegistryEntryList<MerchantTrade> trades) implements MerchantTrade {
    public static final MapCodec<Random> CODEC = MerchantTrade.LIST_CODEC.fieldOf("trades").xmap(Random::new, Random::trades);

    @Override
    public List<TradeOffer> create(MerchantEntity merchant, net.minecraft.util.math.random.Random random) {
        return this.trades.getRandom(random).map(entry -> entry.value().create(merchant, random)).orElse(List.of());
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
