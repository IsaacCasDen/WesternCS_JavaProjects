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
public class Dijkstra extends Paths {

    private final List<Vertex> nodes = new ArrayList();
    private final List<Edge> edges = new ArrayList();
    private Set<Vertex> settledNodes;
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public Dijkstra(PathSet pathSet) {
        super(pathSet);
    }

    public Dijkstra(City start, City end, PathType pathType) {
        super(start, end, pathType, PathMethod.Dijkstras);
    }

    @Override
    protected PathData run() {
        PathData value = null;
        int dist = 0;
        LinkedList<Vertex> vals;
        if (getStartCity() != null && getEndCity() != null) {
            setStart(System.nanoTime());
            Vertex vStart = new Vertex(String.valueOf(getStartCity().getId()), getStartCity().getName());
            Vertex vEnd = new Vertex(String.valueOf(getEndCity().getId()), getEndCity().getName());
            buildGraph(null, null, getStartCity());
            execute(vStart);
            vals = getPath(vEnd);
            dist = getShortestDistance(vEnd);
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
            for (Edge e: edges) {
                if (e.equals(edge)) {
                    include=false;
                    break;
                }
            }
            if (include) {
                edges.add(edge);
                System.out.println(edge);
            }
        }
        if (!nodes.contains(v)) {
            nodes.add(v);
            for (Road r : current.getRoads()) {
                buildGraph(v, r, r.getDestination());
            }
        }
    }

    private void execute(Vertex start) {
        settledNodes = new HashSet<Vertex>();
        unsettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(start, 0);
        unsettledNodes.add(start);
        while (unsettledNodes.size() > 0) {
            Vertex node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target)
                    > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target,
                        getShortestDistance(node)
                        + getDistance(node, target)
                );
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }
    }

    private int getDistance(Vertex node, Vertex target) {
        int value = Integer.MAX_VALUE;
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                switch (getPathType()) {
                    case ShortestLength:
                        value = edge.getLength();
                        break;
                    case ShortestTime:
                        value = edge.getTime();
                        break;
                }
                return value;
            }
        }
        throw new RuntimeException("Missing Edge Weight");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
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
