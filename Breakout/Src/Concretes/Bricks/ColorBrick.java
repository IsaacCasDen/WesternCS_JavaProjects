/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Bricks;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class ColorBrick extends Brick {
    
    public ArrayList<Color> getColors() {
        if (_Colors==null) {
            ArrayList<Color> value = new ArrayList();
            value.add(Color.red);
            value.add(Color.orange);
            value.add(Color.green);
            value.add(Color.yellow);
            value.add(Color.blue);
            value.add(new Color(255,0,255));
            setColors(value);
        }
        
        return (ArrayList<Color>)_Colors.clone();
    }
    private void setColors(ArrayList<Color> value) {
        _Colors=value;
    }
    private ArrayList<Color> _Colors;
    
    private int colorIndex;
    private int tickIndex;
    
    public ColorBrick(Dimension size) {
        super(size);
        init();
    }
  
    private void init() {
        colorIndex=0;
        tickIndex=-1;
    }

    private void refreshColor() {
        if (tickIndex%10==0) {
            colorIndex+=1;
            super.setColor(getColors().get(colorIndex%getColors().size()));
        }
    }
    
    @Override
    public void tick() {
        tickIndex+=1;
        refreshColor();
    }
    
}
