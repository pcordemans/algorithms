package algorithms;


public class DynamicArray<E> implements IndexList<E> {
	private int size;
	private E[] elements;
	private int capacity;
	
	public DynamicArray() {
		capacity = 2;
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[capacity];
		elements = temp;
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public E set(int index, E element) {
		if(capacity <= index) doubleArray();
		E e = get(index);
		elements[index] = element;
		return e;
	}
	
	private void doubleArray() {
		capacity *=2;
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[capacity];
		
		for (int i = 0; i < size; i++) {
			temp[i] = elements[i];
		}
		elements = temp;
	}

	@Override
	public E get(int index) {
		return elements[index];
	}

	@Override
	public E remove(int index) {
		E e = get(index);
		elements[index] = null;
		return e;
	}
}
