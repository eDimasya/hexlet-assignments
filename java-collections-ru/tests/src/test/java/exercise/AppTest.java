package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    List<Integer> emptyList = new ArrayList<>();
    List<Integer> listSeveralInts = new ArrayList<>(List.of(-1, 2, -5, 10));
    List<Integer> listOfNulls = new ArrayList<>(Arrays.asList(null, null));

    @Test
    void testTake() {
        // BEGIN
        assertThat(App.take(emptyList, 0)).isEqualTo(emptyList);
        assertThat(App.take(listSeveralInts, 0)).isEqualTo(emptyList);
        assertThat(App.take(listSeveralInts, 1)).isEqualTo(List.of(-1));
        assertThat(App.take(listSeveralInts, 4)).isEqualTo(listSeveralInts);
        assertThat(App.take(listSeveralInts, 5)).isEqualTo(listSeveralInts);
        assertThat(App.take(listSeveralInts, -1)).isEqualTo(emptyList);
        assertThat(App.take(listOfNulls, 3)).isEqualTo(listOfNulls);
        assertThat(App.take(listOfNulls, 1)).isEqualTo(new ArrayList<>(Arrays.asList((Object) null)));
        // END
    }
}
