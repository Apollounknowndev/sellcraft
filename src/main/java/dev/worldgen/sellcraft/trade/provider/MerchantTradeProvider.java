package dev.worldgen.sellcraft.trade.provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.sellcraft.registry.SellcraftRegistryKeys;
import dev.worldgen.sellcraft.trade.MerchantTrade;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.village.VillagerProfession;

import java.util.List;

public record MerchantTradeProvider(EntityType<?> entity, RegistryKey<VillagerProfession> profession, List<TradeTier> tradeTiers) {
    public static final Codec<MerchantTradeProvider> CODEC = RecordCodecBuilder.<MerchantTradeProvider>create(instance -> instance.group(
        EntityType.CODEC.fieldOf("entity").forGetter(MerchantTradeProvider::entity),
        RegistryKey.createCodec(RegistryKeys.VILLAGER_PROFESSION).fieldOf("profession").orElse(VillagerProfession.NITWIT).forGetter(MerchantTradeProvider::profession),
        TradeTier.CODEC.listOf().fieldOf("trade_tiers").forGetter(MerchantTradeProvider::tradeTiers)
    ).apply(instance, MerchantTradeProvider::new)).validate(MerchantTradeProvider::validate);

    private static DataResult<MerchantTradeProvider> validate(MerchantTradeProvider provider) {
        if (provider.profession().equals(VillagerProfession.NITWIT) && provider.entity() == EntityType.VILLAGER) {
            return DataResult.error(() -> "Missing villager profession");
        }
        return DataResult.success(provider);
    }

    public record TradeTier(int count, RegistryEntryList<MerchantTrade> trades) {
        public static final Codec<TradeTier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.NON_NEGATIVE_INT.fieldOf("count").forGetter(TradeTier::count),
            RegistryCodecs.entryList(SellcraftRegistryKeys.MERCHANT_TRADE, MerchantTrade.CODEC, true).fieldOf("trades").forGetter(TradeTier::trades)
        ).apply(instance, TradeTier::new));
    }
}
