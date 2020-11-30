package Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 美妙的约会：
 * 牛牛和妞妞在一天晚上决定一起去看一场情人节演唱会，可是由于这场演唱会实在太出名了，有很多情侣都来观看，牛牛和妞妞不小心被人流冲散了！
 * 维持秩序的人决定，让大家排成一列，相邻两个进去的人（2k-1和2k，k为正整数）坐在相邻座位。但是现在的队伍乱糟糟的，有很多情侣都不在相邻位置。维持秩序的人同意让情侣们跟相邻的人交换位置，直到所有情侣都在2k-1和2k位置上为止。
 * 但是维持秩序的人很没有耐心，所以需要最少的交换次数，你能帮情侣们算出这个次数吗？
 */

/**
 * 思路：
 * n对情侣，先把 2n 个人都存入集合，从第一个i=0开始遍历
 * 从最后面开始往前找到与第一个相同的位置 index
 * 交换位置使得情侣的位置相邻，index-i-1 即为要交换的次数，同时把index编号位置的数从集合中删去
 *
 * 比如三个人，编号如下
 * 1 3 1 2 3 2
 * 第一次交换：
 * 1 1 3 2 3 2（交换一次）
 * 第二次交换：
 * 1 1 3 3 2 2（交换一次）
 * 结束。其实你会发现某个数交换完成后，其他的相对位置是不变的，比如，1交换完成后，3之间的相对位置是不变的，2同样
 *
 * 因此我们可以把它放到集合里面，找到与他相同编号的，就把那个剔除（记录交换次数），这也不会影响其他的编号交换
 * 找到第二个1后：
 * 1 3 2 3 2（交换一次）
 * 找到第二个3后：
 * 1 3 2 2（交换一次）
 * 找到第二个2后：
 * 1 3 2（交换0次）
 */
public class ChangeNum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  //n对情侣
        String[] str = br.readLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < 2*n ; i++){
            list.add(Integer.valueOf(str[i]));
        }
        int count = 0 ;
        int i = 0 ;
        while(i < list.size()){
            int index = list.lastIndexOf(list.get(i));
            count += index-i-1 ;
            list.remove(index);
            i++;
        }
        System.out.println(count);
    }
}
