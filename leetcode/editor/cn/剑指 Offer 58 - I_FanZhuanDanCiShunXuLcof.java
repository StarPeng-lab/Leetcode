package leetcode.editor.cn;
//fan-zhuan-dan-ci-shun-xu-lcof
//输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
//则输出"student. a am I"。 
//
// 示例 1：
// 输入: "the sky is blue"
//输出: "blue is sky the"
//
// 示例 2：
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
//
// 示例 3：
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
// 说明：
// 无空格字符构成一个单词。
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
//
// 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/ 
//
// 注意：此题对比原题有改动 , s 中 可以不存在 单词
// Related Topics 字符串 
// 👍 72 👎 0

public class FanZhuanDanCiShunXuLcof{
    public static void main(String[] args) {
        Solution solution = new FanZhuanDanCiShunXuLcof().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        if(s == null)
            return "";

        //1、去除多余空格，得到有效字符串
        char[] ch = s.toCharArray();
        int n = ch.length;
        int len = 0; //有效字符串的长度
        int cur = 0 ; //可以保存有效字符的位置
        boolean space = true; //前一个位置是否是空格（true-是空格，false-是字符）

        for(int i = 0 ; i < n ; i++){
            if(ch[i] != ' '){ //ch[i]是字符
                ch[cur++] = ch[i]; //将ch[i]的字符保存到ch[cur]位置
                space = false;
            }else if(space == false){ //ch[i]是空格，并且前一个位置是字符
                ch[cur++] = ch[i]; //将ch[i]的空格保存到ch[cur]位置
                space = true;
            }
        }

        len = space ? cur-1 : cur; //如果space为true，那么有效字符串的最后一位是空格，还要cur-1；false-有效字符串最后一位是字符，cur即为len

        if(len <= 0)
            return ""; //输入字符为”     “

        //2、翻转-先翻转整个有效字符串，再翻转单个单词
        reverse(ch,0,len);

        int preSpace = -1; //哨兵-假想的空格位置；我们通过前后空格位置得出单词的范围
        for(int i = 0 ; i < len ; i++){
            if(ch[i] != ' ')
                continue;
            reverse(ch,preSpace+1,i); //翻转 [preSpace+1,i) 位置的单词
            preSpace = i;
        }
        reverse(ch,preSpace+1,len); //翻转最后一个单词（for循环中不直接用i<=len，是因为整个ch字符数组中，ch[len]位置上不一定是空格，我们在消除多余空格时，是直接将有效字符覆盖原字符，因此字符数组后面可能有残留字符）

        return new String(ch,0,len);
    }

    private void reverse(char[] ch , int li , int ri){ //翻转字符数组中 [li,ri) 范围的字符
        ri--;
        while(li < ri){
            char tmp = ch[li];
            ch[li] = ch[ri];
            ch[ri] = tmp;

            li++;
            ri--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}