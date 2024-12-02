package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String getPostsPath() {
        return rootPath() + "posts";
    }

    public static String getPostPath() {
        return getPostsPath() + "/{id}";
    }
    // END
}
