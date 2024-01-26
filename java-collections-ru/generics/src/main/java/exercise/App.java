package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static <T extends Map> List<T> findWhere(List<T> books, T dictionary) {
        List<T> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.entrySet()
                    .containsAll(dictionary.entrySet()))
                result.add(book);});
        return result;
    }
}
//END
