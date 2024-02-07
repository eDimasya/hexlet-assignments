package exercise;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    static String env = "environment=";
    static String forwarded = "X_FORWARDED_";
    static Function<String, List<String>> getListOfEnv = str ->
            Arrays.stream(str.split("\\s*\n\\s*"))
                    .filter(line ->
                            line.contains(env))
                    .collect(Collectors.toList());
    static Function<String, List<String>> getVariablesList = str ->
            Arrays.asList(str.split(","));
    static Function<List<String>, List<String>> getEnvVars = list -> {
        List<String> variables = new ArrayList<>();
        list.forEach(envVar -> {
            envVar = envVar.replace(env, "")
                    .replaceAll("\"", "");
            variables.addAll(getVariablesList.apply(envVar));
        });
        return variables;
    };
    static Function<List<String>, List<String>> getForwardedVars = vars -> vars.stream()
            .filter(variable ->
                    variable.startsWith(forwarded))
            .map(variable ->
                    variable.replace(forwarded, ""))
            .collect(Collectors.toList());

    public static String getForwardedVariables(String data) {
        return String.join(",", getForwardedVars.apply(
                getEnvVars.apply(
                        getListOfEnv.apply(data))));
    }
}
//END
