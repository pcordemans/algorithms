package algorithms.h5.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Tree<E> implements ITree<E> {
	private TreeNode<E> root;
	private int size;

	public Tree() {
		size = 0;
	}

	/**
	 * Constructs a new tree with a root element
	 * 
	 * @param root
	 */
	public Tree(E root) {
		this.root = new TreeNode<E>(root);
		size = 1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * @return an iterable collection of positions of elements
	 */
	public ArrayList<TreePosition<E>> positions() {
		ArrayList<TreePosition<E>> positions = new ArrayList<TreePosition<E>>();
		preorderPositions(root, positions);
		return positions;
	}

	public boolean isInternal(TreePosition<E> v) {
		if (v.numberOfChildren() == 0)
			return false;
		return true;
	}

	public boolean isExternal(TreePosition<E> v) {
		return !isInternal(v);
	}

	public boolean isRoot(TreePosition<E> v) {
		return (v.getParent() == null);
	}

	public TreePosition<E> root() {
		if (root == null)
			throw new NullPointerException();
		return root;
	}

	/**
	 * @param v
	 *            the position of the element to be replaced
	 * @param e
	 *            the new element
	 * @return the element which has been replaced
	 */
	public E replace(TreePosition<E> v, E e) throws InvalidPositionException {
		E oldValue = v.getElement();
		TreeNode<E> n = checkPosition(v);
		n.setElement(e);
		return oldValue;
	}

	/**
	 * @param v
	 *            the position where to attach the subtree
	 * @param subtree
	 *            to attach
	 */
	public void attach(TreePosition<E> v, ITree<E> subtree)
			throws InvalidPositionException {
		TreeNode<E> n = checkPosition(v);
		n.addChild(checkPosition(subtree.root()));
		size += subtree.size(); // parent subtree?
	}

	/**
	 * Checks if the position v is a tree node
	 * 
	 * @param v
	 *            position
	 * @return the TreeNode object
	 * @throws InvalidPositionException
	 *             when the position is not a tree node
	 */
	private TreeNode<E> checkPosition(TreePosition<E> v)
			throws InvalidPositionException {
		if (v == null || !(v instanceof TreeNode))
			throw new InvalidPositionException();
		return (TreeNode<E>) v;
	}

	private void preorderPositions(TreePosition<E> v,
			ArrayList<TreePosition<E>> positions) throws InvalidPositionException {
		positions.add(v);
		for (TreePosition<E> child : v.getChildren()) {
			preorderPositions(child, positions);
		}
	}

	private class TreeNode<T> implements TreePosition<T> {
		private TreeNode<T> parent;
		private T element;
		private ArrayList<TreeNode<T>> children;

		/**
		 * Constructor for a root element
		 * 
		 * @param element
		 */
		public TreeNode(T element) {
			this.parent = null;
			this.element = element;
			this.children = new ArrayList<TreeNode<T>>();
		}

		/**
		 * Constructor for a child element
		 * 
		 * @param parent
		 * @param element
		 */
		public TreeNode(TreeNode<T> parent, T element) {
			this.parent = parent;
			this.element = element;
			this.children = new ArrayList<TreeNode<T>>();
		}

		public TreePosition<T> getParent() {
			return parent;
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}

		public Iterable<? extends TreePosition<T>> getChildren() {
			return children;
		}

		public void addChild(TreeNode<T> child) {
			children.add(child);
		}

		public int numberOfChildren() {
			return (children.size());
		}

		private ArrayList<TreeNode<T>> getChildrenList() {
			return children;
		}

		@Override
		public int hashCode() {
			return 41 * element.hashCode() + 41 * children.hashCode();
		}

		@Override
		public boolean equals(Object other) {
			if (other == null)
				return false;
			if (other == this)
				return true;
			if (!(other instanceof TreeNode))
				return false;
			TreeNode<T> rhs = (TreeNode<T>) other;
			ArrayList<TreeNode<T>> list = rhs.getChildrenList();
			return (rhs.getElement() == this.getElement() && Arrays.deepEquals(
					list.toArray(), children.toArray()));

		}
	}
}
