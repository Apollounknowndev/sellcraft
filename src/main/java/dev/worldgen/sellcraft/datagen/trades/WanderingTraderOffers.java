package dev.worldgen.sellcraft.datagen.trades;

import dev.worldgen.sellcraft.trade.MerchantTrade;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class WanderingTraderOffers {
    public static void bootstrap(BiConsumer<Identifier, MerchantTrade> consumer) {

    }
        /*regular(consumer, sellItem(Items.ALLIUM));
        regular(consumer, sellItem(Items.AZURE_BLUET));
        regular(consumer, sellItem(Items.BLUE_ORCHID), 8);
        regular(consumer, sellItem(Items.CORNFLOWER));
        regular(consumer, sellItem(Items.DANDELION));
        regular(consumer, sellItem(Items.LILY_OF_THE_VALLEY), 7);
        regular(consumer, sellItem(Items.ORANGE_TULIP));
        regular(consumer, sellItem(Items.OXEYE_DAISY));
        regular(consumer, sellItem(Items.PINK_TULIP));
        regular(consumer, sellItem(Items.POPPY));
        regular(consumer, sellItem(Items.RED_TULIP));
        regular(consumer, sellItem(Items.WHITE_TULIP));
        regular(consumer, sellItem(Items.FERN));
        regular(consumer, sellItem(Items.BROWN_MUSHROOM));
        regular(consumer, sellItem(Items.RED_MUSHROOM));
        regular(consumer, sellItem(Items.BEETROOT_SEEDS));
        regular(consumer, sellItem(Items.MELON_SEEDS));
        regular(consumer, sellItem(Items.PUMPKIN_SEEDS));
        regular(consumer, sellItem(Items.WHEAT_SEEDS));
        regular(consumer, sellItem(Items.PUMPKIN), 4);
        regular(consumer, sellItem(Items.SUGAR_CANE), 8);
        regular(consumer, sellItem(Items.VINE));
        regular(consumer, sellItems(Items.LILY_PAD, 2), 5);
        regular(consumer, sellItems(Items.MOSS_BLOCK, 2), 5);
        regular(consumer, sellItems(Items.POINTED_DRIPSTONE, 2), 5);
        regular(consumer, sellItems(Items.ROOTED_DIRT, 2), 5);
        regular(consumer, sellItems(Items.SMALL_DRIPLEAF, 2), 5);
        regular(consumer, sellItems(Items.RED_SAND, 4), 6);
        regular(consumer, sellItems(Items.SAND, 8), 8);
        Arrays.stream(DyeColor.values()).forEach(color ->
            regular(consumer, sellItems(DyeItem.byColor(color), 3))
        );

        regular(consumer, sellItem(Items.GLOWSTONE, 2), 6);
        regular(consumer, sellItem(Items.SEA_PICKLE, 2), 6);

        regular(consumer, sellItem(Items.CACTUS, 3), 8);
        regular(consumer, sellItem(Items.BRAIN_CORAL_BLOCK, 3), 8);
        regular(consumer, sellItem(Items.BUBBLE_CORAL_BLOCK, 3), 8);
        regular(consumer, sellItem(Items.FIRE_CORAL_BLOCK, 3), 8);
        regular(consumer, sellItem(Items.HORN_CORAL_BLOCK, 3), 8);
        regular(consumer, sellItem(Items.TUBE_CORAL_BLOCK, 3), 8);
        regular(consumer, sellItem(Items.KELP, 3), 12);

        regular(consumer, sellItem(Items.SLIME_BALL, 4), 5);

        regular(consumer, sellItem(Items.ACACIA_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.BIRCH_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.DARK_OAK_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.JUNGLE_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.OAK_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.SPRUCE_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.MANGROVE_PROPAGULE, 5), 8);
        regular(consumer, sellItem(Items.CHERRY_SAPLING, 5), 8);
        regular(consumer, sellItem(Items.NAUTILUS_SHELL, 5), 8);

        special(consumer, sellItem(Items.GUNPOWDER), 8);
        special(consumer, sellItems(Items.PODZOL, 3, 3), 6);
        special(consumer, sellItem(Items.PACKED_ICE, 3), 6);
        special(consumer, sellItem(Items.PUFFERFISH_BUCKET, 5), 4);
        special(consumer, sellItem(Items.TROPICAL_FISH_BUCKET, 5), 4);
        special(consumer, sellItem(Items.BLUE_ICE, 6), 6);
    }

    private static void regular(BiConsumer<Identifier, MerchantTrade> consumer, BaseItemListing listing) {
        regular(consumer, listing, 12);
    }

    private static void regular(BiConsumer<Identifier, MerchantTrade> consumer, BaseItemListing listing, int maxUses) {
        Item item = listing.getSelling().getItem();
        consumer.accept(key(item, "regular"), buildOffer(wandering(false), listing, maxUses));
    }

    private static void special(BiConsumer<Identifier, MerchantTrade> consumer, BaseItemListing listing, int maxUses) {
        Item item = listing.getSelling().getItem();
        consumer.accept(key(item, "special"), buildOffer(wandering(true), listing, maxUses));
    }

    private static TradeOffer buildOffer(TraderPredicate predicate, ItemListing itemListing, int maxUses) {
        return new TradeOffer(1, predicate, itemListing, maxUses, 0, 0f);
    }

    private static Identifier key(Item item, String type) {
        Identifier id = Registries.ITEM.getId(item);
        return id.withPrefixedPath("wandering_trader/" +type+"/sell_");
    }*/
}
