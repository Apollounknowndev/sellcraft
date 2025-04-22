package dev.worldgen.sellcraft.trade;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import java.util.List;
import java.util.Optional;

public interface SimpleMerchantTrade extends MerchantTrade {
    ItemTrade createItemTrade(MerchantEntity entity, Random random);

    int maxUses();

    int experience();

    float priceMultiplier();

    @Override
    default List<TradeOffer> create(MerchantEntity entity, Random random) {
        ItemTrade trade = this.createItemTrade(entity, random);
        if (trade == null) return null;
        TradeOffer offer = new TradeOffer(trade.buying, trade.buyingSecondary, trade.selling, this.maxUses(), this.experience(), this.priceMultiplier());
        return List.of(offer);
    }

    record ItemTrade(TradedItem buying, Optional<TradedItem> buyingSecondary, ItemStack selling) {
    }
}
