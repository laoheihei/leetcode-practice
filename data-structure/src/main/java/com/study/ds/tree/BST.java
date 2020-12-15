package com.study.ds.tree;

import com.study.ds.tree.node.BinNode;

public abstract class BST<T> extends BinTree {
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
	abstract BinNode<T> search(T data);

	/**
	 * 插入
	 * @param data
	 * @return
	 */
	abstract BinNode<T> insert(T data);

	/**
	 * 删除
	 * @param data
	 * @return
	 */
	abstract boolean remove(T data);

	/**
	 * 在子树 node 中查找 data节点
	 * @param node
	 * @param data
	 * @param hot
	 * @param <T>
	 * @return 未找到，返回null；此时_hot节点为
	 */
	public static <T> BinNode<T> searchIn(BinNode<T> node, T data, BinNode<T> hot) {
		if (node == null || node.getData().equals(data)) { return node; }

		hot = node;
		T data1 = node.getData();
		if (node.getData()) {
			searchIn(node.getRightChild(), data, hot);
		}
	}
}
