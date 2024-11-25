@import gg.jte.Content

@param Content content
@param boolean footer = true

<!doctype html>
<html lang="en">
    @template.layout.header()
    <body>
        <div class="mx-auto p-4 py-md-5">
            <main>
                ${content}
            </main>
        </div>
        @if(footer)
            @template.layout.footer()
        @endif
    </body>
</html>