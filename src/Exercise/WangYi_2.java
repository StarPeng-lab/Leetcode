package Exercise;

/**
 * 已知摩尔斯电码和字符映射关系如下：
 * A -> 0
 * B -> 1
 * C -> 10
 * D -> 11
 * E -> 100
 * F -> 101
 * G -> 110
 * H -> 111
 * 当前我们获取到了一串01数字字符串，需要进行摩尔斯电码解码，请问共有多少种解码方法？
 *
 * 输入描述: 一行由0和1组成的字符串
 * 输出描述: 一行一个数字表示答案，即解码方法数量
 * 示例1 输入: 11
 *      输出: 2（有D和BB两种解法）
 * 示例2 输入: 100
 *      输出: 3（有E,BAA和CA三种解法）
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class WangYi_2{
    public static void main(String[] args) throws IOException{
        //动态规划求解，注意当遇到字符'1'的时候，有三种翻译的方式
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = br.readLine().toCharArray();
        int n = ch.length;
        int[] dp = new int[n+1];
        dp[n] = 1; //dp数组比ch数组长度多1，并赋初值dp[n]为1，代表1种解法
        for(int i = n-1 ; i >= 0 ; i--){
            dp[i] = dp[i+1]; //单字符，只有一种解法
            if(ch[i] == '1'){ //对于'1'，有双字符和三字符的解法
                if(i+2 <= n) //i<=n-2,双字符
                    dp[i] += dp[i+2];
                if(i+3 <= n) //i<=n-3,三字符
                    dp[i] += dp[i+3];
            }
        }
        System.out.println(dp[0]);
    }
}
