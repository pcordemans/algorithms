package algorithms.h1.linkedlist;

/**
 * Singly linked list implementation
 *
 * @param <E> type of the elements in the list
 */
public class List<E> {
	private Node head;
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
		head = new Node(element);
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
	 * @return the head element
	 */
	public E head(){
		return head.get();
	}
	
	/**
	 * Prepends a node to the list
	 * @param element to prepend
	 * @return itself
	 */
	public List<E> prepend(E element){
		Node node = new Node(element);
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
	 * @return the removed element
	 */
	public E removeHead(){
		Node oldHead = head;
		head = head.next();
		size--;
		return oldHead.get();
	}
	
	private Node last(Node cursor){
		if (cursor.next() == null)
			return cursor;
		return last(cursor.next());
	}
	
	/**
	 * Node class for a singly linked list
	 * 
	 * @param <E> element type of the node
	 */
	private class Node {
		private E element;
		private Node next;
		
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
		public Node next(){
			return next;
		}
		
		/**
		 * Sets the next node
		 * @param node becomes the next
		 */
		public void setNext(Node node){
			next = node;
		}
		
		public int hashCode(){
			return 41 * (41 * element.hashCode()) + next.hashCode();
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof List<?>.Node){
				List<?>.Node that = (List<?>.Node) obj; 
				return (this.element == that.element) &&
						(this.next == that.next);
			}
			return false;
		}
		
		public String toString(){
			return "(" + element +")";
		}
	}
	
}
