package com.hansheng.sequence;

public class Test2 extends com.hansheng.sequence.Test1 {
	public static int k1 = 0;
	public static Test2 t3 = new Test2("t1");
	public static Test2 t4= new Test2("t2");
	public static int i1 = print("i");
	public static int n1= 99;
	public int j1 = print("j");
	{
		print("构造块1");
	}

	static{
		print("静态块1");
	}
	public static int print(String str){
		System.out.println("test="+(++k)+":"+str+"    i="+i+"    n="+n);
		++n;
		return ++i;
	}
	public Test2(){
		super();
	}

	public Test2(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test2("init");
	}

}