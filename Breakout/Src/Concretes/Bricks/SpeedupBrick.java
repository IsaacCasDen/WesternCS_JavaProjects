/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Bricks;

import Concretes.Ball;
import Enums.PlayerGroup;
import java.awt.Dimension;

/**
 *
 * @author IsaacCD
 */
public class SpeedupBrick extends Brick {
    
    public SpeedupBrick(Dimension size) {
        super(size);
    }
    
    public void tick() {
        
    }
    
    private void accelerate(Ball ball) {
        ball.setSpeedModifier(2);
    }
    public void hit(Ball ball, PlayerGroup playerGroup) {
        super.hit(ball,playerGroup,1);
        accelerate(ball);
    }
    public void hit(Ball ball, PlayerGroup group, int damage) {
        super.hit(ball,group,damage);
        accelerate(ball);
    }
    
}
