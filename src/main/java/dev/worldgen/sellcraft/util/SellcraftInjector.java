package dev.worldgen.sellcraft.util;

import dev.worldgen.sellcraft.Sellcraft;
import dev.worldgen.sellcraft.trade.MerchantTrade;
import dev.worldgen.sellcraft.trade.provider.MerchantTradeProvider.TradeTier;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

import java.util.ArrayList;
import java.util.List;

public class SellcraftInjector {
    public static void addSellcraftTrades(MerchantEntity entity, TradeOfferList tradeOfferList, TradeTier tradeTier) {
        int count = tradeTier.count();
        RegistryEntryList<MerchantTrade> tradeSet = tradeTier.trades();
        if (count > tradeSet.size()) {
            Sellcraft.LOGGER.error("Trade tier has less trades than needed! Expected at least {}, got {}", count, tradeSet.size());
            count = tradeSet.size();
        }

        ArrayList<MerchantTrade> trades = new ArrayList<>(tradeSet.stream().map(RegistryEntry::value).toList());
        for (int i = 0; i < count;) {
            List<TradeOffer> offers = trades.remove(entity.getRandom().nextInt(trades.size())).create(entity, entity.getRandom());
            if (!offers.isEmpty()) {
                tradeOfferList.addAll(offers);
                ++i;
            }
        }

    }
}
