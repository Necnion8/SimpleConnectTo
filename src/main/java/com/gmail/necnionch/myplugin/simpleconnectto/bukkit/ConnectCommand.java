package com.gmail.necnionch.myplugin.simpleconnectto.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConnectCommand implements CommandExecutor {

    private final SimpleConnectTo plugin;

    public ConnectCommand(SimpleConnectTo plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1) {
            String serverName = args[0];
            Player player;

            if (args.length >= 2) {
                player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません");
                    return true;
                }
            } else if (sender instanceof Player) {
                player = ((Player) sender);
            } else {
                sender.sendMessage(ChatColor.RED + "プレイヤーを指定してください");
                return true;
            }

            try (ByteArrayOutputStream os = new ByteArrayOutputStream();
                 DataOutputStream dos = new DataOutputStream(os)) {
                dos.writeUTF("Connect");
                dos.writeUTF(serverName);

                player.sendPluginMessage(plugin, "BungeeCord", os.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

}
