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
		BinNode<T> insert = search(data);
		if (insert != null) { return insert; }

		insert = new BinNode<>(data, this._hot);
		this._size++;

		for (BinNode<T> p = this._hot; p != null; p = p.getParent()) {
			if (!avlBalanced(p)) {
				BinNode<T> dummy = new BinNode<>();
				BinNode.replaceParentChildLink(p, dummy);
				BinNode<T> succ = rotateAt(BinNode.tallerChild(BinNode.tallerChild(p)));
				BinNode.replaceParentChildLink(dummy, succ);
				break;
			} else {
				updateHeight(p);
			}
		}

		return insert;
	}

	@Override
	public boolean remove(T data) {
		BinNode<T> remove = search(data);
		if (remove == null) { return false; }

		removeAt(remove, this);
		_size--;

		for (BinNode<T> p = this._hot; p != null; p = p.getParent()) {
			if (!avlBalanced(p)) {
				BinNode<T> dummy = new BinNode<>();
				BinNode.replaceParentChildLink(p, dummy);
				BinNode<T> succ = rotateAt(BinNode.tallerChild(BinNode.tallerChild(p)));
				BinNode.replaceParentChildLink(dummy, succ);
			}
			// 即使 p 未失衡，高度也可能更新
			updateHeight(p);
		}
		return true;
	}


}
