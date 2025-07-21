package top.brmc.mcsmcmd;

import org.bukkit.plugin.java.JavaPlugin;

public class McsmCmdPlugin extends JavaPlugin {
    private static McsmCmdPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("mcsmcmd").setExecutor(new CommandExecutorImpl());

        getLogger().info("Plugin MCSM Cmd has loaded.");
        getLogger().info("By MeTerminator (QQ3532095196)");
    }

    public static McsmCmdPlugin getInstance() {
        return instance;
    }
}
