package com.study.ds.tree.node;

/**
 * @author llc
 * @description
 * @date 2020/12/7 14:05
 */
public interface Node {
	/**
	 * @description 统计以当前节点为根的树中包含的节点数量
	 * @return 节点总数
	 */
	int size();

	/**
	 * 统计当前节点的高度
	 * @return
	 */
	int getHeight();
}
