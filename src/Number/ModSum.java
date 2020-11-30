package Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 模数求和：
 * 现给定n个整数，并定义一个非负整数m，且令f(m) = (m%a1)+(m%a2)+...+(m%an)。
 * 此处的X % Y的结果为X除以Y的余数。
 * 现请你找出一个m，求出f(m)的最大值。
 */

/**
 * 分析：
 * 假设 m % a = a - 1; //a-1是能取到的最大余数，即m+1是a的倍数
 * 如果 m+1 是 an 的倍数，则 f(m)可以取到最大值，即f(m)是an的最大公倍数
 * f(m) = (a1-1)+(a2-1)+(a3-1)+...+(an-1) = sum(a)-n;
 */

public class ModSum {
    public static void main(String[] args) throws IOException {
        int sum = 0 ;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        String[] str = br.readLine().split(" ");
        for(int i = 0 ; i < n ; i++){
            num[i] = Integer.parseInt(str[i]);
            sum += num[i];
        }
        sum -= n;
        System.out.println(sum);
    }
}
