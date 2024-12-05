@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(content = @`
    @if(page.getName() != null)
        Welcome, ${page.getName().toString()}!
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <input type="submit" class="btn btn-primary" value="Выйти" />
            </form>
        </div>
    @else
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @endif
`)