package com.cifor.practice.leetcode.arithmetic;

public class Question {

    /*给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
        注意：字符串长度 和 k 不会超过 10^4。
        https://leetcode-cn.com/problems/longest-repeating-character-replacement/
    示例 2：
        输入：s = "AABABBA", k = 1
        输出：4
        解释：
        将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
        子串 "BBBB" 有最长重复字母, 答案为 4。
    */
    public static Object q1(String s, int k) {
        char[] chars = s.toCharArray();
        int flipIndex = 0;
        int currentIndex = 1;
        int tolerate = 0;
        int len = 0;
        int lastIndex = 0;
        while (currentIndex < chars.length) {
            char lastChar = chars[flipIndex];
            int tmpFlipIndex = 0;
            for (; tolerate <= k && currentIndex < chars.length; currentIndex++) {
                if (chars[currentIndex] != lastChar) {
                    if (tmpFlipIndex == 0) {
                        tmpFlipIndex = currentIndex;
                        flipIndex = tmpFlipIndex;
                    }
                    tolerate++;
                    if(tolerate > k){
                        currentIndex --;
                        break;
                    }
                }
            }
            tolerate = 0;
            if(currentIndex >chars.length - 1){
                currentIndex = chars.length - 1;
            }
            len = len > (currentIndex  - (lastIndex) +1 ) ? len : (currentIndex  - (lastIndex) +1);
            if (chars.length - currentIndex - 1 < len) {
                break;
            }
            currentIndex = flipIndex;
            lastIndex = currentIndex;
        }
        return len;
    }

}
