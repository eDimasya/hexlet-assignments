package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String tag;
    protected Map<String, String> attributes;

    protected Tag (String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    protected String printAttributes() {
        StringBuilder output = new StringBuilder();
        attributes.forEach((type, value) ->
                output.append(" ").append(type).append("=\"").append(value).append("\"")
        );
        return output.toString();
    }
}
// END
