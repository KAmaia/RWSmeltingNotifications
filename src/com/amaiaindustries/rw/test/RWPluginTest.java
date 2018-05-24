package com.amaiaindustries.rw.test;

import net.risingworld.api.*;
import net.risingworld.api.events.*;
import net.risingworld.api.events.player.*;
import net.risingworld.api.objects.*;
import net.risingworld.api.utils.*;


/**
 * @author Krystal Amaia
 * This project is free to use for any non-commercial purpose.  For commercial use please contact the author to
 * negotiate further licensing terms.
 */


public class RWPluginTest extends Plugin implements Listener {
	@java.lang.Override
	public void onEnable() {
		registerEventListener(this);
		System.out.println("Hello from Amaia Industries");
	}

	@EventMethod(Threading.Sync)
	public void onPlayerObjectInteractionEvent(PlayerObjectInteractionEvent event) {
		Player p = event.getPlayer();
		byte b = event.getObjectStatus();
		if (event.getObjectDefinition().isFurnace()) {
			Timer t = new Timer(360, 0, 1, () -> {
				p.sendTextMessage("[#FF0000]Your Ore Is Now Done!");
				SoundInformation si = new SoundInformation(this, "/assets/sounds/ding.ogg");
				p.playSound(si);
			});
			if (b == 0x00) {
				p.sendTextMessage("[#00FF00] Starting Ore Timer");
				t.start();
			}

			if (b == 0x01) {
				if (t.isActive()) {
					p.sendTextMessage("[#00FF00]Stopping Ore Timer");
					t.kill();
				}

			}
		}
	}

	@java.lang.Override
	public void onDisable() {

	}
}
