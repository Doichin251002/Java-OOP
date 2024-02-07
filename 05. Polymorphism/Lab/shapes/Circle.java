package shapes;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public final double getRadius() {
        return radius;
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(Math.PI * 2 * radius);
    }

    @Override
    protected void calculateArea() {
        setPerimeter(Math.PI * Math.pow(radius, 2));
    }
}
