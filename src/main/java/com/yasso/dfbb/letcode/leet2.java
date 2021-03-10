package com.yasso.dfbb.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/1 12:54
 */
public class leet2 {


    public static void main(String[] args) {

        String[] words = new String[]{"no", "on", "te"};
        String[] puzzles = new String[]{"one","twoe","three"};
        List<Integer> numOfValidWords = findNumOfValidWords2(words, puzzles);
        System.out.println("==============================");
        numOfValidWords.forEach(f -> System.out.println(f));
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> list = new ArrayList<>();
        int k = puzzles.length;
        boolean b1 = false;
        boolean b2 = true;
        while (k > 0) {
            String puzzle = puzzles[puzzles.length - k];
            Integer num = 0;
            for (String word : words) {
                if (word.contains(puzzle.substring(0, 1)))
                    b1 = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!puzzle.contains(String.valueOf(word.charAt(i))))
                        b2 = false;
                }
                if (b1 && b2) {
                    System.out.println(word + " 可以作为 " + puzzle + " 的谜底");
                    num++;
                }
                b1 = false;
                b2 = true;
            }
            list.add(num);
            num = 0;
            k--;
        }
        return list;
    }

    public static List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        List<Integer> list = new ArrayList<>();
        int k = puzzles.length;
        while (k > 0) {
            String puzzle = puzzles[puzzles.length - k];
            Integer num = 0;
            for (String word : words) {
                if (word.contains(puzzle.substring(0, 1))) {
                    boolean b = true;
                    for (int i = 0; i < word.length(); i++) {
                        if (!puzzle.contains(String.valueOf(word.charAt(i)))) {
                            b = false;
                        }
                    }
                    if (b) {
                        System.out.println(word + " 可以作为 " + puzzle + " 的谜底");
                        num++;
                    }
                }
            }
            list.add(num);
            num = 0;
            k--;
        }
        return list;
    }
}
