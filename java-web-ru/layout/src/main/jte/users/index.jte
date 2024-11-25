@import exercise.dto.users.UsersPage
@import gg.jte.Content

@param UsersPage page
@param Content footer = null

@template.layout.page(
content = @`
    <h1>Users list:</h1>
    @if(page.getUsers().isEmpty())
        <p>Empty</p>
    @else
        @for(var user : page.getUsers())
            <h2><a href="/users/${user.getId()}">${user.getFirstName()}</a></h2>
            <p>${user.getLastName()}</p>
        @endfor
    @endif
`,
footer = false)