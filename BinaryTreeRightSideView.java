import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public static List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) { //O(n) T.C
            int size = q.size(); //take size to find level of nodes
            for(int i=0; i<size; i++) {
                TreeNode current = q.poll();
                assert current != null;
                if(i == size-1) result.add(current.val); //if the current node is at the end of BFS traversal
                //its value is right most. Similarly, if it is at beginning, its value is left most.

                if(current.left != null) q.add(current.left); //O(n) S.C
                if(current.right != null) q.add(current.right);
            }
        }
        return result;
    }

    static List<Integer> result;
    public static List<Integer> rightSideViewDFS(TreeNode root) {
        result = new ArrayList<>();
        dfs(root, 0); //O(n) T.C
        return result;
    }

    static void dfs(TreeNode root, int level) {
        if(root == null) return;

        if(result.size() == level) { //if size of result list is equal to current level of traversal
            result.add(root.val); //it means current level's value is not added yet, hence add it now
        }

        dfs(root.right, level+1); //we perform dfs on right child first to only write right most values into result
        dfs(root.left, level+1); //O(h) S.C, recursive stack space
    }

    public static void main(String[] args) {
        // Test case 1: Example tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);

        System.out.println("Right Side View of Test Case 1: " + rightSideViewBFS(root1)); // Output: [1, 3, 4]

        // Test case 2: Left-skewed tree
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);

        System.out.println("Right Side View of Test Case 2: " + rightSideViewDFS(root4)); // Output: [1, 2, 3]
    }
}