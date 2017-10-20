package zcom.rahul.ranjan.groupon.three;

import java.io.*;
import java.util.*;
 
public class Graph {
 
    private Map<String, List<String>> adjacencyList;
 
    /**
     * Instatiates the 'adjacencyList' and then parse the data file.
     */
    public Graph(String fileName) throws FileNotFoundException {
        adjacencyList = new HashMap<String, List<String>>();
        parseDataFile(fileName);
    }
 
    /**
     * This is an undirected graph, so we connect 'vertexA' to 'vertexB' 
     * and the other way around.
     */
    public void addConnection(String vertexA, String vertexB) {
        connect(vertexA, vertexB);
        connect(vertexB, vertexA);
    }
 
    /**
     * A private helper-method to connect 'vertexA' to 'vertexB'.
     * If 'vertexA' alreay exists in our 'adjacencyList', get it's 
     * edges-list and add 'vertexB' to it. If it doesn't exist,  
     * create a new ArrayList, add 'vertexB' to it and put it all 
     * in our 'adjacencyList'.
     */
    private void connect(String vertexA, String vertexB) {
        List<String> edges;
        if(adjacencyList.containsKey(vertexA)) {
            edges = adjacencyList.get(vertexA);
            edges.add(vertexB);
        } else {
            edges = new ArrayList<String>();
            edges.add(vertexB);
            this.adjacencyList.put(vertexA, edges);
        }
    }
 
    /**
     * Returns true iff 'vertexA' poits to to 'vertexB'.
     * Note that since this is an undirected graph, we do not 
     * need to check the other way around, the case if 'vertexB' 
     * is points to 'vertexA'.
     */
    public boolean isConnectedTo(String vertexA, String vertexB) {
        List<String> edges = getEdges(vertexA);
        return edges.contains(vertexB);
    }
 
    /**
     * Returns all the edges of a certain vertex, or throws an 
     * exception if the vertex doesn't exist in this graph.
     */
    public List<String> getEdges(String vertex) {
        List<String> edges = adjacencyList.get(vertex);
        if(edges == null) {
            throw new RuntimeException(vertex+" not present in the graph.");
        }
        return edges;
    }
 
    /**
     * Reads a text file with the graph-data. The text file contains 
     * N-blocks of lines where each block starts with the movie followed
     * by N-lines of text representing the actors and ending with an 
     * empty line.
     */
    private void parseDataFile(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        while(file.hasNextLine()) {
            String movie = file.nextLine().trim();
            while(file.hasNextLine()) {
                String actor = file.nextLine().trim();
                if(actor.length() == 0) break;
                addConnection(movie, actor);
            }
        }
    }
 
    /**
     * A Sting representation if this Graph.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator<String> vertices = adjacencyList.keySet().iterator();
        while(vertices.hasNext()) {
            String vertex = vertices.next();
            List<String> edges = adjacencyList.get(vertex);
            builder.append(vertex);
            builder.append(" --> ");
            builder.append(edges);
            builder.append('\n');
        }
        return builder.toString();
    }
 
    /**
     * main
     */
    public static void main(String[] args) {
        try {
            Graph graph = new Graph("data.txt");
            System.out.println(graph);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
 