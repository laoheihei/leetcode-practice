package com.study.ds.tree;

import com.study.ds.tree.node.BinNode;

public abstract class BST<T extends Comparable<T>> extends BinTree<T> {
	// BST:search()最后访问的非空（除非树空）的节点位置
	protected BinNode<T> _hot;

	/**
	 * 按照 “3 + 4” 结构，联结 3 个节点和 4 颗子树
	 * @param a1
	 * @param a2
	 * @param a3
	 * @param b1
	 * @param b2
	 * @param b3
	 * @param b4
	 * @return
	 */
	abstract BinNode<T> connect34(BinNode<T> a1, BinNode<T> a2, BinNode<T> a3,
	                     BinNode<T> b1, BinNode<T> b2, BinNode<T> b3, BinNode<T> b4);

	/**
	 * 查找
	 * @param data
	 * @return
	 */
	public BinNode<T> search(T data) {
		return searchIn(_root, data, null);
	}

	/**
	 * 插入
	 * @param data
	 * @return
	 */
	public BinNode<T> insert(T data) {
		BinNode<T> ret = search(data);
		if (ret != null) { return ret; }

		ret = new BinNode<>(data, _hot);
		_size++;
		updateHeightAbove(ret);
		return ret;
	}

	/**
	 * 删除
	 * @param data
	 * @return
	 */
	public boolean remove(T data) {
		BinNode<T> search = search(data);
		if (search == null) { return false; }

		_size--;
		// 1、无孩子节点
		if (!BinNode.hasChild(search)) {
			BinNode.cutParentChildLink(search);
		}

		// 2、有 1 个孩子节点
		if (!BinNode.hasBothChild(search)) {

		}

		// 3、有 2 个孩子节点
		if (BinNode.hasBothChild(search)) {

		}

		updateHeightAbove(_hot);
		return true;
	}

	/**
	 *
	 * @param removeNode
	 * @param hot remove节点的父节点
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<T>> BinNode<T> removeAt(BinNode<T> removeNode, BinNode<T> hot) {
		// 实际被删除节点，初值为remove
		BinNode<T> w = removeNode;
		// 被删除节点的接替者
		BinNode<T> succ = null;
		// 1、左子树为空
		if (!BinNode.hasLChild(removeNode)) {
			succ = removeNode.getRightChild();
		// 2、左子树不为空，右子树为空
		} else if (!BinNode.hasRChild(removeNode)) {
			succ = removeNode.getLeftChild();
		// 3、左右子树都存在
		} else {
			w = BinNode.succ(w);
			BinNode.swapData(w, removeNode);
			succ = w.getRightChild();

			// 隔离节点w
			BinNode<T> u = w.getParent();
			if (u == removeNode) {
				u.setRightChild(w.getRightChild());
			} else {
				u.setLeftChild(w.getRightChild());
			}
		}

		hot = w.getParent();  // 记录实际被删除节点的父亲节点
		if (succ != null) { succ.setParent(hot); }
		return succ;
	}

	/**
	 * 在子树 node 中查找 data节点
	 * @param node
	 * @param data
	 * @param hot
	 * @param <T>
	 * @return 未找到，返回null；此时_hot节点为
	 */
	public static <T extends Comparable<T>> BinNode<T> searchIn(BinNode<T> node, T data, BinNode<T> hot) {
		if (node == null || node.getData().equals(data)) { return node; }

		hot = node;

		int compare = node.getData().compareTo(data);
		if (compare < 0) {
			return searchIn(node.getRightChild(), data, hot);
		} else {
			return searchIn(node.getLeftChild(), data, hot);
		}
	}
}
