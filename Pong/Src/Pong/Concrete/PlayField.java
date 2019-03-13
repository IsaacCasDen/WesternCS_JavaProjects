/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong.Concrete;

import Pong.Abstract.PongObject;
import Pong.Interfaces.PongField;
import Pong.Interfaces.GameDisplay;
import Pong.Enums.TeamScored;
import Pong.Enums.Edge;
import java.awt.Point;
import java.util.ArrayList;
import Pong.Concrete.Paddle;
import Pong.Concrete.Ball;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public class PlayField implements PongField {
        
        private boolean _IsRunning;
        private int _LeftScore;
        private int _RightScore;
        
        private GameDisplay display;
        private ArrayList<Paddle> paddles;
        private ArrayList<Ball> balls;
        private ArrayList<Ball> finBalls;
        
        public int getLeftScore() {
            return _LeftScore;
        }
        private void setLeftScore(int value) {
            _LeftScore=value;
            display.scoreChanged(TeamScored.Left, value);
        }
        
        public int getRightScore() {
            return _RightScore;
        }
        private void setRightScore(int value) {
            _RightScore=value;
            display.scoreChanged(TeamScored.Right, value);
        }
        
        public ArrayList<Paddle> getPaddles() {
            if (paddles==null) {paddles = new ArrayList();}
            return paddles;
        }
        public ArrayList<Ball> getBalls() {
            if (balls==null) {balls = new ArrayList();}
            return balls;
        }
        private ArrayList<Ball> getFinBalls() {
            if (finBalls == null) {finBalls = new ArrayList();}
            return finBalls;
        }
        
        public PlayField(GameDisplay display) {
            this.display=display;
            initField();
        }

        public void teamScores(TeamScored team) {
            switch (team) {
                case Left:
                    setLeftScore(getLeftScore()+1);
                    break;
                case Right:
                    setRightScore(getRightScore()+1);
                    break;
            }
            if (getBalls().size()<1) {
                resetField();
            }
        }
        @Override
        public ArrayList<Edge> validateBorderContact(PongObject item) {
            
            ArrayList<Edge> value = new ArrayList();
            Point pos = item.getPosition();
            Point size = item.getSize();
            
            if (pos.x<0) { value.add(Edge.Left); }
            if (pos.y<0) { value.add(Edge.Top); }
            if ((pos.x+size.x)>display.getWidth()) { value.add(Edge.Right);}
            if ((pos.y+size.y)>display.getHeight()) { value.add(Edge.Bottom);}

            return value;
        }
        @Override
        public ArrayList<Edge> validatePaddleContact(PongObject item) {
            
            ArrayList<Edge> value = new ArrayList();
            
            for (Object p:getPaddles()) {
                Paddle paddle = (Paddle)p;
                
                Rectangle intersect = item.getBounds().intersection(paddle.getBounds());
                if (intersect!=null&&(intersect.width>0&&intersect.height>0)) {
                    value.add(Edge.Left);

                    int itemCenter = item.getCenter().y;
                    int paddleCenter = paddle.getCenter().y;
                    double centerDiff = (double)item.getCenter().y-paddle.getCenter().y;
                    double regionHit = centerDiff/paddle.getHeight();
                
                    if (regionHit>0.25) { 
                            value.add(Edge.Bottom); 
                        }  else if (regionHit<-0.25) {
                            value.add(Edge.Top);
                        } else {
                            value.add(Edge.Right);
                    }
                }
            }
            return value;
        }
        public ArrayList<Edge> validateBallContact(PongObject item) {
            return new ArrayList<Edge>();
        }
        public Point getSize() {
            return new Point(display.getWidth(),display.getHeight());
        }
        
        private void resetField() {
            _IsRunning = false;
            if (paddles!=null) { paddles.clear();}
            if (balls!=null) {balls.clear();}
            
            createPaddles();
            createBall();
            _IsRunning=true;
        }
        private void createPaddles() {
            Paddle paddleLeft = new Paddle(this, new Point());
            Paddle paddleRight = new Paddle(this, new Point());

            int paddleLeftX=15;
            int paddleRightx=display.getWidth()-(paddleLeftX+paddleRight.getSize().x);
            
            paddleLeft.setPosition(paddleLeftX,display.getHeight()/2);
            paddleRight.setPosition(paddleRightx,display.getHeight()/2);
            
            addPaddle(paddleLeft);
            addPaddle(paddleRight);
        }
        public void createBall() {
            
            Random rand = new Random();
            
            Ball ball = new Ball(this, new Point(display.getWidth()/2,display.getHeight()/2)); 
            while (
                    (ball.getVelocity().x<3&&ball.getVelocity().x>-3)||
                    (ball.getVelocity().y<3&&ball.getVelocity().y>-3)
                    ) {
                ball.setVelocity(rand.nextInt(10)-5, rand.nextInt(10)-5);
            }
            
            addBall(ball);
        }
        public void initField() {
            setLeftScore(0);
            setRightScore(0);
            resetField();
        }
        
        public void nextFrame() {
            if (_IsRunning) {
                clearBalls();
                advanceBalls();
                cpuPaddle();
            }
        }
        private void cpuPaddle() {
            
            Paddle cpu = null;
            Ball ball = null;
            
            if (getPaddles().size()>1) {
                cpu = (Paddle)getPaddles().get(1);
            }
            
            if (cpu!=null) {
                do {
                    ball=getTarget();
                } while (getBalls().size()>0&&ball==null);
                
                if (ball!=null) {
                    Point position = (Point)cpu.getPosition().clone();
                    int ballY = ball.getCenter().y;
                    int cpuHeight = (int)(cpu.getSize().y/2);
                    
                    position.y = ballY-cpuHeight;
                    cpu.setPosition(position);
                }
            }
            
        }
        private Ball getTarget() {
            Ball value = null;
            
            try {
                for (Ball item:getBalls()) {
                    if (value==null) {value=item;}
                    if (item.getVelocity().x>value.getVelocity().x) {
                        value=item;
                    } 
                    if ((item.getPosition().x>value.getPosition().x)) {
                        value=item;
                    }
                }
            } catch (Exception ex) {
                if (ex!=null) {
                    
                }
            }
            
            return value;
        }
        private void clearBalls() {
            try {
                for (Ball ball:getFinBalls()) {
                    removeBall(ball);
                }
            } catch (Exception ex) {
                clearBalls();
            }
        }
        private void advanceBalls() {
            for (Object item: balls) {
                Ball ball = (Ball)item;
                Point vel = ball.getVelocity();
                Point oldPos = ball.getPosition();
                Point newPos = (Point)oldPos.clone();

                if (vel.y!=0) {
                    //Is the ball moving up or down?

                    newPos.y+=vel.y;
                    newPos.x+=vel.x;

                } else {

                }

                ball.setPosition(newPos);
            }
        }
        public void ballDestroyed(Ball ball) {
            getFinBalls().add(ball);
        }
        
        private void addBall(Ball ball) {
            getBalls().add(ball);
        }
        private void removeBall(Ball ball) {
            getBalls().remove(ball);
        }
        
        private void addPaddle(Paddle paddle) {
            getPaddles().add(paddle);
        }
        private void removePaddle(Paddle paddle) {
            getPaddles().remove(paddle);
        }
    }
