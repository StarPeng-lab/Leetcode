package leetcode.editor.cn;
//reverse-words-in-a-string
//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 说明：
// 无空格字符构成一个 单词 。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
// 示例 1：
// 输入："the sky is blue"
// 输出："blue is sky the"
//
// 示例 2：
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
//
// 示例 3：
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
// 示例 4：
// 输入：s = "  Bob    Loves  Alice   "
// 输出："Alice Loves Bob"
//
// 示例 5：
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
//
// 提示：
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
//
// 进阶：
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 字符串 
// 👍 288 👎 0

public class ReverseWordsInAString{
    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAString().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        if(s == null)
            return " ";

        //1、消除多余空格，得到有效字符串
        char[] ch = s.toCharArray();
        int n = ch.length;
        int len = 0 ; //有效字符串的长度
        int cur = 0; //指向当前存放有效字符位置
        boolean space = true; // 表示前一个位置是否是空格（true-前一个是空格；false-前一个是字符），初始化为true
        for(int i = 0 ; i < n ; i++){ //指向前进位置
            if(ch[i] != ' '){ //此位置是字符
                ch[cur++] = ch[i];
                space = false;
                len++;
            }else if(space == false){ //此位置是空格，并且前一个位置是字符（ch[i-1]是字符，ch[i]是空格）
                ch[cur++] = ch[i];
                space = true;
                len++;
            }
        }
        len = space ? cur-1 : cur; //true-有效字符串的末尾有空格，即cur指向的位置；false-有效字符串末尾无空格，cur就是有效字符串长度

        //2、翻转
        //先翻转整个有效字符串，再单个翻转单词
        reverse(ch,0,len);

        int preSpace = -1; //哨兵，有效字符串的-1位置 为 前一个空格位置
        for(int i = 0 ; i < len ; i++){
            if(ch[i] != ' ')
                continue;
            reverse(ch,preSpace+1,i); //翻转单个单词，单个单词在 [preSpace+1 , i)中
            preSpace = i;
        }
        reverse(ch,preSpace+1,len); //翻转最后一个单词

        return new String(ch,0,len); //将有效字符数组重新new一个字符串
    }

    private void reverse(char[] ch , int li , int ri){ //翻转字符数组 [left_i,right_i) 位置的字符
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