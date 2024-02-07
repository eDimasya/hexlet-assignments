package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        Function<String[], String[]> doubleElementsInRow = array -> {
            String[] doubled = new String[array.length * 2];
            int pos = 0;
            for (String element : array) {
                doubled[pos] = element;
                pos++;
                doubled[pos] = element;
                pos++;
            }
            return doubled;
        };
        List<String[]> enlargedList = new ArrayList<>();
        Arrays.asList(image)
                .forEach(row -> {
                    enlargedList.add(doubleElementsInRow.apply(row));
                    enlargedList.add(doubleElementsInRow.apply(row));
                });
        return enlargedList.toArray(String[][]::new);

    }
}
// END
