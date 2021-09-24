import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] array = {11, 12, 22, 23, 33, 34};
        /**
         * среднее
         * минимум и индекс
         * количество элементов равных нулю
         * количество элементов не равных нулю
         * умножить масив на число
         */
        System.out.println(Arrays.stream(array).average().getAsDouble() + System.lineSeparator());

        IntStream.range(0, array.length - 1).reduce((a, b) -> array[a] > array[b] ? b : a)
                       .ifPresent(i -> System.out.println("index: " + i + " minimum: " + array[i] +
                               System.lineSeparator()));

        System.out.println(Arrays.stream(array).filter(a -> a == 0).count() + System.lineSeparator());

        System.out.println(Arrays.stream(array).filter(a -> a > 0).count() + System.lineSeparator());

        Arrays.stream(array).map(v -> v * 10).forEach(System.out::println);


    }
}
