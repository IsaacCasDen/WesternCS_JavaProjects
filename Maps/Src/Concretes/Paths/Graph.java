/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Paths;

import java.util.List;

/**
 *
 * @author IsaacCD
 */
public class Graph {
    
    public List<Vertex> getVertexes() {
        return vertexes;
    }
    public List<Edge> getEdges() {
        return edges;
    }
    
    private final List<Vertex> vertexes;
    private final List<Edge> edges;
    
    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes=vertexes;
        this.edges=edges;
    }
}
