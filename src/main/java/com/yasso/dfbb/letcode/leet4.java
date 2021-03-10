package com.yasso.dfbb.letcode;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/1 13:34
 */
public class leet4 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
    }

    static class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int[] dp = new int[nums.length];
            int sum = 0;
            for (int i = 2; i < dp.length; i++) {
                if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                    dp[i] = 1 + dp[i - 1];
                    sum += dp[i];
                }
            }
            return sum;
        }
    }
}
