/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Concretes.Bricks.Brick;
import Concretes.Bricks.BrickGroup;
import Concretes.Bricks.StandardBrick;
import Enums.Edge;
import Enums.HorizontalDirection;
import Enums.VerticalDirection;
import Factories.Levels;
import Factories.Paddles;
import Interfaces.GameInterface;
import Interfaces.ScoreInterface;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author IsaacCD
 */
public class PlayField 
        implements GameInterface
{

    public GameInterface getGameInterface() {
        return _GameInterface;
    }
    protected void setGameInterface(GameInterface value) {
        _GameInterface = value;
    }
    private GameInterface _GameInterface;
   
     public ScoreInterface getScoreInterface() {
        return _ScoreInterface;
    }
    protected void setScoreInterface(ScoreInterface value) {
        _ScoreInterface = value;
    }
    private ScoreInterface _ScoreInterface;
    
    public GameState getGameState() {
        return _GameState;
    }
    private void setGameState(GameState value) {
        _GameState=value;
    }
    private GameState _GameState;
    
    private ArrayList<Paddles> _Paddles;
    
    private ArrayList<Player> _Players;
    
    public PlayField(GameInterface gameInterface,ScoreInterface scoreInterface) {
        setGameInterface(gameInterface);
        setScoreInterface(scoreInterface);
        init();
    }
    private void init() {
        setLevel(Levels.LevelType.LevelOne);
    }
    public void setLevel(Levels.LevelType level) {
        setGameState(Levels.getLevel(Levels.LevelType.LevelOne,this,getScoreInterface()));
    }
    
    public void tick() {
        nextFrame();
    }
    private void nextFrame() {
        getGameState().GC();
        advanceBricks();
        advanceBalls();
        advancePaddles();
    }
    private void advanceBricks() {
        for (BrickGroup group:getGameState().getBrickGroupArray()) {
            for (Brick brick:group.getBricks()) {
                brick.tick();
            }
        }
    }
    private void advanceBalls() {
        for (Ball ball:getGameState().getBallArray()) {
            ball.tick();
        }
    }
    private void advancePaddles() {
        for (Paddle paddle:getGameState().getPaddleArray()) {
            paddle.tick();
        }
    }
    
    @Override
    public Color getBackground() {
        return getGameInterface().getBackground();
    }

    @Override
    public void setBackground(Color color) {
        getGameInterface().setBackground(color);
    }

    @Override
    public int getWidth() {
        return getGameInterface().getWidth();
    }

    @Override
    public int getHeight() {
        return getGameInterface().getHeight();
    }

    @Override
    public Edge checkScreenEdgeIntersection(GameObject gameObject) {
        Edge value = Edge.None;
        Rectangle bounds = gameObject.getBounds();
        if (bounds.x<0) {
            value=Edge.LeftMid;
        } else if (bounds.y<0) {
            value=Edge.TopMid;
        } else if (bounds.x+bounds.width>getWidth()) {
            value=Edge.RightMid;
        } else if (bounds.y+bounds.height>getHeight()) {
            value=Edge.BottomMid;
        }
        return value;
    }
    public Direction checkBrickEdgeIntersection(Ball ball) {
        Direction value = ball.getDirection();
        
        Rectangle ballBounds = ball.getBounds();
        for (BrickGroup groups:getGameState().getBrickGroupArray()) {
            for (Brick brick:groups.getBricks()) {
                Rectangle brickBounds = brick.getBounds();
                Rectangle intersection = ballBounds.intersection(brickBounds);
                if (intersection!=null&&intersection.width>0&&intersection.height>0) {
                    value=GameObject.getRebound(ball, brick);
                    brick.hit(ball,ball.getLastHit());
                    break;    
                }
            }
        }
        return value;
    }
    public Direction checkPaddleEdgeIntersection(Ball ball) {
        Direction value = ball.getDirection();
        Rectangle ballBounds = ball.getBounds();
            for (Paddle paddle:getGameState().getPaddleArray()) {
                Rectangle brickBounds = paddle.getBounds();
                Rectangle intersection = ballBounds.intersection(brickBounds);
                if (intersection!=null&&intersection.width>0&&intersection.height>0) {
                    ball.setLastHit(paddle.getPlayer());
                    value=GameObject.getRebound(ball, paddle);
                    break;    
                }
            }        
        return value;
    }

    @Override
    public void mouseTarget(Point target) {
        for (Paddle paddle:getGameState().getPaddleArray()) {
            paddle.setTarget(target);
        }
    }

    @Override
    public void pauseGame() {
        getGameInterface().pauseGame();
    }

    @Override
    public void showGameOver(boolean isVisible) {
        getGameInterface().showGameOver(isVisible);
    }

}
 