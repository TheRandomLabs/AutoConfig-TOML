/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018-2020 TheRandomLabs
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

package com.therandomlabs.autoconfigtoml.testmod.client.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.therandomlabs.autoconfigtoml.testmod.TestMod;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.text.TranslatableText;

/**
 * The command that reloads the AutoConfig-TOML test mod client-sided configuration.
 */
public final class TestModClientConfigReloadCommand {
	private TestModClientConfigReloadCommand() {}

	/**
	 * Registers the command that reloads the AutoConfig-TOML test mod client-sided configuration.
	 *
	 * @param dispatcher the {@link CommandDispatcher}.
	 * @param dedicated whether the server is dedicated.
	 */
	public static void register(
			CommandDispatcher<CottonClientCommandSource> dispatcher, boolean dedicated
	) {
		final String name = TestMod.config().misc.configReloadCommand;

		if (!name.isEmpty()) {
			dispatcher.register(
					LiteralArgumentBuilder.<CottonClientCommandSource>literal(name).
							executes(context -> execute(context.getSource()))
			);
		}
	}

	private static int execute(CottonClientCommandSource source) {
		TestMod.reloadConfig();
		source.sendFeedback(
				new TranslatableText("commands.testmodclientconfigreload.success"), true
		);
		return Command.SINGLE_SUCCESS;
	}
}
