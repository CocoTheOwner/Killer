package nl.codevs.killer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Killer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void on(ServerCommandEvent e) {
        if (e.getCommand().replace("/", "").equalsIgnoreCase("kill")) {
            Bukkit.getServer().shutdown();
            e.setCancelled(true);
        }
        if (e.getCommand().replace("/", "").equalsIgnoreCase("killer")) {
            e.setCancelled(true);
            e.getSender().sendMessage("Killer v" + getDescription().getVersion());
            e.getSender().sendMessage("/kill - Kill the server. This is unsafe!");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(getDescription().getPermissions().get(0))) {
            return false;
        }
        if (command.getName().equalsIgnoreCase("killer")) {
            sender.sendMessage("Killer v" + getDescription().getVersion());
            sender.sendMessage("/kill - Kill the server. This is unsafe!");
        }
        if (!sender.hasPermission("killer.kill")){
            return false;
        }
        if (command.getName().equalsIgnoreCase("kill") || command.getName().equalsIgnoreCase("forceStop")) {
            sender.sendMessage("Killing the server. This is not a safe operation; your worlds will not be saved!");
            Bukkit.getServer().shutdown();
            return true;
        }
        return false;
    }
}
