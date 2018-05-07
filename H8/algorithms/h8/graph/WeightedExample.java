package algorithms.h8.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class WeightedExample extends SingleGraph {
	private String styleSheet = "node {fill-color: black;text-size: 24px; }" 
			+ "node.marked {fill-color: red;}"
			+ "edge {text-size: 24px; }";
	
	public WeightedExample() {
		super("weightedexample");

		addNode("BOS");
		addNode("PVD");
		addNode("JFK");
		addNode("BWI");
		addNode("MIA");
		addNode("ORD");
		addNode("DFW");
		addNode("SFO");
		addNode("LAX");
		addEdge("A", "BOS", "JFK").addAttribute("length", 187);
		addEdge("B", "BOS", "SFO").addAttribute("length", 2704);
		addEdge("C", "BOS", "MIA").addAttribute("length", 1258);
		addEdge("D", "BOS", "ORD").addAttribute("length", 867);
		addEdge("E", "PVD", "JFK").addAttribute("length", 144);
		addEdge("F", "PVD", "ORD").addAttribute("length", 849);
		addEdge("G", "JFK", "BWI").addAttribute("length", 184);
		addEdge("H", "JFK", "ORD").addAttribute("length", 740);
		addEdge("I", "JFK", "DFW").addAttribute("length", 1391);
		addEdge("J", "JFK", "MIA").addAttribute("length", 1090);
		addEdge("K", "BWI", "MIA").addAttribute("length", 946);
		addEdge("L", "BWI", "ORD").addAttribute("length", 621);
		addEdge("M", "MIA", "DFW").addAttribute("length", 1121);
		addEdge("N", "MIA", "LAX").addAttribute("length", 2342);
		addEdge("O", "DFW", "ORD").addAttribute("length", 802);
		addEdge("P", "DFW", "SFO").addAttribute("length", 1464);
		addEdge("Q", "DFW", "LAX").addAttribute("length", 1235);
		addEdge("R", "ORD", "SFO").addAttribute("length", 1846);
		addEdge("S", "SFO", "LAX").addAttribute("length", 337);

		for (Node n : this)
			n.addAttribute("label", n.getId());
		for (Edge e : getEachEdge())
			e.addAttribute("label", "" + (int) e.getNumber("length"));
		
		addAttribute("ui.stylesheet", styleSheet);
	}

	public static void main(String[] args) {
		WeightedExample graph = new WeightedExample();
		graph.display();
	}
}
