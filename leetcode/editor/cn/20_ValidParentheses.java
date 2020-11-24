package leetcode.editor.cn;
//valid-parentheses
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
// 有效字符串需满足：
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1:
// 输入: "()"
//输出: true
//
// 示例 2:
// 输入: "()[]{}"
//输出: true
//
// 示例 3:
// 输入: "(]"
//输出: false
//
// 示例 4:
// 输入: "([)]"
//输出: false
//
// 示例 5:
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 2002 👎 0

public class ValidParentheses{
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        //方式一：
        /*
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()","");
            s = s.replace("[]","");
            s = s.replace("{}","");
        }
        return s.isEmpty();
        */

        //方式二：
        //如果是左括号，则入栈；
        //碰到右括号，则出栈：若此时栈为空，则false;
        //                若此时栈不为空，则匹配左右括号，不匹配则返回false
        //循环字符串结束后，若此时栈为空，则返回true；若栈不为空，则返回false
        /*
        Stack<Character> stack = new Stack<Character>();
        int length = s.length();
        for(int i = 0 ; i < length ; i++){
            char c = s.charAt(i);
            if(c=='(' || c=='[' || c=='{'){ //如果是左括号，则入栈；
                stack.push(c);
            }else { //如果是右括号，则开始匹配
                if (stack.isEmpty()) { //若字符为)]，则没有左括号，栈里面为空
                    return false;
                }
                char left = stack.pop(); //开始匹配字符串
                if (left == '(' && c != ')')
                    return false;
                if (left == '[' && c != ']')
                    return false;
                if (left == '{' && c != '}')
                    return false;
            }
        }
        return stack.isEmpty();
        */

        //方式三：用HashMap存储匹配的括号
        Stack<Character> stack = new Stack<>();

        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');

        int len = s.length();
        for(int i = 0 ; i < len ; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty())
                    return false;

                char left = stack.pop();
                if(c!=map.get(left))
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}