package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Graph abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the graph abstract data type.
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in vertices in the graph
 * @param <E> the type of data in edges in the graph
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {

    /**
     * If true, the graph is directed; if false, the graph is undirected
     */
    private boolean isDirected;

    /**
     * Constructs a new AbstractGraph
     * 
     * @param directed if true, the graph represents a directed graph; if false, the
     *                 graph represents an undirected graph
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    /**
     * Sets a AdjacencyListGraph as a directedGraph (or Map or Matrix) by passing a boolean param directed
     * indicating that a graph is directed or not directed (true = directed, false = not directed)
     * @param directed a boolean that will be true or false based on whether a graph, matrix, or map is directed
     */
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * Gets directed status of Adjacency map, matrix, or list
     * @return directed True if a map, matrix, or list is directed, false otherwise
     */
    @Override
    public boolean isDirected() {
        return isDirected;
    }
    
    /**
     * Returns end point vertices of a edge and checks that edge is a valid edge by passing to validate helper method
     * @return Vertex a vertex array containing the two end points of an edge
     */
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }
    
    /**
     * Checks if a passed vertex is an incident edge and returns the end point
     * that is the incident edge
     * @param vertex the passed vertex that will be compared to end point vertices to determine which one is an incident edge if there is one at all
     * @param edge that edge that it's end points will be examined to determine if one it's end points (is an incident edge) and returns the endpoint
     * @throws IllegalArgumentException if vertex does not have an incident with passed edge
     * @return Vertex the end point that makes an incident edge (is an incident edge)
     */
    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if (ends[0] == vertex) {
            return ends[1];
        }
        if (ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }

    /**
     * GraphVertex implements the Graph.Vertex abstract data type for graphs
     * 
     * @author Dr. King
     *
     */
    protected class GraphVertex implements Vertex<V> {
    	/** A graph vertex element */
        private V element;
        /** A graph vertex position */
        private Position<Vertex<V>> position;

        /**
         * Create a new graph vertex with the given element
         * 
         * @param element the element to store in the vertex
         */
        public GraphVertex(V element) {
            setElement(element);
        }

        /**
         * Sets the element within the given vertex
         * 
         * @param element the element to store in the vertex
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         * Gets element from GraphVertex and returns in
         * @return element the element connected to the graph vertex
         */
        @Override
        public V getElement() {
            return element;
        }

        /**
         * Return the position of the vertex within the Graph's list of vertices
         * 
         * @return the position of the vertex within the Graph's list of vertices
         */
        public Position<Vertex<V>> getPosition() {
            return position;
        }

        /**
         * Sets the position of the vertex within the Graph's list of vertices
         * 
         * @param pos the position of the vertex within the Graph's list of vertices
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }

    /**
     * GraphEdge implements the Graph.Edge abstract data type for graphs
     * 
     * @author Dr. King
     *
     */
    protected class GraphEdge implements Edge<E> {
    	/** The element connected to a graph edge */
        private E element;
        /**  The end points connected to a graph edge */
        private Vertex<V>[] endpoints;
        /** The position of the graph edge */
        private Position<Edge<E>> position;

        /**
         * Create a new graph edge with the given element
         * 
         * @param element the element to store in the edge
         * @param v1      an endpoint vertex of the new edge
         * @param v2      an endpoint vertex of the new edge
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[]) new Vertex[] { v1, v2 };
        }

        /**
         * Sets the element stored in the edge
         * 
         * @param element the element to store in the edge
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Gets element connected to graph edge and returns it (an edge)
         * @return element the graph edge's element (the edge)
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * Returns an array representing the two endpoint vertices of the edge
         * 
         * @return an array representing the two endpoint vertices of the edge
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        /**
         * Returns the position of the edge in the Graph's list of edges
         * 
         * @return the position of the edge in the Graph's list of edges
         */
        public Position<Edge<E>> getPosition() {
            return position;
        }

        /**
         * Sets the position of the edge in the Graph's list of edges
         * 
         * @param pos the position of the edge in the Graph's list of edges
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        /**
         * Returns graph edge as a String
         * @return String the graph edge as a String
         */
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }

    /**
     * Safely casts an Edge into a GraphEdge
     * 
     * @param e the edge to cast into a GraphEdge
     * @return a GraphEdge representation of the given edge
     * @throws IllegalArgumentException if the Edge is not a valid GraphEdge
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}

