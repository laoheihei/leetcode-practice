package com.study.ds.tree;

import com.study.ds.tree.node.BinNode;

public class BST<T extends Comparable<T>> extends BinTree<T> {
	// BST:search()最后访问的非空（除非树空）的节点位置
	protected BinNode<T> _hot;

	/**
	 * 查找
	 * @param data
	 * @return
	 */
	public BinNode<T> search(T data) {
		this._hot = null;
		return searchIn(_root, data);
	}

	/**
	 * 插入
	 * @param data
	 * @return 返回新插入节点
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

		removeAt(search, this);
		_size--;
		updateHeightAbove(this._hot);
		return true;
	}

	/**
	 * BST节点旋转变换统一算法（3+4），返回调整之后局部子树根节点的位置
	 * @param node node为非空的孙辈节点
	 * @return
	 */
	public BinNode<T> rotateAt(BinNode<T> node) {
		BinNode<T> parent = node.getParent();
		BinNode<T> grantParent = parent.getParent();

		if (BinNode.isLeftChild(parent)) {
			if (BinNode.isLeftChild(node)) {
				return connect34(node, parent, grantParent, node.getLeftChild(), node.getRightChild(), parent.getRightChild(), grantParent.getRightChild());
			} else {
				return connect34(parent, node, grantParent, parent.getLeftChild(), node.getLeftChild(), node.getRightChild(), grantParent.getRightChild());
			}
		} else {
			if (BinNode.isRightChild(node)) {
				return connect34(grantParent, parent, node, grantParent.getLeftChild(), parent.getLeftChild(), node.getLeftChild(), node.getRightChild());
			} else {
				return connect34(grantParent, node, parent, grantParent.getLeftChild(), node.getLeftChild(), node.getRightChild(), parent.getRightChild());
			}
		}
	}

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
	public BinNode<T> connect34(BinNode<T> a1, BinNode<T> a2, BinNode<T> a3,
	                            BinNode<T> b1, BinNode<T> b2, BinNode<T> b3, BinNode<T> b4) {
		a1.setLeftChild(b1);
		if (b1 != null) { b1.setParent(a1); }

		a1.setRightChild(b2);
		if (b2 != null) { b2.setParent(a1); }
		updateHeight(a1);

		a3.setLeftChild(b3);
		if (b3 != null) { b3.setParent(a3); }

		a3.setRightChild(b4);
		if (b4 != null) { b4.setParent(a3); }
		updateHeight(b4);

		a2.setLeftChild(a1); a1.setParent(a2);
		a2.setRightChild(a3); a3.setParent(a2);
		updateHeight(a2);

		return a2;
	}

	/**
	 * 流程：
	 *  1、找到被删除节点的接替者 succ 节点；
	 *  2、建立 hot 节点和 succ 节点的联系；
	 *
	 * @param removeNode
	 * @param tree 用于传递tree._hot节点
	 * @param
	 * @return
	 */
	protected BinNode<T> removeAt(BinNode<T> removeNode, BST<T> tree) {
		// 被删除节点的接替者
		BinNode<T> succ = null;
		// 1、找到被删除节点的接替者 succ 节点；
		// 1）左子树为空
		if (!BinNode.hasLChild(removeNode)) {
			succ = removeNode.getRightChild();
		// 2）左子树不为空，右子树为空
		} else if (!BinNode.hasRChild(removeNode)) {
			succ = removeNode.getLeftChild();
		// 3）左右子树都存在，使用removeNode的后继来替代
		} else {
			BinNode<T> temp = removeNode;
			removeNode = BinNode.succ(removeNode);
			BinNode.swapData(temp, removeNode);
			succ = removeNode.getRightChild();
		}

		// 2、更新_hot节点，建立新的父子链接
		tree._hot = removeNode.getParent();
		BinNode.replaceParentChildLink(removeNode, succ);

		return succ;
	}

	/**
	 * 在子树 node 中查找 data节点
	 * @param node
	 * @param data
	 * @return 未找到，返回null；此时_hot节点为
	 */
	protected BinNode<T> searchIn(BinNode<T> node, T data) {
		if (node == null || node.getData().equals(data)) { return node; }

		this._hot = node;

		int compare = data.compareTo(node.getData());
		if (compare < 0) {
			return searchIn(node.getLeftChild(), data);
		} else {
			return searchIn(node.getRightChild(), data);
		}
	}

	public BinNode<T> get_hot() {
		return _hot;
	}

	public void set_hot(BinNode<T> _hot) {
		this._hot = _hot;
	}
}
