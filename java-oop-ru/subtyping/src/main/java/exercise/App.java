package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = new HashMap<>(storage.toMap());
        map.forEach((key, value) -> {
            storage.unset(key);
            storage.set(value, key);
        });
    }
}
// END
