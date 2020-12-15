package com.study.ds.tree.node;

/**
 * @author llc
 * @description
 * @date 2020/12/7 14:05
 */

public class BinNode<T> implements Node {
	protected T data;

	protected BinNode<T> parent, leftChild, rightChild;

	// 节点高度，无孩子的节点高度为0
	protected int height;

	// Null Path Length（左式堆，也可以用height代替）
	protected int npl;

	public BinNode(T data, BinNode parent) {
		this.data = data;
		this.parent = parent;
	}

	public BinNode(T data) {
		this.data = data;
	}

	/**
	 * @description 统计以当前节点为根的树中包含的节点数量
	 * @return 节点总数
	 */
	@Override
	public int size() {
		int size = 1;
		if (this.leftChild != null) { size += leftChild.size(); }
		if (this.rightChild != null) { size += rightChild.size(); }

		return size;
	}

	/**
	 *
	 * @param data
	 * @return 返回被插入的新节点
	 */
	public BinNode<T> insertAsLC(T data) {
		this.leftChild = new BinNode<T>(data, this);
		return this.leftChild;
	}

	/**
	 *
	 * @param data
	 * @return 返回被插入的新节点
	 */
	public BinNode<T> insertAsRC(T data) {
		this.rightChild = new BinNode<T>(data, this);
		return this.rightChild;
	}

	/******************************************************************************************************************/

	/**
	 * 当前节点的直接后继
	 * @return
	 */
	public static <T> BinNode<T> succ(BinNode<T> node) {
		BinNode<T> succ = null;
		// 1、node有右子树
		if (hasRChild(node)) {
			succ = node.rightChild;
			while (hasLChild(succ)) {
				succ = succ.leftChild;
			}

			return succ;
		}

		// 2、node是祖先节点左子树一员，祖先节点即为node.succ
		while (isRightChild(node)) {
			node = node.parent;
		}
		if (isLeftChild(node)) {
			succ = node.getParent();

			return succ;
		}

		return null;
	}

	/**
	 * 返回节点高度，如果node == null，node.height = -1;
	 * @return
	 */
	public static int getNodeHeight(Node node) {
		return node == null ? -1 : node.getHeight();
	}

	public static void attachParentLeftChildLink(BinNode parent, BinNode leftChild) {
		parent.leftChild = leftChild;
		leftChild.parent = parent;
	}

	public static void attachParentRightChildLink(BinNode parent, BinNode rightChild) {
		parent.rightChild = rightChild;
		rightChild.parent = parent;
	}

	public static void cutParentChildLink(BinNode child) {
		if (!hasParent(child)) {
			return;
		}

		if (isLeftChild(child)) {
			child.parent.leftChild = null;
		} else {
			child.parent.rightChild = null;
		}

		child.parent = null;
	}



	public static boolean isRoot(BinNode node) {
		return node.parent == null;
	}

	public static boolean isLeftChild(BinNode node) {
		return !isRoot(node) && (node.parent.leftChild == node);
	}

	public static boolean isRightChild(BinNode node) {
		return !isRoot(node) && (node.parent.rightChild == node);
	}

	public static boolean hasParent(BinNode node) {
		return !isRoot(node);
	}

	public static boolean hasLChild(BinNode node) {
		return node.leftChild != null;
	}

	public static boolean hasRChild(BinNode node) {
		return node.rightChild != null;
	}

	public static boolean hasChild(BinNode node) {
		return hasLChild(node) || hasRChild(node);
	}

	public static boolean hasBothChild(BinNode node) {
		return hasLChild(node) && hasRChild(node);
	}

	public static boolean isLeaf(BinNode node) {
		return !hasChild(node);
	}

	// 兄弟
	public static BinNode sibling(BinNode node) {
		return isLeftChild(node) ?
				node.parent.rightChild :
				node.parent.leftChild;
	}

	// 叔叔
	public static BinNode uncle(BinNode node) {
		return isLeftChild(node.parent) ?
				node.parent.parent.rightChild :
				node.parent.parent.leftChild;
	}

	/******************************************************************************************************************/

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BinNode<T> getParent() {
		return parent;
	}

	public void setParent(BinNode<T> parent) {
		this.parent = parent;
	}

	public BinNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public BinNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNpl() {
		return npl;
	}

	public void setNpl(int npl) {
		this.npl = npl;
	}
}
