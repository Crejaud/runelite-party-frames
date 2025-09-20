package com.partyframes;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PartyFramesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PartyFramesPlugin.class);
		RuneLite.main(args);
	}
}