package algorithms.h8.graph;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class DirectedExample extends SingleGraph {
	private String styleSheet = "node {fill-color: black;text-size: 24px; }" 
			+ "node.marked {fill-color: red;}";
	
	public DirectedExample() {
		super("directedExample");
		
		addNodes();
		addEdges();

		addAttribute("ui.stylesheet", styleSheet);
	}

	private void addNodes() {
		for (char c = 'A'; c <= 'H'; c++) {
			addNode(Character.toString(c));
		}

		for (Node node : this) {
			node.addAttribute("ui.label", node.getId());
		}
	}

	private void addEdges() {
		addEdge("AC", "A", "C", true);
		addEdge("AD", "A", "D", true);
		addEdge("CD", "C", "D", true);
		addEdge("BD", "B", "D", true);
		addEdge("BF", "B", "F", true);
		addEdge("CE", "C", "E", true);
		addEdge("EG", "E", "G", true);
		addEdge("GH", "G", "H", true);
		addEdge("DF", "D", "F", true);
		addEdge("FG", "F", "G", true);
		addEdge("FH", "F", "H", true);
		addEdge("CH", "C", "H", true);
	}
	
	public static void main(String[] args) {
		DirectedExample graph = new DirectedExample();
		graph.display();
	}	
}

