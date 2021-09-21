import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>(
                Arrays.asList(4, 5, -6, 4, 5, 3, 4, 2, 4, 5, 7)) ;

        arrayList.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                         Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);
    }
}
