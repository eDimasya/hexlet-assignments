@import exercise.dto.users.UsersPage
@param UsersPage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Users list</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <h1>${page.getHeader()}</h1>
        @if(page.getUsers().isEmpty())
            <p>Empty</p>
        @else
            @for(var course : page.getUsers())
                <div>
                    <h2><a href="/users/${course.getId()}">${course.getFirstName()}</a></h2>
                    <p>${course.getLastName()}</p>
                </div>
            @endfor
        @endif
    </main>
</div>
</body>
</html>