/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Listeners.PlayerLivesChangedListener;
import Listeners.PlayerScoreChangedListener;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class Player {
    
    protected void scoreChanged() {
        for (PlayerScoreChangedListener listener:getPlayerScoreChangedListeners()) {
            listener.playerScoreChanged(this);
        }
    }
    public void addPlayerScoreChangedListener(PlayerScoreChangedListener listener) {
        getPlayerScoreChangedListeners().add(listener);
    }
    public void removePlayerScoreChangedListener(PlayerScoreChangedListener listener) {
        getPlayerScoreChangedListeners().remove(listener);
    }
    protected ArrayList<PlayerScoreChangedListener> getPlayerScoreChangedListeners() {
        if (_PlayerScoreChangedListeners==null) {
            _PlayerScoreChangedListeners=new ArrayList();
        }
        return _PlayerScoreChangedListeners;
    }
    private ArrayList<PlayerScoreChangedListener> _PlayerScoreChangedListeners;
    
    public int getScore() {
        return _Score;
    }
    public void addScore(int value) {
        setScore(getScore()+value);
    }
    public void setScore(int value) {
        _Score=value;
        scoreChanged();
    }
    private int _Score;
    
    protected void livesChanged() {
        for (PlayerLivesChangedListener listener:getPlayerLivesChangedListeners()) {
            listener.playerLivesChanged(this);
        }
    }
    public void addPlayerLivesChangedListener(PlayerLivesChangedListener listener) {
        getPlayerLivesChangedListeners().add(listener);
    }
    public void removePlayerLivesChangedListener(PlayerLivesChangedListener listener) {
        getPlayerLivesChangedListeners().remove(listener);
    }
    protected ArrayList<PlayerLivesChangedListener> getPlayerLivesChangedListeners() {
        if (_PlayerLivesChangedListeners==null) {
            _PlayerLivesChangedListeners=new ArrayList();
        }
        return _PlayerLivesChangedListeners;
    }
    private ArrayList<PlayerLivesChangedListener> _PlayerLivesChangedListeners;
    
    public int getLives() {
        return _Lives;
    }
    public void addLives(int value) {
        setLives(getLives()+value);
    }
    public void setLives(int value) {
        _Lives=value;
        livesChanged();
    }
    private int _Lives;
    
    public Player() {
        setScore(0);
        setLives(3);
    }
    
}
