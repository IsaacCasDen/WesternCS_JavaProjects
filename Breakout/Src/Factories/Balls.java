/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import Concretes.Ball;
import Interfaces.GameInterface;
import java.awt.Dimension;
import java.util.HashMap;

/**
 *
 * @author IsaacCD
 */
public class Balls {
    public enum BallType {
        Standard
    }
    
    protected static HashMap<BallType,Dimension> getDefaultSizes() {
        if (_DefaultSizes==null) {
            HashMap<BallType,Dimension> values = new HashMap<>();
            values.put(BallType.Standard, new Dimension(10,10));
            
            _DefaultSizes=values;
        }
        
        return _DefaultSizes;
    }
    private static HashMap<BallType,Dimension> _DefaultSizes;
    
    public static Ball createBall(BallType ballType,GameInterface gameInterface) {
        Ball value = null;
        switch (ballType) {
            case Standard:
                value = createStandardBall(gameInterface);
                break;
        }
        return value;
    }
    protected static Ball createStandardBall(GameInterface gameInterface) {
        Ball value = new Ball(getDefaultSizes().get(BallType.Standard),gameInterface);
        return value;
    }
}
