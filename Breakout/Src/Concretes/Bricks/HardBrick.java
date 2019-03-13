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
public class HardBrick extends ColorBrick {
    
    public HardBrick(Dimension size) {
        super(size);
        setHealth(3);
        super.setScoreValue(3);
        refreshColors();
    }
    
    private void refreshColors() {
        setColor(getColors().get(getHealth()%getColors().size()));
    }
    
    public void tick() {
        
    }
    public void hit(Ball ball, PlayerGroup playerGroup) {
        super.hit(ball,playerGroup,1);
        refreshColors();
    }
    public void hit(Ball ball, PlayerGroup group, int damage) {
        super.hit(ball,group,damage);
        refreshColors();
    }
    
}
