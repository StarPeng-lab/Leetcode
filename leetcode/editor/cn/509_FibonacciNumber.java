package leetcode.editor.cn;
//fibonacci-number
//斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是： 
//
// F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
// 
//
// 给定 N，计算 F(N)。 
//
// 
//
// 示例 1： 
//
// 输入：2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
// 
//
// 示例 2： 
//
// 输入：3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
// 
//
// 示例 3： 
//
// 输入：4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
// 
//
// 
//
// 提示： 
//
// 
// 0 ≤ N ≤ 30 
// 
// Related Topics 数组 
// 👍 183 👎 0

public class FibonacciNumber{
    public static void main(String[] args) {
        Solution solution = new FibonacciNumber().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int fib(int n) {
        //方法一，复杂度：O(2^n)，耗时8ms （n是指数据规模）
        /*
        if(n<=1)
            return n;
        return fib(n-1)+fib(n-2);*/

        //方法二：复杂度：O(n)，耗时0ms
        if(n<=1)
            return n;
        int first = 0;
        int second = 1;
        /*int sum = 0;
        for(int i=0 ; i<n-1; i++){ //f(n)，前面需要加n-1次；如n=3,过程为0,1,1,2----sum=0+1=1,sum=1+1=2
            sum = first + second;
            first = second;
            second = sum;
        }*/
        while(n-- > 1){
            second += first;
            first = second - first;
        }
        return second;

        //方法三，复杂度：O(1)，耗时0ms
        //F(n)=c1*x1^n + c2*x2^n;  x1=(1+根号5)/2, x2=(1-根号5)/2;  c1=1/根号5, c2=-1/根号5
        //F(n)= 1/根号5 *[(x1^n)-(x2^n)]
        /*
        double c = Math.sqrt(5);
        return (int)((Math.pow((1+c)/2,n)-Math.pow((1-c)/2,n))/c);*/
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}