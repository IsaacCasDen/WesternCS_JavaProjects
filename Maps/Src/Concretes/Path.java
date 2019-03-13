/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class Path {
    
    public static int attempts=0;
    public long timeTaken;
    public int length;
    public int time;
    public ArrayList<Integer> visited = new ArrayList();
    public boolean isValid = false;
    
}
