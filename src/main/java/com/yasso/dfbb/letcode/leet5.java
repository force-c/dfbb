package com.yasso.dfbb.letcode;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/1 14:43
 */
public class leet5 {

    public static void main(String[] args) {
        String string = new Solution().longestDiverseString(2, 3, 4);
        System.out.println(string);
    }

    static class Solution {
        public String longestDiverseString(int a, int b, int c) {
            StringBuffer sbu = new StringBuffer();
            sbu.append(this.appendS(a)).append(this.appendS(b)).append(this.appendS(c));
            return sbu.toString();
        }

        public StringBuffer appendS(int num) {
            StringBuffer sbu = new StringBuffer();
            for (int i = 0; i < num; i++) {
                sbu.append(num);
            }
            return sbu;
        }
    }
}
