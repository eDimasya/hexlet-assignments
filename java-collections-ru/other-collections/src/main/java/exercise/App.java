package exercise;

import java.util.*;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        TreeMap<String, String> diffOrdered = new TreeMap<>();
        Set<String> commonKeys = new TreeSet<>(map1.keySet());
        commonKeys.retainAll(map2.keySet());
        commonKeys.forEach(key -> {
                    if (map1.get(key).equals(map2.get(key)))
                        diffOrdered.put(key, "unchanged");
                    else
                        diffOrdered.put(key, "changed");
                }
        );
        Set<String> removedKeys = new TreeSet<>(map1.keySet());
        removedKeys.removeAll(map2.keySet());
        removedKeys.forEach(key -> diffOrdered.put(key, "deleted"));
        Set<String> addedKeys = new TreeSet<>(map2.keySet());
        addedKeys.removeAll(map1.keySet());
        addedKeys.forEach(key -> diffOrdered.put(key, "added"));
        return new LinkedHashMap<>(diffOrdered);
    }
}
//END
