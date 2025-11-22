package tree;

import lombok.Getter;
import model.TreeNode;

@Getter
public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public boolean insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return true;
        }
        TreeNode current = root;
        while (true) {
            if (key < current.getKey()) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(key));
                    return true;
                }
                current = current.getLeft();
            } else if (key > current.getKey()) {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode(key));
                    return true;
                }
                current = current.getRight();
            } else {
                return false;
            }
        }
    }

    public boolean delete(int key) {
        if (root == null) {
            return false;
        }
        if (root.getKey() == key && root.isLeaf()) {
            root = null;
            return true;
        }
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
            if (key < current.getKey()) {
                parent = current;
                current = current.getLeft();
            } else if (key > current.getKey()) {
                parent = current;
                current = current.getRight();
            } else {
                break;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.isLeaf()) {
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }

        } else if (current.hasOnlyLeftChild()) {
            if (parent == null) {
                root = current.getLeft();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }

        } else if (current.hasOnlyRightChild()) {
            if (parent == null) {
                root = current.getRight();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }

        } else {
            TreeNode successor = findMinNode(current.getRight());
            int successorKey = successor.getKey();
            delete(successorKey);
            current.setKey(successorKey);
        }
        return true;
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
