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
		return constructPathsInTree(root);
	}
	
	/**
	 * Bottom-up construction of the paths in the Tree
	 * @param node current node
	 * @return (partial) list of paths in the Tree
	 */
	private ArrayList<ArrayList<E>> constructPathsInTree(TreeNode<E> node){
		if(!node.hasChildren()){
			ArrayList<E> path = new ArrayList<E>();
			path.add(node.getElement());
			ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
			result.add(path);
			return result;
		}
		else{
			ArrayList<ArrayList<E>> partialResult = new ArrayList<ArrayList<E>>();
			for(TreeNode<E> child: node.getChildren()){
				ArrayList<ArrayList<E>> currentPaths = constructPathsInTree(child);
				for(ArrayList<E> path : currentPaths){
					if(!node.isRoot())
						path.add(node.getElement());
					partialResult.add(path);
				}
			}
			return partialResult;
		}
	}
}
