package com.galaxy.mecury.tree;

public class MyString {
    public static int subString(String str, String subString) {
        int lenA = str.length();
        int lenB = subString.length();
        int i = 0;
        int j = 0;

        while (i < lenA && j < lenB) {
            if (str.charAt(i) == subString.charAt(j)) {
                i ++;
                j ++;
            } else {
                j = 0;
                i = i - j + 1;
            }

            if (j == lenB) {
                return i - lenB;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(subString("cc*abcde****************axb", "abcde"));
    }
}
