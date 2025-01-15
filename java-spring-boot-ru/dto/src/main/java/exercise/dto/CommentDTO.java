package exercise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Setter
@Getter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String body;
}
// END
