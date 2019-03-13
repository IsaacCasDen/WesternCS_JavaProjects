/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Bricks;

import Concretes.Ball;
import Concretes.GameObject;
import Enums.PlayerGroup;
import Listeners.BrickDestroyedListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public abstract class Brick extends GameObject {
    public Rectangle getBounds() {
        Rectangle value=new Rectangle(getOffset(),super._getBounds().getSize());
        if (getBrickGroup()!=null) {
            value.x+=getBrickGroup().getBounds().x;
            value.y+=getBrickGroup().getBounds().y;
        } 
        return value;
    }
    
    public Point getVelocity() {
        return super._getVelocity();
    }
    
    protected void brickDestroyed() {
        for (BrickDestroyedListener listener:getBrickDestroyedListeners()) {
            listener.brickDestroyed(this);
        }
    }
    public void addBrickDestroyedListener(BrickDestroyedListener listener) {
        getBrickDestroyedListeners().add(listener);
    }
    public void removeBrickDestroyedListener(BrickDestroyedListener listener) {
        getBrickDestroyedListeners().remove(listener);
    }
    protected ArrayList<BrickDestroyedListener> getBrickDestroyedListeners() {
        if (_BrickDestroyedListeners==null) {
            _BrickDestroyedListeners=new ArrayList();
        }
        return _BrickDestroyedListeners;
    }
    private ArrayList<BrickDestroyedListener> _BrickDestroyedListeners;
    
    public void setColor(Color color) {
        super.setColor(color);
    }
    
    public BrickGroup getBrickGroup() {
        return _BrickGroup;
    }
    protected void setBrickGroup(BrickGroup value) {
        _BrickGroup=value;
    }
    private BrickGroup _BrickGroup; 
    
    public Point getOffset() {
        return _Offset;
    }
    public void setOffset(Point value) {
        _Offset=value;
    }
    private Point _Offset;
    
    public int getScoreValue() {
        return _ScoreValue;
    }
    protected void setScoreValue(int value) {
        _ScoreValue=value;
    }
    private int _ScoreValue;
    
    protected int getHealth() {
        return _Health;
    }
    protected void setHealth(int value) {
        _Health=value;
        if (getHealth()<=0) {
            brickDestroyed();
        }
    }
    private int _Health;
    
    public PlayerGroup getLastHitBy() {
        return _LastHitBy;
    }
    protected void setLastHitBy(PlayerGroup playerGroup) {
        _LastHitBy=playerGroup;
    }
    PlayerGroup _LastHitBy;
    
    protected boolean getIsInit() {
        return _IsInit;
    }
    protected void setIsInit(boolean value) {
        _IsInit=value;
    }
    private boolean _IsInit;
    
    public Brick(Dimension size) {
        this(size, new Point(0,0));
    }
    public Brick(Dimension size, Point offset) {
        this(size, offset,true);
    }
    public Brick(Dimension size, Point offset, boolean isInit) {
        setOffset(new Point(0,0));
        _setBounds(new Rectangle(getOffset(),size));
        setHealth(1);
        setScoreValue(1);
        setLastHitBy(PlayerGroup.None);
        if (isInit) {setIsInit(true);}
    }

    public void hit(Ball ball, PlayerGroup group) {
        hit(ball,group,1);
    }
    public void hit(Ball ball, PlayerGroup group, int damage) {
        setLastHitBy(group);
        setHealth(getHealth()-damage);
        ball.setSpeedModifier(1);
    }
    
}
