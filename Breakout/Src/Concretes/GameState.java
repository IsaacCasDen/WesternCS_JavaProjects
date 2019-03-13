/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Concretes.Bricks.Brick;
import Concretes.Bricks.BrickGroup;
import Enums.PlayerGroup;
import Factories.Levels;
import Interfaces.GameInterface;
import Interfaces.ScoreInterface;
import Listeners.BallDestroyedListener;
import Listeners.BrickDestroyedListener;
import Listeners.PlayerLivesChangedListener;
import Listeners.PlayerScoreChangedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author IsaacCD
 */
public class GameState implements 
        BallDestroyedListener,
        BrickDestroyedListener,
        PlayerLivesChangedListener,
        PlayerScoreChangedListener
{
    
    public GameInterface getGameInterface() {
        return _GameInterface;
    }
    protected void setGameInterface(GameInterface value) {
        _GameInterface=value;
    }
    private GameInterface _GameInterface;
    
    public ScoreInterface getScoreInterface() {
        return _ScoreInterface;
    }
    protected void setScoreInterface(ScoreInterface value) {
        _ScoreInterface=value;
    }
    private ScoreInterface _ScoreInterface;
    
    public BrickGroup[] getBrickGroupArray() {
        return getBrickGroups().toArray(new BrickGroup[getBrickGroups().size()]);
    }
    public void addBrickGroup(BrickGroup group) {
        group.addBrickDestroyedListener(this);
        getBrickGroups().add(group);
    }
    public void removeBrickGroup(BrickGroup group) {
        group.removeBrickDestroyedListener(this);
        getBrickGroups().remove(group);
    }
    private ArrayList<BrickGroup> getBrickGroups() {
        if (_BrickGroups==null) {
            _BrickGroups=new ArrayList();
        }
        return _BrickGroups;
    }
    private ArrayList<BrickGroup> _BrickGroups;
    
    private ArrayList<Brick> getFinBricks() {
        if (_FinBricks==null) {
            _FinBricks=new ArrayList();
        }
        return _FinBricks;
    }
    private ArrayList<Brick> _FinBricks;
    
    public Ball[] getBallArray() {
        return getBalls().toArray(new Ball[getBalls().size()]);
    }
    public void addBall(Ball ball) {
        ball.addBallDestroyedListener(this);
        getBalls().add(ball);
    }
    public void removeBall(Ball ball) {
        ball.removeBallDestroyedListener(this);
        getBalls().remove(ball);
    }
    private ArrayList<Ball> getBalls() {
        if (_Balls==null) {
            _Balls=new ArrayList<>();
        }
        return _Balls;
    }
    private ArrayList<Ball> _Balls;
    
    private ArrayList<Ball> getFinBalls() {
        if (_FinBalls==null) {
            _FinBalls=new ArrayList();
        }
        return _FinBalls;
    }
    private ArrayList<Ball> _FinBalls;
    
    public Paddle[] getPaddleArray() {
        return getPaddles().toArray(new Paddle[getPaddles().size()]);
    }
    public void addPaddle(Paddle paddle) {
        getPaddles().add(paddle);
    }
    public void removePaddle(Paddle paddle) {
        getPaddles().remove(paddle);
    }
    private ArrayList<Paddle> getPaddles() {
        if (_Paddles==null) {
            _Paddles = new ArrayList();
        }
        return _Paddles;
    }
    private ArrayList<Paddle> _Paddles;
    
    public Player[] getPlayerArray() {
        return getPlayers().values().toArray(new Player[getPlayers().size()]);
    }
    public void addPlayer(PlayerGroup playerGroup, Player player) {
        player.addPlayerLivesChangedListener(this);
        player.addPlayerScoreChangedListener(this);
        getPlayers().put(playerGroup, player);
        refreshScoreDisplay();
    }
    public void removePlayer(Player player) {
        player.removePlayerLivesChangedListener(this);
        player.removePlayerScoreChangedListener(this);
        getPlayers().remove(player);
    }
    protected HashMap<PlayerGroup,Player> getPlayers() {
        if (_Players==null) {
            _Players=new HashMap();
        }
        return _Players;
    }
    public void setPlayers(GameState oldGameState) {
        setPlayers(oldGameState.getPlayers());
    }
    public void setPlayers(HashMap<PlayerGroup, Player> values) {
        _Players=null;
        for (PlayerGroup playerGroup:values.keySet()) {
            addPlayer(playerGroup, values.get(playerGroup));
        }
    }
    private HashMap<PlayerGroup, Player> _Players;
    
    public GameState(GameInterface gameInterface,ScoreInterface scoreInterface) {
        setGameInterface(gameInterface);
        setScoreInterface(scoreInterface);
    }

    public void GC() {
        clearBalls();
        clearBricks();
    }
    private void clearBalls() {
        for(Ball ball:getFinBalls()) {
            getBalls().remove(ball);
        }
        getFinBalls().clear();
        
        if (getBalls().size()==0) {
            boolean hasLives=false;
            for (PlayerGroup group:getPlayers().keySet()) {
                if (getPlayers().get(group).getLives()>0) {
                    hasLives=true;
                    break;
                }
            }
            if (hasLives) {
                Levels.resetLevelOnePaddle(this);
                Levels.resetLevelOneBall(this);
                getGameInterface().pauseGame();
            } else {
                getGameInterface().showGameOver(true);
            }
        }
    }
    private void clearBricks() {
        for(Brick brick:getFinBricks()) {
            brick.getBrickGroup().removeBrick(brick);
        }
        getFinBricks().clear();
    }

    private void refreshScoreDisplay() {
        for (PlayerGroup group:getPlayers().keySet()) {
            getScoreInterface().setScore(group,getPlayers().get(group).getScore());
            getScoreInterface().setLives(group,getPlayers().get(group).getLives());
        }
    }
    private void updatePlayerScore(PlayerGroup playerGroup, int points) {
        if (getPlayers().containsKey(playerGroup)) {
            getPlayers().get(playerGroup).addScore(points);
        }
    }
    private void updatePlayerLives(PlayerGroup playerGroup, int lives) {
        if (getPlayers().containsKey(playerGroup)) {
            getPlayers().get(playerGroup).addLives(lives);
        }
    }
    
    @Override
    public void ballDestroyed(Ball ball) {
        if (ball.getLastHit()!=PlayerGroup.None) {
            updatePlayerLives(ball.getLastHit(),-1);
        }
        getFinBalls().add(ball);
    }

    @Override
    public void brickDestroyed(Brick brick) {
        if (brick.getLastHitBy()!=PlayerGroup.None) {
            updatePlayerScore(brick.getLastHitBy(),brick.getScoreValue());
        }
        getFinBricks().add(brick);
    }

    @Override
    public void playerLivesChanged(Player player) {
        if (getPlayers().containsValue(player)) {
            for (Map.Entry<PlayerGroup, Player> item:getPlayers().entrySet()) {
                if (item.getValue()==player) {
                    getScoreInterface().setLives(item.getKey(),player.getLives());
                    break;
                }
            }
        }
    }

    @Override
    public void playerScoreChanged(Player player) {
    
        if (getPlayers().containsValue(player)) {
            for (Map.Entry<PlayerGroup, Player> item:getPlayers().entrySet()) {
                if (item.getValue()==player) {
                    getScoreInterface().setScore(item.getKey(),player.getScore());
                    break;
                }
            }
        }
    }
}
