package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;

import java.util.List;

public record Condition(LootCondition condition, RegistryEntry<MerchantTrade> onTrue, RegistryEntry<MerchantTrade> onFalse) implements MerchantTrade {
    public static final MapCodec<Condition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        LootCondition.BASE_CODEC.fieldOf("condition").forGetter(Condition::condition),
        MerchantTrade.ENTRY_CODEC.fieldOf("on_true").orElse(EMPTY).forGetter(Condition::onTrue),
        MerchantTrade.ENTRY_CODEC.fieldOf("on_false").orElse(EMPTY).forGetter(Condition::onFalse)
    ).apply(instance, Condition::new));

    @Override
    public List<TradeOffer> create(MerchantEntity entity, Random random) {
        LootContext context = MerchantTrade.createContext(entity);
        var trade = this.condition.test(context) ? this.onTrue : this.onFalse;
        return trade.value().create(entity, random);
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
