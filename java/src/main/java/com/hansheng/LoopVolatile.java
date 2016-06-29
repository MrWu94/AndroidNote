package com.hansheng;

public class LoopVolatile  implements Runnable{
	private static volatile int value=0;

	public void run() {
		// TODO Auto-generated method stub
		int count=0;
		while(count<1000){
			value++;
			count++;
		}
		
	}
	public static void main(String[] args){
		Thread t1=new Thread(new LoopVolatile());
		t1.start();
		Thread t2=new Thread(new LoopVolatile());
		t2.start();
		while(t1.isAlive()||t2.isAlive()){}
		System.out.println("final value is="+value);
	}

}
