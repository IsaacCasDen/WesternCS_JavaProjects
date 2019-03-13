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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * @author IsaacCD
 */
public class BranchRecursion extends Paths {
    

    public BranchRecursion(PathSet pathSet) {
        super(pathSet);
    }
    public BranchRecursion(City start, City end, PathType pathType) {
        super(start,end,pathType,PathMethod.BranchRecursion);
    }
    @Override
    protected PathData run() {
        PathData value = null;
        Path.attempts=0;
        setStart(System.nanoTime());
        value = begin(new ArrayList<Integer>(), getStartCity(), getEndCity(), getPathType());
        setFinish(System.nanoTime());
        value.path.timeTaken-=getStart();
        
        return value;
    }
    private PathData begin(ArrayList<Integer> visited, City current, City end, PathType pathType) {
        Path.attempts+=1;
        PathData value = new PathData();
        value.path=new Path();
        visited.add(current.getId());
        if (current.getId()==end.getId()) {
            value.path.isValid = true;
            value.path.timeTaken=System.nanoTime();
            value.path.visited=visited;
        } else {
            ArrayList<PathData> paths = new ArrayList<PathData>();
            for (Road road:current.getRoads()) {
                if (!visited.contains(road.getDestination().getId())) {
                    PathData p = begin((ArrayList<Integer>) visited.clone(),road.getDestination(),end,pathType);
                    if (p.path.isValid) {
                        p.path.length+=road.getLength();
                        p.path.time+=road.getTime();
                        paths.add(p);
                    }
                }
            }
            if (paths.size()>0) {
                Integer key=0;
                Integer length=0;
                Integer time=0;
                Path val=null;
                switch (pathType) {
                    case ShortestTime:
                        val=paths.get(0).path;
                        key=val.time;
                        length+=val.length;
                        time+=val.time;

                        for (int i = 1; i<paths.size();i++) {
                            if (paths.get(i).path.time<key) {
                                val=paths.get(i).path;
                                key=val.time;
                                length+=val.length;
                                time+=val.time;
                            } else {
                                value.otherPaths.add(paths.get(i));
                            }
                        }
                        break;
                    case ShortestLength:
                        val=paths.get(0).path;
                        key=val.length;
                        length+=val.length;
                        time+=val.time;

                        for (int i = 1; i<paths.size();i++) {
                            if (paths.get(i).path.length<key) {
                                val=paths.get(i).path;
                                key=val.length;
                                length+=val.length;
                                time+=val.time;
                            } else {
                                value.otherPaths.add(paths.get(i));
                            }
                        }
                        break;
                }
                if (val!=null) {
                    value.path.visited=val.visited;
                    value.path.timeTaken=val.timeTaken;
                    value.path.isValid=val.isValid;
                    value.path.length+=val.length;
                    value.path.time+=val.time;
                }                
            }
        }
        return value;
    }
}
