import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>(
                Arrays.asList(4, 5, -6, 4, 5, 3, 4, 2, 4, 5, 7)) ;

        arrayList.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                         Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);


        MyList<String> strings = new MySimpleArrayList<>();
        strings.add("F");
        strings.add("S");
        strings.add("T");
        strings.forEach(System.out::println);
        System.out.println(strings.get(1));
        System.out.println(strings.size());
        strings.update(1, "update");
        System.out.println(strings.get(1));
        strings.delete(1);
        System.out.println(strings.get(1));
        MySimpleLinked<String> linkedStrings = new MySimpleLinkedContainer<>();
        linkedStrings.addLast("abc");
        linkedStrings.addLast("123");
        System.out.println(linkedStrings.size());
        System.out.println(linkedStrings.getElementByIndex(0));


    }
}
