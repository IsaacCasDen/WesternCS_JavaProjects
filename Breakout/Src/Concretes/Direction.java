/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Enums.HorizontalDirection;
import Enums.VerticalDirection;

/**
 *
 * @author IsaacCD
 */
public class Direction {
    HorizontalDirection horizontalDirection = HorizontalDirection.None;
    VerticalDirection verticalDirection=VerticalDirection.None;
    
    public Direction() {
        
    }
    public Direction(HorizontalDirection horizontalDirection,VerticalDirection verticalDirection) {
        this.horizontalDirection=horizontalDirection;
        this.verticalDirection=verticalDirection;
    }
}
