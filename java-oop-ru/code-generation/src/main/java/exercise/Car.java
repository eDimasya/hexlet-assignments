package exercise;

import lombok.SneakyThrows;
import lombok.ToString;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
public final class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        return new ObjectMapper().writeValueAsString(this);
    }

    @SneakyThrows
    public static Car unserialize(String json) {
        return new ObjectMapper().readValue(json, Car.class);
    }
    // END
}
