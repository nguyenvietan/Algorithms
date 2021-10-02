package oop;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *      underlying data structure? Array
 *      capacity
 *      hash collision handling? separate chaining
 */
public class MyHashMap {
	private class Pair<K, V> {
		K key;
		V val;
		public Pair(K key, V val) {
			this.key = key;
			this.val = val;
		}
		public Pair() {}
	}
	private class Bucket {
		private LinkedList<Pair<Integer, Integer>> list;
		public Bucket() { list = new LinkedList<>(); }
		public int get(int k) {
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i).key == k) return list.get(i).val;
			}
			return -1;
		}
		public int put(int k, int v) {
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i).key == k) {
					int old = list.get(i).val;
					list.get(i).val = v;
					return old;
				}
			}
			list.add(new Pair<Integer, Integer>(k, v));
			return -1;
		}
		public int remove(int k) {
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i).key == k) {
					int old = list.get(i).val;
					list.remove(i);
					return old;
				}
			}
			return -1;
		}
	}
	//-- HashMap --
	private Bucket[] map;
	private int capacity;
	public MyHashMap(int capacity) {
		this.capacity = capacity;
		map = new Bucket[capacity];
		for (int i = 0; i < capacity; ++i) map[i] = new Bucket();
	}
	public MyHashMap() { this(11); }
	//---
	public int get ( int key){
		int h = getHash(key);
		return map[h].get(key); // bucket.get(h)
	}
	public void put ( int key, int val)  {
		int h = getHash(key);
		map[h].put(key, val); // bucket.put(h)
	}
	public void remove ( int key) {
		int h = getHash(key);
		map[h].remove(key); // bucket.remove(h)
	}
	private int getHash ( int key) {
		return key % capacity;
	}
	public static void main (String[] args){
		MyHashMap map = new MyHashMap(16);

//		map.put(5, 78);
//		map.put(5, 99);
//		map.put(21, 100);
//		map.put(15, 299);
//		map.put(4, 333);
//		map.remove(5);
//		map.remove(21);
//		map.remove(4);
//		System.out.println(map.get(5));
//		System.out.println(map.get(21));
//		System.out.println(map.get(15));
//		System.out.println(map.get(4));
		map.put(5, 78);
		map.put(21, 99);
		System.out.println(map.get(21));
	}
}
