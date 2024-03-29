package exercise;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

// BEGIN
public class App {
    @SneakyThrows
    public static void save(Path path, Car car) {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
        writer.write(car.serialize());
        writer.close();
    }

    @SneakyThrows
    public static Car extract(Path path) {
        BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
        return Car.unserialize(reader.readLine());
    }
}
// END
