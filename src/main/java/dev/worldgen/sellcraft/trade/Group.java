package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.MapCodec;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;

import java.util.ArrayList;
import java.util.List;

public record Group(RegistryEntryList<MerchantTrade> trades) implements MerchantTrade {
    public static final MapCodec<Group> CODEC = MerchantTrade.LIST_CODEC.fieldOf("trades").xmap(Group::new, Group::trades);

    @Override
    public List<TradeOffer> create(MerchantEntity merchant, Random random) {
        List<TradeOffer> offers = new ArrayList<>();
        this.trades.stream().map(entry -> entry.value().create(merchant, random)).forEach(offers::addAll);
        return offers;
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
