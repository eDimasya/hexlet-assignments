@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
content = @`
    <h1>Users list:</h1>
    @for(var user : page.getUsers())
        <h2><a href="/users/${user.getId()}">${user.getFirstName()}</a></h2>
        <p>${user.getLastName()}</p>
    @endfor
`
)