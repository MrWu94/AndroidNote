package com.hansheng.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureDemo {

	static ExecutorService mExecutor=Executors.newSingleThreadExecutor();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		futureWithRunnable();
		futureWithCallable();
		futureTask();

	}

	private static void futureWithRunnable(){

		Future<?> result=mExecutor.submit(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				fibc(20);
			}

		});
		try {
			System.out.println("future result from runnable:"+result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void futureWithCallable(){
		Future<Integer> result2=mExecutor.submit(new Callable<Integer>(){

			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return fibc(20);
			}

		});
		try {
			System.out.println("future result from callable:"+result2.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void futureTask(){

		FutureTask<Integer> futureTask=new FutureTask<Integer>(new Callable<Integer>(){

			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return fibc(20);
			}});

		mExecutor.submit(futureTask);

		try {
			System.out.println("suture result from futureTask"+futureTask.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int fibc(int num) {
		// TODO Auto-generated method stub
		if(num==0){
			return 0;
		}
		if(num==1){
			return 1;
		}
		return fibc(num-1)+fibc(num-2);
	}

}
