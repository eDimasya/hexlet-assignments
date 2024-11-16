package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        Javalin app = Javalin.create(config ->
                config.bundledPlugins.enableDevLogging());
        app.get("/phones", context ->
                context.result(Data.getPhones().toString()));
        app.get("/domains", context ->
                context.result(Data.getDomains().toString()));
        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
