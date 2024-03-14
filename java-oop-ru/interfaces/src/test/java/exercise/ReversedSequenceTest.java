package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReversedSequenceTest {
    String string = "abcdef";

    @Test
    void length() {
        Assertions.assertEquals(6, new ReversedSequence(string).length());
        Assertions.assertEquals(0, new ReversedSequence("").length());
    }

    @Test
    void charAt() {
        Assertions.assertEquals('f', new ReversedSequence(string).charAt(0));
        Assertions.assertEquals('a', new ReversedSequence(string).charAt(5));
    }

    @Test
    void subSequence() {
        Assertions.assertEquals("fe", new ReversedSequence(string).subSequence(0, 2));
    }
}