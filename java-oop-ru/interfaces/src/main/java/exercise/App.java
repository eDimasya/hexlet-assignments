package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        return homes.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .collect(Collectors.toList())
                .subList(0,
                        (count <= homes.size()) ? count : 0);
    }
}
// END
