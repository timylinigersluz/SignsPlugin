package ch.ksrminecraft.signs;

import ch.ksrminecraft.signs.commands.CreateSignCommand;
import ch.ksrminecraft.signs.listeners.SignChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Signs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("sign").setExecutor(new CreateSignCommand());
        getServer().getPluginManager().registerEvents(new SignChangeListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
