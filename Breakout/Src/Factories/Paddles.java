/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import Concretes.Paddle;
import Enums.PlayerGroup;
import Interfaces.GameInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 *
 * @author IsaacCD
 */
public class Paddles {
    public enum PaddleType {
        Standard
    }
    
    protected static HashMap<PaddleType,Dimension> getDefaultSizes() {
        if (_DefaultSizes==null) {
            HashMap<PaddleType,Dimension> values = new HashMap<>();
            values.put(PaddleType.Standard, new Dimension(50,10));
            
            _DefaultSizes=values;
        }
        
        return _DefaultSizes;
    }
    private static HashMap<PaddleType,Dimension> _DefaultSizes;
    
    public static Paddle createPaddle(PaddleType paddleType,GameInterface gameInterface, Point position, PlayerGroup player, Color color) {
        Paddle value = null;
        switch (paddleType) {
            case Standard:
                value = createStandardPaddle(gameInterface,position,player,color);
                break;
        }
        return value;
    }
    protected static Paddle createStandardPaddle(GameInterface gameInterface, Point position,PlayerGroup player, Color color) {
        Paddle value = new Paddle(gameInterface,player,new Rectangle(position,getDefaultSizes().get(PaddleType.Standard)),color);
        return value;
    }
}
