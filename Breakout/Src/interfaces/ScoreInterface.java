/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Enums.PlayerGroup;

/**
 *
 * @author IsaacCD
 */
public interface ScoreInterface {
    public void setScore(PlayerGroup playerGroup, int score);
    public void setLives(PlayerGroup playerGroup, int lives);
}
