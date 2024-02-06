package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    protected static List<String> freeDomains = Arrays.asList("gmail.com", "yandex.ru", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .map(email ->
                        email.split("@")[1])
                .filter(email ->
                    freeDomains.contains(email))
                .count();
    }
}
// END
