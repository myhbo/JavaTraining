import java.util.ArrayList;
import java.util.List;

public class Model {

    private int correctNumber;

    List<Integer> attempts = new ArrayList<>();

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min - 1)) + min + 1);
    }

    public int getCorrectNumber() { return correctNumber; }

    public int getMinBoundary() { return 0; }

    public int getMaxBoundary() { return 100; }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    }

    public List<Integer> getAttempts() { return attempts; }
}
