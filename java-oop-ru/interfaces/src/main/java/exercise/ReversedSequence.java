package exercise;

import java.util.stream.Stream;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String str;

    private String reverseWord(String str) {
        StringBuilder result = new StringBuilder();
        for (int index = str.length() - 1; index >= 0; index--) {
            result.append(str.charAt(index));
        }
        return result.toString();
    }
    public ReversedSequence(String str) {
        this.str = reverseWord(str);
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.subSequence(start, end);
    }

    @Override
    public String toString() {
        return str;
    }
}
// END
