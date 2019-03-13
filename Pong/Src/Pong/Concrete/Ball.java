/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong.Concrete;

import Pong.Abstract.PongObject;
import Pong.Enums.TeamScored;
import Pong.Enums.Edge;
import Pong.Interfaces.PongField;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public class Ball extends PongObject {

    private Point velocity;

    public Point getVelocity() {
        if (velocity!=null) {
            return (Point)velocity.clone();
        }

        return null;
    }
    public void setVelocity(int x, int y) {
        setVelocity(new Point(x,y));
    }
    public void setVelocity(Point value) {
        if (value!=null) {velocity=value;}
    }

    public Ball(PongField field, Point position) {
        
        setColor(Pong.Main.getColor());
        setField(field);
        velocity=initVelocity();

        Point size=new Point(20,20);
        setBounds(position,size);
    }
    private Point initVelocity() {
        Random r = new Random();
        Point value = new Point(r.nextInt(5)+3,r.nextInt(5)+3);
        return value;
    }

    protected void validateSize(int width, int height) {
        updateSize(width,height);
    }
    protected void validatePosition(int x, int y) {
        updatePosition(x,y);
        for (Edge contact:getField().validateBorderContact(this)) {
                switch(contact) {
                    case Top:
                        velocity.y=-velocity.y;
                        getBounds().y=Math.abs(getBounds().y);
                        break;
                    case Bottom:
                        int offset = Math.abs(getBounds().y-getField().getSize().y);
                        velocity.y= -velocity.y;
                        getBounds().y= getField().getSize().y-offset;
                        break;
                    case Left:
                        getField().ballDestroyed(this);
                        getField().teamScores(TeamScored.Right);
                        break;
                    case Right:
                        getField().ballDestroyed(this);
                        getField().teamScores(TeamScored.Left);
                        break;
                }
            }

            for (Edge contact:getField().validatePaddleContact(this)) {
                switch(contact) {
                    case Top:
                        if (velocity.y>3) {
                            velocity.y--;
                            velocity.y=-velocity.y;
                        } else if (velocity.y<-3) {
                            velocity.y--;
                        } else {
                            velocity.y=-3;
                        }

                        if (velocity.x>4) 
                        {
                            velocity.x--;
                        } else if (velocity.x<-4) {
                            velocity.x++;
                        }
                        break;
                    case Bottom:
                        if (velocity.y<-3) {
                            velocity.y++;
                            velocity.y=-velocity.y;
                        } else if (velocity.y>3) {
                            velocity.y++;
                        } else {
                            velocity.y=3;
                        }

                        if (velocity.x>4) 
                        {
                            velocity.x--;
                        } else if (velocity.x<-4) {
                            velocity.x++;
                        }
                        break;
                    case Left:
                        velocity.x=-velocity.x;
                        break;
                    case Right:
                        if (velocity.x>0) 
                        {
                            velocity.x++;
                        } else if (velocity.x<0) {
                            velocity.x--;
                        }
                        if (velocity.y<4) {
                            velocity.y++;
                        } else if (velocity.y>4) {
                            velocity.y--;
                        }
                }
            }
    }
}
