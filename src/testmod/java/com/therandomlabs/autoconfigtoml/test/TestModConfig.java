package com.therandomlabs.autoconfigtoml.test;

import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.SpecIntInRange;
import com.therandomlabs.autoconfigtoml.TOMLConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

/**
 * The AutoConfig-TOML test mod configuration.
 */

@TOMLConfigSerializer.Comment("AutoConfig-TOML test mod configuration.")
@Config(name = TestMod.MOD_ID)
public final class TestModConfig implements ConfigData {
	public static final class Client implements ConfigData {
		@TOMLConfigSerializer.Comment({
				"The name of the command that reloads this configuration from disk on the client.",
				"Set this to an empty string to disable the command.",
				"Changes to this option are applied when a server is loaded."
		})
		@ConfigEntry.Gui.Tooltip
		public String configReloadCommand = "testmodclientconfigreload";

		@Override
		public void validatePostLoad() {
			configReloadCommand = configReloadCommand.trim();
		}
	}

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
				"This value is automatically rounded down to the nearest integer."
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

	@TOMLConfigSerializer.Comment("Client-sided options.")
	@ConfigEntry.Category("client")
	@ConfigEntry.Gui.TransitiveObject
	public Client client = new Client();

	@TOMLConfigSerializer.Comment("Miscellaneous options.")
	@ConfigEntry.Category("misc")
	@ConfigEntry.Gui.TransitiveObject
	public Misc misc = new Misc();
}
