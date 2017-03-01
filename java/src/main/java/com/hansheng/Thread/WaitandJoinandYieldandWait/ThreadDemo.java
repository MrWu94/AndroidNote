package com.hansheng.Thread.WaitandJoinandYieldandWait;

public class ThreadDemo {

	private static Object sLockObject =new Object();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		waitAndNotifyAll();
		joinDemo();

	}
	static void waitAndNotifyAll(){
		System.out.println("主线程运行");
		Thread thread=new WaitThread();
		thread.start();
		Long startTime=System.currentTimeMillis();
		try {
			System.out.println("主线程运行run");
			synchronized (sLockObject) {
				System.out.println("主线程  等待");
				sLockObject.wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timsMs=System.currentTimeMillis()-startTime;
		System.out.println("主线程等待_--继续等待"+timsMs+"ms");

	}
	static class WaitThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println("主线程运行thread");
				synchronized (sLockObject) {
					System.out.println("主线程  等待thrad");
					Thread.sleep(3000);
					sLockObject.notifyAll();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}
	static void joinDemo(){
		Worker worker1=new Worker("work-1");
		Worker worker2=new Worker("work-2");
		worker1.start();
		System.out.println("启动线程一");
		try {
			worker1.join();
			System.out.println("启动线程二");
			worker2.start();
//			worker2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("主线程继续执行");
	}
	static class Worker extends Thread{
		public Worker(String name){
			super(name);
		}
		public void run(){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("work in"+getName());
		}
	}

}
