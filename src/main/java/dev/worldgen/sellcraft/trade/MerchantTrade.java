package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.sellcraft.registry.SellcraftRegistries;
import dev.worldgen.sellcraft.registry.SellcraftRegistryKeys;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MerchantTrade {
    Codec<MerchantTrade> CODEC = SellcraftRegistries.MERCHANT_TRADE_TYPE.getCodec().dispatch(MerchantTrade::codec, Function.identity());
    Codec<RegistryEntry<MerchantTrade>> ENTRY_CODEC = RegistryElementCodec.of(SellcraftRegistryKeys.MERCHANT_TRADE, CODEC);
    Codec<RegistryEntryList<MerchantTrade>> LIST_CODEC = RegistryCodecs.entryList(SellcraftRegistryKeys.MERCHANT_TRADE, CODEC);
    Codec<TradedItem> BUYING_CODEC = Codec.withAlternative(TradedItem.CODEC, Item.ENTRY_CODEC, item -> new TradedItem(item.value()));
    Codec<ItemStack> SELLING_CODEC = Codec.withAlternative(ItemStack.CODEC, Item.ENTRY_CODEC, ItemStack::new);
    RegistryEntry<MerchantTrade> EMPTY = RegistryEntry.of(new Empty());

    List<TradeOffer> create(MerchantEntity merchant, Random random);

    MapCodec<? extends MerchantTrade> codec();

    static LootContext createContext(MerchantEntity entity) {
        LootWorldContext worldContext = new LootWorldContext.Builder((ServerWorld) entity.getWorld())
                .add(LootContextParameters.ORIGIN, entity.getPos())
                .addOptional(LootContextParameters.THIS_ENTITY, entity)
                .build(LootContextTypes.COMMAND);
        return new LootContext.Builder(worldContext).build(Optional.empty());
    }
}
