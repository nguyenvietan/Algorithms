package trees;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


/**
 * [Review] Delete a Node in a BST
 * keys: recursion, delete a node in a tree (must keep the previous pointer by using recursion)
*/
class DeleteANodeInBST {
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	private TreeNode findPredecessor(TreeNode root) {
		if (root == null || root.left == null) return null;
		TreeNode n = root.left;
		while (n.right != null) n = n.right;
		return n;
	}

	private TreeNode findSuccessor(TreeNode root) {
		if (root == null || root.right == null) return null;
		TreeNode n = root.right;
		while (n.left != null) n = n.left;
		return n;
	}

	public TreeNode deleteNode(TreeNode root, int key) {
		// base cases
		if (root == null) return null;	
		// cases >, <, =, recursion
		if (root.val > key) root.left = deleteNode(root.left, key);
		else if (root.val < key) root.right = deleteNode(root.right, key);
		else {
			TreeNode p = findPredecessor(root);
			TreeNode s = findSuccessor(root);
			if (p == null && s == null) return null;
			else if (p != null) { root.val = p.val; root.left = deleteNode(root.left, p.val); }
			else { root.val = s.val; root.right = deleteNode(root.right, s.val); }		
		}
		// return
		return root;						
	}
}


