/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import Concretes.Bricks.Brick;
import Concretes.Bricks.BrickGroup;
import Factories.Bricks.BrickType;
import Interfaces.GameInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author IsaacCD
 */
public class BrickGroups {
    public enum BrickPattern {
        Square
    }
    
    public static BrickGroup createBrickGroup(BrickPattern pattern, BrickType brickType, GameInterface gameInterface, Rectangle bounds, int count) {
        
        BrickGroup value=null;
        switch (pattern) {
            case Square:
                value=createSquareBrickGroup(brickType,gameInterface,bounds,count);
                break;
        }
        return value;
    }
    protected static BrickGroup createSquareBrickGroup(BrickType brickType,GameInterface gameInterface,Rectangle bounds, int count) {
        BrickGroup value = new BrickGroup(gameInterface,bounds);
        Dimension defaultSize = Bricks.getDefaultSizes().get(brickType);
        
        int c=
                ((int)bounds.getWidth())/defaultSize.width;
        int r=count/c;
        if (count==-1||r>((int)bounds.getHeight())/defaultSize.height) {
            r=((int)bounds.getHeight())/defaultSize.height;
        }
        if (r==0) {r=1;}

        for (int ri=0;ri<r;ri++) {
            for (int ci=0;ci<c;ci++) {
                int brickX = (ci*defaultSize.width);
                int brickY = (ri*defaultSize.height);

                Brick newBrick = Bricks.createBrick(brickType);
                newBrick.setOffset(new Point(brickX,brickY));
                if ((ri+ci)%2==0) {
                    newBrick.setColor(Color.blue);
                } else {
                    newBrick.setColor(Color.GRAY);
                }
                value.addBrick(newBrick);
            }
        }
        
        return value;
    }
}
