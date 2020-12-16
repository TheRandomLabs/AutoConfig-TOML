/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 TheRandomLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.therandomlabs.autoconfigtoml.testmod;

import com.therandomlabs.autoconfigtoml.TOMLConfigSerializer;
import com.therandomlabs.autoconfigtoml.testmod.command.TestModConfigReloadCommand;
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
