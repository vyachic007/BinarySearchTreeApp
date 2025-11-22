package service;

import model.BalancedNodeInfo;
import model.TreeNode;
import tree.BinarySearchTree;
import java.util.List;

public class TreeService {
    private final BinarySearchTree tree;
    private final TreeAnalyzer analyzer;

    public TreeService() {
        this.tree = new BinarySearchTree();
        this.analyzer = new TreeAnalyzer();
    }

    public void buildTreeFromList(List<Integer> numbers) {
        for (int number : numbers) {
            tree.insert(number);
        }
    }

    public List<BalancedNodeInfo> findBalancedNodes() {
        return analyzer.findNodesWithDifferenceOne(tree.getRoot());
    }

    public int getHeight() {
        return analyzer.getTreeHeight(tree.getRoot());
    }

    public TreeNode findKthLeaf(int k) {
        return analyzer.findKthLeaf(tree.getRoot(), k);
    }

    public boolean deleteNode(int key) {
        return tree.delete(key);
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }
}
