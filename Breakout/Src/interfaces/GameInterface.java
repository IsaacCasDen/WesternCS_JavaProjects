/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Concretes.Ball;
import Concretes.Direction;
import Concretes.GameObject;
import Enums.Edge;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author IsaacCD
 */
public interface GameInterface {
    public void pauseGame();
    public void showGameOver(boolean isVisible);
    
    public Color getBackground();
    public void setBackground(Color color);

    public int getWidth();
    public int getHeight();
    
    public Edge checkScreenEdgeIntersection(GameObject gameObject);
    public Direction checkBrickEdgeIntersection(Ball ball);
    public Direction checkPaddleEdgeIntersection(Ball ball);
    
    public void mouseTarget(Point target);
}
