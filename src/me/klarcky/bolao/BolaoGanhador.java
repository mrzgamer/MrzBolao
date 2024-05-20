package me.klarcky.bolao;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BolaoGanhador {
	public static Main pl = Main.main;

	@SuppressWarnings("deprecation")
	public static void defineganhador() {

		if (Main.bolao.isEmpty()) {
			Bukkit.broadcastMessage("§cBolao finalizado nao houve apostadores!!!");
			Main.parado = false;
			Main.bolao.clear();
			return;
		}

		Random rand = new Random();
		Player vencedor = Main.bolao.get(rand.nextInt(Main.bolao.size()));
		try {
			Bukkit.broadcastMessage(
					pl.configs.getConfig().getString("Ganhou").replace("%player%", vencedor.getDisplayName())
							.replace("%valor%", Integer.toString(Main.bolao.size() * Main.premio)).replace("&", "§"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VaultAPI.getEconomy().depositPlayer(vencedor.getName(), +Main.premio * Main.bolao.size());
		Main.parado = false;
		Main.bolao.clear();
	}
}
