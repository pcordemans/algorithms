package algorithms;
/**
 * Node class for a singly linked list
 * 
 * @param <E> element type of the node
 */
public class Node<E> {
	private E element;
	private Node<E> next;
	
	/**
	 * Constructs a node with an element and next referring to null
	 * @param element of the node
	 */
	public Node(E element){
		this.element = element;
		next = null;
	}
	
	/**
	 * Retrieve the element
	 * @return element
	 */
	public E get(){
		return element;
	}
	
	/**
	 * Retrieves the next node
	 * @return next node
	 */
	public Node<E> next(){
		return next;
	}
	
	/**
	 * Sets the next node
	 * @param node becomes the next
	 */
	public void setNext(Node<E> node){
		next = node;
	}
	
	public int hashCode(){
		return 41 * (41 * element.hashCode()) + next.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Node<?>){
			Node<?> that = (Node<?>) obj; 
			return (this.element == that.element) &&
					(this.next == that.next);
		}
		return false;
	}
	
	public String toString(){
		return "(" + element +")";
	}
}
