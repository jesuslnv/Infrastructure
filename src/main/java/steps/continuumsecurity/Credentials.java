package steps.continuumsecurity;

import java.util.HashMap;
import java.util.Map;

public class Credentials {
    Map<String, String> creds = new HashMap<String, String>();

    public Credentials() {
    }

    public Credentials(String... strings) {
        creds = stringsToMap(strings);
    }

    public void add(String... strings) {
        creds.putAll(stringsToMap(strings));
    }

    public Map<String, String> stringsToMap(String... strings) {
        Map<String, String> map = new HashMap<String, String>();
        if (strings.length % 2 > 0)
            throw new RuntimeException("Credentials must be provided in pairs, e.g. 'username','bob'");

        for (int it = 0; it <= strings.length / 2; it = it + 2) {
            map.put(strings[it], strings[it + 1]);
        }
        return map;
    }

    public boolean containsKey(String key) {
        for (String credName : creds.keySet()) {
            if (credName.equalsIgnoreCase(key)) return true;
        }
        return false;
    }

    public String get(String name) {
        return creds.get(name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : creds.keySet()) {
            sb.append(" ").append(key).append("=").append(creds.get(key)).append("\n");
        }
        return sb.toString();
    }
}