package com.galaxy.mecury.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 分治算法：快速排序、归并排序、大整数乘法、二分查找、递归
 * 基本概念：把一个复杂的问题分为若干个相同或相似的子问题，再把子问题分成更小的子问题。。。，直到最后子问题可以简单的直接求解，原问题的解即子问题的解的合并
 * 类似Fork/Join 或者 Map-Reduce
 * 适用场景：
 * 1) 当问题规模缩小到一定的程度就可以很容易解决
 * 2）该问题可以分解为若干个规模较小的相同问题
 * 3）该问题分解出的若干个子问题的解可以合并为该问题的解
 * 4）每个子问题都是独立的，相互之间没有交集
 */
public class DivideAndConquer {
    public static void main(String[] args) {
        int[] a = { -2, 11, -4, 13, -5, -2 };// 最大子序列和为20
        int[] b = { -6, 2, 4, -7, 5, 3, 2, -1, 6, -9, 10, -2 };// 最大子序列和为16
        System.out.println(maxSubSum4(a));
        System.out.println(maxSubSum4(b));
    }

    // Time complexity: n ^ 2
    public static int maxSubSum1(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i ++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    // 2分logn，for循环n，时间复杂度O(nlogn)
    public static int maxSumRec(int[] a, int left, int right) {
        if (left == right) {
            if (a[left] > 0) return a[left];

            return 0;
        }

        List<Integer> leftArrayList = new ArrayList<>();

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            leftArrayList.add(a[i]);

            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];

            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    public static int max3(int a, int b, int c) {
        return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }

    // O(n)
    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum;
    }
}
