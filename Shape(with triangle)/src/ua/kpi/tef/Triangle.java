package ua.kpi.tef;

public class Triangle extends Shape{
    Point first;
    Point second;
    Point third;

    public Triangle(Point first, Point second, Point third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Triangle (double firstX, double firstY, double secondX, double secondY,
                     double thirdX, double thirdY) {
        this.first = new Point(firstX, firstY);
        this.second = new Point(secondX, secondY);
        this.third = new Point(thirdX, thirdY);
    }

    @Override
    public double square() {
        double triangleSquare = Math.abs((first.getX() * (second.getY() - third.getY()) +
                                second.getX() * (third.getY() - first.getY()) +
                                third.getX() * (first.getY() - second.getY())) / 2.);

        return triangleSquare;
    }

    @Override
    public double perimeter() {
        double firstSide = Math.sqrt(Math.pow((first.getX() - second.getX()), 2) +
                Math.pow((first.getY() - second.getY()), 2));

        double secondSide = Math.sqrt(Math.pow((first.getX() - third.getX()), 2) +
                Math.pow((first.getY() - third.getY()), 2));

        double thirdSide = Math.sqrt(Math.pow((second.getX() - third.getX()), 2) +
                Math.pow((second.getY() - third.getY()), 2));

        double trianglePerimetr = firstSide + secondSide + thirdSide;

        return trianglePerimetr;
    }

    @Override
    public void move(double dx, double dy) {
        first.setX(first.getX() + dx);
        first.setY(first.getY() + dy);
        second.setY(second.getX() + dx);
        second.setY(second.getY() + dy);
        third.setY(third.getX() + dx);
        third.setY(third.getY() + dy);
    }

    public Point getFirst() {
        return first;
    }

    public Point getSecond() {
        return second;
    }

    public Point getThird() {
        return third;
    }

    public void setFirst(Point first) {
        this.first = first;
    }

    public void setSecond(Point second) {
        this.second = second;
    }

    public void setThird(Point third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "firstVertex=" + first +
                ", secondVertex=" + second +
                ", thirdVertex=" + third +
                '}';
    }
}
