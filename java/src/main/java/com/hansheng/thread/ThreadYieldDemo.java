package com.hansheng.thread;

public class ThreadYieldDemo {

	private static final int MAX = 5;

	public static void main(String[] args) {
//	        joinDemo();
//	         yield();
//	        yieldWithSync();
//	         waitWithSync();
		waitAndNotifyAll();
	}

	// YieldTest.java的源码
	static class YieldThread extends Thread {
		public YieldThread(String name) {
			super(name);
		}

		public synchronized void run() {
			for (int i = 0; i < MAX; i++) {
				System.out.printf("%s ,优先级为 : %d ----> %d\n", this.getName(), this.getPriority(), i);
				// i整除4时，调用yield
				if (i == 2) {
					Thread.yield();
				}
			}
		}
	}

	static void yield() {
		YieldThread t1 = new YieldThread("thread-1");
		YieldThread t2 = new YieldThread("thread-2");
		t1.start();
		t2.start();
	}

	static void joinDemo() {
		Worker worker1 = new Worker("work-1");
		Worker worker2 = new Worker("work-2");

		worker1.start();
		System.out.println("启动线程1");
		try {
			worker1.join();
			System.out.println("启动线程2");
			worker2.start();
			worker2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("主线程继续执行");
	}

	static class Worker extends Thread {

		public Worker(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("work in " + getName());
		}
	}

	private static Object sLockObject = new Object();

	// http://www.cnblogs.com/skywang12345/p/3479243.html
	static void yieldWithSync() {
		YieldSyncThread t1 = new YieldSyncThread("t1");
		YieldSyncThread t2 = new YieldSyncThread("t2");
		t1.start();
		t2.start();
	}

	static class YieldSyncThread extends Thread {
		public YieldSyncThread(String name) {
			super(name);
		}

		public void run() {
			// 获取obj对象的同步锁
			synchronized (sLockObject) {
				for (int i = 0; i < MAX; i++) {
					System.out.printf("%s [%d] ---> %d\n", this.getName(), this.getPriority(), i);
					// i整除4时，调用yield
					if (i % 4 == 0) {
						Thread.yield();
					}
				}
			}
		} // end run
	}

	static void waitWithSync() {
		WaitSyncThread t1 = new WaitSyncThread("t1");
		WaitSyncThread t2 = new WaitSyncThread("t2");
		t1.start();
		t2.start();
	}

	static class WaitSyncThread extends Thread {
		public WaitSyncThread(String name) {
			super(name);
		}

		public void run() {
			try {
				doWork();
			} catch (Exception e) {
			}

		} // end run

		private void doWork() throws InterruptedException {
			// 获取obj对象的同步锁
			synchronized (sLockObject) {
				for (int i = 0; i < MAX; i++) {
					System.out.printf("%s [%d] ---> %d\n", this.getName(), this.getPriority(),
							i);
					// i整除4时，调用wait
					if (i > 0 && i % 3 == 0) {
						sLockObject.wait(3000);
					}
				}
			}
		}
	}

	static void waitAndNotifyAll() {
		System.out.println("主线程  运行");
		Thread thread = new WaitThread();
		thread.start();
		long startTime = System.currentTimeMillis();
		try {
			synchronized (sLockObject) {
				System.out.println("主线程  等待");
				sLockObject.wait();
			}
		} catch (Exception e) {
		}
		System.out
				.println("主线程  继续 --> 等待耗时 : " + (System.currentTimeMillis() - startTime) + " ms");
	}

	static class WaitThread extends Thread {
		@Override
		public void run() {
			try {
				synchronized (sLockObject) {
					Thread.sleep(3000);
					sLockObject.notifyAll();
				}
			} catch (Exception e) {
			}
		}
	}

}
