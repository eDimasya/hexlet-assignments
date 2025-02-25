package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Getter
@AllArgsConstructor
public class EditPostPage {
    private final Post post;
    private Map<String, List<ValidationError<Object>>> errors;
    public EditPostPage(Post post) {
        this.post = post;
    }
}
// END
