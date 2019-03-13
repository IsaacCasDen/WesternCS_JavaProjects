/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Enums.HorizontalDirection;
import Enums.Edge;
import Enums.PlayerGroup;
import Enums.VerticalDirection;
import Interfaces.GameInterface;
import Listeners.BallDestroyedListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class Ball extends GameObject {
    
    protected void ballDestroyed() {
        for (BallDestroyedListener listener:getBallDestroyedListeners()) {
            listener.ballDestroyed(this);
        }
    }
    public void addBallDestroyedListener(BallDestroyedListener listener) {
        getBallDestroyedListeners().add(listener);
    }
    public void removeBallDestroyedListener(BallDestroyedListener listener) {
        getBallDestroyedListeners().remove(listener);
    }
    protected ArrayList<BallDestroyedListener> getBallDestroyedListeners() {
        if (_BallDestroyedListeners==null) {
            _BallDestroyedListeners=new ArrayList();
        }
        return _BallDestroyedListeners;
    }
    private ArrayList<BallDestroyedListener> _BallDestroyedListeners;
    
    public int getSpeed() {
        return _Speed*getSpeedModifier();
    }
    protected void setSpeed(int value) {
        _Speed=value;
    }
    private int _Speed;
    
    public int getSpeedModifier() {
        return _SpeedModifier;
    }
    public void setSpeedModifier(int value) {
        if (value>2) {
            _SpeedModifier=2;
        } else if (value<1) {
            _SpeedModifier=1;
        } else {
            _SpeedModifier=value;
        }
    }
    private int _SpeedModifier;
    
    public Rectangle getBounds() {
        return super._getBounds();
    }
    
    public Point getLastPosition() {
        return _LastPosition;
    }
    protected void setLastPosition(Point value) {
        _LastPosition = value;
    }
    private Point _LastPosition;
    
    public Point getVelocity() {
        return super._getVelocity();
    }
    
    public PlayerGroup getLastHit() {
        return _LastHit;
    }
    public void setLastHit(PlayerGroup value) {
        _LastHit=value;
    }
    private PlayerGroup _LastHit;
    
    public Ball(Dimension size,GameInterface gameInterface) {
        this(size,gameInterface,3);
    }
    public Ball(Dimension size, GameInterface gameInterface, int speed) {
        super._setBounds(new Rectangle(0,0,size.width,size.height));
        setGameInterface(gameInterface);
        setSpeedModifier(1);
        setSpeed(speed);
        setLastHit(PlayerGroup.None);
        directionChanged(new Direction(HorizontalDirection.Right,VerticalDirection.Down));
    }
    
    public void setPosition(Point value) {
        Rectangle bounds = getBounds();
        bounds.x=value.x;
        bounds.y=value.y;
    }

    public void directionChanged(HorizontalDirection horizontalDirection, VerticalDirection verticalDirection) {
        directionChanged(new Direction(horizontalDirection,verticalDirection));
    }
    public void directionChanged(Direction direction) {
        
        Point newVelocity = new Point();
        
        switch (direction.verticalDirection) {
            case None:
                setDirection(new Direction(direction.horizontalDirection,VerticalDirection.Up));
                break;
            default:
                setDirection(direction);
                break;
        }
        switch(getDirection().horizontalDirection) {
            case None:
                newVelocity.x=0;
                break;
            case Left:
                newVelocity.x=-getSpeed();
                break;
            case Right:
                newVelocity.x=getSpeed();
                break;
        }
        switch(getDirection().verticalDirection) {
            case None:
                newVelocity.y=0;
                break;
            case Up:
                newVelocity.y=-getSpeed();
                break;
            case Down:
                newVelocity.y=getSpeed();
                break;
        }
        
        _setVelocity(newVelocity);
    }
    
    public void tick() {
        Rectangle newPos = getBounds();
        setLastPosition(newPos.getLocation());
        
        newPos.x+=getVelocity().x;
        newPos.y+=getVelocity().y;
        
        super._setBounds(newPos);
        
        if (getGameField()!=null) {
            checkEdgeIntersect();
            checkBrickIntersect();
            checkPaddleIntersect();
        }
    }
    
    private void checkEdgeIntersect() {
        Edge edge = getGameField().checkScreenEdgeIntersection(this);
        if (edge!=Edge.None) {
            switch (edge) {
                case LeftMid:
                    directionChanged(HorizontalDirection.Right,getDirection().verticalDirection);
                    break;
                case TopMid:
                    directionChanged(getDirection().horizontalDirection,VerticalDirection.Down);
                    break;
                case RightMid:
                    directionChanged(HorizontalDirection.Left,getDirection().verticalDirection);
                    break;
                case BottomMid:
                    ballDestroyed();
                    break;
            }
        }
    }
    private void checkBrickIntersect() {
        Direction direction = getGameField().checkBrickEdgeIntersection(this);
        directionChanged(direction);
    }
    private void checkPaddleIntersect() {
        Direction direction = getGameField().checkPaddleEdgeIntersection(this);
        directionChanged(direction);
    }
}
