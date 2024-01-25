package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap<>();
        if (sentence.isEmpty())
            return result;
        String[] words = sentence.split(" ");
        for (String word : words) {
            if (result.containsKey(word)) {
                result.put(word, result.get(word) + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }

    public static String toString(Map<String, Integer> resultMap) {
        StringBuilder result = new StringBuilder();
        resultMap.forEach((key, value) ->
            result.append("\n").append("  ").append(key).append(": ").append(value));
        if (!result.toString().isEmpty())
            result.append("\n");
        return  "{"
                + result
                + "}";
    }
}
//END
