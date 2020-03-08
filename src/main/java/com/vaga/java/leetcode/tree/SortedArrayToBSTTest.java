package com.vaga.java.leetcode.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author vaga
 * @version 2020/3/8 7:41 下午
 * @description 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class SortedArrayToBSTTest extends BaseTreeTest {
    @DataProvider(name = "data")
    public Object[][] dataset() {
        return new Object[][]{
                {new int[]{-10,-3,0,5,9}},
//                {new int[]{-10,-3,0,1,5,9}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums) {
        sortedArrayToBST(nums);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode node = null;
        if (null == nums || nums.length < 1) return node;

        int len = nums.length;
        int mid = len % 2 == 0 ? len / 2 - 1 : len / 2;
        int left = mid;
        int right = mid;
        node = new TreeNode(nums[mid]);
        TreeNode leftNode = node;
        TreeNode rightNode = node;
        while(--left >= 0 && ++right < len) {
            if (left >= 0) {
                leftNode.left = new TreeNode(nums[left]);
                leftNode = leftNode.left;
            }

            if (right < len) {
                rightNode.right = new TreeNode(nums[right]);
                rightNode = rightNode.right;
            }
        }

        if (right < len - 1) {
            rightNode.right = new TreeNode(nums[len - 1]);
        }

        return node;
    }
}
