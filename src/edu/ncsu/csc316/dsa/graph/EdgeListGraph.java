package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An EdgeListGraph is an implementation of the {@link Graph} abstract data type.
 * EdgeListGraph maintains a list of vertices in the graph and a list of edges in the
 * graph.
 * 
 * The EdgeListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {
	/** A list vertices connected to the edge list graph */
    private PositionalList<Vertex<V>> vertexList;
    /** A list of edges connected to the edge list graph */
    private PositionalList<Edge<E>> edgeList;

    /**
     * Creates a new undirected edge list graph
     */
    public EdgeListGraph() {
        this(false);
    }

    /**
     * Creates a new edge list graph
     * @param directed if true, the graph is directed; if false, the graph is undirected
     */
    public EdgeListGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    /**
     * Returns the number of vertices within the vertex list
     * @return Integer the number of vertices within the vertex list
     */
    @Override
    public int numVertices() {
        return vertexList.size();
    }
    
    /**
     * Returns an iterable set of vertices aka the vertex list
     * @return Iterable an iterable set of vertices (the vertex list)
     */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }
    
    /**
     * Returns the number of edges within the edge list
     * @return Integer the number of edges within the edge list
     */
    @Override
    public int numEdges() {
        return edgeList.size();
    }
    
    /**
     * Returns an iterable set of edges aka the edge list
     * @return Iterable an iterable set of edges aka the edge list
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }
    
    /**
     * Helps determine if a passed vertex is a valid GraphVertex and can be type casted as a GraphVertex
     * @param v the vertex that will be examined to determine if it can be casted as a GraphVertex
     * @throws IllegalArgumentException if the passed vertex v is not an instance of the AbstractGraph.GraphVertex
     * @return GraphVertex a valid GraphVertex (if the passed vertex was able to be type casted
     */
    private GraphVertex validate(Vertex<V> v) {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
        }
        return (GraphVertex) v;
    }
    
    /**
     * Gets an edge with a passed vertex and vertex one and returns the edge
     * if it exists, otherwise this method will return null
     * @param vertex1 the first passed vertex
     * @param vertex2 the second passed vertex
     * @return Edge the edge that was found when using the two vertices, if an edge does not exist this method will return null
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        GraphVertex v1 = validate(vertex1);
        GraphVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == vertex1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }
    
    /**
     * Returns the number of outgoing edges connected a vertex
     * @param vertex the passed vertex that will be examined to determine it's number of outgoing edges
     * @return Integer the number of outgoing edges connected to the passed vertex
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }
    
    /**
     * Returns a list of edges containing all outgoing edges connected to a passed vertex param
     * @param vertex the passed vertex that will be used to determine it's outgoing edges and add to a list of edges 
     * @return list a list of outgoing edges connected the passed param vertex
     */
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[0] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[1] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }
    
    /**
     * Returns a list of edges containing all incoming edges connected a passed vertex param
     * @param vertex the passed vertex that will be used to determine it's incoming edges and it's incoming edges will be added to a list of edges
     * @return list a list of incoming edges connected a passed vertex param
     */
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[1] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[0] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }
    
    /**
     * Returns the number of incoming edges connected to a passed vertex param
     * @param vertex the vertex that it's number of incoming edges will be determined and returned
     * @return Integer the number of incoming edges connected a vertex
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }
    
    /**
     * Returns an iterable set of outgoing edges connected to a passed vertex param
     * @param vertex the vertex that will be examined to determine it's outgoing edges and it will then return an iterable set of it's edges
     * @return Iterable an iterable set of outgoing edges which were connected to passed param vertex 
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }
    
    /**
     * Returns an iterable set of incoming edges connected to a passed vertex param
     * @param vertex the vertex that will be examined to determine it's incoming edges and it willl then return an iterable set of it's edges
     * @return Iterable an iterable set of incoming edges connected to a passed param vertex
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }
    
    /**
     * Uses a passed vertex value V to construct a new vertex and add to vertex list
     * @param vertexData the value V that will be used to construct a new vertex
     * @return Vertex the newly constructed vertex
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        GraphVertex v = new GraphVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        return v;
    }
    
    /**
     * Uses passed vertices, and edge data E to construct a new edge and then add it to the edge list
     * @param vertex1 the first vertex that will be used to construct an edge
     * @param vertex2 the second vertex that will be used to construct and edge
     * @param edgeData the edge data that will be used to construct a new edge
     * @return Edge the newly constructed edge
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
        GraphVertex origin = validate(vertex1);
        GraphVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        return e;
    }
    
    /**
     * Removes a passed vertex from vertex list and all connected outgoing 
     * and incoming edges connected to the end points (of the passed vertex)
     * @param vertex the passed vertex that will removed (if it exists, and including it's outgoing and incoming edges)
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        for (Edge<E> e : outgoingEdges(v)) {
            removeEdge(e);
        }
        for (Edge<E> e : incomingEdges(v)) {
            removeEdge(e);
        }
        vertexList.remove(v.getPosition());
    }
    
    /**
     * Remove a passed edge from edge list
     * @param edge the edge that will be removed from the edge list (if it exists)
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        edgeList.remove(e.getPosition());
    }
}