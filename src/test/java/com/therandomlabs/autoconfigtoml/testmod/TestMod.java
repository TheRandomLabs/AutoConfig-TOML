package com.therandomlabs.autoconfigtoml.testmod;

import com.therandomlabs.autoconfigtoml.TOMLConfigSerializer;
import com.therandomlabs.autoconfigtoml.testmod.command.TestModConfigReloadCommand;
import me.shedaniel.autoconfig1u.AutoConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

/**
 * The main class for the AutoConfig-TOML test mod.
 */
@Mod(TestMod.MOD_ID)
public final class TestMod {
	/**
	 * The AutoConfig-TOML test mod ID.
	 */
	public static final String MOD_ID = "testmod";

	private static TOMLConfigSerializer<TestModConfig> serializer;

	/**
	 * Constructs a {@link TestMod} instance.
	 */
	public TestMod() {
		reloadConfig();

		if (ModList.get().isLoaded("cloth-config")) {
			ModLoadingContext.get().registerExtensionPoint(
					ExtensionPoint.CONFIGGUIFACTORY,
					() -> (mc, screen) -> AutoConfig.getConfigScreen(
							TestModConfig.class, screen
					).get()
			);
		}

		MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
	}

	private void registerCommands(RegisterCommandsEvent event) {
		TestModConfigReloadCommand.register(event.getDispatcher());
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
