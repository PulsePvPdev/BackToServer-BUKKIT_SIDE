package pulsepvp_.BackToServer_Spigot.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Player;

import pulsepvp_.BackToServer_Spigot.BackToServer_Spigot;

public class Functions {
	public static void teleportServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);

		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException error) {
			BackToServer_Spigot.getConsole()
					.sendMessage(BackToServer_Spigot.console_prefix + "Error when connecting to Bungeecord: " + error);
		}

		p.sendPluginMessage(BackToServer_Spigot.getInstance(), "BungeeCord", b.toByteArray());
		try {
			out.close();
		} catch (IOException error) {
			BackToServer_Spigot.getConsole()
					.sendMessage(BackToServer_Spigot.console_prefix + "Error when connecting to Bungeecord: " + error);
		}
	}

	public static void sendToLastServer(Player player) {
		ResultSet result = BackToServer_Spigot.database
				.GetResult("SELECT last_server_name FROM players WHERE player_name = '" + player.getName() + "'");
		try {
			if (result.next()) {
				teleportServer(player, result.getString("last_server_name"));
			}
		} catch (SQLException e) {
		}
	}
}
