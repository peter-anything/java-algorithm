package com.galaxy.mecury.tree;

public class DynamicProgramming {
    /**
     * 将带求解的问题分解为若干个子问题，按顺序求解子问题，前一个问题的解，为后一个问题的求解提供有用的信息。
     * 在求解任一子问题时，列出各种可能的局部解，通过决策保留那些有可能达到最优的局部解，丢弃其他局部解。
     * 依次解决各种子问题，最后一个子问题就是初始问题的解。
     * 由于动态规划算法解决的问题是有重叠子问题，为了减少重复计算，对每一个子问题只解一次，将其不同阶段的不同状态保存在一个二维数组中。
     * 动态规划算法和分治算法类似，不同在于：适合于动态规划算法求解的问题，经分解后得到的子问题，往往不是互相独立的，而是下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步求解的。
     * 适合场景：
     * 1）最优化原理：该问题的最优解所包含的子问题的解也是最优的
     * 2）无后效性：某阶段状态一旦确定，就不受这个状态以后决策的影响。即某状态以后的过程不会影响到以前的状态，只与当前状态有关
     * 3）有重叠子问题；即子问题之间不互相独立，一个子问题的解在下一个决策中可能被多次使用到。
     * */
    public static void main(String[] args) {
//        String A = "1A2C3D4B56";
//        String B = "B1D23CA45B6A";
//        System.out.println(findLCS(A, B));

        String C = "112119990999";
        System.out.println(C.length());
        System.out.println(findMaxPalindrome2(C));
        C.toLowerCase();
        C.toUpperCase();

        int p[]={30,35,15,5,10,20,25};
        MatrixChainOrder(p);
    }

    public static void MatrixChainOrder(int p[]) {
        int n = p.length -1;
        //存放计算代价
        int[][] m = new int[n][n];
        //存放分割标号k的值
        int[][] s = new int[n-1][n];
        //链长为1的最小计算代价为0.
        for(int i = 0; i < n; i++){
            m[i][i] = 0;
        }
        //链长从2到n遍历
        for(int L = 2; L <= n; L++){
            //遍历的范围，逐渐缩小范围，从第一个开始。
            for(int i = 0; i < n - L + 1; i++){
                //每次区间加1，遵从自底向上的递归形式求解。
                int j = i + L -1;
                //初始化m[i,j]的值为无限大。
                m[i][j] = Integer.MAX_VALUE;
                for(int k=i; k <=j-1; k++){
                    //递归求解公式
                    int q = m[i][k] + m[k+1][j] + p[i]*p[k+1]*p[j+1];
                    if(q < m[i][j]){
                        //保存序列代价的结果
                        m[i][j] = q;
                        //保存分割点k的值
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static String findMaxPalindrome2(String A) {
        int len = A.length();
        boolean dp[][] = new boolean[len + 1][len + 1];

        int left = 0;
        int right = 0;
        for (int i = len; i >= 1; i --) {
            dp[i][i] = true;
            for (int j = i + 1; j <= len; j ++) {
                dp[i][j] = A.charAt(i - 1) == A.charAt(j - 1) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j]  && (right - left) < j - i) {
                    left = i - 1;
                    right = j;
                }
            }
        }

        return A.substring(left, right);
    }

    public static String findMaxPalindrome(String A) {
        char[] cArr = A.toCharArray();
        int start = 0;
        int end = 0;
        int maxLen = 0;
        for (int i = 0; i < cArr.length; i++) {
            // 像两边拓展
            int len1 = extandPalindrome(cArr, i, i);
            int len2 = 0;
            if (i < cArr.length - 1 && cArr[i] == cArr[i + 1]) {
                len2 = extandPalindrome(cArr, i, i + 1);
            }
            int len = len1 > len2 ? len1 : len2;
            if (len > maxLen) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
                maxLen = len;
            }
        }

        return String.valueOf(cArr, start, maxLen);
    }

    public static int extandPalindrome(char[] cArr, int left, int right) {
        while (left >= 0 && right < cArr.length && cArr[left] == cArr[right]) {
            left --;
            right ++;
        }

        return right - left - 1;
    }

    public static boolean isPalindrome(char[] cArr, int i, int j) {
        if (i == j) return true;
        boolean result = true;
        while (i <= j) {
            if (cArr[i] == cArr[j]) {
                j --;
                i ++;
            } else {
                result = false;
                break;
            }
        }

        return result;
    }

    public static String findLCS(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int dp[][] = new int[lenA + 1][lenB + 1];

        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j ++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        int i = lenA;
        int j = lenB;
        StringBuffer stringBuffer = new StringBuffer();
        while ((i != 0) && (j != 0)) {
            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                stringBuffer.append(A.charAt(i - 1));
                i --;
                j --;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    j--;
                } else {
                    i --;
                }
            }
        }

        return stringBuffer.reverse().toString();
    }

}
