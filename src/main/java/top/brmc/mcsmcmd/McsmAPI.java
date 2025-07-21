package top.brmc.mcsmcmd;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class McsmAPI {
    private final FileConfiguration config = McsmCmdPlugin.getInstance().getConfig();

    public List<Map<String, String>> getTargets(String name) {
        List<Map<String, String>> targets = new ArrayList<>();
        ConfigurationSection instances = config.getConfigurationSection("mcsm.instances");
        ConfigurationSection groups = config.getConfigurationSection("mcsm.groups");

        if (groups != null && groups.contains(name)) {
            for (String instName : groups.getStringList(name)) {
                Map<String, String> inst = getInstance(instName);
                if (inst != null) targets.add(inst);
            }
        } else {
            Map<String, String> inst = getInstance(name);
            if (inst != null) targets.add(inst);
        }

        return targets;
    }

    private Map<String, String> getInstance(String name) {
        ConfigurationSection section = config.getConfigurationSection("mcsm.instances." + name);
        if (section == null) return null;
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("uuid", section.getString("uuid"));
        data.put("daemonId", section.getString("daemonId"));
        return data;
    }

    public boolean sendCommand(String uuid, String daemonId, String command) {
        try {
            String apiUrl = config.getString("mcsm.api_url") + "/api/protected_instance/command";
            String apiKey = config.getString("mcsm.api_key");
            String urlStr = apiUrl + "?uuid=" + uuid + "&daemonId=" + daemonId + "&command=" + java.net.URLEncoder.encode(command, "UTF-8") +
                    "&apikey=" + apiKey;
            System.out.println(urlStr);
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            System.out.println(conn.getResponseMessage());

            int code = conn.getResponseCode();
            return code == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
