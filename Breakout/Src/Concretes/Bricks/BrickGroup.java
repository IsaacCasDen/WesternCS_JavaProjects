/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Bricks;

import Concretes.GameObject;
import Interfaces.GameInterface;
import Listeners.BrickDestroyedListener;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class BrickGroup extends GameObject {

    @Override
    public Rectangle getBounds() {
        return super._getBounds();
    }
    
    @Override
    public Point getVelocity() {
        return super._getVelocity();
    }
    
    public void addBrickDestroyedListener(BrickDestroyedListener listener) {
        for(Brick brick:getBricks()) {
            brick.addBrickDestroyedListener(listener);
        }
    }
    public void removeBrickDestroyedListener(BrickDestroyedListener listener) {
        for(Brick brick:getBricks()) {
            brick.removeBrickDestroyedListener(listener);
        }
    }
    
    public ArrayList<Brick> getBricks() {
        if (_Bricks == null) {
            _Bricks = new ArrayList<>();
        }
        return _Bricks;
    }
    private void setBricks(ArrayList<Brick> values) {
        _Bricks=values;
    }
    private ArrayList<Brick> _Bricks;
    
    public BrickGroup(GameInterface gameInterface,Rectangle bounds) {
        setGameInterface(gameInterface);
        _setBounds(bounds);
    }
    
    public void addBrick(Brick brick) {
        if (!getBricks().contains(brick)) {
            brick.setBrickGroup(this);
            getBricks().add(brick);
        }
    }
    public boolean removeBrick(Brick brick) {
        if (getBricks().contains(brick)) {
            getBricks().remove(brick);
            return true;
        }
        return false;
    }
    
    public void tick() {
        
    }
}
