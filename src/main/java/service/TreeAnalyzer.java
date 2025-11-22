package service;

import model.BalancedNodeInfo;
import model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeAnalyzer {

    public List<BalancedNodeInfo> findNodesWithDifferenceOne(TreeNode root) {
        List<BalancedNodeInfo> result = new ArrayList<>();
        findNodesPostorder(root, result);
        return result;
    }

    private int findNodesPostorder(TreeNode node, List<BalancedNodeInfo> result) {
        if (node == null) {
            return 0;
        }

        int leftCount = findNodesPostorder(node.getLeft(), result);
        int rightCount = findNodesPostorder(node.getRight(), result);

        if (Math.abs(leftCount - rightCount) == 1) {
            result.add(new BalancedNodeInfo(node.getKey(), leftCount, rightCount));
        }

        return leftCount + rightCount + 1;
    }

    public TreeNode findKthLeaf(TreeNode root, int k) {
        int[] counter = {0};
        return findKthLeafInorder(root, k, counter);
    }

    private TreeNode findKthLeafInorder(TreeNode node, int k, int[] counter) {
        if (node == null) {
            return null;
        }

        TreeNode leftResult = findKthLeafInorder(node.getLeft(), k, counter);
        if (leftResult != null) {
            return leftResult;
        }

        if (node.isLeaf()) {
            counter[0]++;
            if (counter[0] == k) {
                return node;
            }
        }

        return findKthLeafInorder(node.getRight(), k, counter);
    }

    public  int getTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodeStack = new Stack<>(); //узлы
        Stack<Integer> depthStack = new Stack<>(); //текущая глубина

        nodeStack.push(root);
        depthStack.push(1);

        int maxHeight = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode current = nodeStack.pop();
            int currentDepth = depthStack.pop();

            maxHeight = Math.max(maxHeight, currentDepth);

            if (current.getRight() != null) {
                nodeStack.push(current.getRight());
                depthStack.push(currentDepth + 1);
            }

            if (current.getLeft() != null) {
                nodeStack.push(current.getLeft());
                depthStack.push(currentDepth + 1);
            }
        }

        return maxHeight;
    }
}
