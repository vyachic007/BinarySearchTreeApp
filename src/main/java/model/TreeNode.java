package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreeNode {
    private int key;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasOnlyLeftChild() {
        return left != null && right == null;
    }

    public boolean hasOnlyRightChild() {
        return left == null && right != null;
    }

}
