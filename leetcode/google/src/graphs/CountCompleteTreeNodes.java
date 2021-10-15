package graphs;

/**
 * Solution: accumulate each half of the tree and subtrees; check left-side height; divide and conquer
 *                   1
 *                /    \
 *               2      3
 *             /  \    /  \
 *            4    5  6   7
 *          /   \
 *          8    9
 */
public class CountCompleteTreeNodes {
	private class TreeNode {
		private int val;
		private TreeNode left;
		private TreeNode right;
		public TreeNode(int val) { this.val = val; }
		public TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	private int numNodes;
	private int findMaxDepth(TreeNode root) {
		return root != null ? findMaxDepth(root.left) + 1 : 0;
	}
	private void find0(TreeNode root, int h) {
		if (root == null || h == 0) return;
		if (findMaxDepth(root.right) == h-1) {
			numNodes += 1 << (h-1);
			find0(root.right, h-1);
		} else {
			numNodes += 1 << (h-2);
			find0(root.left, h-1);
		}
	}
	public int countNodes(TreeNode root) {
		numNodes = 0;
		int h = findMaxDepth(root);
		find0(root, h);
		return numNodes;
	}

}
