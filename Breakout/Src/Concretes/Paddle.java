/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Enums.Edge;
import Enums.HorizontalDirection;
import Enums.PlayerGroup;
import Enums.VerticalDirection;
import Interfaces.GameInterface;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author IsaacCD
 */
public class Paddle extends GameObject {
    public Rectangle getBounds() {
        return super._getBounds();
    }
    
    public Point getVelocity() {
        return super._getVelocity();
    }
    
    public Point getTarget() {
        return _Target;
    }
    public void setTarget(Point value) {
        _Target=value;
        updateDirection();
    }
    private Point _Target;
    
    public PlayerGroup getPlayer() {
        return _Player;
    }
    protected void setPlayer(PlayerGroup value) {
        _Player=value;
    }
    private PlayerGroup _Player;
    
    public Paddle(GameInterface gameInterface, PlayerGroup player, Rectangle bounds, Color color) {
        super.setGameInterface(gameInterface);
        setPlayer(player);
        super._setBounds(bounds);
        setColor(color);
        _setVelocity(new Point(5,0));
    }
    
    private void updateDirection() {
        Direction value = new Direction();
        
        if (getTarget()!=null) {
            if (getTarget().x<getCenter().x) {
                value.horizontalDirection=HorizontalDirection.Left;
            } else if (getTarget().x>getCenter().x) {
                value.horizontalDirection=HorizontalDirection.Right;
            }
            if (getTarget().y<getCenter().y) {
                value.verticalDirection=VerticalDirection.Up;
            } else if (getTarget().y>getCenter().y) {
                value.verticalDirection=VerticalDirection.Down;
            }
        }
        setDirection(value);
        Point newVel = _getVelocity();
        
        switch(getDirection().horizontalDirection) {
            case Left:
                newVel.x=-Math.abs(newVel.x);
                break;
            case Right:
                newVel.x=Math.abs(newVel.x);
                break;
        }
        switch (getDirection().verticalDirection) {
            case Up:
                newVel.y=-Math.abs(newVel.y);
                break;
            case Down:
                newVel.y=Math.abs(newVel.y);
                break;
        }
        
    }
    
    public void tick() {
        updateDirection();
        if (getTarget()!=null) {
            Rectangle newPos = getBounds();
            Point target = getTarget();
            if (Math.abs(getCenter().x-getTarget().x)>5) {
                newPos.x+=_getVelocity().x;
                super._setBounds(newPos);
                
                if (getGameField()!=null) {
                    checkEdgeIntersect();
                }
            }
        }
    }
    
    private void checkEdgeIntersect() {
        Rectangle newPos = getBounds();
        Edge edge = getGameField().checkScreenEdgeIntersection(this);
        if (edge!=Edge.None) {
            switch (edge) {
                case LeftMid:
                    newPos.x=0;
                    break;
                case TopMid:

                    break;
                case RightMid:
                    newPos.x=getGameField().getWidth()-newPos.width;
                    break;
                case BottomMid:
                    break;
            }
        }
        super._setBounds(newPos);
    }
}