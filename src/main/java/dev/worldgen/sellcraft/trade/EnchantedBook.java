package dev.worldgen.sellcraft.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradedItem;

import java.util.Optional;

public record EnchantedBook(Optional<TradedItem> buyingSecondary, RegistryEntryList<Enchantment> enchantments, int minLevel, int maxLevel, int maxUses, int experience, float priceMultiplier) implements SimpleMerchantTrade {
    public static final MapCodec<EnchantedBook> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        BUYING_CODEC.optionalFieldOf("buying_secondary").forGetter(EnchantedBook::buyingSecondary),
        RegistryCodecs.entryList(RegistryKeys.ENCHANTMENT).fieldOf("enchantments").forGetter(EnchantedBook::enchantments),
        Codec.intRange(1, 255).fieldOf("min_level").orElse(1).forGetter(EnchantedBook::minLevel),
        Codec.intRange(1, 255).fieldOf("max_level").orElse(255).forGetter(EnchantedBook::maxLevel),
        Codec.INT.fieldOf("max_uses").orElse(12).forGetter(EnchantedBook::maxUses),
        Codec.INT.fieldOf("experience").orElse(1).forGetter(EnchantedBook::experience),
        Codec.FLOAT.fieldOf("price_multiplier").orElse(0.2f).forGetter(EnchantedBook::priceMultiplier)
    ).apply(instance, EnchantedBook::new));

    @Override
    public ItemTrade createItemTrade(MerchantEntity entity, Random random) {
        Optional<RegistryEntry<Enchantment>> optional = this.enchantments.getRandom(random);
        if (optional.isEmpty()) return null;

        RegistryEntry<Enchantment> registryEntry = optional.get();
        Enchantment enchantment = registryEntry.value();

        int level = random.nextBetween(Math.max(minLevel, enchantment.getMinLevel()), Math.min(maxLevel, enchantment.getMaxLevel()));
        ItemStack book = EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(registryEntry, level));
        int emeralds = 2 + random.nextInt(5 + level * 10) + 3 * level;
        if (registryEntry.isIn(EnchantmentTags.DOUBLE_TRADE_PRICE)) {
            emeralds *= 2;
        }

        if (emeralds > 64) {
            emeralds = 64;
        }

        return new ItemTrade(new TradedItem(Items.EMERALD, emeralds), this.buyingSecondary, book);
    }

    @Override
    public MapCodec<? extends MerchantTrade> codec() {
        return CODEC;
    }
}
