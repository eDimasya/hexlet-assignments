package exercise;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        Field[] fields =
                object.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field ->
                        field.isAnnotationPresent(NotNull.class))
                .filter(field ->
                {
                    field.setAccessible(true);
                    try {
                        return field.get(object) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Field[] fields =
                object.getClass().getDeclaredFields();
        Map<String, List<String>> result = new HashMap<>();
        Arrays.stream(fields)
                .forEach(field -> {
                    List<String> violations = new ArrayList<>();
                    boolean isViolate = false;
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(MinLength.class)) {
                        try {
                            if (field.get(object).toString().length() <
                                    field.getAnnotation(MinLength.class).minLength()) {
                                isViolate = true;
                                violations.add("length less than " + field.getAnnotation(MinLength.class).minLength());
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (field.isAnnotationPresent(NotNull.class)) {
                        try {
                            if (field.get(object) == null) {
                                isViolate = true;
                                violations.add("can not be null");
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (isViolate) {
                        result.put(field.getName(), violations);
                    }
                });
        return result;
    }
}
// END
