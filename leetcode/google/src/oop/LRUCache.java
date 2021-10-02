package oop;

import java.util.HashMap;

/**
 * 	LRU cache: HashMap + Double Linked List
 * 	HEAD <-> 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> TAIL
 * 	key : (key, node)
 */
class LRUCache {

	//-- LRUCache attributes and constructors
	private class Node {
		int key;
		int val;
		Node prev;
		Node next;
		Node(int key, int val) { this.key = key; this.val = val; }
		Node() { this(0, 0); }
	}
	private HashMap<Integer, Node> map;
	private int capacity;
	private int size;
	private Node head, tail;
	public LRUCache(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.map = new HashMap<>();
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}
	public LRUCache() { this(11); }

	//-- LRUCache methods on the double linked list
	private void addToHead(Node p) {
		Node afterHead = head.next;
		head.next = p;
		p.prev = head;
		p.next = afterHead;
		afterHead.prev = p;
	}
	private void remove(Node p) {
		Node before = p.prev;
		Node after = p.next;
		before.next = after;
		after.prev = before;
	}

	//-- LRUCache basic operations
	public void put(int key, int val) {
		if (map.containsKey(key)) {
			// update val, remove node, then add to head
			Node p = map.get(key);
			p.val = val;
			remove(p);
			addToHead(p);
		} else {
			if (size == capacity) {
				// double linked list: remove node T before tail, map: remove key of node T
				// map: add new node, double linked list: add node to head
				Node t = tail.prev;
				remove(t);
				map.remove(t.key);
				Node p = new Node(key, val);
				map.put(key, p);
				addToHead(p);
			} else {
				// double linked list: add node to head, map: add new node
				Node p = new Node(key, val);
				addToHead(p);
				map.put(key, p);
				size++;
			}
		}
	}
	public int get(int key) {
		if (map.containsKey(key)) {
			// remove node, add to head
			Node p = map.get(key);
			remove(p);
			addToHead(p);
			return p.val;
		}
		return -1;
	}

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */