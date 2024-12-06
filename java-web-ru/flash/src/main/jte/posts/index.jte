@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage page

@template.layout.page(content = @`
    <table class="table table-striped">
        @for(Post post : page.getPosts())
            <tr>
                <td>
                    ${post.getName()}
                </td>
                <td>
                    ${post.getBody()}
                </td>
            </tr>
        @endfor
    </table>
`, page = page)