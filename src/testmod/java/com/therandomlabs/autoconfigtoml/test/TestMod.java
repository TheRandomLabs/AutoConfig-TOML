package com.therandomlabs.autoconfigtoml.test;

import com.therandomlabs.autoconfigtoml.TOMLConfigSerializer;
import com.therandomlabs.autoconfigtoml.test.command.TestModConfigReloadCommand;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

/**
 * The main class for the AutoConfig-TOML test mod.
 */
public final class TestMod implements ModInitializer {
	/**
	 * The AutoConfig-TOML test mod ID.
	 */
	public static final String MOD_ID = "testmod";

	private static TOMLConfigSerializer<TestModConfig> serializer;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInitialize() {
		reloadConfig();
		CommandRegistrationCallback.EVENT.register(TestModConfigReloadCommand::register);
	}

	/**
	 * Returns the AutoConfig-TOML test mod configuration.
	 *
	 * @return a {@link TestModConfig} object.
	 */
	public static TestModConfig config() {
		if (serializer == null) {
			reloadConfig();
		}

		return serializer.getConfig();
	}

	/**
	 * Reloads the AutoConfig-TOML test mod configuration from disk.
	 */
	public static void reloadConfig() {
		if (serializer == null) {
			AutoConfig.register(TestModConfig.class, (definition, configClass) -> {
				serializer = new TOMLConfigSerializer<>(definition, configClass);
				return serializer;
			});
		} else {
			serializer.reloadFromDisk();
		}
	}
}
