package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context context) {
        int pageNum = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        PostsPage page = new PostsPage(pageNum, PostRepository.findAll(pageNum, 5));
        context.render("posts/index.jte", model("page", page));
    }

    public static void show(Context context) {
        long id = context.pathParamAsClass("id", Long.class).get();
        if (PostRepository.find(id).isPresent())
            context.render("posts/show.jte", model("page", new PostPage(PostRepository.find(id).get())));
        else
            context.status(404).result("Page not found");
    }
    // END
}
