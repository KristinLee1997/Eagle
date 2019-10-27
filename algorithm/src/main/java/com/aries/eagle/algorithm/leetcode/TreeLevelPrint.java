package com.aries.eagle.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Kristin
 * @Date: 2019/10/20 17:10
 * @Comment： LeetCode102-层序遍历二叉树
 */
public class TreeLevelPrint {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return levels;
        }
        helper(root, 0);
        return levels;
    }

    private void helper(TreeNode node, Integer level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<Integer>());
        }
        levels.get(level).add(node.val);
        if (node.left != null) {
            helper(node.left, level + 1);
        }
        if (node.right != null) {
            helper(node.right, level + 1);
        }
    }
}
