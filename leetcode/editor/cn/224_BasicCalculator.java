package leetcode.editor.cn;
//basic-calculator
//实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
// 示例 1：
//输入：s = "- 1 + 1"
//输出：0
//
// 示例 2：
//输入：s = " 2-1 + 2 "
//输出：3
//
// 示例 3：
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
//
// 提示：
// 1 <= s.length <= 3 * 105 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 数学 
// 👍 324 👎 0

public class BasicCalculator{
    public static void main(String[] args) {
        Solution solution = new BasicCalculator().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /*
          方法一：栈+正序遍历字符串
          解决 - 结合律的问题的一个分厂简单的方法就是使将 - 运算符看作右侧操作数的大小。一旦我们将 - 看作操作数的大小，则表达式将只剩下一个操作符。就是 + 运算符，而 + 是遵循结合律的。
          例如，A-B-C 等于 A + (-B) + (-C)。
          重写以后的表达式将遵循结合律，所以我们从左或从右计算表达式都是正确的。
          我们需要注意的是给定的表达式会很复杂，即会有嵌套在其他表达式的表达式。
          即 (A - (B - C))，我们需要 B-C 外面的 - 号与 B-C 关联起来，而不是仅仅与 B 关联起来。
          我们可以通过遵循前面的基本练习并将符号与其右侧的表达式关联来解决此问题。然而，我们将采用的方法有一个小的转折，因为我们将在运行中评估大多数表达式。这减少了推送和弹出操作的数量。
            【算法】：
            正序迭代字符串。
            操作数可以由多个字符组成，字符串 "123" 表示数字 123，它可以被构造为：123 >> 120 + 3 >> 100 + 20 + 3。如果我们读取的字符是一个数字，则我们要将先前形成的操作数乘以 10 并于读取的数字相加，形成操作数。
            每当我们遇到 + 或 - 运算符时，我们首先将表达式求值到左边，然后将正负符号保存到下一次求值。
            如果字符是左括号 (，我们将迄今为止计算的结果和符号添加到栈上，然后重新开始进行计算，就像计算一个新的表达式一样。
            如果字符是右括号 )，则首先计算)左侧的表达式。则产生的结果就是刚刚结束的子表达式的结果。
            如果栈顶部有符号，则将此结果与符号相乘，继续取出栈顶元素进行运算。
         */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int operand = 0;
        int result = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                operand = 10 * operand + (int) (ch - '0');
            } else if (ch == '+') {
                result += sign * operand;
                sign = 1;
                operand = 0;
            } else if (ch == '-') {
                result += sign * operand;
                sign = -1; //保留给下一次遍历时，运算
                operand = 0;
            } else if (ch == '(') {
                stack.push(result); //将上一次遍历的运算结果（左侧表达式的结果）压入栈
                stack.push(sign); //将上一次遍历的运算结果的符号压入栈
                sign = 1;
                result = 0;
            } else if (ch == ')') {
                result += sign * operand; //记录上一次遍历的结果，用到上一次保存的运算符 （当前表达式结果）
                result *= stack.pop(); // 取出栈中的运算符
                result += stack.pop(); // 取出栈中的结果，与这次结果进行运算
                operand = 0;
            }
        }
        return result + (sign * operand);

    }


     /* 方法二：栈+逆序遍历字符串
    //这里只需实现简单的加减运算，虽然加减运算是同级的，但是减法需要注意顺序
    //这里用的方法是，通过反转字符串，然后再按需添加到栈中，我们将字符串从右到左放入栈中，并从左到右正确的计算表达式
    //准备一个栈，【从右到左】遍历字符串s，
        //遇到' ' ，就忽略，继续for循环
        //遇到数字就将连续的数字拼接为真正的数字，如21，这是两个字符，需要拼接为数字21，并入栈
        //遇到'-','+',')'，就直接入栈；
        //遇到'('，就开始从栈顶弹出元素，如果弹出为字符，则继续弹出新的栈顶元素代表数字的正负；如果弹出为数字，则直接使用
           //此时从栈中继续弹出元素，即符号'-'或'+'；再弹一个元素，即数字；之后就开始运算，并将运算结果压入栈
    //字符串s遍历完毕，注意，如果s的第一个（遍历的最后一个）为数字，要压入栈；此时若栈中不为空，则将栈中元素按顺序出栈并运算
    //注意：字符串s对数字的拼接；
    //     栈中存放的元素类型多样，因此用Object，注意类型转换；注意-2+1和2+1中，从栈顶弹出的元素会有多种类型，要判断是java.lang.Character还是java.lang.Integer
    public int calculate(String s) {
        Stack<Object> stack = new Stack<>();
        int len = s.length();
        int n = 0;
        int operand = 0;
        int result = 0;
        for(int i = len-1 ; i >= 0 ; i--){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) { //判断ch是否为数字
                operand = (int)Math.pow(10,n) * (ch-'0') + operand; //这里的ch-'0'要用括号包着，否则可能内存溢出
                n++;
            }else if(ch != ' '){

                if(n != 0){ //说明不是数字，并且数字已拼接完成，需要压入栈
                    stack.push(operand);
                    n = 0 ;
                    operand = 0 ;
                }

                if(ch == '('){ // 从栈中弹出元素，进行运算，运算完毕后，先弹出'('，再将运算结果压入栈
                    result = getCount(stack);
                    stack.pop();
                    stack.push(result);
                }else{ //')'、'-'、'+'，直接压入栈
                    stack.push(ch);
                }

            }
        }

        if(n != 0){ //遍历到s的第一个位置，为数字，此时未进入for循环中的else分支，因此这个数字没有入栈
            stack.push(operand);
        }

        return getCount(stack); //最后直接从栈中弹出元素，得到最终结果
    }
    public int getCount(Stack<Object> stack){
        int result = 0;

        if(!stack.isEmpty()){//栈非空，先取出栈顶元素，注意判断栈顶元素的类型；注意栈中可能只有一个元素，此时这个元素即为运算结果
            String type = (stack.peek()).getClass().getName(); //stack.peek(),得到的数据类型为Object
            if(type.equals("java.lang.Integer")){ //2+1，取2
                result = (int)stack.pop(); //虽然+=会进行强制类型转换，但stack.pop()是Object类型，是所有类的父类，而Integer是int的包装类，这里不能对Object直接类型转换为int
                //result = ((Integer)stack.pop()).intValue();
            }else{ //-2+1，取-2
                char sign = (char)stack.pop();
                if(sign == '+'){
                    result += (int)stack.pop();
                }else if(sign == '-'){
                    result -= (int)stack.pop();
                }
            }
        }

        while(!stack.isEmpty() && (char)stack.peek() != ')'){//遇到 栈为空 或 ')'，就退出循环
            char sign = (char)stack.pop();
            if(sign == '+'){
                result += (int)stack.pop();
            }else if(sign == '-'){
                result -= (int)stack.pop();
            }
        }

        return result;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}