package ua.com.alevel.levels.second.tree;

public class TreeNode {

    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public String toString() {
        return this.treeNodeToModel(new StringBuilder(), true, new StringBuilder()).toString();
    }

    public StringBuilder treeNodeToModel(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.treeNodeToModel(new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "L__ " : "г--- ").append(value).append("\n");
        if (left != null) {
            left.treeNodeToModel(new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb);
        }
        return sb;
    }
}
