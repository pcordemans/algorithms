package algorithms;

public interface TreePosition<E> {
	public TreePosition<E> getParent();

	public E getElement();

	public Iterable<? extends TreePosition<E>> getChildren();

	public int numberOfChildren();
}
