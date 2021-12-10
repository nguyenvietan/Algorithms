package trees;

import java.util.HashMap;
import java.util.Map;

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
class ConstructBinaryTreeFromPreorderAndInorderTraversal {
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
	private int[] inorder, postorder;
	private Map<Integer, Integer> map;
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
		// init inorder, postorder
		this.inorder = inorder;
		this.postorder = postorder;
		// build map on inorder: val->pos
		map = new HashMap<>();
		for (int i = 0; i < inorder.length; ++i) map.put(inorder[i], i);		
		return recursion(0, inorder.length-1, 0, postorder.length-1);										        
    }
    
    private TreeNode recursion(int iL, int iR, int pL, int pR) {
		if (iL > iR || pL > pR) return null;
    	TreeNode root = new TreeNode(postorder[pR]);    	
        System.out.printf("root = %d\n", root.val);
    	int idxRoot = map.get(postorder[pR]);
    	root.left = recursion(iL, idxRoot-1, pL, idxRoot-1);
    	root.right = recursion(idxRoot+1, iR, idxRoot, pR-1);
        return root;
    }

}
