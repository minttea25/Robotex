package Model;

import java.util.HashMap;
import java.util.Map;

public class SetupDataModel {
    Map<String, Integer> map = new HashMap<>();

    public Map<String, Integer> getMap() {
        return map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (var key : map.keySet()) {
            sb.append(key + ": " + map.get(key) + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
