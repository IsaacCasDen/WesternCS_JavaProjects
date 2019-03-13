/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Paths;

import Concretes.City;
import Concretes.Path;
import Concretes.PathData;
import Concretes.PathSet;
import Concretes.Road;
import Enums.PathMethod;
import Enums.PathType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author IsaacCD
 */
public abstract class Paths {
    
    public PathSet getPathSet() {
        return pathSet;
    }
    protected void setPathSet(PathSet value) {
        pathSet=value;
    }
    private PathSet pathSet;
    
    public PathData getPathData() {
        return pathData;
    }
    protected void setPathData(PathData value) {
        pathData=value;
    }
    private PathData pathData;
    
    public Path getPath() {
        if (getPathData()!=null) {
            return getPathData().path;
        }
        return null;
    }
    
    private HashMap<Integer,City> cities = new HashMap();
    
    public int getPathLength() {
        int value = 0;
        if (pathLength!=null) {
            value = pathLength;
        } else {
            if (getPath()!=null)  {
                value = getPathLength(0);
            }
        }
        return value;
    }
    protected int getPathLength(int i) {
        int value = 0;
        if ((i+1)<getPath().visited.size()) {
            City s = cities.get(getPath().visited.get(i));
            City e = cities.get(getPath().visited.get(i+1));
            for (Road r:s.getRoads()) {
                if (r.getDestination().getId()==e.getId()) {
                    value = r.getLength();
                    value += getPathLength(i+1);
                    break;
                }
            }
        }
        return value;
    }
    private Integer pathLength = null;
    
    public int getPathTime() {
        int value = 0;
        if (pathTime!=null) {
            value = pathTime;
        } else {
            if (getPath()!=null)  {
                value = getPathTime(0);
            }
        }
        return value;
    }
    protected int getPathTime(int i) {
        int value = 0;
        if ((i+1)<getPath().visited.size()) {
            City s = cities.get(getPath().visited.get(i));
            City e = cities.get(getPath().visited.get(i+1));
            for (Road r:s.getRoads()) {
                if (r.getDestination().getId()==e.getId()) {
                    value = r.getTime();
                    value += getPathLength(i+1);
                    break;
                }
            }
        }
        return value;
    }
    private Integer pathTime = null;
    
    public String getDuration() {
        String value = "";
        long val = Math.abs(getFinish()-getStart());
        
        value = getStringFromTimeLong(val);
        
        return value;
    }
    public static String getStringFromTimeLong(long val) {
        String value = "";
        
        Duration dur = Duration.ofNanos(val);
        long seconds = dur.toMillis()/1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds -= (minutes*hours*60);
        minutes -= (minutes*60);
        value = String.format("%02d:%02d:%02d",hours,minutes,seconds);
        
        return value;
    }
    
    public PathType getPathType() {
        return pathType;
    }
    protected void setPathType(PathType value) {
        pathType=value;
    }
    private PathType pathType=PathType.None;
    
    public long getStart() {
        return start;
    }
    protected void setStart(long value) {
        start=value;
    }
    private long start;
    
    public long getFinish() {
        return finish;
    }
    protected void setFinish(long value) {
        finish=value;
    }
    private long finish;
    
    public City getStartCity() {
        return startCity;
    }
    private City startCity;
    
    public City getEndCity() {
        return endCity;
    }
    private City endCity;
    
    protected Paths(PathSet value) {
        if (value!=null) {
            setPathSet(value);
            this.startCity=getPathSet().getStartCity();
            this.endCity=getPathSet().getEndCity();
            setPathType(getPathSet().getPathType());
            initCities(getStartCity());
            initCities(getEndCity());
        }
    }
    protected Paths(City start, City end, PathType pathType, PathMethod pathMethod) {
        setPathSet(new PathSet(pathMethod,pathType,start,end));
        this.startCity=start;
        this.endCity=end;
        setPathType(pathType);
        initCities(getStartCity());
        initCities(getEndCity());
    }
    private void initCities(City city) {
        if (city!=null) {
            if (!cities.containsKey(city.getId())) {
                cities.put(city.getId(), city);
                for (Road r:city.getRoads()) {
                    initCities(r.getDestination());
                }
            }
        }
    }
    
    public PathData connect() {
        setPathData(run());
        if (getPathData()!=null&&getPathData().path!=null) {
            getPathData().path.length=getPathLength();
            getPathData().path.time=getPathTime();
        }
        
        return getPathData();
    }
    protected abstract PathData run();
}
