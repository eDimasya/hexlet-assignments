package exercise;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", context -> {
            String id = context.pathParam("id");
            Map<String, String> result = new HashMap<>();
            COMPANIES.forEach(company -> {
                if (Objects.equals(company.get("id"), id)) {
                    result.putAll(company);
                }
            });
            if (!result.isEmpty())
                context.json(result);
            else
                throw new NotFoundResponse("Company not found");
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}