package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        LoginPage page = new LoginPage(ctx.sessionAttribute("currentUser"), null);
        ctx.render("build.jte", model("page", page));
    }

    public static void login(Context ctx) {
        String username = ctx.formParam("name");
        String password = Security.encrypt(ctx.formParam("password"));
        if (UsersRepository.findByName(username).isPresent() &&
                UsersRepository.findByName(username).get().getPassword().equals(password)) {
            ctx.sessionAttribute("currentUser", username);
            ctx.status(302).redirect(NamedRoutes.rootPath());
        } else {
            LoginPage page = new LoginPage(username, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void root(Context ctx) {
        MainPage page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }
    // END
}
