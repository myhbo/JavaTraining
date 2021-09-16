package ua.kpi.tef;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Shape shape = new Rectangle(new Point(3,4),new Point(7,6));
        Shape shape = new Triangle(new Point(-4,2), new Point(0,-1),
                new Point(3,3));
        System.out.println(shape.square());
        System.out.println(shape.perimeter());
    }
}
