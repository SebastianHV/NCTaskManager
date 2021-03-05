package mx.edu.j2se.hernandez.tasks.tests;

import java.security.InvalidParameterException;

public class Evaluation1 {
    public static void main(String[] args) {
        try {
            Circle invalidCircle = new Circle(-1);
        }
        catch (InvalidParameterException e) {
            e.getMessage();
        }
        finally {
            Circle validCircle1 = new Circle(1);
            Circle validCircle2 = new Circle(17);
            Circle validCircle3 = new Circle(7);
            Circle circleArray[] = new Circle[]{validCircle1, validCircle2, validCircle3};
            int biggestCircleIndex = Evaluation1.biggestCircle(circleArray);
            System.out.println("Biggest Circle Index: " + biggestCircleIndex);
        }
    }

    public static int biggestCircle(Circle[] arrayCircle) {
        int bigCircleIndex = 0;
        double biggestArea = 0;
        for (int i = 0; i < arrayCircle.length; i++) {
            if (arrayCircle[i].getArea() > biggestArea) {
                biggestArea = arrayCircle[i].getArea();
                bigCircleIndex = i;
            }
        }
        return bigCircleIndex;
    }
}
