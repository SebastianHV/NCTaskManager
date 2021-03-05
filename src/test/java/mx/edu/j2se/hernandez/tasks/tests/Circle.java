package mx.edu.j2se.hernandez.tasks.tests;

public class Circle {

    private int radius;

    public Circle() {
        this.radius = 1;
    }

    public Circle(int radius) throws IllegalArgumentException {
        if (radius < 0) {
            throw new IllegalArgumentException("The radius must be a positive number");
        }
        else {
            this.radius = radius;
        }

    }

    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius < 0) {
            throw new IllegalArgumentException("The radius must be a positive number");
        }
        else {
            this.radius = radius;
        }
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * getRadius() * getRadius();
    }
}
