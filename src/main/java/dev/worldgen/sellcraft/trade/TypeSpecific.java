package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerDataContainer;
import net.minecraft.village.VillagerType;

import java.util.List;
import java.util.Map;

public record TypeSpecific(Map<RegistryEntry<VillagerType>, RegistryEntry<MerchantTrade>> trades, RegistryEntry<MerchantTrade> fallback) implements MerchantTrade {
    public static final MapCodec<TypeSpecific> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Codec.unboundedMap(VillagerType.CODEC, MerchantTrade.ENTRY_CODEC).fieldOf("trades").forGetter(TypeSpecific::trades),
        MerchantTrade.ENTRY_CODEC.fieldOf("fallback").orElse(EMPTY).forGetter(TypeSpecific::fallback)
    ).apply(instance, TypeSpecific::new));

    @Override
    public List<TradeOffer> create(MerchantEntity merchant, Random random) {
        if (merchant instanceof VillagerDataContainer container) {
            RegistryEntry<VillagerType> type = container.getVillagerData().type();
            return this.trades.getOrDefault(type, this.fallback).value().create(merchant, random);
        }
        return this.fallback.value().create(merchant, random);
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
