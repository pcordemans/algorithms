package algorithms;

import java.util.ArrayList;

public interface ITree<E> {
	public int size();

	public boolean isEmpty();

	public ArrayList<TreePosition<E>> positions();

	public boolean isInternal(TreePosition<E> v);

	public boolean isExternal(TreePosition<E> v);

	public boolean isRoot(TreePosition<E> v);

	public TreePosition<E> root();

	public E replace(TreePosition<E> v, E e) throws InvalidPositionException;

	public void attach(TreePosition<E> v, ITree<E> subtree)
			throws InvalidPositionException;

}
