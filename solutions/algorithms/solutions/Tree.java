package algorithms.solutions;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Tree implementation to calculate permutations and combinations of a set of elements
 *
 * @param <E> type of the elements
 */
public class Tree<E> {
	private TreeNode<E> root;
	private int size = 0;
	
	/**
	 * Constructs a Tree of permutations given a collection of elements
	 * @param elements collection
	 */
	public Tree(Collection<E> elements){
		root = new TreeNode<E>(null);
		addElementAndRecur(new ArrayList<E>(elements), root);
	}
	
	private void addElementAndRecur(ArrayList<E> elements, TreeNode<E> node){
		node.addChildren(elements);
		size += elements.size();
		for(TreeNode<E> child : node.getChildren()){
			ArrayList<E> listMinElement = new ArrayList<E>(elements);
			listMinElement.remove(child.getElement());
			addElementAndRecur(listMinElement, child);
		}
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer();
		for(TreeNode<E> child : root.getChildren()){
			buf.append(child.toString());
			buf.append("\r\n");
		}
		return buf.toString();
	}
	
	/**
	 * 
	 * @return number of elements in the Tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 
	 * @return true if no elements in the tree, otherwise false
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * 
	 * @return a list of all permutations of elements, permutations of elements are represented as an ArrayList<E>
	 */
	public ArrayList<ArrayList<E>> permutations(){
		
		return combinations(Integer.MAX_VALUE);
	}
	
	/**
	 * Constructs a list of all combinations given a number of elements
	 * @param numberOfElements in the combinations
	 * @return the list of combinations
	 */
	public ArrayList<ArrayList<E>> combinations(int numberOfElements){
		ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
		for(TreeNode<E> child : root.children){
			result.addAll(constructPathsInTree(child, numberOfElements));
		}
		return result;
	}
	
	/**
	 * Bottom-up construction of the paths in the Tree
	 * @param node current node
	 * @param level number to start from (if the level number is larger than the number of levels in the tree, it is ignored)
	 * @return (partial) list of paths in the Tree
	 */
	private ArrayList<ArrayList<E>> constructPathsInTree(TreeNode<E> node, int level){
		if(!node.hasChildren() || node.getLevel() == level){
			ArrayList<E> path = new ArrayList<E>();
			path.add(node.getElement());
			ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
			result.add(path);
			return result;
		}
		else{
			ArrayList<ArrayList<E>> partialResult = new ArrayList<ArrayList<E>>();
			for(TreeNode<E> child: node.getChildren()){
				ArrayList<ArrayList<E>> currentPaths = constructPathsInTree(child, level);
				for(ArrayList<E> path : currentPaths){
					path.add(node.getElement());
					partialResult.add(path);
				}
			}
			return partialResult;
		}
	}
	
	/**
	 * Filters the set of permutations to cover all combinations given a level
	 * @param level of combinations
	 * @return list of permutations which cover all combinations
	 */
	public ArrayList<ArrayList<E>> filterPaths(int level){
		ArrayList<ArrayList<E>> combinations = combinations(level);
		ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
		
		while(combinations.size() != 0){
		//step 1: select partial path with next combination
		TreeNode<E> node = partialPathNode(combinations.get(0));
		//step 2: collect all complete paths of partial path
		ArrayList<ArrayList<TreeNode<E>>> completedPathsInTree = findPaths(node, new ArrayList<TreeNode<E>>());
		ArrayList<ArrayList<E>> completedPaths = new ArrayList<ArrayList<E>>();
		for(ArrayList<TreeNode<E>> nodes : completedPathsInTree){
			ArrayList<E> path = new ArrayList<E>();
			for(TreeNode<E> n : completePath(nodes)){
				path.add(n.getElement());
			}
			completedPaths.add(path);
		}
		//step 3: select complete path with most combinations: check numberOfCoveredCombinations
		ArrayList<E> mostCovered = null;
		int numberCovered = 0;
		for(ArrayList<E> path : completedPaths){
			int currentNumber = numberOfCoveredCombinations(path, combinations);
			if( currentNumber > numberCovered){
				mostCovered = path;
				numberCovered = currentNumber;
			}				
		}
		result.add(mostCovered);
		
		//step 4: filter all combinations not covered: check coveredCombinations
		ArrayList<ArrayList<E>> covered = coveredCombinations(mostCovered, level+1);
		combinations.removeAll(covered);
		//step 5: repeat until all combinations are covered
		}
		return result;
	}
	
	/**
	 * Returns the number of covered combinations in a path
	 * @param path of elements
	 * @param combinations set to cover
	 * @return the number of combinations in the set which are covered by the path
	 */
	private int numberOfCoveredCombinations(ArrayList<E> path, ArrayList<ArrayList<E>> combinations){
		ArrayList<ArrayList<E>> inpath = coveredCombinations(path, combinations.get(0).size());
		ArrayList<ArrayList<E>> covered = new ArrayList<ArrayList<E>>(combinations);
		covered.retainAll(inpath);
		return covered.size();
	}
	
	
	private ArrayList<ArrayList<E>> coveredCombinations(ArrayList<E> path, int level){
		
		TreeNode<E> tree = new TreeNode<E>(null);
		constructCombinationTree(tree, path, level);
		
		return returnPaths(tree, new ArrayList<E>());
	}
	
	/**
	 * Constructs all paths in order in a given Tree
	 * @param node root of the Tree
	 * @param partialPath empty path
	 * @return list of all paths
	 */
	private ArrayList<ArrayList<E>> returnPaths(TreeNode<E> node, ArrayList<E> partialPath){
		ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
		
		if(!node.hasChildren()){
			result.add(partialPath);
			return result;
		}
		
		for(TreeNode<E> child : node.getChildren()){
			ArrayList<E> path = new ArrayList<E>(partialPath);
			path.add(child.getElement());
			result.addAll(returnPaths(child, path));
		}
		
		return result;
	}
	
	/**
	 * Constructs a tree of combinations of a given path
	 * @param path
	 * @param root node
	 */
	private void constructCombinationTree(TreeNode<E> node, ArrayList<E> path, int level){
		int index = 0;
		if(!node.isRoot()) index =  path.indexOf(node.getElement())+1;
		for(int i = index; i <= node.getLevel(); i++ ){
			node.addChild(path.get(i));
		}		
		if(level-1 == node.getLevel()) return;
		for(TreeNode<E> child : node.getChildren()){
			constructCombinationTree(child, path, level);
		}
	}
	
	private TreeNode<E> partialPathNode(ArrayList<E> combination){
		TreeNode <E> result = root;
		for(E element : combination){
			result = nextNodeInPath(element, result);
		}
		return result;
	}
	
	private TreeNode<E> nextNodeInPath(E nextElement, TreeNode<E> current){
		TreeNode<E> found = null;
		for(TreeNode<E> node : current.getChildren())
			if(node.getElement() == nextElement) found = node;
		return found;	
	}
	
	/***
	 * Finds all paths originating from the node
	 * @param node
	 * @param path
	 * @return the list of paths
	 */
	private ArrayList<ArrayList<TreeNode<E>>> findPaths(TreeNode<E> node, ArrayList<TreeNode<E>> path){
		path.add(node);
		if(!node.hasChildren()){
			ArrayList<ArrayList<TreeNode<E>>> result = new ArrayList<ArrayList<TreeNode<E>>>();
			result.add(path);
			return result;
		}
		ArrayList<ArrayList<TreeNode<E>>> result = new ArrayList<ArrayList<TreeNode<E>>>();
		
		for(TreeNode<E> child : node.getChildren()){
			ArrayList<TreeNode<E>> partialPath = new ArrayList<TreeNode<E>>(path);
			result.addAll(findPaths(child, partialPath));
		}
		return result;
	}
	
	/***
	 * Construct the completed path, given a partial path  
	 * @param partial
	 * @return completed path
	 */
	private ArrayList<TreeNode<E>> completePath(ArrayList<TreeNode<E>> partial){
		TreeNode<E> current = partial.get(0);
		if(current.getParent().isRoot()) return partial;
		partial.add(0, current.getParent());
		return completePath(partial);
	}
	
	/**
	 * Tree node implementation	
	 * @param <T> type of the element at the TreeNode
	 */
	private class TreeNode<T> {
		private T element;
		private TreeNode<T> parent;
		private ArrayList<TreeNode<T>> children;
			
		/**
		 * Constructor of TreeNode without children
		 * @param element
		 * @param parent
		 */
		private TreeNode(T element, TreeNode<T> parent){
			this.element = element;
			this.parent = parent;
			children = new ArrayList<TreeNode<T>>();
		}
		
		/**
		 * Constructor of TreeNode without children or parent
		 * @param element
		 */
		protected TreeNode(T element){
			this(element, null);
		}
		
		/**
		 * @return the element at the TreeNode
		 */
		public T getElement(){
			return element;
		}
		
		public void setElement(T element){
			this.element = element;
		}
		
		/**
		 * @return the parent of the TreeNode
		 */
		public TreeNode<T> getParent(){
			return parent;
		}
		
		/**
		 * @return an Iterable of the children of the TreeNode
		 */
		public Iterable<TreeNode<T>> getChildren(){
			return children;
		}
		
		/**
		 * Adds a child to the TreeNode
		 * @param element of the child
		 * @return the TreeNode reference of the added child
		 */
		public TreeNode<T> addChild(T element){
			TreeNode<T> child = new TreeNode<T>(element, this);
			children.add(child);
			return child;
		}
		
		/**
		 * Adds a set of elements as children to the TreeNode
		 * @param elements
		 */
		public void addChildren(Iterable<T> elements){
			for(T element: elements){
				addChild(element);
			}
		}
		
		/**
		 * @return the String representation of the element and its children
		 */
		public String toString(){
			StringBuffer buf = new StringBuffer();
			levelAppend(buf, "   ");
			levelAppend(buf, ">");
			buf.append( element.toString());
			
			for(TreeNode<T> child: children){
				buf.append("\n");
				buf.append(child.toString());
			}
			return buf.toString();
		}
		
		private void levelAppend(StringBuffer buf, String text){
			for(int i = 0; i < getLevel(); i++) {
				buf.append(text);
			}
		}
		
		/**
		 * 
		 * @return true if TreeNode is the root, otherwise false
		 */
		public boolean isRoot(){
			return (parent == null);
		}
		
		/**
		 * 
		 * @return the level number in the tree, root == 0
		 */
		public int getLevel(){
			if(isRoot()) return 0;
			return parent.getLevel() + 1; 
		}
		
		/**
		 * 
		 * @return the number of children of the TreeNode
		 */
		public int numChildren(){
			return children.size();
		}
		
		/**
		 * 
		 * @return true if the number of children < 0, otherwise false
		 */
		public boolean hasChildren(){
			return (numChildren() != 0);
		}
	}
}
