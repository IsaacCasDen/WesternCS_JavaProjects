/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public class Main {

    private static HashMap<String,Color> _Colors;
    private static HashMap<String,Color> get_Colors() {
        if (_Colors==null) {
            _Colors = new HashMap<String,Color>();
            initColors();
        }
        
        return _Colors;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FramePong frame = new FramePong();
        frame.setVisible(true);
    }
    
    private static void initColors() {
        HashMap<String,Color> colorMap=get_Colors();
        colorMap.clear();
        
        colorMap.put("Midnight Blue", new Color(25, 25, 112));
        colorMap.put("Navy", new Color(0, 0, 128));
        colorMap.put("Cornflower Blue", new Color(100, 149, 237));
        colorMap.put("Dark Slate Blue", new Color(72, 61, 139));
        colorMap.put("Slate Blue", new Color(106, 90, 205));
        colorMap.put("Medium Slate Blue", new Color(123, 104, 238));
        colorMap.put("Light Slate Blue", new Color(132, 112, 255));
        colorMap.put("Medium Blue", new Color(0, 0, 205));
        colorMap.put("Royal Blue", new Color(65, 105, 225));
        colorMap.put("Blue", new Color(0, 0, 255));
        colorMap.put("Dodger Blue", new Color(30, 144, 255));
        colorMap.put("Deep Sky Blue", new Color(0, 191, 255));
        colorMap.put("Sky Blue", new Color(135, 206, 250));
        colorMap.put("Light Sky Blue", new Color(135, 206, 250));
        colorMap.put("Steel Blue", new Color(70, 130, 180));
        colorMap.put("Light Steel Blue", new Color(176, 196, 222));
        colorMap.put("Light Blue", new Color(173, 216, 230));
        colorMap.put("Powder Blue", new Color(176, 224, 230));
        colorMap.put("Pale Turquoise", new Color(175, 238, 238));
        colorMap.put("Dark Turquoise", new Color(0, 206, 209));
        colorMap.put("Medium Turquoise", new Color(72, 209, 204));
        colorMap.put("Turquoise", new Color(64, 224, 208));
        colorMap.put("Cyan", new Color(0, 255, 255));
        colorMap.put("Light Cyan", new Color(224, 255, 255));
        colorMap.put("Cadet Blue", new Color(95, 158, 160));
        colorMap.put("Medium Aquamarine", new Color(102, 205, 170));
        colorMap.put("Aquamarine", new Color(127, 255, 212));
        colorMap.put("Dark Green", new Color(0, 100, 0));
        colorMap.put("Dark Olive Green", new Color(85, 107, 47));
        colorMap.put("Dark Sea Green", new Color(143, 188, 143));
        colorMap.put("Sea Green", new Color(46, 139, 87));
        colorMap.put("Medium Sea Green", new Color(60, 179, 113));
        colorMap.put("Light Sea Green", new Color(32, 178, 170));
        colorMap.put("Pale Green", new Color(152, 251, 152));
        colorMap.put("Spring Green", new Color(0, 255, 127));
        colorMap.put("Lawn Green", new Color(124, 252, 0));
        colorMap.put("Chartreuse", new Color(127, 255, 0));
        colorMap.put("Medium Spring Green", new Color(0, 250, 154));
        colorMap.put("Green Yellow", new Color(173, 255, 47));
        colorMap.put("Lime Green", new Color(50, 205, 50));
        colorMap.put("Yellow Green", new Color(154, 205, 50));
        colorMap.put("Forest Green", new Color(34, 139, 34));
        colorMap.put("Olive Drab", new Color(107, 142, 35));
        colorMap.put("Dark Khaki", new Color(189, 183, 107));
        colorMap.put("Khaki", new Color(240, 230, 140));
        colorMap.put("Pale Goldenrod", new Color(238, 232, 170));
        colorMap.put("Light Goldenrod Yellow", new Color(250, 250, 210));
        colorMap.put("Light Yellow", new Color(255, 255, 224));
        colorMap.put("Yellow", new Color(255, 255, 0));
        colorMap.put("Gold", new Color(255, 215, 0));
        colorMap.put("Light Goldenrod", new Color(238, 221, 130));
        colorMap.put("Goldenrod", new Color(218, 165, 32));
        colorMap.put("Dark Goldenrod", new Color(184, 134, 11));
        colorMap.put("Rosy Brown", new Color(188, 143, 143));
        colorMap.put("Indian Red", new Color(205, 92, 92));
        colorMap.put("Saddle Brown", new Color(139, 69, 19));
        colorMap.put("Sienna", new Color(160, 82, 45));
        colorMap.put("Peru", new Color(205, 133, 63));
        colorMap.put("Burlywood", new Color(222, 184, 135));
        colorMap.put("Beige", new Color(245, 245, 220));
        colorMap.put("Wheat", new Color(245, 222, 179));
        colorMap.put("Sandy Brown", new Color(244, 164, 96));
        colorMap.put("Tan", new Color(210, 180, 140));
        colorMap.put("Chocolate", new Color(210, 105, 30));
        colorMap.put("Firebrick", new Color(178, 34, 34));
        colorMap.put("Brown", new Color(165, 42, 42));
        colorMap.put("Dark Salmon", new Color(233, 150, 122));
        colorMap.put("Salmon", new Color(250, 128, 114));
        colorMap.put("Light Salmon", new Color(255, 160, 122));
        colorMap.put("Orange", new Color(255, 165, 0));
        colorMap.put("Dark Orange", new Color(255, 140, 0));
        colorMap.put("Coral", new Color(255, 127, 80));
        colorMap.put("Light Coral", new Color(240, 128, 128));
        colorMap.put("Tomato", new Color(255, 99, 71));
        colorMap.put("Orange Red", new Color(255, 69, 0));
        colorMap.put("Red", new Color(255, 0, 0));
        colorMap.put("Hot Pink", new Color(255, 105, 180));
        colorMap.put("Deep Pink", new Color(255, 20, 147));
        colorMap.put("Pink", new Color(255, 192, 203));
        colorMap.put("Light Pink", new Color(255, 182, 193));
        colorMap.put("Pale Violet Red", new Color(219, 112, 147));
        colorMap.put("Maroon", new Color(176, 48, 96));
        colorMap.put("Medium Violet Red", new Color(199, 21, 133));
        colorMap.put("Violet Red", new Color(208, 32, 144));
        colorMap.put("Violet", new Color(238, 130, 238));
        colorMap.put("Plum", new Color(221, 160, 221));
        colorMap.put("Orchid", new Color(218, 112, 214));
        colorMap.put("Medium Orchid", new Color(186, 85, 211));
        colorMap.put("Dark Orchid", new Color(153, 50, 204));
        colorMap.put("Dark Violet", new Color(148, 0, 211));
        colorMap.put("Blue Violet", new Color(138, 43, 226));
        colorMap.put("Purple", new Color(160, 32, 240));
        colorMap.put("Medium Purple", new Color(147, 112, 219));
        colorMap.put("Thistle", new Color(216, 191, 216));

    }
    public static Color getColor() {
        Random rand = new Random();
        String key = null;
        Color value = null;
             
        int index = rand.nextInt(get_Colors().size());
        key = (String)get_Colors().keySet().toArray()[index];
        
        value = get_Colors().get(key);
        
        return value;
    }
}
