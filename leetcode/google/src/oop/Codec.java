package oop;

import java.util.LinkedList;
import java.util.List;

/*
*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
*/
public class Codec {

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	private List<String> list;
	private int i;

	public String serialize(TreeNode root) {
		list = new LinkedList<String>();
		serialize0(root);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); ++i) {
			sb.append(list.get(i));
			if (i < list.size()-1) sb.append(" ");
		}
		return sb.toString();
	}
	private void serialize0(TreeNode root) {
		if (root == null) { list.add("null"); return; }
		list.add(String.valueOf(root.val));
		serialize0(root.left);
		serialize0(root.right);
	}

	public TreeNode deserialize(String data) {
		String[] d = data.split("\\s+");
		TreeNode root = null;
		i = 0;
		root = deserialize0(d, root);
		return root;
	}
	private TreeNode deserialize0(String[] d, TreeNode node) {
		if (i == d.length) return node;
		if (d[i].equals("null")) { node = null; i++; return node; }
		node = new TreeNode(Integer.parseInt(d[i]));
		i++;
		node.left = deserialize0(d, node.left);
		node.right = deserialize0(d, node.right);
		return node;
	}
}
