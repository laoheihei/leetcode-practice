package com.study.ds.tree;

import com.study.ds.tree.node.BinNode;

/**
 * @author llc
 * @description
 * @date 2020/12/7 14:03
 */
public abstract class BinTree<T> {
	protected int _size;
	BinNode<T> _root;

	// 更新节点x及其祖先的高度
	abstract void updateHeightAbove(BinNode node);




}
