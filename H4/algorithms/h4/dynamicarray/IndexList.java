package algorithms.h4.dynamicarray;

public interface IndexList<E> {
	
	/**
	 * 
	 * @return if empty
	 */
	boolean isEmpty();
	
	/**
	 * 
	 * @return size
	 */
	int size();
	
	/**
	 * Sets the element at index
	 * @param index
	 * @param element to add
	 * @return old element
	 */
	E set(int index, E element);
	
	/**
	 * Returns the element at index
	 * @param index
	 * @return element at index
	 */
	E get(int index);
	
	/**
	 * Removes the element at index
	 * @param index
	 * @return old element
	 */
	E remove(int index);
}
