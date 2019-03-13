/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concretes.Paths;

/**
 *
 * @author IsaacCD
 */
public class Edge {

    public String getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public int getLength() {
        return length;
    }

    public int getTime() {
        return time;
    }

    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int length;
    private final int time;

    public Edge(String id, Vertex source, Vertex destination, int length, int time) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.length = length;
        this.time = time;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean reverse) {
        String value;
        if (reverse) {
            value = destination + " - " + source;
        } else {
            value = source + " - " + destination;
        }

        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Edge other = (Edge) obj;
        if (this.toString() == null) {
            if (other.toString() != null) {
                return false;
            }
        } else if (!this.toString().equals(other.toString())) {
            return false;
        }

        return true;
    }

}
