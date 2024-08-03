package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {
	/** A positional list of vertices connected to the adjacency map graph */
    private PositionalList<Vertex<V>> vertexList;
    /** A positional list of edges connected to the adjacency map graph */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */    
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    /**
     * Returns the number of vertices connected the vertex list
     * @return Integer the number of vertices in the vertex list
     */
    @Override
    public int numVertices() {
        return vertexList.size();
    }
    
    /**
     * Returns a list of iterable vertices
     * @return vertexList an iterable list of vertices
     */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }
    
    /**
     * Returns the number of edges in the edge list
     * @return Integer the number of edges in the edge list
     */
    @Override
    public int numEdges() {
        return edgeList.size();
    }
    
    /**
     * Returns a list of iterable of edges
     * @return edgeList a list of iterable edges
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }
    
    /**
     * Gets a edge (if it exists) by using passed vertices to find an edge
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return Edge the edge that was found or if it wasn't this method will return null
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }
    
    /**
     * Returns the number of outgoing edges connected to a passed vertex param
     * @param vertex the passed vertex that will be examined to find it's number of outgoing edges
     * @return Integer the number of outgoing edges connected to a passed vertex param
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }
    
    /**
     * Returns the number of incoming edges connected to a passed vertex param
     * @param vertex the vertex that will be examined to determine it's number of incoming edges
     * @return Integer the number of incoming edges connected to a passed vertex param
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }
    
    /**
     * Returns an iterable list of outgoing edges connected to a passed vertex param
     * @param vertex the passed vertex that will be used to determine it's outgoing edges and then it will be returned as an iterable list of edges
     * @return Iterable an iterable list of edges connected to a passed vertex param
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }
    
    /**
     * Returns an iterable list of incoming edges connected to a passed vertex param
     * @param vertex the passed vertex that will be used to determine it's incoming edges and then it will be returned as an iterable list of edges
     * @return Iterable an iterable list of edges connected to a passed vertex param
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    /**
     * Creates a new vertex with passed vertex data V and constructs a new vertex and adds vertex to vertex list
     * @param vertexData the vertex data v that will be used to construct a new vertex
     * @return Vertex the newly constructed vertex that was added to vertex list
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }
    
    /**
     * Constructs a new edge with passed vertices and edge data E and adds edge to edge list
     * @param v1 the first vertex that will be used to construct the edge
     * @param v2 the second vertex that will be used to construct the edge
     * @param edgeData the edge data E that will be used to construct the new edge
     * @return Edge the newly constructed edge
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
            AMVertex origin = validate(v1);
            AMVertex destination = validate(v2);
            GraphEdge edge = new GraphEdge(edgeData, origin, destination);
            
            // Add the new edge to the edgeList
            Position<Edge<E>> pos = edgeList.addLast(edge);
            edge.setPosition(pos);
     
            // Add the edge into the hashmaps at each vertex
            origin.getOutgoing().put(v2, edge);
            destination.getIncoming().put(v1, edge);
            
            return edge;
            // Remember to add the edge to the maps for each endpoint vertex
    }
    
    /**
     * Removes a vertex param from the vertex list and remove all of it's outgoing and incoming edges connected to it's end points
     * @param vertex the vertex that will be removed along with it's outgoing and incoming edges connected to it's end points
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        
        for(Edge<E> e: v.getOutgoing().values()) {
        	removeEdge(e);
        }
        
        for(Edge<E> e: v.getIncoming().values()) {
        	removeEdge(e);
        }
        
        vertexList.remove(v.getPosition());
    }
    
    /**
     * Removes an edge from edge list and remove it's outgoing and incoming edges as well
     * @param edge the edge that will be removed
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        
        // Remove the location aware edge
        edgeList.remove(e.getPosition());

        // Remove the edge from each endpoint's hashmaps
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);

        // this might have to be adjusted
        origin.getOutgoing().remove(dest);
        dest.getIncoming().remove(origin);
        
    }
    
    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class AMVertex extends GraphVertex {

        /**
         * A map of outgoing edges opposite vertex, edge to reach opposite vertex>
         */
        private Map<Vertex<V>, Edge<E>> outgoing;

        /**
         * A map of incoming edges opposite vertex, edge to reach opposite vertex>
         */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Safely casts a Vertex to an adjacency map vertex
     * @param v a vertex
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     *                                  vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
}
