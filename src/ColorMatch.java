import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryan on 7/9/2015.
 */
public class ColorMatch {

    private final int ORANGE = 0;
    private final int BLUE = 1;
    private final int GREEN = 2;
    private final int YELLOW = 3;
    private final int PINK = 4;
    private final int BROWN = 5;
    private final int PURPLE = 6;
    private final int RED = 7;

    private List<Integer> colorList;
    private List<String> colorDescription;
    private Map<String,Color> colorMap;

    public ColorMatch() {
        colorList = new ArrayList<>();
        colorDescription = new ArrayList<>();
        colorMap = new HashMap<>();

        colorList.add(ORANGE);
        colorList.add(BLUE);
        colorList.add(GREEN);
        colorList.add(YELLOW);
        colorList.add(PINK);
        colorList.add(BROWN);
        colorList.add(PURPLE);
        colorList.add(RED);

        colorDescription.add("ORANGE");
        colorDescription.add("BLUE");
        colorDescription.add("GREEN");
        colorDescription.add("YELLOW");
        colorDescription.add("PINK");
        colorDescription.add("BROWN");
        colorDescription.add("PURPLE");
        colorDescription.add("RED");

        colorMap.put("ORANGE", Color.ORANGE);
        colorMap.put("BLUE", Color.BLUE);
        colorMap.put("GREEN", Color.GREEN);
        colorMap.put("YELLOW", Color.YELLOW);
        colorMap.put("PINK", Color.PINK);
        colorMap.put("BROWN", Color.BROWN);
        colorMap.put("PURPLE", Color.PURPLE);
        colorMap.put("RED", Color.RED);
    }

    public int getColor(int x) {
        return colorList.get(x);
    }

 /*   public String getDescription(int x) {
        return colorDescription.get(x);
    }*/

    public Color getDescription(String str) {
        return colorMap.get(str);
    }
}
