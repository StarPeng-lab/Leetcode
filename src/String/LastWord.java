package String;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/*
   获得字符串的最后一个单词
 */

public class LastWord {
    public static void main(String[] args) {
        System.out.print("请输入你的字符串：");
        Scanner s=new Scanner(System.in);
        //String ss=s.next(); // 前后都不读取空格
        String ss=s.nextLine();
        System.out.println("你输入的字符串为："+ss);
        char[] arr=ss.toCharArray();
        if(arr.length==1 && arr[0]==' ') {
            System.out.println(0);
        }else {
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<arr.length;i++) {
                if(arr[i]==' ') {
                    list.add(i);
                }
            }
            String result=ss.substring(list.get(list.size()-1)+1);
            System.out.println("最后一个单词为："+result+"，git "+result.length());
        }

        /*
        * System.out.println("请输入你的字符串：");
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		if(s.length()==1 && s==" ") {
			System.out.println("0");
		}else {
			String[] arr=s.split("\\s+");
			for(String a:arr) {
				System.out.println("字符串为："+a);
			}
			System.out.println("最后一个字符串为"+arr[arr.length-1]);
		}*/
    }
}
