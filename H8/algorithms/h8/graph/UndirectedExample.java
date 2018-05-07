package algorithms.h8.graph;

import java.util.Iterator;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class UndirectedExample extends SingleGraph {
	private String styleSheet = "node {fill-color: black;text-size: 24px; }" 
								+ "node.marked {fill-color: red;}";

	public UndirectedExample() {
		super("UndirectedExample");

		addNodes();
		addEdges();

		addAttribute("ui.stylesheet", styleSheet);
	}

	private void addNodes() {
		for (char c = 'A'; c <= 'P'; c++) {
			addNode(Character.toString(c));
		}

		for (Node node : this) {
			node.addAttribute("ui.label", node.getId());
		}
	}

	private void addEdges() {
		addEdge("AB", "A", "B");
		addEdge("AE", "A", "E");
		addEdge("AF", "A", "F");
		addEdge("BC", "B", "C");
		addEdge("BF", "B", "F");
		addEdge("CD", "C", "D");
		addEdge("CG", "C", "G");
		addEdge("DH", "D", "H");
		addEdge("DG", "D", "G");
		addEdge("EF", "E", "F");
		addEdge("HL", "H", "L");
		addEdge("LP", "L", "P");
		addEdge("GL", "G", "L");
		addEdge("GK", "G", "K");
		addEdge("GJ", "G", "J");

		addEdge("KO", "K", "O");
		addEdge("KN", "K", "N");
		addEdge("KJ", "K", "J");
		addEdge("IE", "I", "E");
		addEdge("IF", "I", "F");
		addEdge("IJ", "I", "J");
		addEdge("IN", "I", "N");
		addEdge("IM", "I", "M");
		addEdge("MN", "M", "N");
	}
	
	public void explore(Node source) {
		Iterator<? extends Node> k = source.getDepthFirstIterator();

		int i = 0;
		while (k.hasNext()) {
			Node next = k.next();
			next.setAttribute("ui.class", "marked");
			next.setAttribute("ui.label", next.getId() + i++);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		UndirectedExample graph = new UndirectedExample();
		graph.display();
		graph.explore(graph.getNode("A"));

	}
}
