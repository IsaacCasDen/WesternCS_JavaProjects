/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import Concretes.Ball;
import Concretes.Bricks.BrickGroup;
import Concretes.GameState;
import Concretes.Paddle;
import Concretes.Player;
import Enums.PlayerGroup;
import Factories.Bricks.BrickType;
import Interfaces.GameInterface;
import Interfaces.ScoreInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author IsaacCD
 */
public class Levels {
    public enum LevelType {
        LevelOne
    }
    
    public static GameState getLevel(LevelType levelType,GameInterface gameInterface, ScoreInterface scoreInterface) {
        GameState value = null;
        switch(levelType) {
            case LevelOne:
                value=getLevelOne(gameInterface,scoreInterface);
                break;
        }
        return value;
    }
    protected static GameState getLevelOne(GameInterface gameInterface,ScoreInterface scoreInterface) {
        GameState value = new GameState(gameInterface,scoreInterface);

        Player player = new Player();
        value.addPlayer(PlayerGroup.Left,player);
        
        resetLevelOneBall(value);
        resetLevelOnePaddle(value);
        resetLevelOneBricks(value);
        
        return value;
    }
    public static void resetLevelOneBall(GameState gameState) {
        for (Ball ball: gameState.getBallArray()) {gameState.removeBall(ball);}
        Ball ball = Balls.createBall(Balls.BallType.Standard,gameState.getGameInterface());
        ball.setLastHit(PlayerGroup.Left);
        ball.setPosition(new Point(gameState.getGameInterface().getWidth()/2,gameState.getGameInterface().getHeight()-150));
        gameState.addBall(ball);
    }
    public static void resetLevelOnePaddle(GameState gameState) {
        for (Paddle paddle: gameState.getPaddleArray()) {gameState.removePaddle(paddle);}
        Point position = new Point();
        position.x=gameState.getGameInterface().getWidth()/2;
        position.y=gameState.getGameInterface().getHeight()-(10+Paddles.getDefaultSizes().get(Paddles.PaddleType.Standard).height);
        Paddle paddle = Paddles.createPaddle(Paddles.PaddleType.Standard, gameState.getGameInterface(),position, PlayerGroup.Left, Color.BLACK);
        gameState.addPaddle(paddle);
    }
    public static void resetLevelOneBricks(GameState gameState) {
        for (BrickGroup group: gameState.getBrickGroupArray()) {gameState.removeBrickGroup(group);}
        BrickGroup bricks;
        Rectangle bounds = new Rectangle();
        bounds.x = 30;
        bounds.y = 30;
        bounds.width =gameState.getGameInterface().getWidth()-60;
        bounds.height=(gameState.getGameInterface().getHeight()-(60+160))/4;
        bricks = BrickGroups.createBrickGroup(BrickGroups.BrickPattern.Square,BrickType.Standard, gameState.getGameInterface(), bounds, -1);
        gameState.addBrickGroup(bricks);
        
        bounds = new Rectangle(bounds.x,bounds.y+bounds.height+5,bounds.width,bounds.height/2);
        bricks = BrickGroups.createBrickGroup(BrickGroups.BrickPattern.Square,BrickType.Color, gameState.getGameInterface(), bounds, -1);
        gameState.addBrickGroup(bricks);
        
        bounds = new Rectangle(bounds.x,bounds.y+bounds.height+5,bounds.width,bounds.height);
        bricks = BrickGroups.createBrickGroup(BrickGroups.BrickPattern.Square,BrickType.Hard, gameState.getGameInterface(), bounds, -1);
        gameState.addBrickGroup(bricks);
        
        bounds = new Rectangle(bounds.x,bounds.y+bounds.height+5,bounds.width,bounds.height/2);
        bricks = BrickGroups.createBrickGroup(BrickGroups.BrickPattern.Square,BrickType.Teleport, gameState.getGameInterface(), bounds, -1);
        gameState.addBrickGroup(bricks);
        
        bounds = new Rectangle(bounds.x,bounds.y+bounds.height+5,bounds.width,bounds.height);
        bricks = BrickGroups.createBrickGroup(BrickGroups.BrickPattern.Square,BrickType.Speedup, gameState.getGameInterface(), bounds, -1);
        gameState.addBrickGroup(bricks);
    }
}
