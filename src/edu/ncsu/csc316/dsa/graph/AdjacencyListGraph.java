package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyListGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyListGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyListGraph vertices each maintain
 * lists of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {
	/** The adjacency list graph vertex list */
    private PositionalList<Vertex<V>> vertexList;
    /** The adjacency list edge list */
    private PositionalList<Edge<E>> edgeList;

    /**
     * Creates a new undirected adjacency list graph
     */    
    public AdjacencyListGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency list graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */    
    public AdjacencyListGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    /**
     * Returns the number of vertices in vertex list
     * @return Integer the number of vertices in vertex list
     */
    @Override
    public int numVertices() {
        return vertexList.size();
    }
    
    /**
     * Returns an iterable set of vertices from the vertex list (aka the vertices within vertex list)
     * @return vertexList the list that holds the adjacency list vertices
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
     * Returns an iterable set of edges from the edge list
     * @return edgeList an iterable set of edges
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }
    
    /**
     * Returns an iterable set of outgoing edges connected to a vertex
     * @return Iterable an iterable set of outgoing edges
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    /**
     * Returns an iterable set of incoming edges connected to a vertex
     * @return Iterable an iterable set of incoming edges
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }
    
    /**
     * Gets the edge connected to two passed vertices
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return Edge an edge connected to the two vertices, otherwise if an edge cannot be found this method will return null
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }
    
    /**
     * Returns the number of outgoing edges connected to a vertex
     * @param vertex the passed vertex which will be examined to determine it's number of outgoing edges
     * @return Integer the number of outgoing edges connected to a vertex
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }
    
    /**
     * Returns the number of incoming edges connected to a vertex
     * @param vertex the passed vertex that will be examined to determine it's number of incoming edges
     * @return Integer the number of outgoing edges connected to a passed vertex
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }
    
    /**
     * Inserts a value V to a vertex, and adds the newly constructed vertex to the end of the vertex list
     * @param vertexData the value V that will be used to construct a new vertex which will then be added to the end of the vertex list
     * @return Vertex the newly created vertex
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }
    
    /**
     * Inserts a value E and creates a new edge with passed edge data E and two vertices and constructs an edge that will then
     * be added to edge list
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param edgeData the data that will be connected (aka the element) to the newly constructed edge
     * @return Edge the newly constructed edge
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
    	if(getEdge(vertex1, vertex2) == null) {
            ALVertex origin = validate(vertex1);
            ALVertex destination = validate(vertex2);
            ALEdge edge = new ALEdge(edgeData, origin, destination);
            Position<Edge<E>> pos = edgeList.addLast(edge);
            edge.setPosition(pos);
            // remember to add edge record to appropriate lists (outgoing and incoming) MIGHT HAVE TO ADJUST THIS
            // these add lines might not be necessary
           // origin.getOutgoing().addLast(edge);
            //destination.getIncoming().addLast(edge);
            
            // this might need to be modified but add edge record to lists for
            // endpoint vertices origin and dest and set outgoing position and incoming position for edge
            edge.setOutgoingListPosition(origin.getOutgoing().addLast(edge));
            edge.setIncomingListPosition(destination.getIncoming().addLast(edge));
            return edge;
    	}
        // Remember to set the edge's positions in the outgoingEdges 
        //    and incomingEdges lists for the appropriate vertices
        return null;
    }
    
    /**
     * Removes a vertex and all of it's outgoing and incoming edges from edge list and removes vertex from vertex list
     * @param vertex the vertex the vertex which all of it's edges (outgoing and incoming) will be removed and then it will be removed from the vertex list
     * at the end as will
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
        
        for(Edge<E> e: v.getOutgoing()) {
        	removeEdge(e);
        }
        
        for(Edge<E> e: v.getIncoming()) {
        	removeEdge(e);
        }
        
        vertexList.remove(v.getPosition());
    }
    
    /**
     * Removes an edge from edge list and removes both outgoing and incoming edges as well
     * @param edge the edge that will be removed (including it's endpoint incoming and outgoing edges)
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        ALEdge e = validate(edge);
        // remove edge record from edge list
        edgeList.remove(e.getPosition());
        
        // Next, remove the edge record from the appropriate lists for the two input vertices
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        // This might need to be modified, removes the edge record from the endpoint
        // vertices = origin and dest, uses the stored outgoing and incoming list position
        origin.getOutgoing().remove(e.getOutgoingListPosition());
        dest.getIncoming().remove(e.getIncomingListPosition());
    }

    /**
     * Represents a vertex in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALVertex extends GraphVertex {

        /** A positional list of outgoing edges */
        private PositionalList<Edge<E>> outgoing;

        /** A positional list of incoming edges */
        private PositionalList<Edge<E>> incoming;

        /**
         * Creates a new adjacency list vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if (isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a positional list of outgoingEdges from the vertex. For an undirected
         * graph, returns the same as getIncoming()
         * 
         * @return a positional list of outgoing edges from the vertex
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns a positional list of incomingEdges to the vertex. For an undirected
         * graph, returns the same as getOutgoing()
         * 
         * @return a positional list of incoming edges to the vertex
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Represents an edge in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALEdge extends GraphEdge {

        /** The position of the edge in a vertex's outgoing edge list */
        private Position<Edge<E>> outgoingListPosition;

        /** The position of the edge in a vertex's incoming edge list */
        private Position<Edge<E>> incomingListPosition;

        /**
         * Creates a new adjacency list edge
         * 
         * @param element the data to store in the edge
         * @param v1      an endpoint vertex
         * @param v2      an endpoint vertex
         */
        public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            super(element, v1, v2);
        }

        /**
         * Returns the position of the edge in the associated vertex's outgoing edge
         * list
         * 
         * @return the position of the edge in the associated vertex's outgoing edge
         *         list
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's outgoing edge list
         * 
         * @param outgoingListPosition the position of the edge in the associated
         *                             vertex's outgoing edge list
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }

        /**
         * Returns the position of the edge in the associated vertex's incoming edge
         * list
         * 
         * @return the position of the edge in the associated vertex's incoming edge
         *         list
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's incoming edge list
         * 
         * @param incomingListPosition the position of the edge in the associated
         *                             vertex's incoming edge list
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    /**
     * Safely casts a Vertex to an adjacency list vertex
     * @param v a vertex
     * @return an adjacency list vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency list
     *                                  vertex
     */
    private ALVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex) v;
    }

    /**
     * Safely casts an Edge to an adjacency list edge
     * @param e an edge
     * @return an adjacency list edge representation of the given Edge
     * @throws IllegalArgumentException if the edge is not a valid adjacency list
     *                                  edge
     */
    protected ALEdge validate(Edge<E> e) {
        if (!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge) e;
    }
}
