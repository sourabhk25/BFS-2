// Time Complexity : O(n)
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach - Perform BFS traversal, but prioritize the right child before the left child.
//- For each level of the tree, capture the first node you see (which is the rightmost node due to right-to-left enqueue order).
//- Add this node’s value to the result list.


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
public class BTRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        //Reverse Level Order Traversal(BFS with Right to Left)

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) {  //return empty list for empty tree
            return ans;
        }

        queue.offer(root);  //add root in queue to start BFS

        while(!queue.isEmpty()) {   //loop till queue is not empty
            int size = queue.size();
            for(int i=0; i<size; i++) {     //looping in size of queue for each level
                TreeNode currNode = queue.poll();   //getting element
                if(i == 0) {    //if first element from right, add to list
                    ans.add(currNode.val);
                }
                if(currNode.right != null) {    //first add right since we want to traverse from right to left in each level
                    queue.offer(currNode.right);    //add right child if exists
                }
                if(currNode.left != null) {     //add left child if exists
                    queue.offer(currNode.left);
                }
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));

        BTRightSideView view = new BTRightSideView();
        List<Integer> result = view.rightSideView(root);

        System.out.println("Right side view of binary tree: " + result);
    }
}
