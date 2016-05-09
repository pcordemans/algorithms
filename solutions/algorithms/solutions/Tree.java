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
		
		return variations(Integer.MAX_VALUE);
	}
	
	/**
	 * Constructs a list of all combinations given a number of elements
	 * @param numberOfElements in the combinations
	 * @return the list of combinations
	 */
	public ArrayList<ArrayList<E>> variations(int numberOfElements){
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
