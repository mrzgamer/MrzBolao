package me.klarcky.bolao;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.bukkit.Bukkit;

public class BolaoRun {

	public static Main pl = Main.main;
	private static int delay;

	public static void IniciarAutomatico() {
		if (Main.parado == true) {
			return;
		}
		Main.parado = true;
		Random r = new Random();
		Main.premio = r.nextInt(10000);
		try {
			delay = pl.configs.getConfig().getInt("Delay");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pl.task1 = Bukkit.getScheduler().runTaskTimer((pl), new Runnable() {

			@Override
			public void run() {

				if (delay == 0) {
					pl.task1.cancel();
					BolaoGanhador.defineganhador();
					return;
				}

				try {
					Bukkit.broadcastMessage(pl.configs.getConfig().getString("Anuncio")
							.replace("%premio%", Integer.toString(Main.premio))
							.replace("%tempo%", Integer.toString(delay))
							.replace("%acumulado%", Integer.toString(Main.premio * Main.bolao.size()))
							.replace("&", "§"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				delay = delay - 10;
			}
		}, 20L, 4 * delay);
	}

	public static void IniciarManual(int i) {
		Main.parado = true;
		Main.premio = i;
		try {
			delay = pl.configs.getConfig().getInt("Delay");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pl.task1 = Bukkit.getScheduler().runTaskTimer((pl), new Runnable() {

			@Override
			public void run() {

				if (delay == 0) {
					pl.task1.cancel();
					BolaoGanhador.defineganhador();
					return;
				}
				try {
					Bukkit.broadcastMessage(pl.configs.getConfig().getString("Anuncio")
							.replace("%premio%", Integer.toString(Main.premio))
							.replace("%tempo%", Integer.toString(delay))
							.replace("%acumulado%", Integer.toString(Main.premio * Main.bolao.size()))
							.replace("&", "§"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				delay = delay - 10;
			}
		}, 20L, 4 * delay);
	}
}
