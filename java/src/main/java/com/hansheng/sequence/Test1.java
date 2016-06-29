package com.hansheng.sequence;

public class Test1 {

    public static int k = 0;
    public static Test1 t1 = new Test1("t1");
    public static Test1 t2 = new Test1("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");
    {
        print("构造块");
    }

    static{
        print("静态块");
    }

    public Test1(String str){
        System.out.println((++k)+":"+str+"    i="+i+"    n="+n);
        ++i;++n;
    }

    public Test1() {
        // TODO Auto-generated constructor stub
    }

    public static int print(String str){
        System.out.println((++k)+":"+str+"    i="+i+"    n="+n);
        ++n;
        return ++i;
    }

//    public static void main(String[] args) {  
//        // TODO Auto-generated method stub  
//        Test1 t = new Test1("init");  
//    }  

}
