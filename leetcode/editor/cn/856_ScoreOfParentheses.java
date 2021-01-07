package leetcode.editor.cn;
//score-of-parentheses
//给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
// () 得 1 分。 
// AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。 
// (A) 得 2 * A 分，其中 A 是平衡括号字符串。
//
// 示例 1：
// 输入： "()"
//输出： 1
//
// 示例 2：
// 输入： "(())"
//输出： 2
//
// 示例 3：
// 输入： "()()"
//输出： 2
//
// 示例 4：
// 输入： "(()(()))"
//输出： 6
//
// 提示：
// S 是平衡括号字符串，且只含有 ( 和 ) 。 
// 2 <= S.length <= 50 
// 
// Related Topics 栈 字符串 
// 👍 172 👎 0

public class ScoreOfParentheses{
    public static void main(String[] args) {
        Solution solution = new ScoreOfParentheses().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /* 方法一：反冶：对于一个字符串 S，我们将左括号 ( 记为 1，右括号记为 -1，
    如果 S 的某一个非空前缀对应的和为 0，那么这个前缀就是一个平衡括号字符串。否则它的最外层一定有一对左右括号
     */
    public int scoreOfParentheses(String S) {
        return F(S, 0, S.length());
    }

    public int F(String S, int i, int j) {
        //Score of balanced string S[i:j]
        int ans = 0, bal = 0;

        for (int k = i; k < j; ++k) {
            bal += S.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) ans++;
                else ans += 2 * F(S, i + 1, k);
                i = k + 1;
            }
        }
        return ans;
    }
}
    /* 方法二：统计核心的数目
    事实上，我们可以发现，只有 () 会对字符串 S 贡献实质的分数，其它的括号只会将分数乘二或者将分数累加。
    因此，我们可以找到每一个 () 对应的深度 x，那么答案就是 2^x 的累加和。
    public int scoreOfParentheses(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i-1) == '(')
                    ans += 1 << bal; // ans = ans + (1<< bal);
            }
        }
        return ans;
    }*/

    /* 方法三：栈；
    我们用一个栈来维护当前所在的深度，以及每一层深度的得分。当我们遇到一个左括号 ( 时，我们将深度加一，并且新的深度的得分置为 0。
    当我们遇到一个右括号 ) 时，我们将当前深度的得分乘二并加到上一层的深度。这里有一种例外情况，如果遇到的是 ()，那么只将得分加一。
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0);

        for (char c: S.toCharArray()) {
            if (c == '(')
                stack.push(0);
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }*/

    /* myself: 利用数据结构，栈，这种方法较为耗时
    //泛型用Object的话，push时可以直接push(1)；取栈顶元素时直接强制类型转换为int即可，即 (int)stack.pop();
    public int scoreOfParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        int sum = 0;
        int len = S.length(); //比如 (())
        for(int i=0 ; i<len ; i++){
            char ch = S.charAt(i);
            if(ch == '('){
                stack.push(ch); // stack：((
            }else{
                char temp = stack.pop();
                if(temp == '('){ // stack: (
                    stack.push((char)(1 + 48)); // stack: (1
                }else{
                    sum += temp -'0';
                    while(stack.peek() != '('){
                        sum += stack.pop() - '0';
                    }
                    stack.pop(); //弹出'('
                    count = sum*2;
                    stack.push((char)(count + 48));
                    sum = 0;
                    count = 0;
                }

            }
        }

        int result = stack.pop() - '0';
        while(!stack.isEmpty()){
            result += stack.pop() - '0';
        }
        return result;
    }
  }*/

//leetcode submit region end(Prohibit modification and deletion)

