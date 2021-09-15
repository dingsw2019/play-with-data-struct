/**
 * leetcode 387问题
 */
class Solution {

    private int[] freq;

    public int firstUniqChar(String s) {

        freq = new int[26];

        // 统计每个单词出现的频率
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a'] ++;

        // 查找第一个频率为1的字母, 并返回索引
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;

        // 没找到, 返回 -1
        return -1;
    }
}