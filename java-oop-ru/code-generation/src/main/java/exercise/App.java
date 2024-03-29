package exercise;

import java.io.*;
import java.nio.file.Path;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
        writer.write(car.serialize());
        writer.close();
    }

    public static Car extract(Path path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
        return Car.unserialize(reader.readLine());
    }
}
// END
