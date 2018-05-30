package com.amaiaindustries.rw.SmeltingNotifier;

import net.risingworld.api.*;
import net.risingworld.api.events.*;
import net.risingworld.api.events.player.world.*;
import net.risingworld.api.objects.*;
import net.risingworld.api.utils.*;


/**
 * @author Krystal Amaia
 * This project is free to use for any non-commercial purpose.  For commercial use please contact the author to
 * negotiate further licensing terms.
 */


public class SmeltingNotifier extends Plugin implements Listener {
	private SoundInformation dingSound;
	@java.lang.Override
	public void onEnable() {
		registerEventListener(this);
		System.out.println("Loading Ding Sound.");
		dingSound = new SoundInformation(this, "/assets/sounds/ding.ogg");
		System.out.println("Hello from Amaia Industries");
	}
	@EventMethod(Threading.Sync)
	public void onPlayerChangeObjectStatusEvent(PlayerChangeObjectStatusEvent event) {
		Player p = event.getPlayer();
		byte b = event.getObjectStatus();
		byte b1 = event.getNewObjectStatus();
		if (event.getObjectDefinition().isFurnace()) {
			Timer t = new Timer(360, 0, 1, () -> {
				p.sendTextMessage("[#FF0000]Your Ore Is Now Done!");
				p.playSound(dingSound);
			});

			if (b == 0x00 && b1 == 0x01) {
				p.sendTextMessage("[#00FF00] Starting Ore Timer");
				t.start();
			}

			if (b == 0x01 && b1 == 0x00) {
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
