package com.study.ds.tree.node;

/**
 * @author llc
 * @description
 * @date 2020/12/7 14:05
 */

public class BinNode<T> implements Node {
	public static boolean isRoot(BinNode node) {
		return node.parent == null;
	}

	public static boolean isLeftChild(BinNode node) {
		return !isRoot(node) && (node.parent.lChild == node);
	}

	public static boolean isRightChild(BinNode node) {
		return !isRoot(node) && (node.parent.rChild == node);
	}

	public static boolean hasParent(BinNode node) {
		return !isRoot(node);
	}

	public static boolean hasLChild(BinNode node) {
		return node.lChild != null;
	}

	public static boolean hasRChild(BinNode node) {
		return node.rChild != null;
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
				node.parent.rChild :
				node.parent.lChild;
	}

	// 叔叔
	public static BinNode uncle(BinNode node) {
		return isLeftChild(node.parent) ?
				node.parent.parent.rChild :
				node.parent.parent.lChild;
	}

	/**
	 * 返回节点高度，如果node == null，node.height = -1;
	 * @return
	 */
	public static int getNodeHeight(Node node) {
		return node == null ? -1 : node.height();
	}

	protected T data;

	protected BinNode<T> parent, lChild, rChild;

	// 高度
	protected int height;

	// Null Path Length（左式堆，也可以用height代替）
	protected int npl;

	public BinNode(T data, BinNode parent) {
		this.data = data;
		this.parent = parent;
	}

	/**
	 * @description 统计以当前节点为根的树中包含的节点数量
	 * @return 节点总数
	 */
	public int size() {
		int size = 1;
		if (this.lChild != null) { size += lChild.size(); }
		if (this.rChild != null) { size += rChild.size(); }

		return size;
	}

	public int height() {

	}

	public BinNode<T> insertAsLC(T data) {
		this.lChild = new BinNode<T>(data, this);
		return this.lChild;
	}

	public BinNode<T> insertAsRC(T data) {
		this.rChild = new BinNode<T>(data, this);
		return this.rChild;
	}

	/**
	 * 当前节点的直接后继
	 * @return
	 */
	public static <T> BinNode<T> succ(BinNode<T> node) {
		BinNode<T> succ = null;
		// 1、node有右子树
		if (hasRChild(node)) {
			succ = node.rChild;
			while (hasLChild(succ)) {
				succ = succ.lChild;
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

	public BinNode<T> getlChild() {
		return lChild;
	}

	public void setlChild(BinNode<T> lChild) {
		this.lChild = lChild;
	}

	public BinNode<T> getrChild() {
		return rChild;
	}

	public void setrChild(BinNode<T> rChild) {
		this.rChild = rChild;
	}

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
