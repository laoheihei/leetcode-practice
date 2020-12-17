package com.study.ds.tree;

import com.study.ds.tree.node.BinNode;

/**
 * 任一节点v平衡因子定义为左右子树的高度差：balFac(v) = height( lc(V) ) - height( rc(v) )
 * @author llc
 * @description
 * @date 2020/12/16 15:27
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	public static boolean balanced(BinNode node) {
		return BinNode.getNodeHeight(node.getLeftChild()) == BinNode.getNodeHeight(node.getRightChild());
	}

	public static int balanceFactor(BinNode node) {
		return BinNode.getNodeHeight(node.getLeftChild()) - BinNode.getNodeHeight(node.getRightChild());
	}

	public static boolean avlBalanced(BinNode node) {
		return (-2 < balanceFactor(node)) && ((balanceFactor(node) < 2));
	}

	@Override
	public BinNode<T> insert(T data) {
		return super.insert(data);
	}

	@Override
	public boolean remove(T data) {
		return super.remove(data);
	}


}
