/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import Concretes.Bricks.Brick;
import Concretes.Bricks.BrickGroup;
import Concretes.Bricks.ColorBrick;
import Concretes.Bricks.HardBrick;
import Concretes.Bricks.SpeedupBrick;
import Concretes.Bricks.StandardBrick;
import Concretes.Bricks.TeleportBrick;
import Interfaces.GameInterface;
import java.awt.Dimension;
import java.util.HashMap;

/**
 *
 * @author IsaacCD
 */
public class Bricks {
    public enum BrickType {
        Color,Hard,Speedup,Standard,Teleport
    }
    
    protected static HashMap<BrickType,Dimension> getDefaultSizes() {
        if (_DefaultSizes==null) {
            HashMap<BrickType,Dimension> values = new HashMap<>();
            values.put(BrickType.Color, new Dimension(30,20));
            values.put(BrickType.Hard, new Dimension(30,20));
            values.put(BrickType.Speedup, new Dimension(30,20));
            values.put(BrickType.Standard, new Dimension(30,20));
            values.put(BrickType.Teleport, new Dimension(30,20));
            
            _DefaultSizes=values;
        }
        
        return _DefaultSizes;
    }
    private static HashMap<BrickType,Dimension> _DefaultSizes;
    
    public static Brick createBrick(BrickType brickType) {
        Brick value = null;
        switch(brickType) {
            case Color:
                value = createColorBrick();
                break;
            case Hard:
                value=createHardBrick();
                break;
            case Speedup:
                value=createSpeedupBrick();
                break;
            case Standard:
                value = createStandardBrick();
                break;
            case Teleport:
                value=createTeleportBrick();
                break;
        }
        return value;
    }
    protected static Brick createStandardBrick() {
        Brick value;
        
        value = new StandardBrick(getDefaultSizes().get(BrickType.Standard));
        
        return value;
    }
    protected static Brick createColorBrick() {
        Brick value;
        
        value = new ColorBrick(getDefaultSizes().get(BrickType.Color));
        
        return value;
    }
    protected static Brick createHardBrick() {
        Brick value;
        
        value = new HardBrick(getDefaultSizes().get(BrickType.Hard));
        
        return value;
    }
    protected static Brick createSpeedupBrick() {
        Brick value;
        
        value = new SpeedupBrick(getDefaultSizes().get(BrickType.Speedup));
        
        return value;
    }
    protected static Brick createTeleportBrick() {
        Brick value;
        
        value = new TeleportBrick(getDefaultSizes().get(BrickType.Teleport));
        
        return value;
    }
}
