package classBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setLength(double length) {
        if (!isPositive(length)) {
            throw new NumberFormatException("Length cannot be zero or negative.");
        }
        this.length = length;
    }

    private void setWidth(double width) {
        if (!isPositive(width)) {
            throw new NumberFormatException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    private void setHeight(double height) {
        if (!isPositive(height)) {
            throw new NumberFormatException("Height cannot be zero or negative.");
        }
        this.height = height;
    }

    private boolean isPositive (double side) {
        return side > 0;
    }

    public double calculateSurfaceArea() {
        return 2 * (length * width + length * height + width * height);
    }

    public double calculateLateralSurfaceArea() {
        return 2 * (length * height + width * height);
    }

    public double calculateVolume() {
        return length * width * height;
    }
}
