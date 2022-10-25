package com.gmail.necnionch.myplugin.simpleconnectto.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class SimpleConnectTo extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Optional.ofNullable(getCommand("connectto")).ifPresent(cmd ->
                cmd.setExecutor(new ConnectCommand(this)));

    }

}
