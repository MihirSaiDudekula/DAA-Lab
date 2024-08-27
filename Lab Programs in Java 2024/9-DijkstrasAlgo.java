import java.util.*;

// idea
// basically, we want the minimum distance between source and every vertex in the graph
// we give a dist parameter to each node, which we update at every relaxation
// first we set dist from src to src as 0 and everything else as INF
// then, for each neighbour of src, relax the distance

// unlike prims, here our minheap stores the node and not the edge
// in general, the idea is, every time we do a relaxation step, we add that node to minheap
// this is for 2 reasons
// 1. we are greedy: we want the shortest path, and at every relaxation the path which is shortest is picked to traverse
// 2. path shrinks: once the relaxation occurs, if a path to already visited node relaxes, then overall path length also decreases
// this is the reason why the same node may enter the minheap multiple times and we are not concerned about visited state

// Node class represents a node in the graph
class Node {
    int val; // Node value
    List<Edge> nbs; // List of edges (neighbors)
    int dist; // Distance from the source node

    // Constructor to initialize a node with a value
    public Node(int val) {
        this.val = val;
        this.nbs = new ArrayList<>();
        this.dist = Integer.MAX_VALUE;
    }

    // Method to add a neighbor (edge) to the node with a specified weight
    public void addEdge(Node neighbor, int weight) {
        nbs.add(new Edge(this, neighbor, weight));
    }
}

// Edge class represents an edge between two nodes with a weight
class Edge {
    Node from; // Starting node of the edge
    Node to; // Ending node of the edge
    int weight; // Weight of the edge

    // Constructor to initialize an edge with starting node, ending node, and weight
    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

// Graph class represents a collection of nodes and edges
class Graph {
    List<Node> nodes; // List of nodes in the graph

    // Constructor to initialize an empty graph
    public Graph() {
        this.nodes = new ArrayList<>();
    }

    // Method to add a node to the graph
    public void addNode(Node n) {
        nodes.add(n);
    }

    // Method to add a weighted edge between two nodes in the graph
    public void addEdge(Node from, Node to, int weight) {
        from.addEdge(to, weight);
        // Assuming undirected graph, adding both directions
        to.addEdge(from, weight);
    }

    // Dijkstra's algorithm to find shortest path from start node to all nodes
    public void dijkstra(Node start) {
        // Priority queue to fetch nodes with the smallest known distance
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
        
        // Initialize distances of all nodes as infinity
        for (Node node : nodes) {
            node.dist = Integer.MAX_VALUE;
        }
        
        // Distance from start node to itself is 0
        start.dist = 0;
        minHeap.offer(start);
        
        // Process nodes in the priority queue until it is empty
        while (!minHeap.isEmpty()) {
            // Extract the node with the smallest distance from the priority queue
            Node u = minHeap.poll();
            
            // Iterate through all neighboring edges of the current node
            for (Edge edge : u.nbs) {
                Node v = edge.to;
                int weight = edge.weight;
                int newDist = u.dist + weight;
                
                // If a shorter path to node v is found, update its distance and add to the priority queue
                if (newDist < v.dist) {
                    v.dist = newDist;
                    minHeap.offer(v);
                }
            }
        }
    }
}

// Main class for testing Dijkstra's algorithm
public class Main {
    public static void main(String[] args) {
        // Create nodes
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Create graph
        Graph graph = new Graph();
        graph.addNode(node0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        // Create weighted edges
        graph.addEdge(node0, node1, 2);
        graph.addEdge(node0, node2, 3);
        graph.addEdge(node1, node3, 4);
        graph.addEdge(node2, node4, 1);

        // Perform Dijkstra's algorithm from node0
        graph.dijkstra(node0);

        // Print shortest distances
        System.out.println("Shortest distances from node " + node0.val + ":");
        for (Node node : graph.nodes) {
            System.out.println("To node " + node.val + ": " + node.dist);
        }
    }
}
