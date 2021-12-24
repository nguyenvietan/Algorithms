package bst;

public class LowestCommonAncestor {
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	private TreeNode res=null;

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		dfs(root, p, q);
		return res;
	}

	private int dfs(TreeNode node, TreeNode p, TreeNode q) {
		if (node==null) return 0;
		int parent=(node==p || node==q) ? 1:0;
		int left=dfs(node.left, p, q);
		int right=dfs(node.right, p, q);
		int total=parent+left+right;
		if (total==2) res=node;
		return total>0 ? 1:0;
	}

}
