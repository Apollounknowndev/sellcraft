package dev.worldgen.sellcraft.registry;

import com.mojang.serialization.MapCodec;
import dev.worldgen.sellcraft.Sellcraft;
import dev.worldgen.sellcraft.trade.MerchantTrade;
import dev.worldgen.sellcraft.trade.provider.MerchantTradeProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public interface SellcraftRegistryKeys {
    RegistryKey<Registry<MerchantTrade>> MERCHANT_TRADE = RegistryKey.ofRegistry(Sellcraft.id("merchant_trade"));
    RegistryKey<Registry<MapCodec<? extends MerchantTrade>>> MERCHANT_TRADE_TYPE = RegistryKey.ofRegistry(Sellcraft.id("merchant_trade_type"));
    RegistryKey<Registry<MerchantTradeProvider>> MERCHANT_TRADE_PROVIDER = RegistryKey.ofRegistry(Sellcraft.id("merchant_trade_provider"));
}
