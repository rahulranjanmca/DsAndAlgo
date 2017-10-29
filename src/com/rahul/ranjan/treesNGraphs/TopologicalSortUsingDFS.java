package com.rahul.ranjan.treesNGraphs;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.rahul.ranjan.common.MyInputStreamReader;

public class TopologicalSortUsingDFS {

	private int noOfVertices; // No. of vertices
	private LinkedList<Integer> adjacencyList[]; // Adjacency List

	// Constructor
	@SuppressWarnings("unchecked")
	TopologicalSortUsingDFS(int noOfVertices) {
		this.noOfVertices = noOfVertices;
		adjacencyList = new LinkedList[noOfVertices];
		for (int i = 0; i < noOfVertices; ++i)
			adjacencyList[i] = new LinkedList<>();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adjacencyList[v].add(w);
	}

	// A recursive function used by topologicalSort
	void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
		// Mark the current node as visited.
		visited[v] = true;
		Integer i;

		// Recur for all the vertices adjacent to this
		// vertex
		Iterator<Integer> it = adjacencyList[v].iterator();
		while (it.hasNext()) {
			i = it.next();
			if (!visited[i])
				topologicalSortUtil(i, visited, stack);
		}

		// Push current vertex to stack which stores result
		stack.push(new Integer(v));
	}

	// The function to do Topological Sort. It uses
	// recursive topologicalSortUtil()
	void topologicalSort() {
		Stack<Integer> stack = new Stack<Integer>();

		// Mark all the vertices as not visited
		boolean visited[] = new boolean[noOfVertices];
		for (int i = 0; i < noOfVertices; i++)
			visited[i] = false;

		// Call the recursive helper function to store
		// Topological Sort starting from all vertices
		// one by one
		for (int i = 0; i < noOfVertices; i++)
			if (visited[i] == false)
				topologicalSortUtil(i, visited, stack);

		// Print contents of stack
		while (stack.empty() == false)
			System.out.print(stack.pop()+1 + " ");
	}
	
	public static void main(String[] args) throws Exception {
		    MyInputStreamReader myInputStreamReader;
		    InputStream is = TopologicalSortUsingDFS.class.getResourceAsStream(TopologicalSortUsingDFS.class.getSimpleName()+".txt");
			if (is == null) {
				myInputStreamReader = new MyInputStreamReader(System.in);
			} else {
				myInputStreamReader = new MyInputStreamReader(is);
			}
			int noOfVertices = myInputStreamReader.readInt();
			int noOfEdges = myInputStreamReader.readInt();

             TopologicalSortUsingDFS topologicalSortUsingBFS= new TopologicalSortUsingDFS(noOfVertices);
			for (int i = 0; i < noOfEdges; i++) {
				topologicalSortUsingBFS.addEdge(myInputStreamReader.readInt()-1, myInputStreamReader.readInt()-1);

			}
			
			topologicalSortUsingBFS.topologicalSort();

		
	}

}
