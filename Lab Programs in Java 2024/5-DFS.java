import java.util.*;

class Node
{
	int val;
	List<Node> nbs; //list of neighbours 
	
	public Node(int val)
	{
		this.val=val;
		this.nbs = new ArrayList<>();
	}
	
	public void addNbs(Node nb)
	{
		nbs.add(nb);
	}
}

class Graph
{
	List<Node> nodes;
	
	public Graph()
	{
		this.nodes = new ArrayList<>();
	}
	
	public void addNode(Node n)
	{
		nodes.add(n);
	}
}

class DFS
{
	public static void dfs(Node start)
	{
		Set<Node> visited = new HashSet<>();
		dfsRecursive(start,visited);
	}
	
	private static void dfsRecursive(Node n,Set<Node> visited)
	{
		visited.add(n);
		System.out.println("Visited node: " + n.val);
		
		for(Node x:n.nbs)
		{
			if(!visited.contains(x))
			{
				dfsRecursive(x,visited);
			}
		}
	}
}

public class Main {
    public static void main(String[] args) {
        // Create nodes
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Create edges
        node0.addNbs(node1);
        node0.addNbs(node2);
        node1.addNbs(node3);
        node2.addNbs(node4);

        // Create graph
        Graph graph = new Graph();
        graph.addNode(node0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        // Perform DFS
        System.out.println("DFS Traversal:");
        DFS.dfs(node0);

    }
}
