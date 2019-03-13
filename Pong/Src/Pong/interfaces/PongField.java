/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong.Interfaces;

import Pong.Concrete.Ball;
import Pong.Enums.TeamScored;
import Pong.Enums.Edge;
import Pong.Abstract.PongObject;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public interface PongField {
        public void teamScores(TeamScored team);
        public void ballDestroyed(Ball ball);
        public ArrayList<Edge> validateBorderContact(PongObject item);
        public ArrayList<Edge> validatePaddleContact(PongObject item);
        public ArrayList<Edge> validateBallContact(PongObject item);
        public Point getSize();
    }