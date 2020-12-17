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
		removeAt(search, _hot);
		// succ可能为 null，所以将updateHeightAbove的调用移植removeAt()方法中
		// updateHeightAbove(succ.getParent());
		_size--;

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
				// 向上链接
				parent.setParent(grantParent.getParent());
				return connect34(node, parent, grantParent, node.getLeftChild(), node.getRightChild(), parent.getRightChild(), grantParent.getRightChild());
			} else {
				// 向上链接
				node.setParent(grantParent.getParent());
				return connect34(parent, node, grantParent, parent.getLeftChild(), node.getLeftChild(), node.getRightChild(), grantParent.getRightChild());
			}
		} else {
			if (BinNode.isRightChild(node)) {
				// 向上链接
				parent.setParent(grantParent.getParent());
				return connect34(grantParent, parent, node, grantParent.getLeftChild(), parent.getLeftChild(), node.getLeftChild(), node.getRightChild());
			} else {
				// 向上链接
				node.setParent(grantParent.getParent());
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
	 *  3、更新节点高度；
	 *
	 * @param removeNode
	 * @param hot remove节点的父节点
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<T>>BinNode<T> removeAt(BinNode<T> removeNode, BinNode<T> hot) {
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

		// 2、建立新的父子链接
		hot = removeNode.getParent();
		if (hot != null) {
			if (BinNode.isRightChild(removeNode)) {
				hot.setRightChild(succ);
			} else {
				hot.setLeftChild(succ);
			}
		}
		if (succ != null) { succ.setParent(hot); }

		// 3、更新高度
		updateHeightAbove(hot);
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
