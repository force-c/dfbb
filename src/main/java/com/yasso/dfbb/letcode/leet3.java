package com.yasso.dfbb.letcode;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/1 12:55
 */
public class leet3 {




    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 2, 3, 4});
        int i = numArray.sumRange(1, 3);
        System.out.println(i);

    }


    /**
     *
     */
    static class NumArray {
        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            while (i <= j) {
                sum += this.nums[i];
                i++;
            }
            return sum;
        }
    }
}
