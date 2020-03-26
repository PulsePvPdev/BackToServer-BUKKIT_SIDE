package pulsepvp_.BackToServer_Spigot.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.xephi.authme.events.LoginEvent;
import pulsepvp_.BackToServer_Spigot.utils.Functions;

public class Auth implements Listener {
	@EventHandler
	public void onAuth(LoginEvent event) {
		Player player = event.getPlayer();
		player.sendMessage("ca devrait etre bon");
		Functions.sendToLastServer(player);

	}
}
