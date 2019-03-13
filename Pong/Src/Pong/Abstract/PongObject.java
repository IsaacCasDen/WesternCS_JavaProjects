/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong.Abstract;

import Pong.Interfaces.PongField;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public abstract class PongObject {
    
    private Color color;
    private Rectangle bounds;
    private PongField field;
    
    public PongField getField() {
        return field;
    }
    protected void setField(PongField value) {
        this.field=value;
    }
    
    public Color getColor() {
        return color;
    }
    protected void setColor(Color value) {
        color=value;
    }
    
    public Point getCenter() {
        int centerH = getBounds().x+(getBounds().width/2);
        int centerV = getBounds().y+(getBounds().height/2);
        
        Point value = new Point (centerH, centerV);
        return value;
    }
    
    public Rectangle getBounds() {
        if (bounds==null) {
            bounds = new Rectangle();
        }

        return bounds;
    }
    protected void setBounds(int x, int y, int width, int height) {
        setBounds(new Point(x,y),new Point(width,height));
    }
    protected void setBounds(Rectangle value) {
        if (value!=null) {
            setBounds(new Point(value.x,value.y),new Point(value.width,value.height));
        }
    }
    protected void setBounds(Point position, Point size) {
        if (size!=null) {setSize(size);}
        if (position!=null) {setPosition(position);}
    }

    public Point getPosition() {
        return new Point(getBounds().x,getBounds().y);
    }
    public void setPosition(Point value) {
        if (value!=null) {
            setPosition(value.x,value.y);
        }
    }
    public void setPosition(int x, int y) {
        validatePosition(x, y);
    }
    protected abstract void validatePosition(int x, int y);
    protected void updatePosition(int x, int y) {
        getBounds().x=x;
        getBounds().y=y;
    }

    public int getWidth() {
        return getBounds().width;
    }
    public int getHeight() {
        return getBounds().height;
    }
    public Point getSize() {
        return new Point(getBounds().width,getBounds().height);
    }
    protected void setSize(Point value) {
        if (value!=null) {
            setSize(value.x,value.y);
        }
    }
    protected void setSize(int x, int y) {
        validateSize(x,y);
    }
    protected abstract void validateSize(int width, int height);
    protected void updateSize(int width, int height) {
        getBounds().width=width;
        getBounds().height=height;
    }
}
