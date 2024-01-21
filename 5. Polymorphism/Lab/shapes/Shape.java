package shapes;

public abstract class Shape {
    private double perimeter;
    private double area;

    abstract void calculatePerimeter();
    abstract void calculateArea();

    public double getPerimeter() {
        return perimeter;
    }

    protected void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    protected void setArea(double area) {
        this.area = area;
    }

    public double getArea() {
        return area;
    }
}
