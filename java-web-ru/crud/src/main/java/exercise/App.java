package exercise;

import exercise.controller.PostsController;
import io.javalin.Javalin;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        // BEGIN
        app.get(NamedRoutes.getPostsPath(), PostsController::getPosts);
        app.get(NamedRoutes.getPostPath(), PostsController::getPost);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
