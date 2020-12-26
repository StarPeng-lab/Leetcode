package _01_复杂度;

import Tools.TimeTool;

public class FibonacciNumber {

    public static int fib1(int n){
        if(n<=1)
            return n;
        return fib1(n-1)+fib1(n-2);
    }

    public static int fib2(int n){
        if(n<=1)
            return n;
        int first = 0;
        int second = 1;
        for(int i=0 ; i<n-1; i++){
            second = second + first;
            first = second - first;
        }
        return second;
    }

    public static int fib3(int n){
        double c = Math.sqrt(5);
        return (int)((Math.pow((1+c)/2,n)-Math.pow((1-c)/2,n))/c);
    }

    public static void main(String[] args){
        int n = 30;

        TimeTool.test("fib1",new TimeTool.Task(){
            @Override
            public void execute(){
                System.out.println(fib1(n));
            }
        });

        TimeTool.test("fib2",new TimeTool.Task(){
           @Override
           public void execute(){
               System.out.println(fib2(n));
           }
        });

        TimeTool.test("fib3",new TimeTool.Task(){
           @Override
           public void execute(){
               System.out.println(fib3(n));
           }
        });

    }
}
