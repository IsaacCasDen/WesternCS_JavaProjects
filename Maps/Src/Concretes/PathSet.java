/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Enums.PathMethod;
import Enums.PathType;

/**
 *
 * @author IsaacCD
 */
public class PathSet {
    
    public PathMethod getPathMethod() {
        return pathMethod;
    }
    
    public PathType getPathType() {
        return pathType;
    }
    
    public City getStartCity() {
        return startCity;
    }
    
    public City getEndCity() {
        return endCity;
    }
    
    private PathMethod pathMethod = PathMethod.None;
    private PathType pathType=PathType.None;
    private City startCity=null;
    private City endCity=null;
    
    public PathSet(PathMethod pathMethod, PathType pathType, City startCity, City endCity) {
        this.pathMethod=pathMethod;
        this.pathType=pathType;
        this.startCity=startCity;
        this.endCity=endCity;
    }

    @Override
    public String toString() {
        String value = "";
        value += "PathMethod: " + pathMethod;
        value += " PathType: " + pathType;
        if (startCity!=null) { value += " Start City: " + startCity.getName(); } else { value += " Start City: None"; }
        if (endCity!=null) { value += " End City: " + endCity.getName(); } else { value += " End City: None"; }
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;
        return obj.toString().equals(obj.toString());
    }
    
    
    
    
}
