package leetcode.editor.cn;
//reverse-integer
//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1:
// 输入: 123
//输出: 321
//
// 示例 2:
// 输入: -123
//输出: -321
//
// 示例 3:
// 输入: 120
//输出: 21
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学 
// 👍 2375 👎 0

public class ReverseInteger{
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        /* 方法一：强制类型转换判断是否溢出
        long res = 0 ; // 定义为long，如果是int类型，则超出int范围后会被截取再赋给res，因此我们直接给long的范围。再转int类型看是否相等
        while(x != 0){
            res = res*10 + x%10;
            x /= 10;
        }
        return (int)res == res ? (int)res : 0; //把最后结果转成int 和 long去比， 如果溢出了 int 肯定不等于long ；不溢出的时候 int 就会等于 long
        */

        //方法二：求反的数，原路返回，看是否与原值相等，来判断是否溢出
        int res = 0 ;
        while(x != 0){
            int newres = res*10 + x%10;
            if((newres-x%10)/10 != res){ //res存着的是上一轮while中还未溢出的值，newres存着的是这轮可能溢出的值
                return 0 ;
            }
            res = newres;
            x /= 10;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}