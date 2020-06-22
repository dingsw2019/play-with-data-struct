/**
 * 有序集合和无序集合
 *      有序集合中的元素具有顺序性, (二分搜索树)
 *      无序集合中的元素没有顺序性, 按插入的顺序排列, (链表,哈希)
 *
 * 多重集合
 *      集合中的元素可以重复
 */

import java.util.TreeSet;

public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set = new TreeSet<>();
        // 遍历单词
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            // 一个单词的摩斯码
            for (int i = 0; i < word.length(); i ++)
                res.append(codes[word.charAt(i) - 'a']);

            set.add(res.toString());
        }

        return set.size();
    }
}
