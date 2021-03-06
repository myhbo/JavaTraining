import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Immutable im = Immutable.createNewInstance(100,"test", new Date());
        System.out.println(im);
        tryModification(im.getImmutableField1(),im.getImmutableField2(),im.getMutableField());
        System.out.println(im);
    }

    private static void tryModification(Integer immutableField1, String immutableField2, Date mutableField) {
        immutableField1 = 10000;
        immutableField2 = "test changed";
        mutableField.setTime(10);
    }

}
