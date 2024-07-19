public class CousinsInBinaryTree {
    TreeNode xp; //parent node of x-value
    TreeNode yp; //parent node of y-value
    int xl; //level at where x is found
    int yl; //level at where y is found
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, 0, x, y); //O(n) T.C
        return (xp != yp && xl == yl); //when parents of x and y are not same but levels of both are same
    }

    void dfs(TreeNode root, TreeNode parent, int level, int x, int y) {
        if(root == null) return;

        if(root.val == x) { //if current root value is x
            xp = parent; //update xp to current parent
            xl = level; //and xl to current level
        }
        if(root.val == y) { //same as above for y value
            yp = parent;
            yl = level;
        }

        dfs(root.left, root, level+1, x, y); //continue dfs on left child
        if(xp == null || yp == null) { //continue dfs on right child only if we haven't found both values on left side
            dfs(root.right, root, level+1, x, y); //O(h) S.C, recursive stack
        }
    }

    public static void main(String[] args) {
        CousinsInBinaryTree solution = new CousinsInBinaryTree();

        // Test case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        System.out.println("Are 4 and 5 cousins in Test Case 1? " + solution.isCousins(root1, 4, 5)); // Output: true

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);

        System.out.println("Are 2 and 3 cousins in Test Case 2? " + solution.isCousins(root2, 2, 3)); // Output: false

        // Test case 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.right = new TreeNode(4);
        root3.right.right = new TreeNode(5);

        System.out.println("Are 4 and 5 cousins in Test Case 3? " + solution.isCousins(root3, 3, 4)); // Output: true

        // Test case 4
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.right = new TreeNode(4);
        root4.right.right = new TreeNode(5);

        System.out.println("Are 4 and 6 cousins in Test Case 4? " + solution.isCousins(root4, 4, 6)); // Output: false
    }
}