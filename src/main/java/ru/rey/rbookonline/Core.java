package ru.rey.rbookonline;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Listener(), this);

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§6rBookOnline §f| §aSuccessfully enabled!");
        Bukkit.getConsoleSender().sendMessage("§6rBookOnline §f| §aBy: §fvk.com/re1khsempai!");
        Bukkit.getConsoleSender().sendMessage("");
    }

}
