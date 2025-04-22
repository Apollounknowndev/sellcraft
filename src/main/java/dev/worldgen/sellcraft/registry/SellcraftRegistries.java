package dev.worldgen.sellcraft.registry;

import com.mojang.serialization.MapCodec;
import dev.worldgen.sellcraft.Sellcraft;
import dev.worldgen.sellcraft.trade.*;
import dev.worldgen.sellcraft.trade.provider.MerchantTradeProvider;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;

public class SellcraftRegistries {
    public static final Registry<MapCodec<? extends MerchantTrade>> MERCHANT_TRADE_TYPE = FabricRegistryBuilder.createSimple(SellcraftRegistryKeys.MERCHANT_TRADE_TYPE).buildAndRegister();

    public static void init() {
        DynamicRegistries.register(SellcraftRegistryKeys.MERCHANT_TRADE_PROVIDER, MerchantTradeProvider.CODEC);
        DynamicRegistries.register(SellcraftRegistryKeys.MERCHANT_TRADE, MerchantTrade.CODEC);

        registerTradeType("empty", Empty.CODEC);
        registerTradeType("base", Base.CODEC);
        registerTradeType("type_specific", TypeSpecific.CODEC);
        registerTradeType("enchanted_book", EnchantedBook.CODEC);
        registerTradeType("random", Random.CODEC);
        registerTradeType("group", Group.CODEC);
        registerTradeType("condition", Condition.CODEC);
    }

    private static void registerTradeType(String name, MapCodec<? extends MerchantTrade> codec) {
        Registry.register(MERCHANT_TRADE_TYPE, Sellcraft.id(name), codec);
    }
}
