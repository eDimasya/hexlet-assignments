package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final TagInterface tagInterface;
    private final String value;

    public LabelTag(String value, TagInterface tagInterface) {
        this.tagInterface = tagInterface;
        this.value = value;
    }

    @Override
    public String render() {
        return "<label>" + value + tagInterface.render() + "</label>";
    }
}
// END
