package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.MapCodec;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;

import java.util.List;

public record Empty() implements MerchantTrade {
    public static final MapCodec<Empty> CODEC = MapCodec.unit(Empty::new);

    @Override
    public List<TradeOffer> create(MerchantEntity merchant, Random random) {
        return List.of();
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
