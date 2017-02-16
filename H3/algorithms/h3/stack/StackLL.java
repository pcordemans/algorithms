package algorithms.h3.stack;
import algorithms.h1.linkedlist.List;

public class StackLL<E> implements Stack<E> {
	private List<E> list;
	
	public StackLL(){
		
		list = new List<E>();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void push(E element){
		list.prepend(element);
	}
	
	public E top(){
		if(isEmpty()) return null;
		return list.head();
	}
	
	public E pop(){
		if(top() == null) return null;
		return list.removeHead();
	}
}
