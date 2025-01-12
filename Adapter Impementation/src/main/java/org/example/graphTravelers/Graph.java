package main.java.org.example.graphTravelers;

import java.util.Collection;

public interface Graph<V, E> {
    void addVertex(V vertex);
    void addEdge(E edge, V v1, V v2);
    Collection<V> getNeighbors(V vertex);
}