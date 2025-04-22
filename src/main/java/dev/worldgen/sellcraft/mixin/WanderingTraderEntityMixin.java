package dev.worldgen.sellcraft.mixin;

import dev.worldgen.sellcraft.registry.SellcraftRegistryKeys;
import dev.worldgen.sellcraft.trade.provider.MerchantTradeProvider;
import dev.worldgen.sellcraft.util.SellcraftInjector;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTraderEntity.class)
public abstract class WanderingTraderEntityMixin {

	@Inject(at = @At("HEAD"), method = "fillRecipes", cancellable = true)
	private void sellcraft$injectSellcraftTrades(CallbackInfo ci) {
		WanderingTraderEntity $this = ((WanderingTraderEntity) (Object) this);

		Registry<MerchantTradeProvider> registry = $this.getRegistryManager().getOrThrow(SellcraftRegistryKeys.MERCHANT_TRADE_PROVIDER);
		var tradeProvider = registry.stream().filter(provider -> provider.entity() == $this.getType()).findFirst();

		if (tradeProvider.isPresent()) {
			for (MerchantTradeProvider.TradeTier tradeTier : tradeProvider.get().tradeTiers()) {
				SellcraftInjector.addSellcraftTrades($this, $this.getOffers(), tradeTier);
			}
			ci.cancel();
		}
	}
}