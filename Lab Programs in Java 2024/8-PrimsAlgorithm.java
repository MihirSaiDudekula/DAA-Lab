import java.util.*;

// the idea
// visit start first and add all its neigbouring edges. this step is done only once, in the beginning

//now comes the main part
// we need to repeatedly look into the priority queue, obtain the minimum length edge using pop
// we know the edge and its weight, but we dont know anything about the vertices.
// so check whether this edge will form a loop - check if both its extrema vertices are already visited, 
// if yes, we cannot include it in our solution
// if not, add it to the MST(that was the only thing we needed to worry about!!)

// now, obviously, we just checked that both the nodes were unvisited, i.e. either node1 is visited or node2 is visited 
// both cannot be visited. so now simply visit the one we havent visited!!

// doing this, we can cover the entire graph.

class Node {
  //each node has a value
  String val;
  //then it has a list of edges, which itself are objects. the name of this list is nbs - neighbours 
  List <Edge> nbs; // list of edges to neighbors

  //constructor to construct the node
  public Node(String val) {
    this.val = val;
    this.nbs = new ArrayList <> ();
  }

  //addEdge function creates a new edge by calling the constructor of edge class which takes from which node , 
  //to which node and weight of the edge, and adds it to the nbs list 
  public void addEdge(Node neighbor, int weight) {
    this.nbs.add(new Edge(this, neighbor, weight));
  }
}

class Edge {
  //edge has the properties : from which node , 
  // to which node and weight of the edge, and adds it to the nbs list
  Node from;
  Node to;
  int weight;

  //constructor - which was called when we wanted to add edge for a node     
  public Edge(Node from, Node to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }
}

class Graph {
  //graph is simply a list of nodes 
  List <Node> nodes;

  public Graph() {
    this.nodes = new ArrayList <> ();
  }

  public void addNode(Node n) {
    nodes.add(n);
  }

  // Function to add weighted edge
  public void addEdge(Node from, Node to, int weight) {
    from.addEdge(to, weight);
    to.addEdge(from, weight); // Assuming undirected graph
  }

  // Prim's algorithm to find Minimum Spanning Tree (MST)
  public List <Edge> primMST(Node start) {
    // Set to keep track of visited nodes
    Set <Node> visited = new HashSet <> ();

    // List to store edges of the Minimum Spanning Tree (MST)
    List <Edge> mst = new ArrayList <> ();

    // Priority queue to store edges sorted by weight
    PriorityQueue <Edge> minHeap = new PriorityQueue <> (Comparator.comparingInt(e -> e.weight));

    // Start the Prim's algorithm by visiting the start node

    //visit function, basically just adds current node to visited and all it neighbouring edges , which are pointing to unvisited nodes, into the priorityqueue
    visit(start, visited, minHeap);

    // Continue until all nodes are visited or priority queue is empty
    while (!minHeap.isEmpty()) {
      // Extract the edge with the smallest weight from the priority queue
      Edge edge = minHeap.poll();

      // Nodes at either ends of the edge that we got from the above min poll 
      Node u = edge.from;
      Node v = edge.to;

      // If both nodes are already visited, which indicates the formation of a cycle, skip this edge
      if (visited.contains(u) && visited.contains(v)) {
        continue;
      }

      // Add the edge to the MST
      mst.add(edge);

      // Determine the next node to visit
      Node next = visited.contains(u) ? v : u;

      // Visit the next node
      visit(next, visited, minHeap);
    }

    // Return the Minimum Spanning Tree (MST) edges
    return mst;
  }

  // Helper method to visit a node and add its edges to the priority queue
  private void visit(Node node, Set <Node> visited, PriorityQueue <Edge> minHeap) {
    // Mark the node as visited
    visited.add(node);

    // Iterate through all edges of the current node
    for (Edge edge: node.nbs) {
      // If the edge leads to an unvisited node, add it to the priority queue
      if (!visited.contains(edge.to)) {
        minHeap.offer(edge);
      }
    }
  }

}

public class Main {
  public static void main(String[] args) {
    // Create nodes
    Node nodeA = new Node("A");
    Node nodeB = new Node("B");
    Node nodeC = new Node("C");
    Node nodeD = new Node("D");
    Node nodeE = new Node("E");
    Node nodeF = new Node("F");


    // Create graph
    Graph graph = new Graph();
    graph.addNode(nodeA);
    graph.addNode(nodeB);
    graph.addNode(nodeC);
    graph.addNode(nodeD);
    graph.addNode(nodeE);
    graph.addNode(nodeF);


    // Create weighted edges
    graph.addEdge(nodeF, nodeE, 8);
    graph.addEdge(nodeD, nodeE, 3);
    graph.addEdge(nodeF, nodeD, 7);
    graph.addEdge(nodeD, nodeA, 6);
    graph.addEdge(nodeE, nodeA, 4);
    graph.addEdge(nodeE, nodeC, 3);
    graph.addEdge(nodeA, nodeC, 2);
    graph.addEdge(nodeC, nodeB, 2);
    graph.addEdge(nodeA, nodeB, 5);

    // Perform Prim's algorithm to find MST
    List <Edge> mst = graph.primMST(nodeF);

    // Print MST edges
    System.out.println("Minimum Spanning Tree (MST) using Prim's Algorithm:");
    for (Edge edge: mst) {
      System.out.println(edge.from.val + " - " + edge.to.val + " : " + edge.weight);
    }
  }
}


