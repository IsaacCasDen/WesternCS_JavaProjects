/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Enums.Edge;
import Enums.HorizontalDirection;
import Enums.VerticalDirection;
import Interfaces.GameInterface;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/**
 *
 * @author IsaacCD
 */
public abstract class GameObject {
    
    public abstract Rectangle getBounds();
    protected Rectangle _getBounds() {
        return _Bounds;
    }
    protected void _setBounds(Rectangle value) {
        _Bounds=value;
    }
    private Rectangle _Bounds;
    
    public HashMap<Rectangle,Edge> getGrid() {
        HashMap<Rectangle,Edge> values = new HashMap<>();
        Rectangle bounds = getBounds();
        
        double factor = 1.0/3;
        double width = bounds.width*factor,height=bounds.height*factor;
        
        for (int x=0;x<3;x++) {
            double xPos = bounds.x+(x*width);
            for (int y=0;y<3;y++) {
                double yPos = bounds.y + (y*height);
                
                if (x==0&&y==0) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.TopLeft);
                } else if (x==0&&y==1) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.LeftMid);
                } else if (x==0&&y==2) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.BottomLeft);
                } else if (x==1&&y==0) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.TopMid);
                } else if (x==1&&y==1) {
                     
                } else if (x==1&&y==2) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.BottomMid);
                } else if (x==2&&y==0) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.TopRight);
                } else if (x==2&&y==1) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.RightMid);
                } else if (x==2&&y==2) {
                    values.put(new Rectangle((int)xPos,(int)yPos,(int)width,(int)height), Edge.BottomRight);
                }
            }
        }
        
        return values;
    }
    
    public Point getCenter() {
        int hCenter = ((int)getBounds().getWidth()/2)+getBounds().x;
        int vCenter = ((int)getBounds().getHeight()/2)+getBounds().y;
        
        return new Point(hCenter,vCenter);
    }
    
    public abstract Point getVelocity();
    protected Point _getVelocity() {
        return _Velocity;
    }
    protected void _setVelocity(Point value) {
        _Velocity=value;
    }
    private Point _Velocity;
    
    public Direction getDirection() {
        return _Direction;
    }
    protected void setDirection(Direction value) {
        _Direction=value;
    }
    private Direction _Direction;

    public Color getColor() {
        return _Color;
    }
    protected void setColor(Color value) {
        _Color=value;
    }
    private Color _Color;
    
    public GameInterface getGameField() {
        return _GameInterface;
    }
    protected void setGameInterface(GameInterface value) {
        _GameInterface=value;
    }
    private GameInterface _GameInterface;
    
    public GameObject() {
        setColor(Color.darkGray);
    }
    
    public abstract void tick();
    
    public static Direction getRebound(Ball ball, GameObject object) {
        Direction value = ball.getDirection();
        Edge ballEdge;
        Edge objectEdge;
        
        Rectangle primary=null;
        int primPixelCount=0;
        Rectangle[] pair = new Rectangle[2];

        HashMap<Rectangle,Edge> ballGrid = ball.getGrid();
        HashMap<Rectangle,Edge> objectGrid = object.getGrid();
        for (Rectangle segBall:ballGrid.keySet()) {
            ballEdge = ballGrid.get(segBall);
            for (Rectangle segBrick:objectGrid.keySet()) {
                objectEdge=objectGrid.get(segBrick);
                Rectangle overlap = segBall.intersection(segBrick);
                overlap.grow(1, 1);
                int pixelCount =overlap.width*overlap.height;
                int w=overlap.width,h=overlap.height;
                if (overlap.width>0&&overlap.height>0) {
                    if (primary==null) {
                        primary=overlap;
                        primPixelCount=pixelCount;
                        pair[0]=segBall;
                        pair[1]=segBrick;
                    } else {
                        if (pixelCount>primPixelCount) {
                            primary=overlap;
                            primPixelCount=pixelCount;
                            pair[0]=segBall;
                            pair[1]=segBrick;
                        }
                    }
                }
            }

        }

        ballEdge = ballGrid.get(pair[0]);
        objectEdge = objectGrid.get(pair[1]);

        if (objectEdge==Edge.TopLeft) {
            if (ballEdge==Edge.BottomLeft) {
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.BottomMid) {
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.TopRight) {
                value.horizontalDirection=HorizontalDirection.Left;
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.RightMid) {
                value.horizontalDirection=HorizontalDirection.Left;
            } else if (ballEdge==Edge.BottomRight) {
                value.horizontalDirection=HorizontalDirection.Left;
                value.verticalDirection=VerticalDirection.Up;
            }
        } else if (objectEdge==Edge.LeftMid) {
            if (ballEdge==Edge.TopRight) {
                value.horizontalDirection=HorizontalDirection.Left;
            } else if (ballEdge==Edge.RightMid) {
                value.horizontalDirection=HorizontalDirection.Left;
            } else if (ballEdge==Edge.BottomRight) {
                value.horizontalDirection=HorizontalDirection.Left;
            }
        } else if (objectEdge==Edge.BottomLeft) {
            if (ballEdge==Edge.TopLeft) {
                value.verticalDirection=VerticalDirection.Down;
            } else if (ballEdge==Edge.TopMid) {
                value.verticalDirection=VerticalDirection.Down;
            } else if (ballEdge==Edge.TopRight) {
                value.verticalDirection=VerticalDirection.Down;
                value.horizontalDirection=HorizontalDirection.Left;
            } else if (ballEdge==Edge.RightMid) {
                value.horizontalDirection=HorizontalDirection.Left;
            } else if (ballEdge==Edge.BottomRight) {
                value.horizontalDirection=HorizontalDirection.Left;
            }
        } else if (objectEdge==Edge.TopMid) {
            if (ballEdge==Edge.BottomLeft) {
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.BottomMid) {
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.BottomRight) {
                value.verticalDirection=VerticalDirection.Up;
            }
        } else if (objectEdge==Edge.BottomMid) {
            if (ballEdge==Edge.TopLeft) {
                value.verticalDirection=VerticalDirection.Down;
            } else if (ballEdge==Edge.TopMid) {
                value.verticalDirection=VerticalDirection.Down;
            } else if (ballEdge==Edge.TopRight) {
                value.verticalDirection=VerticalDirection.Down;
            }
        } else if (objectEdge==Edge.TopRight) {
            if (ballEdge==Edge.TopLeft) {
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.LeftMid) {
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.BottomLeft) {
                value.horizontalDirection=HorizontalDirection.Right;
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.BottomMid) {
                value.verticalDirection=VerticalDirection.Up;
            } else if (ballEdge==Edge.BottomRight) {
                value.verticalDirection=VerticalDirection.Up;
            }
        } else if (objectEdge==Edge.RightMid) {
            if (ballEdge==Edge.TopLeft) {
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.LeftMid) {
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.BottomLeft) {
                value.horizontalDirection=HorizontalDirection.Right;
            }
        } else if (objectEdge==Edge.BottomRight) {
            if (ballEdge==Edge.TopLeft) {
                value.verticalDirection=VerticalDirection.Down;
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.LeftMid) {
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.BottomLeft) {
                value.horizontalDirection=HorizontalDirection.Right;
            } else if (ballEdge==Edge.TopMid) {
                value.verticalDirection=VerticalDirection.Down;
            } else if (ballEdge==Edge.TopRight) {
                value.verticalDirection=VerticalDirection.Down;
            }
        }
        return value;
    }
}
