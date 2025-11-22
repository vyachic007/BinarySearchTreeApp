package model;

import lombok.Getter;

@Getter
public class BalancedNodeInfo {
    private final int nodeKey;
    private final int leftCount;
    private final int rightCount;
    private final int difference;

    public BalancedNodeInfo(int nodeKey, int leftCount, int rightCount) {
        this.nodeKey = nodeKey;
        this.leftCount = leftCount;
        this.rightCount = rightCount;
        this.difference = Math.abs(leftCount - rightCount);
    }

    @Override
    public String toString() {
        return String.format("Узел: %d (Левых: %d, Правых: %d, Разница: %d)",
                nodeKey, leftCount, rightCount, difference);
    }
}
