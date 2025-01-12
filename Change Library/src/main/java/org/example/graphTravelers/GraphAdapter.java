package org.example.graphTravelers;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Collection;

public class GraphAdapter implements Graph<Integer, String> {
    private final org.jgrapht.Graph<Integer, DefaultEdge> adaptee;

    public GraphAdapter() {
        this.adaptee = new SimpleGraph<>(DefaultEdge.class);
    }

    @Override
    public void addVertex(Integer vertex) {
        adaptee.addVertex(vertex);
    }

    @Override
    public void addEdge(String edge, Integer v1, Integer v2) {
       adaptee.addEdge(v1, v2);
    }

    @Override
    public Collection<Integer> getNeighbors(Integer vertex) {
        return Graphs.neighborListOf(adaptee, vertex);
    }

    public org.jgrapht.Graph<Integer, DefaultEdge> getAdaptee() {
        return adaptee;
    }
}
