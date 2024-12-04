package exercise.controller;

import io.javalin.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void register(Context ctx) {
        User user = new User(
                ctx.formParam("firstName"),
                ctx.formParam("lastName"),
                ctx.formParam("email"),
                Security.encrypt(ctx.formParam("password")),
                Security.generateToken()
        );
        ctx.cookie("token", user.getToken());
        UserRepository.save(user);
        ctx.redirect("/users/" + user.getId());
    }

    public static void show(Context ctx) {
        User user = UserRepository.find(
                ctx.pathParamAsClass("id", Long.class).get()).get();
        if (user.getToken().equals(ctx.cookie("token")))
            ctx.render("users/show.jte", model("page", new UserPage(user)));
        else
            ctx.redirect("/users/build");
    }
    // END
}
