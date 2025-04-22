package dev.worldgen.sellcraft.mixin;

import dev.worldgen.sellcraft.registry.SellcraftRegistryKeys;
import dev.worldgen.sellcraft.trade.provider.MerchantTradeProvider;
import dev.worldgen.sellcraft.trade.provider.MerchantTradeProvider.TradeTier;
import dev.worldgen.sellcraft.util.SellcraftInjector;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {

	@Inject(at = @At("HEAD"), method = "fillRecipes", cancellable = true)
	private void sellcraft$injectSellcraftTrades(CallbackInfo ci) {
		VillagerEntity $this = ((VillagerEntity) (Object) this);

		VillagerData data = $this.getVillagerData();
		RegistryKey<VillagerProfession> profession = data.profession().getKey().orElse(null);
		if (profession != null) {
			Registry<MerchantTradeProvider> registry = $this.getRegistryManager().getOrThrow(SellcraftRegistryKeys.MERCHANT_TRADE_PROVIDER);
			var tradeProvider = registry.stream().filter(provider -> $this.getType() == provider.entity() && data.profession().matchesKey(provider.profession())).findFirst();

			if (tradeProvider.isPresent() && data.level() <= tradeProvider.get().tradeTiers().size()) {
				TradeTier tradeTier = tradeProvider.get().tradeTiers().get(data.level() - 1);
				SellcraftInjector.addSellcraftTrades($this, $this.getOffers(), tradeTier);
				ci.cancel();
			}
		}
	}
}