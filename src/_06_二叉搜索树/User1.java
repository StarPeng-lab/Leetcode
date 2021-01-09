package _06_二叉搜索树;

public class User1 implements Comparable<User1>{ //Comparable<E>可以直接确定比较器中的类型为User2
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User1(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(User1 u) {
        /*
        if(this.age > u.age)  return 1;
        if(this.age < u.age)  return -1;
        return 0;
        */
        return this.age - u.age;
    }
}
