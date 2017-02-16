package algorithms.h1.linkedlist;

/**
 * Singly linked list implementation
 *
 * @param <E> type of the elements in the list
 */
public class List<E> {
	private Node<E> head;
	private int size;
	
	/**
	 * Constructs an empty list
	 */
	public List(){
		head = null;
		size = 0;
	}
	
	/**
	 * Constructs a list with a single element
	 * 
	 * @param element of the list
	 */
	public List(E element) {
		head = new Node<E>(element);
		size = 1;
	}
	
	/**
	 * Retrieves the size of the list
	 * @return the number of elements in the list
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Retrieves the head of the list 
	 * @return the head node
	 */
	public Node<E> head(){
		return head;
	}
	
	/**
	 * Prepends a node to the list
	 * @param node to prepend
	 * @return itself
	 */
	public List<E> prepend(Node<E> node){
		node.setNext(head);
		head = node;
		size++;
		return this;
	}
	
	/**
	 * Prepends a list to the list
	 * @param list to prepend
	 * @return itself
	 */
	public List<E> prepend(List<E> list){
		size = size + list.size;
		last(list.head).setNext(head);
		head = list.head;
		
		return this;
	}
	
	/**
	 * Removes the head of the list, the next node becomes the new head
	 * @return the removed head
	 */
	public Node<E> removeHead(){
		Node <E> oldHead = head;
		head = head.next();
		size--;
		return oldHead;
	}
	
	private Node<E> last(Node<E> cursor){
		if (cursor.next() == null)
			return cursor;
		return last(cursor.next());
	}
	
}
