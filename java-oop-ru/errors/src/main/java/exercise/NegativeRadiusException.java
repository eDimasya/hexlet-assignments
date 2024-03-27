package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    public NegativeRadiusException(String message) {
        super(message);
    }

    public NegativeRadiusException() {
        super("Не удалось посчитать площадь");
    }
}
// END
