package leetcode.editor.cn;
//longest-substring-without-repeating-characters
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2:
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3:
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// 示例 4:
//输入: s = ""
//输出: 0
//
// 提示：
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 5100 👎 0

public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        //方法一：用map结构来存储每一个字符上一次出现的位置（如果没有上一次出现的位置，由于类型是Integer，因此判断是否为null，或者用getOrDefault方法设置未出现时的默认值）
    /*
    public int lengthOfLongestSubstring(String s) {
        if(s == null)
            return 0;
        char[] ch = s.toCharArray();
        if(ch.length == 0)
            return 0;

        Map<Character,Integer> map = new HashMap<>(); //用来保存每一个字符上一次出现的位置
        map.put(ch[0],0);

        int li = 0; //以i-1位字符结尾的最长不重复字符串的开始索引（最左索引）
        int max = 1; //无重复字符串的最长子串长度

        int len = ch.length;

        for(int i = 1 ; i < len ; i++){
            //Integer pi = map.getOrDefault(ch[i],-1); //如果get(ch[i]) == null 则返回-1
            //if(li <= pi){
            //    li = pi + 1;
            //}
            Integer pi = map.get(ch[i]);
            if(pi != null && li <= pi){
                li = pi +1;
            }

            map.put(ch[i],i); //存储这个字符出现的位置
            max = Math.max(max,i-li+1); //求出最长不重复子串的长度
        }
        return max;
    }
    */

    //方法二：由于存储的都是单字节字符，范围在0~128，用数组preIndex长度表示字符范围，数组的值chars[i] 对应数组的位置preIndex[chars[i]]
    public int lengthOfLongestSubstring(String s) {
        if(s == null)
            return 0;
        char[] chars = s.toCharArray();
        if(chars.length == 0)
            return 0;

        int[] preIndex = new int[128];
        for(int i = 0 ; i < preIndex.length ; i++){
            preIndex[i] = -1; //数组的默认值初始化为-1，表示字符上一次没有出现
        }
        preIndex[chars[0]] = 0; //先给第一个字符赋值，li,max,preIndex[chars[i]]
        int li = 0;
        int max = 1;

        for(int i = 1 ; i < chars.length ; i++){
            int pi = preIndex[chars[i]];
            if(li <= pi){
                li = pi + 1;
            }
            preIndex[chars[i]] = i; //存储这个字符出现的位置
            max = Math.max(max,i-li+1);
        }

        return max;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}