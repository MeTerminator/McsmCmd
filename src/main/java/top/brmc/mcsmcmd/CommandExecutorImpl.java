package top.brmc.mcsmcmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;

public class CommandExecutorImpl implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mcsmcmd.use")) {
            sender.sendMessage("§c你没有权限使用该指令。");
            return true;
        }

        // 重载配置功能
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            McsmCmdPlugin.getInstance().reloadConfig();
            sender.sendMessage("§a配置文件已重载。");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§c用法: /mcsmcmd <子服名称|组名称> <命令>");
            sender.sendMessage("§7或: /mcsmcmd reload §8(重载配置)");
            return true;
        }

        String target = args[0];
        String cmd = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        McsmAPI api = new McsmAPI();

        List<Map<String, String>> targets = api.getTargets(target);
        if (targets.isEmpty()) {
            sender.sendMessage("§c未找到指定的实例或组：" + target);
            return true;
        }

        for (Map<String, String> inst : targets) {
            boolean result = api.sendCommand(inst.get("uuid"), inst.get("daemonId"), cmd);
            sender.sendMessage("§7发送至 §a" + inst.get("name") + "§7: " + (result ? "§a成功" : "§c失败"));
        }

        return true;
    }
}
