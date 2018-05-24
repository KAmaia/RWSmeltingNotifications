package com.amaiaindustries.rw.test;

import com.amaiaindustries.rw.Utils.*;
import net.risingworld.api.*;
import net.risingworld.api.events.*;
import net.risingworld.api.events.player.*;
import net.risingworld.api.events.player.world.*;
import net.risingworld.api.objects.*;
import net.risingworld.api.utils.*;


/**
 * @author Krystal Amaia
 */


public class RWPluginTest extends Plugin implements Listener {
	@java.lang.Override
	public void onEnable() {
		registerEventListener(this);
		System.out.println("Hello from Amaia Industries");
	}
	@EventMethod(Threading.Sync)
	public void onPlayerHitEvent(PlayerDamageEvent phe){
		System.out.println("Hit for: " + AIUtilities.convertShortToString(phe.getDamage()) + " damage");
		phe.getPlayer().sendTextMessage("You Have Been Hit And Taken: "
			                                   + phe.getDamage()
			                                   + " Damage.");
		phe.getPlayer().sendTextMessage("You now have: " + phe.getPlayer().getHealth() + " Health remaining.");
	}

	@EventMethod(Threading.Sync)
	public void onPlayerObjectEvent(PlayerObjectEvent event) {
		if (event.getObjectDefinition().isFurnace()) {
			event.getPlayer().sendTextMessage("Status: " + AIUtilities.byteToString(event.getObjectStatus()));
		}
	}

	@EventMethod(Threading.Sync)
	public void onPlayerObjectInteractionEvent(PlayerObjectInteractionEvent event) {
		Player p = event.getPlayer();
		byte b = event.getObjectStatus();
		if (event.getObjectDefinition().isFurnace()) {
			//check to see if we're activating the furnace and say Hello.
			p.sendTextMessage("Hello");
			Timer t = new Timer(360, 0, 1, () -> {
				p.sendTextMessage("[#FF0000]Your Ore Is Now Done!");
				SoundInformation si = new SoundInformation(getPath() + "/assets/sounds/ding.ogg");
				p.playSound(si);
			});
			if (b == 0x00) {
				t.start();
			}

			if (b == 0x01) {
				if (t.isActive()) {
					p.sendTextMessage("[#00FF00]Stopping Timer");
					t.kill();
				}

			}
		}
	}

	@java.lang.Override
	public void onDisable() {

	}
}
