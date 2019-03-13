/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Bricks;

import Concretes.Ball;
import Enums.PlayerGroup;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author IsaacCD
 */
public class TeleportBrick extends ColorBrick {
    
    public TeleportBrick(Dimension size) {
        super(size);
    }
    
    private void refreshColors() {
        setColor(getColors().get(getHealth()%getColors().size()+2));
    }
    
    public void tick() {
        
    }
    
    private void teleportBall(Ball ball) {
        ball.setPosition(new Point(ball.getLastPosition().x,0));
    }
    
    public void hit(Ball ball, PlayerGroup playerGroup) {
        super.hit(ball,playerGroup,1);
        teleportBall(ball);
    }
    public void hit(Ball ball, PlayerGroup group, int damage) {
        super.hit(ball,group,damage);
        teleportBall(ball);
    }
}
