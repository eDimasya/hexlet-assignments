package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String getPostsPath() {
        return rootPath() + "posts";
    }

    public static String getPagePath(int pageNum) {
        return rootPath() + "posts?page=" + pageNum;
    }

    public static String getPostPath() {
        return getPostsPath() + "/{id}";
    }

    public static String getPostPath(long id) {
        return getPostsPath() + "/" + id;
    }
    // END
}
