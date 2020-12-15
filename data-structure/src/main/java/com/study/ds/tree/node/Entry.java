package com.study.ds.tree.node;

import java.util.Comparator;

public class Entry<K extends Comparable<K>, V> implements Comparable<Entry> {
	private K key;
	private V value;

	@Override
	public int compareTo(Entry o) {
		return this.key.compareTo((K)o.getKey());
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
