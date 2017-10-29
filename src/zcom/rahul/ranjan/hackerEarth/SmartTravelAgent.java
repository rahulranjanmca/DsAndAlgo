package zcom.rahul.ranjan.hackerEarth;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SmartTravelAgent {

	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0);
		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();
		unsettledNodes.add(source);
		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if ((edgeWeigh > evaluationNode.getDistance()) || (edgeWeigh == evaluationNode.getDistance()
				&& sourceNode.getName().compareTo(evaluationNode.getName()) == -1)) {
			evaluationNode.setDistance(edgeWeigh);// DIJI:evaluationNode.setDistance(sourceDistance + edgeWeigh)
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}

	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MIN_VALUE;
		for (Node node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance > lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	// Driver method
	public static void main(String[] args) throws Exception {

		MyInputStreamReader myInputStreamReader;
		InputStream is = SmartTravelAgent.class.getResourceAsStream(SmartTravelAgent.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int noOfVertices = myInputStreamReader.readInt();
		int noOfEdges = myInputStreamReader.readInt();

		Map<String, Node> nodesMap = new HashMap<>();
		Graph graph = new Graph();

		for (int i = 0; i < noOfVertices; i++) {
			String nodeName = "" + (i + 1);
			Node node = new Node(nodeName);
			nodesMap.put(nodeName, node);
			graph.addNode(node);
		}
		for (int i = 0; i < noOfEdges; i++) {
			String nodeName1 = "" + myInputStreamReader.readInt();
			String nodeName2 = "" + myInputStreamReader.readInt();
			int distance = myInputStreamReader.readInt();
			nodesMap.get(nodeName1).getAdjacentNodes().put(nodesMap.get(nodeName2), distance);
			nodesMap.get(nodeName2).getAdjacentNodes().put(nodesMap.get(nodeName1), distance);

		}

		String startNodeName = "" + myInputStreamReader.readInt();
		String endNodeName = "" + myInputStreamReader.readInt();
		;
		int totalPassenger = myInputStreamReader.readInt();
		;

		calculateShortestPathFromSource(graph, nodesMap.get(startNodeName));

		for (Node shortedPath : nodesMap.get(endNodeName).shortestPath) {
			System.out.print(shortedPath.getName() + " ");
		}
		System.out.print(nodesMap.get(endNodeName).getName() + " ");
		int minPassenger = Integer.MAX_VALUE;
		boolean isFirst = true;
		for (Node shortedPath : nodesMap.get(endNodeName).shortestPath) {
			if (isFirst) {
				isFirst = false;
				continue;
			}
			minPassenger = Math.min(minPassenger, shortedPath.distance);
		}
		minPassenger = Math.min(minPassenger, nodesMap.get(endNodeName).getDistance());
		int noOfTrips = totalPassenger / (minPassenger - 1);
		if (totalPassenger % (minPassenger - 1) > 0) {
			noOfTrips++;
		}

		System.out.print("\n" + noOfTrips);

	}

	int findMinimumTrip(int totalPassenger, int minPassenger, int trip) {

		int totalTrip = 0;
		if (minPassenger > totalPassenger)
			return 1;
		if ((totalPassenger + trip) % minPassenger == 0) {
			totalTrip = (totalPassenger + trip) / minPassenger;
		} else {
			totalTrip = (totalPassenger + trip) / minPassenger + 1;
		}
		return totalTrip;

	}

	public static class Node {

		private String name;

		private List<Node> shortestPath = new LinkedList<>();

		private Integer distance = Integer.MIN_VALUE;

		Map<Node, Integer> adjacentNodes = new HashMap<>();

		public void addDestination(Node destination, int distance) {
			adjacentNodes.put(destination, distance);
		}

		public Node(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Node> getShortestPath() {
			return shortestPath;
		}

		public void setShortestPath(List<Node> shortestPath) {
			this.shortestPath = shortestPath;
		}

		public Integer getDistance() {
			return distance;
		}

		public void setDistance(Integer distance) {
			this.distance = distance;
		}

		public Map<Node, Integer> getAdjacentNodes() {
			return adjacentNodes;
		}

		public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
			this.adjacentNodes = adjacentNodes;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	}

	public static class Graph {

		private Set<Node> nodes = new HashSet<>();

		public void addNode(Node nodeA) {
			nodes.add(nodeA);
		}

		public Set<Node> getNodes() {
			return nodes;
		}

		public void setNodes(Set<Node> nodes) {
			this.nodes = nodes;
		}

		// getters and setters
	}

	public static class MyInputStreamReader {
		private byte[] buf = new byte[1 << 8];
		private int curChar;
		private int charsRead;
		private InputStream stream;

		public MyInputStreamReader(InputStream stream) {
			this.stream = stream;
		}

		public int readInt() throws IOException {
			int c = read();
			while (skip(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!skip(c));
			return res * sgn;
		}

		public long readLong() throws IOException {
			int c = read();
			while (skip(c))
				c = read();
			int sign = 1;
			if (c == '-') {
				sign = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!skip(c));
			return res * sign;
		}

		private int read() throws IOException {
			if (charsRead == -1)
				throw new InputMismatchException();
			if (curChar >= charsRead) {
				curChar = 0;
				charsRead = stream.read(buf);
				if (charsRead <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		private static boolean skip(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}

}
