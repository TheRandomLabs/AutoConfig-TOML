package com.therandomlabs.autoconfigtoml.testmod;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;

/**
 * The Mod Menu entry point for the AutoConfig-TOML test mod.
 */
public final class TestModModMenuEntryPoint implements ModMenuApi {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(TestModConfig.class, parent).get();
	}
}
