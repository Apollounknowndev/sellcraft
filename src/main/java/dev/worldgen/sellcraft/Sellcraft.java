package dev.worldgen.sellcraft;

import dev.worldgen.sellcraft.registry.SellcraftRegistries;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sellcraft implements ModInitializer {
	public static final String MOD_ID = "sellcraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		SellcraftRegistries.init();
	}

	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
}