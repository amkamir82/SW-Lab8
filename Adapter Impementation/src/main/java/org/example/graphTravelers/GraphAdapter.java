package main.java.org.example.graphTravelers;

import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.Collection;

public class GraphAdapter implements Graph<Integer, String> {
    private final SparseMultigraph<Integer, String> adaptee;

    public GraphAdapter() {
        this.adaptee = new SparseMultigraph<>();
    }

    @Override
    public void addVertex(Integer vertex) {
        adaptee.addVertex(vertex);
    }

    @Override
    public void addEdge(String edge, Integer v1, Integer v2) {
        adaptee.addEdge(edge, v1, v2);
    }

    @Override
    public Collection<Integer> getNeighbors(Integer vertex) {
        return adaptee.getNeighbors(vertex);
    }

    // Expose the internal SparseMultigraph if needed
    public SparseMultigraph<Integer, String> getAdaptee() {
        return adaptee;
    }
}