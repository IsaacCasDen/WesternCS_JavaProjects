/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes;

import Enums.PathType;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author IsaacCD
 */
public class Map {
    
    public int getNewCityId() {
        int value;
        
        if (lastCityId==null) {
            lastCityId=0;
            value=0;
        } else {
            lastCityId++;
            value=lastCityId;
        }
        return value;
    }
    public int getNewRoadId() {
        int value;
        
        if (lastCityId==null) {
            lastCityId=0;
            value=0;
        } else {
            lastCityId++;
            value=lastCityId;
        }
        return value;
    }
    public boolean hasCity(String name) {
        boolean value = false;
        for (City city:getCityList()) {
            if (city.getName().equalsIgnoreCase(name)) {
                value=true;
            }
        }
        return value;
    }
    public City getCity(int id) {
        City value = null;
        
        for (City city:getCities()) {
            if (city.getId()==id) {
                value=city;
                break;
            }
        }
        
        return value;
    }
    public City getCity(String name) {
        City value = null;
        for (City city:getCityList()) {
            if (city.getName().equalsIgnoreCase(name)) {
                value=city;
            }
        }
        return value;
    }
    public City[] getCities() {
        return getCityList().toArray(new City[getCityList().size()]);
    }
    public String[] getCityNames() {
        ArrayList<String> names = new ArrayList();
        for (City city:getCityList()) {
            names.add(city.getName());
        }
        Collections.sort(names);
        return names.toArray(new String[names.size()]);
    }
    public void addCity(String name, String lat, String lon) {
        if (!hasCity(name)) {
            getCityList().add(new City(this,name,lat,lon));
        }
    }
    public void removeCity(String name) {
        City value = getCity(name);
        if (value!=null) {
            for (Road road:value.getRoads()) {
                City.removeRoad(value, road.getDestination());
            }
            getCityList().remove(value);
        }
    }
    
    private ArrayList<City> getCityList() {
        if(cityList==null) {
            cityList = new ArrayList();
        }
        return cityList;
    }
    private ArrayList<City> cityList;
    private Integer lastCityId;
    private Integer lastRoadId;
    
    public void getShortestPath(PathType pathType) {
        switch (pathType) {
            case ShortestTime:
                getShortestTime();
                break;
            case ShortestLength:
                getShortestLength();
                break;
        }
    }
    private void getShortestTime() {
        
    }
    private void getShortestLength() {
        
    }
    
    public String toString() {
        String value = "";
        for (int i=0;i<getCityList().size();i++) {
            if (i>0) { value+="\n"; }
            value+=getCityList().get(i).toString();
        }
        return value;
    }
}
