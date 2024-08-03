package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMatrixGraph<V, E> extends AbstractGraph<V, E> {
	/** A matrix that holds all graph edges for adjacency matrix graph */
    private GraphEdge[][] matrix;
    /** A positional list that holds all vertices for adjacency matrix graph */
    private PositionalList<Vertex<V>> vertexList;
    /** A positional list that holds all edges for adjacency matrix graph */
    private PositionalList<Edge<E>> edgeList;
    
    /** The index of a vertex which will be necessary for this classes' method implementations */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    /**
     * Returns the number of edges that are within the vertex list
     * @return Integer the number of edges within the vertex list
     */
    @Override
    public int numVertices() {
        return vertexList.size();
    }
    
    /**
     * Returns an iterable list of vertices aka the vertex list
     * @return vertexList an iterable list of vertices
     */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }
    
    /**
     * Returns the number of edges that are within the edge list
     * @return Integer the number of edges that are within the edge list
     */
    @Override
    public int numEdges() {
        return edgeList.size();
    }
    
    /**
     * Returns an iterable list set of edges aka the edge list
     * @return edgeList an iterable list of edges
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }
    
    /**
     * Gets an edge with two passed vertices
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return Edge the edge that was found, if it was not able to be found this method will return null
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        MatrixVertex v1 = validate(vertex1);
        MatrixVertex v2 = validate(vertex2);
        return matrix[v1.getIndex()][v2.getIndex()];
    }
    
    /**
     * Returns the end point vertices connected a passed edge parameter
     * @param edge the edge that will determine it's end point vertices
     * @return Vertex an array containing the two end point vertices connected to a passed edge if it exists
     */
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        GraphEdge e = validate(edge);
        return e.getEndpoints();
    }
    
    /**
     * Returns the incident edge of a passed edge by comparing against the passed vertex param
     * @param vertex the passed vertex that will be compared against end point vertices to determine if an end point vertex is an incident edge
     * @param edge the edge which it's end points will be examined
     * @throws IllegalArgumentException if an incident edge cannot be found
     * @return Vertex the vertex that is an incident edge (an end point vertex if it was able to be found)
     */
    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        GraphVertex v = validate(vertex);
        Vertex<V>[] ends = temp.getEndpoints();
        if (ends[0] == v) {
            return ends[1];
        }
        if (ends[1] == v) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * Returns the number of outgoing edges connected a passed vertex parameter
     * @param vertex the vertex which it's number of outgoing edges will be returned
     * @return Integer the number of outgoing edges connected a passed vertex param
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }
    
    /**
     * Returns a list of outgoing edges which are connected to the passed vertex param
     * @param vertex the vertex which it's outgoing edges will be added to a list of edges (outgoing edges)
     * @return list a list of outgoing edges connected a passed vertex param
     */
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (GraphEdge e : matrix[v.getIndex()]) {
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }
    
    /**
     * Returns a list of incoming edges connected to a passed vertex param
     * @param vertex the vertex which it's incoming edges will be added to a list of edges and returned
     * @return list a list of incoming edges connected to the vertex param
     */
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }
    
    /**
     * Returns the number of incoming edges connected to a vertex param
     * @param vertex the vertex which it's number of incoming edges will be returned
     * @return Integer the number of incoming edges connected to a vertex param
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }
    
    /**
     * Returns an iterable list of outgoing edges connected to a vertex param
     * @param vertex the vertex which a list (iterable) of it's outgoing edges will be returned
     * @return Iterable an iterable collection of outgoing edges connected to a passed vertex param
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }
    
    /**
     * Returns an iterable list of incoming edges connected to a vertex param
     * @param vertex the vertex which a list (iterable) of it's outgoing edges will be returned
     * @return Iterable an iterable collection of incoming edges connected to the passed vertex param
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }
    
    /**
     * Constructs a new vertex with passed vertex data V and adds vertex to vertex list
     * @param vertexData the vertex data that will be used to construct a vertex
     * @return Vertex the newly constructed vertex which was added to the vertex list
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        MatrixVertex v = new MatrixVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        growArray();
        return v;
    }
    
    /**
     * Grows array size when adding an elements goes past the current size of the array
     */
    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }
    
    /**
     * Constructs a new edge with passed vertices and edge data E and adds edge to edge list
     * @param vertex1 the first vertex used to construct an edge
     * @param vertex2 the second vertex used to construct an edge
     * @param edgeData the edge data used to construct an edge
     * @return Edge the newly constructed edge that was added to edge list
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
        MatrixVertex origin = validate(vertex1);
        MatrixVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        matrix[origin.getIndex()][destination.getIndex()] = e;
        if (!isDirected()) {
            matrix[destination.getIndex()][origin.getIndex()] = e;
        }
        return e;
    }
    
    /**
     * Removes a vertex from vertex list and remove's it's outgoing and incoming edges connected to it's end point vertices
     * All credit for this method goes to Griffin Bish NCSU 316 Student section 051 Summer 2022 Semester
     * @param vertex the passed vertex (if it exists) who will be removed from vertex list including it's outgoing and incoming edges connected to it's end point vertices
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        MatrixVertex x = validate(vertex);
        int vIndex = x.getIndex();
        
        for(int i = 0; i < matrix[vIndex].length; i++) {
        	if(matrix[vIndex][i] != null) {
        		removeEdge(matrix[vIndex][i]);
        	}
        	
        	if(matrix[i][vIndex] != null) {
        		removeEdge(matrix[i][vIndex]);
        	}
        }
        
        // Remove vertex from list
        vertexList.remove(v.getPosition());
    }
    
    /**
     * Removes an edge from edge list and updates graph edge matrix to reflect the removal of the edge removal
     * @param edge the edge that will be removed and used to determine it's end point vertices and used to update matrix
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        
        // Remove edge record from edge list
        edgeList.remove(e.getPosition());
        
        // Get integer representations of the endpoint vertices
        Vertex<V>[] ends = e.getEndpoints();
        MatrixVertex origin = validate(ends[0]);
        MatrixVertex dest = validate(ends[1]);
        int originIndex = origin.getIndex();
        int destinationIndex = dest.getIndex();
        // remove the edge record from appropriate cell in matrix, might have to adjust this
        matrix[originIndex][destinationIndex] = null;
        
        if(!isDirected()) {
        	matrix[destinationIndex][originIndex] = null;
        }
    }

    /**
     * Safely casts a Vertex to a graph vertex
     * @param v a vertex
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
    
    /**
     * Gets the vertex index
     * @return Integer the vertex index
     */
    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         * isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
}

