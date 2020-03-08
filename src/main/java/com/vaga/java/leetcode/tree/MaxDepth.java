package com.vaga.java.leetcode.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/6 3:53 下午
 * @description 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class MaxDepth extends BaseTreeTest{

    @DataProvider(name = "data")
    public Object[][] dataset() {
        TreeNode one = createData();


        return new Object[][]{
                {one, 3},
        };
    }

    private TreeNode createData() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    @Test(dataProvider = "data")
    public void test(TreeNode root, int expected) {
        assertThat(leetcodeSolution(root)).isEqualTo(expected);
    }

    private int leetcodeSolution(TreeNode root) {
        if (null == root) return 0;
        int leftDepth = leetcodeSolution(root.left);
        int rightDepth = leetcodeSolution(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


}
