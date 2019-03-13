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
public class MapObject {
    
    protected Map getParent() {
        return parent;
    }
    private void setParent(Map value) {
        parent=value;
    }
    private Map parent;
    
    protected MapObject(Map parent) {
        setParent(parent);
    }
    
}
