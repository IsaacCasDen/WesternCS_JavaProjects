/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class City extends MapObject {

    public int getId() {
        return id;
    }
    private void setId(int value) {
        id=value;
    }
    
    public String getName() {
        return name;
    }
    private void setName(String value) {
        name=value;
    }
    
    public int getLat() {
        return lat;
    }
    private void setLat(String value) {
        setLat(Integer.parseInt(value));
    }
    private void setLat(int value) {
        lat=value;
    }
    
    public int getLon() {
        return lon;
    }
    private void setLon(String value) {
        setLon(Integer.parseInt(value));
    }
    private void setLon(int value) {
        lon=value;
    }
    
    public Road getRoad(City destination) {
        Road value = null;
        
        for (Road road:getRoadsList()) {
            if (road.getDestination()==destination) {
                value=road;
                break;
            }
        }
        
        return value;
    }
    public Road[] getRoads() {
        return getRoadsList().toArray(new Road[getRoadsList().size()]);
    }
    public void addRoad(String name, City destination, int length, int time) {
        City.addRoad(name, this, destination, length, time);
    }
    public void removeRoad(City value) {
        City.removeRoad(this, value);
    }
    private ArrayList<Road> getRoadsList() {
        if (roads==null) {
            roads=new ArrayList();
        }
        return roads;
    }
    
    private String name;
    private int lat,lon;
    private ArrayList<Road> roads;
    private int id;
    
    public City(Map parent, String name,String lat, String lon) {
        super(parent);
        
        setId(getParent().getNewCityId());
        setName(name);
        setLat(lat);
        setLon(lon);
    }
    
    public static void addRoad(Map context, String name, String firstCityName, String secondCityName, String length, String time) {
        addRoad(name,context.getCity(firstCityName),context.getCity(secondCityName),Integer.parseInt(length),Integer.parseInt(time));
    }
    public static void addRoad(String name, City first, City second, int length, int time) {
        if (first!=null&&second!=null) {
            Road r1 = first.getRoad(second);
            Road r2 = second.getRoad(first);
            
            if (r1==null) {
                first.getRoadsList().add(new Road(name,second,length,time));
            } else {
                r1.setName(name);
                r1.setLength(length);
                r1.setTime(time);
            }
            
            if (r2==null) {
                second.getRoadsList().add(new Road(name,first,length,time));
            } else {
                r2.setName(name);
                r2.setLength(length);
                r2.setTime(time);
            }
        }
    }
    public static void removeRoad(City first, City second) {
        if (first!=null&&second!=null) {
            first.getRoadsList().remove(first.getRoad(second));
            second.getRoadsList().remove(second.getRoad(first));
        }
    }
    
    public String toString() {
        String value = String.format("%1$s is at (%2$s, %3$s)", 
                getName(),
                getLat(),
                getLon()
        );
        for (int i = 0; i<getRoadsList().size(); i++) {
            value+=String.format("\n\t%1$s",getRoadsList().get(i).toString());
        }
        
        return value;
    }
}
