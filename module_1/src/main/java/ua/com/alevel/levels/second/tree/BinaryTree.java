package ua.com.alevel.levels.second.tree;

public class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftRouteDepth = maxDepth(root.getLeft());
        int rightRouteDepth = maxDepth(root.getRight());
        if (leftRouteDepth > rightRouteDepth)
            return (leftRouteDepth + 1);
        else
            return (rightRouteDepth + 1);
    }

    public TreeNode getRoot() {
        return root;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Empty tree";
        } else {
            return root.toString();
        }
    }
}
