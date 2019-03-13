/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong.Interfaces;

import Pong.Enums.TeamScored;
import java.awt.Rectangle;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public interface GameDisplay {
        public Rectangle getBounds();

        public int getWidth();
        public int getHeight();
        
        public void scoreChanged(TeamScored teamScored, int score);
    }
