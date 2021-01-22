/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020-2021 TheRandomLabs
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

import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.SpecIntInRange;
import com.therandomlabs.autoconfigtoml.TOMLConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

/**
 * The AutoConfig-TOML test mod configuration.
 */
@SuppressWarnings("CanBeFinal")
@TOMLConfigSerializer.Comment("AutoConfig-TOML test mod configuration.")
@Config(name = TestMod.MOD_ID)
public final class TestModConfig implements ConfigData {
	public static final class Misc implements ConfigData {
		@TOMLConfigSerializer.Comment({
				"The name of the command that reloads this configuration from disk.",
				"Set this to an empty string to disable the command.",
				"Changes to this option are applied when a server is loaded."
		})
		@ConfigEntry.Gui.Tooltip
		public String configReloadCommand = "testmodconfigreload";

		@Path("test_toml_property")
		@SpecIntInRange(min = 1, max = 68)
		@TOMLConfigSerializer.Comment({
				"Test TOML property.",
				"This value is automatically rounded down to the nearest even integer."
		})
		@ConfigEntry.Gui.Tooltip
		public int testTOMLProperty = 66;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void validatePostLoad() {
			configReloadCommand = configReloadCommand.trim();

			if (testTOMLProperty % 2 != 0) {
				testTOMLProperty--;
			}
		}
	}

	@TOMLConfigSerializer.Comment("Miscellaneous options.")
	@ConfigEntry.Category("misc")
	@ConfigEntry.Gui.TransitiveObject
	public Misc misc = new Misc();
}
