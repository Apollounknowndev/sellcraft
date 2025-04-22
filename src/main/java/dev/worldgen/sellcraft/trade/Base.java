package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradedItem;

import java.util.Optional;

public record Base(TradedItem buying, Optional<TradedItem> buyingSecondary, ItemStack selling, Optional<LootFunction> sellingModifier, int maxUses, int experience, float priceMultiplier) implements SimpleMerchantTrade {
    public static final MapCodec<Base> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        BUYING_CODEC.fieldOf("buying").forGetter(Base::buying),
        BUYING_CODEC.optionalFieldOf("buying_secondary").forGetter(Base::buyingSecondary),
        SELLING_CODEC.fieldOf("selling").forGetter(Base::selling),
        LootFunctionTypes.CODEC.optionalFieldOf("selling_modifier").forGetter(Base::sellingModifier),
        Codec.INT.fieldOf("max_uses").orElse(12).forGetter(Base::maxUses),
        Codec.INT.fieldOf("experience").orElse(1).forGetter(Base::experience),
        Codec.FLOAT.fieldOf("price_multiplier").orElse(0.05f).forGetter(Base::priceMultiplier)
    ).apply(instance, Base::new));

    @Override
    public ItemTrade createItemTrade(MerchantEntity entity, Random random) {
        return new ItemTrade(this.buying, this.buyingSecondary, modifyStack(entity, this.selling.copy()));
    }

    private ItemStack modifyStack(MerchantEntity entity, ItemStack original) {
        if (this.sellingModifier.isEmpty()) return original;

        LootContext context = MerchantTrade.createContext(entity);
        ItemStack modified = this.sellingModifier.get().apply(original, context);
        modified.capCount(modified.getMaxCount());

        return modified;
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
