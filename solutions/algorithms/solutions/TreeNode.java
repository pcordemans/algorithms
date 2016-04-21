package algorithms.solutions;

import java.util.ArrayList;

/**
 * Tree node implementation	
 * @param <E> type of the element at the TreeNode
 */
public class TreeNode<E> {
	private E element;
	private TreeNode<E> parent;
	private ArrayList<TreeNode<E>> children;
		
	/**
	 * Constructor of TreeNode without children
	 * @param element
	 * @param parent
	 */
	private TreeNode(E element, TreeNode<E> parent){
		this.element = element;
		this.parent = parent;
		children = new ArrayList<TreeNode<E>>();
	}
	
	/**
	 * Constructor of TreeNode without children or parent
	 * @param element
	 */
	protected TreeNode(E element){
		this(element, null);
	}
	
	/**
	 * @return the element at the TreeNode
	 */
	public E getElement(){
		return element;
	}
	
	/**
	 * @return the parent of the TreeNode
	 */
	public TreeNode<E> getParent(){
		return parent;
	}
	
	/**
	 * @return an Iterable of the children of the TreeNode
	 */
	public Iterable<TreeNode<E>> getChildren(){
		return children;
	}
	
	/**
	 * Adds a child to the TreeNode
	 * @param element of the child
	 * @return the TreeNode reference of the added child
	 */
	public TreeNode<E> addChild(E element){
		TreeNode<E> child = new TreeNode<E>(element, this);
		children.add(child);
		return child;
	}
	
	/**
	 * Adds a set of elements as children to the TreeNode
	 * @param elements
	 */
	public void addChildren(Iterable<E> elements){
		for(E element: elements){
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
		
		for(TreeNode<E> child: children){
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
