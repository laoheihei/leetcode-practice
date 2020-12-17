package com.study.ds.tree;

import com.study.ds.tree.node.BinNode;

import static com.study.ds.tree.node.BinNode.*;

/**
 * @author llc
 * @description todo 遍历
 * @date 2020/12/7 14:03
 */
public class BinTree<T extends Comparable<T>> {
	protected int _size;
	protected BinNode<T> _root;

	/**
	 * 更新二叉树节点node的高度
	 * @param node
	 * @return
	 */
	public static int updateHeight(BinNode node) {
		if (!hasChild(node)) {
			return 0;
		}
		return 1 + Math.max(getNodeHeight(node.getLeftChild()), getNodeHeight(node.getRightChild()));
	}

	/**
	 * 更新二叉树节点node及其祖先的高度；一旦node的高度不变，即可终止更新
	 * @param node
	 */
	public static void updateHeightAbove(BinNode node) {
		while (node != null) {
			int previousHeight = node.getHeight();
			updateHeight(node);
			if (node.getHeight() == previousHeight) {
				break;
			}

			node = node.getParent();
		}
	}

	/******************************************************************************************************************/

	/**
	 * 将data当做根节点插入二叉树，返回树中新插入的节点
	 * @param data
	 * @return
	 */
	public BinNode<T> insertAsRoot(T data) {
		this._size = 1;
		this._root = new BinNode<>(data);
		return this._root;
	}

	/**
	 * 当node节点的左孩子为空时，data插入为 node 的左孩子，返回新插入节点
	 * @param node
	 * @param data
	 * @return
	 */
	public BinNode<T> insertAsLeftChild(BinNode<T> node, T data) {
		this._size++;
		node.insertAsLC(data);
		updateHeightAbove(node);
		return node.getLeftChild();
	}

	/**
	 * 当node节点的右孩子为空时，data插入为 node 的右孩子，返回新插入节点
	 * @param node
	 * @param data
	 * @return
	 */
	public BinNode<T> insertAsRightChild(BinNode<T> node, T data) {
		this._size++;
		node.insertAsRC(data);
		updateHeightAbove(node);
		return node.getRightChild();
	}

	/**
	 * 当node节点的左孩子为空时，将tree作为node的左子树接入，返回node节点
	 * @param node
	 * @param tree
	 * @return
	 */
	public BinNode<T> attachAsLeftChild(BinNode<T> node, BinTree<T> tree) {
		attachParentLeftChildLink(node, tree._root);

		this._size += tree.get_size();
		updateHeightAbove(node);

		// 释放子树
		tree.set_root(null);
		tree.set_size(0);

		// 返回node节点
		return node;
	}

	/**
	 * 当node节点的右孩子为空时，将tree作为node的右子树接入，返回node节点
	 * @param node
	 * @param tree
	 * @return
	 */
	public BinNode<T> attachAsRightChild(BinNode<T> node, BinTree<T> tree) {
		attachParentRightChildLink(node, tree._root);

		this._size += tree.get_size();
		updateHeightAbove(node);

		// 释放子树
		tree.set_root(null);
		tree.set_size(0);

		// 返回node节点
		return node;
	}

	/**
	 * 删除 node 节点及其后代，返回被删除的节点数
	 * @param node
	 * @return
	 */
	public int remove(BinNode node) {
		BinNode parent = node.getParent();
		cutParentChildLink(node);

		updateHeightAbove(parent);
		int n = removeAt(node);
		this._size -= n;
		return n;
	}

	public int removeAt(BinNode<T> node) {
		if (node == null) { return 0; }

		int n = 1 + removeAt(node.getLeftChild()) +removeAt(node.getRightChild());
		node.setData(null);
		return n;
	}

	/**
	 * 将node为根的子树分离
	 * @param node
	 * @return
	 */
	public BinTree<T> secede(BinNode<T> node) {
		BinNode<T> parent = node.getParent();
		cutParentChildLink(node);

		updateHeightAbove(parent);
		int retSize = node.size();
		this._size -= retSize;

		BinTree<T> ret = new BinTree<>();
		ret.set_root(node);
		ret.set_size(retSize);
		return ret;
	}
	/******************************************************************************************************************/

	public int get_size() {
		return _size;
	}

	public void set_size(int _size) {
		this._size = _size;
	}

	public BinNode<T> get_root() {
		return _root;
	}

	public void set_root(BinNode<T> _root) {
		this._root = _root;
	}
}
