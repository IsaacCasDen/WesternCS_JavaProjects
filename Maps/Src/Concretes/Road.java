/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

/**
 *
 * @author IsaacCD
 */
public final class Road {
    public String getName() {
        return name;
    }
    protected void setName(String value) {
        name=value;
    }
    
    public City getDestination() {
        return destination;
    }
    protected void setDestination(City value) {
        destination=value;
    }
    
    public int getLength() {
        return length;
    }
    protected void setLength(int value) {
        length=value;
    }
    
    public int getTime() {
        return time;
    }
    protected void setTime(int value) {
        time=value;
    }
    
    private String name;
    private City destination;
    private int length;
    private int time;

    protected Road(String name, City destination, int length, int time) {
        setName(name);
        setDestination(destination);
        setLength(length);
        setTime(time);
    }
    
    @Override
    public String toString() {
        String value = String.format("%1$s goes to %2$s (%3$s miles, %4$s minutes)", 
                getName(),
                getDestination().getName(),
                getLength(),
                getTime()
        );
        return value;
    }
}
