package me.klarcky.bolao;

import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;

public class Automatico {

	public static Main pl = Main.main;
	private static int delay;

	public static void iniciarauto() {
		try {
			delay = pl.configs.getConfig().getInt("AutoDelay");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bukkit.getScheduler().runTaskTimer((pl), new Runnable() {
			@Override
			public void run() {
				String automatico = null;
				try {
					automatico = pl.configs.getConfig().getString("Automatico");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (!automatico.equals("true")) {
					return;
				}
				BolaoRun.IniciarAutomatico();
			}
		}, 20L, 20 * 60 * delay);
	}
}
