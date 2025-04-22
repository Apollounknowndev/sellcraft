package dev.worldgen.sellcraft.datagen.trades;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

import java.util.Map;

public class VillagerTradeHelper {
    public static final Map<Integer, String> LEVELS_TO_NAMES = Map.of(
        1, "novice",
        2, "apprentice",
        3, "journeyman",
        4, "expert",
        5, "master"
    );

    public static Identifier buy(Item item, VillagerProfession profession, int level) {
        return key(item, profession, level, "buy_");
    }

    public static Identifier sell(Item item, VillagerProfession profession, int level) {
        return key(item, profession, level, "sell_");
    }

    public static Identifier sellEnchanted(Item item, VillagerProfession profession, int level) {
        return key(item, profession, level, "sell_enchanted_");
    }

    private static Identifier key(Item item, VillagerProfession profession, int level, String prefix) {
        Identifier id = Registries.ITEM.getId(item);
        return id.withPrefixedPath(String.format("%s/%s/%s", profession, LEVELS_TO_NAMES.get(level), prefix));
    }
}
