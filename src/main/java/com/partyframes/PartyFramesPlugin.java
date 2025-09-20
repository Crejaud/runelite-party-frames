/*
 * Copyright (c) 2025, Corentin Rejaud <https://github.com/crejaud>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.partyframes;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.party.PartyService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.party.PartyPlugin;
import net.runelite.client.plugins.party.PartyPluginService;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Party Frames",
		description = "Overlay with health and prayer bars of each party member from the Party plugin",
		tags = {"party", "frames", "ui", "grid"}
)
@PluginDependency(PartyPlugin.class)
public class PartyFramesPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private PartyService party;

	@Inject
	private PartyPluginService partyPluginService;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private PartyFramesConfig config;

	@Inject
	private PartyFramesOverlay partyFramesOverlay;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(partyFramesOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(partyFramesOverlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
	}

	@Provides
	PartyFramesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PartyFramesConfig.class);
	}
}
