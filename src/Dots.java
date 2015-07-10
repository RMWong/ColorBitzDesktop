import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ryan on 7/8/2015.
 */
public class Dots {
    private ArrayList dotArray;

    public Dots() {
        this.dotArray = new ArrayList<>();
    }

    public ArrayList getDotArray() {
        return dotArray;
    }

    public Circle drawDots(double x, double y) {
        Random randomizeDots = new Random();

        int colorEnum = randomizeDots.nextInt(8);

        if (!dotArray.contains(colorEnum) || dotArray.isEmpty()) {
            //perhaps add Circle clickDot to the array instead of numbers
            dotArray.add(colorEnum);

            Circle clickDot = new Circle(x, y, 20);
            switch (colorEnum) {
                case 0:
                    clickDot.setFill(javafx.scene.paint.Color.ORANGE);
                    break;
                case 1:
                    clickDot.setFill(javafx.scene.paint.Color.BLUE);
                    break;
                case 2:
                    clickDot.setFill(javafx.scene.paint.Color.GREEN);
                    break;
                case 3:
                    clickDot.setFill(javafx.scene.paint.Color.YELLOW);
                    break;
                case 4:
                    clickDot.setFill(javafx.scene.paint.Color.PINK);
                    break;
                case 5:
                    clickDot.setFill(javafx.scene.paint.Color.BROWN);
                    break;
                case 6:
                    clickDot.setFill(javafx.scene.paint.Color.PURPLE);
                    break;
                case 7:
                    clickDot.setFill(javafx.scene.paint.Color.RED);
                    break;
            }
            return clickDot;
        }
        return null;
    }
}

