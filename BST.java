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
class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        //printInorder(root);
        //System.out.println(height(root)); 
        
        insertInBST(root, 18);
            
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        printLevelOrder(queue);
        
        return true;
    }
    
    // Remember the auditorium analogy
    public int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        
        if(leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
    
    // Inorder will be left, root and right
    // Similarly pre-order is root, left and right
    // post-order is left, right and root
    // These 3 are called depth first travesal
    public void printInorder(TreeNode root) {
        if(root == null)
            return;
        printInorder(root.left);
        System.out.println(root.val);
        printInorder(root.right); 
    }
    

    // For inserting a node in a BST, check values of left and right and assign node.left or       // node.right accordingly
    public TreeNode insertInBST(TreeNode root, int val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }
        
        if(val > root.val) {
            root.right = insertInBST(root.right, val);
        } else {
            root.left = insertInBST(root.left, val);
        }
        
        return root;
    }
    
    public void deleteInBST(TreeNode root, int val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }
        
        if(val > root.val) {
            root.right = insertInBST(root.right, val);
        } else {
            root.left = insertInBST(root.left, val);
        }
        
        return root;
    }
    
    // Prints nodes on each level
    // This is also called BFS travesal
    public void printLevelOrder(Queue<TreeNode> nodeQueue) {
        if(nodeQueue.isEmpty())
            return;
        
        TreeNode node = nodeQueue.poll();
        
        // if root is not empty and print that out first
        System.out.println(node.val);
        if(node.left != null) 
            nodeQueue.add(node.left);
        if(node.right != null) 
            nodeQueue.add(node.right);
        
        printLevelOrder(nodeQueue) ;                    
    }
}
