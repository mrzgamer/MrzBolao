package me.klarcky.bolao;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
	public Config configs = new Config(this, "configs");
	public BukkitTask task1;
	public static Main main;
	public static ArrayList<Player> bolao = new ArrayList<Player>();
	public static boolean parado = false;
	public static int premio;

	public void onEnable() {
		main = this;
		configs.SaveDefault();
		ConsoleCommandSender b = Bukkit.getConsoleSender();
		b.sendMessage("§b===============================");
		b.sendMessage("§b=  §cMrz§8Bolao§c Ativo com Sucesso§b  =");
		b.sendMessage("§b=   §cCriado por Klarcky    §b    =");
		b.sendMessage("§b===============================");

		comandos();
		registrar();
		loadmsg();
		Automatico.iniciarauto();

	}

	private void registrar() {

	}

	private void comandos() {
		getCommand("bolao").setExecutor(new Comandos());

	}

	public void onDisable() {
		ConsoleCommandSender b = Bukkit.getConsoleSender();
		b.sendMessage("§b===============================");
		b.sendMessage("§b=  §cMrz§8Bolao§c Desativado com Sucesso§b  =");
		b.sendMessage("§b=   §cCriado por Klarcky    §b    =");
		b.sendMessage("§b===============================");
	}

	public void loadmsg() {

	}

}
