package Exercise;

/**
 * 疫情逐步缓和后，电影院终于开业了，但是由于当前仍处于疫情期间，应尽量保持人群不聚集的原则。
 * 所以当小易来电影院选定一排后，尽量需要选择一个远离人群的位置。
 * 已知由0和1组成的数组表示当前排的座位情况,其中1表示已被选座，0表示空座
 * 请问小易所选座位和最近人的距离座位数最大是多少？
 * 有如下假设：至少有一个人已选座，至少有一个空座位，且座位数限制为2<=length<=1000
 * 例：输入：1 0 0 1 0
 *    输出：1 （不管是做第2个还是第3个还是第5个座位，和最近人的距离座位数最大是1）
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class WangYi_1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split("\\s+");
        ArrayList<Integer> arr = new ArrayList<>(); //放置已有人座位的编号（从0开始排座）
        ArrayList<Integer> arr1 = new ArrayList<>(); //放置与所有有人的座位的距离

        for(int i = 0 ; i < str.length ; i++){
            if(str[i].equals("1")){
                arr.add(i);
            }
        }

        int distance = 0 ;
        int min = 0;
        for(int i = 0 ; i < str.length ; i++){
            if(str[i].equals("0")){
                for(int j : arr){
                    arr1.add(Math.abs(i-j));
                }
                Collections.sort(arr1); //升序排序
                min = arr1.get(0); //每一个无人的座位，都计算出和所有有人的座位 的距离数，并用min保存最小距离数
                arr1 = new ArrayList<>(); //易错点：arr1每一轮都要重新new
            }
            distance = Math.max(min,distance); // 用distance保存每一轮min中的最大的那个
        }

        System.out.println(distance);
    }
}
