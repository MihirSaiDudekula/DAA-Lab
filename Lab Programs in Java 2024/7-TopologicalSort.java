import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class Node {
    int data;
    ArrayList<Node> nbs;

    public Node(int val) {
        this.data = val;
        this.nbs = new ArrayList<>();
    }

    public void addNbs(Node x) {
        this.nbs.add(x);
    }
}

class DFS {
    Stack<Node> tps = new Stack<>();
    Set<Node> visit = new HashSet<>();

    public void topoSort(Node s) {
        if (!visit.contains(s)) {
            visit.add(s);
            for (Node x : s.nbs) {
                topoSort(x);
            }
            tps.push(s); // Push 's' after processing its neighbors
        }
    }

    public void printTopoSort() {
        while (!tps.empty()) {
            System.out.println(tps.pop().data);
        }
    }

    // this function is here to find inflection points -  the nodes in the graph other than the source whose indegree is 0
    //i.e, they are disconnected from our original graph
    public void topoSortAll(Node[] nodes) {
        for (Node node : nodes) {
            if (!visit.contains(node)) { // Skip nodes that have been visited
                topoSort(node);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node0 = new Node(0);
        
        // Create edges
        node2.addNbs(node3);
        node3.addNbs(node1);
        node4.addNbs(node0);
        node4.addNbs(node1);
        node5.addNbs(node0);
        node5.addNbs(node2);

        // Example usage
        DFS dfs = new DFS();
        Node[] nodes = {node0, node1, node2, node3, node4, node5};
        dfs.topoSortAll(nodes);
        dfs.printTopoSort();
    }
}
