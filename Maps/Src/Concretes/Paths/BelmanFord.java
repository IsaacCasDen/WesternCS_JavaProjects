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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author IsaacCD
 */
public class BelmanFord extends Paths {

    private final List<Vertex> vertices = new ArrayList();
    private final List<Edge> edges = new ArrayList();
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public BelmanFord(PathSet pathSet) {
        super(pathSet);
    }

    public BelmanFord(City start, City end, PathType pathType) {
        super(start, end, pathType, PathMethod.BelmanFord);
    }

    @Override
    protected PathData run() {
        PathData value = null;
        int dist = 0;
        LinkedList<Vertex> vals;
        if (getStartCity() != null && getEndCity() != null) {
            Vertex vStart = new Vertex(String.valueOf(getStartCity().getId()), getStartCity().getName());
            Vertex vEnd = new Vertex(String.valueOf(getEndCity().getId()), getEndCity().getName());
            setStart(System.nanoTime());
            buildGraph(null, null, getStartCity());
            execute();
            vals = getPath(vEnd);
            dist = distance.get(vEnd);
            setFinish(System.nanoTime());
            if (vals != null) {
                value = new PathData();
                value.path = new Path();
                value.path.timeTaken = getFinish() - getStart();
                value.path.length = dist;
                for (Vertex v : vals) {
                    value.path.visited.add(Integer.parseInt(v.getId()));
                }
            }
        }
        return value;
    }

    private void buildGraph(Vertex source, Road road, City current) {
        Vertex v = new Vertex(String.valueOf(current.getId()), current.getName());
        if (source != null && road != null) {
            Edge edge = new Edge(road.getName(), source, v, road.getLength(), road.getTime());;

            boolean include = true;
            for (Edge e : edges) {
                if (e.equals(edge)) {
                    include = false;
                    break;
                }
            }
            if (include) {
                edges.add(edge);
                System.out.println(edge);
            }
        }
        if (!vertices.contains(v)) {
            vertices.add(v);
            for (Road r : current.getRoads()) {
                buildGraph(v, r, r.getDestination());
            }
        }
    }

    private void execute() {
        Vertex start = vertices.get(0);
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap();
        predecessors.put(start, null);
        for (Vertex v : vertices) {
            distance.put(v, null);
            predecessors.put(v, null);
        }
        distance.put(start, 0);

        for (int i = 0; i < vertices.size(); i++) {
            for (Edge e : edges) {
                Vertex source = e.getSource();
                Vertex dest = e.getDestination();

                if (distance.get(source) != null) {
                    Integer dist = distance.get(source) + e.getLength();
                    if (distance.get(dest) == null||dist+e.getLength()<distance.get(dest)) {
                        distance.put(dest, dist);
                        predecessors.put(dest, source);
                    }
                }
            }
        }
    }
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList();
        Vertex step = target;
        if (predecessors.get(step) == null) {
            return null;
        } else {
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            Collections.reverse(path);
            return path;
        }
    }
   
}
