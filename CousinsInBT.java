// Time Complexity : O(n)
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach - - Perform a DFS traversal of the tree, maintaining current level and parent for each node.
//- Track the level and parent for both nodes x and y.
//- After traversal, x and y are cousins only if:
//  - Their levels are the same.
//  - Their parents are different.

class TreeNode {
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

public class CousinsInBT {
    //2 queues, 1 for nodes and other for their parents
    // public boolean isCousins(TreeNode root, int x, int y) {
    //     if(root == null){
    //         return true;
    //     }
    //     Queue<TreeNode> q = new LinkedList<>();
    //     Queue<TreeNode> parentQ = new LinkedList<>();

    //     q.add(root);
    //     parentQ.add(null);

    //     boolean x_found = false, y_found = false;
    //     TreeNode x_parent = null, y_parent = null;

    //     while(!q.isEmpty()) {
    //         int size = q.size();
    //         for(int i = 0; i< size; i++) {
    //             TreeNode curr = q.poll();
    //             TreeNode currParent = parentQ.poll();

    //             if(curr.val == x) {
    //                 x_found = true;
    //                 x_parent = currParent;
    //             }
    //             if(curr.val == y) {
    //                 y_found = true;
    //                 y_parent = currParent;
    //             }

    //             if(curr.left != null) {
    //                 q.add(curr.left);
    //                 parentQ.add(curr);
    //             }

    //             if(curr.right != null) {
    //                 q.add(curr.right);
    //                 parentQ.add(curr);
    //             }
    //         }

    //         if(x_found && y_found) {
    //             return x_parent != y_parent;
    //         }
    //         if(x_found || y_found) {
    //             return false;
    //         }
    //     }

    //     return true;
    // }

    //without parent queue, before processing children check if root has both children and if they are x or y or not, if yes then return false, else add them in queue
    // public boolean isCousins(TreeNode root, int x, int y) {
    //     if(root == null){
    //         return true;
    //     }
    //     Queue<TreeNode> q = new LinkedList<>();

    //     q.add(root);

    //     boolean x_found = false, y_found = false;

    //     while(!q.isEmpty()) {
    //         int size = q.size();
    //         for(int i = 0; i< size; i++) {
    //             TreeNode curr = q.poll();

    //             if(curr.left != null && curr.right != null) {   //has both children
    //                 if((curr.left.val == x && curr.right.val == y) || (curr.left.val == y && curr.right.val == x)) {
    //                     return false;
    //                 }
    //             }
    //             if(curr.val == x) {
    //                 x_found = true;
    //             }
    //             if(curr.val == y) {
    //                 y_found = true;
    //             }

    //             if(curr.left != null) {
    //                 q.add(curr.left);
    //             }

    //             if(curr.right != null) {
    //                 q.add(curr.right);
    //             }
    //         }

    //         if(x_found && y_found) {
    //             return true;
    //         }
    //         if(x_found || y_found) {
    //             return false;
    //         }
    //     }

    //     return true;
    // }

    //DFS - space O(h) time O(n)
    //global variables for level and parent of x and y
    int x_level, y_level;
    TreeNode x_parent, y_parent;
    public boolean isCousins(TreeNode root, int x, int y) {
        helper(root, x, y, 0, null);
        return x_level == y_level && x_parent != y_parent;
    }

    private void helper(TreeNode root, int x, int y, int level, TreeNode parent) {
        if(root == null || (x_parent != null && y_parent != null)) {
            return; //if root is null or both x and y's parents found then return
        }

        if(root.val == x) {
            x_level = level;
            x_parent = parent;
        }

        if(root.val == y) {
            y_level = level;
            y_parent = parent;
        }

        helper(root.left, x, y, level + 1, root);

        helper(root.right, x, y, level + 1, root);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(4)),
                new TreeNode(3));

        CousinsInBT solution = new CousinsInBT();
        boolean result = solution.isCousins(root, 4, 3);
        System.out.println("Are nodes 4 and 3 cousins? " + result);
    }
}
