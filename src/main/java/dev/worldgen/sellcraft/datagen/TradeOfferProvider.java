package dev.worldgen.sellcraft.datagen;

import dev.worldgen.sellcraft.datagen.trades.WanderingTraderOffers;
import dev.worldgen.sellcraft.trade.MerchantTrade;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class TradeOfferProvider extends FabricCodecDataProvider<MerchantTrade> {
    public TradeOfferProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(dataOutput, registriesFuture, DataOutput.OutputType.DATA_PACK, "sellcraft/merchant_trade", MerchantTrade.CODEC);
    }

    @Override
    public String getName() {
        return "Trade Offers";
    }

    @Override
    protected void configure(BiConsumer<Identifier, MerchantTrade> provider, RegistryWrapper.WrapperLookup lookup) {
        WanderingTraderOffers.bootstrap(provider);
    }
}
