import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.*;

/**
 * Created by Ryan on 7/9/2015.
 */
public class ColorText {

    private ArrayList<Color> textColorArray;
    private ColorMatch cm;

    public ColorText() {
        cm = new ColorMatch();
    }

    public Color randTextColor() {
        Random randomizeDots = new Random();

        int colorEnum = randomizeDots.nextInt(8);

        switch (colorEnum) {
            case 0: return Color.ORANGE;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            case 4: return Color.PINK;
            case 5: return Color.BROWN;
            case 6: return Color.PURPLE;
            case 7: return Color.RED;
        }
        return null;
    }

    public String randDescColor() {
        Random randomizeDots = new Random();

        int colorEnum = randomizeDots.nextInt(8);

        switch (colorEnum) {
            case 0: return "ORANGE";
            case 1: return "BLUE";
            case 2: return "GREEN";
            case 3: return "YELLOW";
            case 4: return "PINK";
            case 5: return "BROWN";
            case 6: return "PURPLE";
            case 7: return "RED";
        }
        return null;
    }

 /*   public boolean descriptionMatch(String color) {
        for (int i = 0; i <= 7; i++)
            if(cm.getDescription(i).equals(color))
                return true;
        return false;
    }
*/
    public boolean colorMatch(int colorEnum) {
        for (int i = 0; i <= 7; i++)
            if (cm.getColor(i) == colorEnum)
                return true;
        return false;
    }
}

