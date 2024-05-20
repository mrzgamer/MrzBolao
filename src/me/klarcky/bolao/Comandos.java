package me.klarcky.bolao;

import java.io.UnsupportedEncodingException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comandos implements CommandExecutor {
	public static Main pl = Main.main;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			try {
				sender.sendMessage(pl.configs.getConfig().getString("SemPermissao").replace("&", "§"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			if (Main.parado == false) {
				p.sendMessage("§cNao ha bolao rolando no momento tente mais tarde!!!");
				return true;
			}
			int preco = Main.premio;
			if (!VaultAPI.getEconomy().has(p, preco)) {
				try {
					p.sendMessage(pl.configs.getConfig().getString("Saldo").replace("&", "§"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			if (Main.bolao.contains(p)) {
				try {
					p.sendMessage(pl.configs.getConfig().getString("Aposta").replace("&", "§"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			VaultAPI.getEconomy().withdrawPlayer(p, preco);
			Main.bolao.add(p);
			try {
				p.sendMessage(pl.configs.getConfig().getString("Apostou")
						.replace("%valor%", Integer.toString(Main.premio)).replace("&", "§"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		if (args[0].equalsIgnoreCase("iniciar")) {
			if (!p.hasPermission("mrzbolao.admin")) {
				try {
					p.sendMessage(pl.configs.getConfig().getString("SemPermissao").replace("&", "§"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			if (Main.parado == true) {
				p.sendMessage("§cHa bolao rolando no momento tente mais tarde!!!");
				return true;
			}

			if (args.length == 1) {
				try {
					p.sendMessage(pl.configs.getConfig().getString("UseIniciar").replace("&", "§"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			BolaoRun.IniciarManual(Integer.parseInt(args[1]));
			return true;

		}
		return false;
	}
}
