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
        
        //insertInBST(root, 18);
        
        //Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //queue.add(root);
        //printLevelOrder(queue);
        
        //deleteRec(root, 7);

        //deleteInBST(root, 7);
        
        //Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //queue.add(root);
        //printLevelOrder(queue);

        return checkEvenOdd(root);
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
    
    // Why can't this be recursive?
    // How do you explain need for a queue
    public boolean checkEvenOdd(TreeNode root) {
        if(root == null)
            return true;
        
        // Inital queue
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        
        // Intialize variables needed during the traversal for count
        boolean isEvenOdd = true;
        int level = 0;
        int nodeCount = 0;
        int treeHeight = height(root);
        
        while(level < treeHeight) {
            Queue<TreeNode> childrenQ = new LinkedList<TreeNode>();
            
            while(!q.isEmpty()) {
                TreeNode parentNode = q.poll();
                ++nodeCount;
                System.out.println("Level " + level + " node " + parentNode.val);
                
                if(parentNode.left != null) {
                    childrenQ.add(parentNode.left);
                }
                
                
                if(parentNode.right != null) {
                    childrenQ.add(parentNode.right);
                }
            }
            
            System.out.println("Nodecount " + nodeCount + " Level " + level);
            
            if((nodeCount == 1 && level == 0) || 
               (nodeCount % 2 == 0 && level % 2 == 1) || 
               (nodeCount % 2 == 1 && level % 2 == 0)) {
                ++level;
                q = childrenQ;  
                nodeCount = 0;
            } else {
                return false;
            }
        }
        
        return isEvenOdd;
        
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
    
    TreeNode deleteRec(TreeNode root, int key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)
            return root;
 
        /* Otherwise, recur down the tree */
        if (key < root.val)
            root.left = deleteRec(root.left, key);
        else if (key > root.val)
            root.right = deleteRec(root.right, key);
 
        // if key is same as root's 
        // key, then This is the
        // node to be deleted
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.val = minValue(root.right);
 
            // Delete the inorder successor
            root.right = deleteRec(root.right, root.val);
        }
 
        return root;
    }
 
    int minValue(TreeNode root)
    {
        int minv = root.val;
        while (root.left != null) 
        {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }
    
    // Deleting a node in the three
    public TreeNode deleteInBST(TreeNode root, int val) {
        // Node does not exist and we have reached the end
        if(root == null) {
            System.out.println("It is null");
            return root;
        }
        
        // Node found; now check the subtree existence
        if(root.val == val) {
            // If none, then delete the node by assigning it null
            if(root.left == null && root.right == null) {
                System.out.println("Both null " + root.val);
                return root;
            }
            
            // If right is null then pull up entire left subtree
            if(root.right == null) {
                System.out.println("Right is null " + root.val);
                return root.left;
                
            } // If left is null then pull up entire right subtree
            else if(root.left == null) {
                System.out.println("Left is null " + root.val);
                return root.right;
                
            } // If both exist then pick one subtree to pull value from
            // Here we pick right so we need to find min value on right side 
            else {
                root.val = minVal(root.right);
                // once found, make that root and delete the node with min value
                root.right = deleteInBST(root.right, root.val);     
            }
            
        } // Now we will deal with situations where we still need to find the value 
        else if(root.val > val) {
            root.right = deleteInBST(root.right, val);
            
        } else {
            root.left = deleteInBST(root.left, val);
        }
        
        return root;
        
    }
    
    // We call this by default when the left and right subtree is present during deletion
    public int minVal(TreeNode root) {
        if(root == null) 
            return 0;
        
        if(root.left == null) {
            System.out.println("Min value returned is " + root.val);
            return root.val;
        }
        return minVal(root.left);
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
