@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все посты</h1>
            <table class="table table-striped">
                @for(var post : page.getPosts())
                    <tr>
                        <td>
                            <a href="${NamedRoutes.getPostPath(post.getId())}">${post.getName()}</a>
                        </td>
                    </tr>
                @endfor
            </table>
        </main>
        <footer>
            <tr>
                <td>
                    <a href="${NamedRoutes.getPagePath(page.getPage() - 1)}">Page :${page.getPage() - 1}</a>
                </td>
                <td>
                    <a href="${NamedRoutes.getPagePath(page.getPage())}">Page :${page.getPage()}</a>
                </td>
                <td>
                    <a href="${NamedRoutes.getPagePath(page.getPage() + 1)}">Page :${page.getPage() + 1}</a>
                </td>
            </tr>
        </footer>
    </div>
`
)
