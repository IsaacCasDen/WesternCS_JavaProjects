/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong.Concrete;
import Pong.Abstract.PongObject;
import Pong.Enums.Edge;
import Pong.Interfaces.PongField;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public class Paddle extends PongObject {
        
        public Paddle(PongField field, Point position) {
            setColor(Color.GRAY);
            setField(field);
            Point size = new Point(10,100);
            setBounds(position, size);
        }
        
        protected void validateSize(int width, int height) {
            updateSize(width,height);
        }
        protected void validatePosition(int x, int y) {
            if (getPosition().x==0) {
                updatePosition(x,y);
            } else {                    
                int yDelta = 0;

                if (Math.abs(y-getPosition().y)>10) {
                    yDelta = 10;
                } else {
                    yDelta = Math.abs(getPosition().y-y);
                }
                if (y<getPosition().y) {yDelta = -yDelta;}

                updatePosition(x, getPosition().y+=yDelta);
            }


            for (Edge contact:getField().validateBorderContact(this)) {
                switch(contact) {
                    case Top:
                        setPosition(getPosition().x,0);
                        break;
                    case Bottom:
                        setPosition(getPosition().x,getField().getSize().y-getSize().y);
                        break;
                }
            }
        }
}
