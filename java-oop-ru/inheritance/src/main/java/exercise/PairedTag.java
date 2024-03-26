package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;
    public PairedTag(String tag, Map<String, String> attributes, String body, List<Tag> children) {
        super(tag, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("<").append(tag)
                .append(printAttributes())
                .append(">").append(body);
        children.forEach(singleTag -> output.append(singleTag.toString()));
        output.append("</").append(tag).append(">");
        return output.toString();
    }
}
// END
