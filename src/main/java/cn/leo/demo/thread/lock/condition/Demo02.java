package cn.leo.demo.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 三个线程交替执行
 * 
 * Lock Condition实现
 */
public class Demo02 {
	public static void main(String[] args) {

		PrintMsg printMsg = new PrintMsg();

//		new MyThread1(printMsg).start();
//		new MyThread2(printMsg).start();

		printMsg.print1();

	}
	
	private static class MyThread1 extends Thread {
		private PrintMsg pm;

		public MyThread1(PrintMsg pm) {
			this.pm = pm;
		}

		@Override
		public void run() {
			pm.print2();
		}
	}

	private static class MyThread2 extends Thread {
		private PrintMsg pm;

		public MyThread2(PrintMsg pm) {
			this.pm = pm;
		}

		@Override
		public void run() {
			pm.print3();
		}
	}

	private static class PrintMsg{
		private boolean print1 = true;
		private boolean print2 = false;
		private boolean print3 = false;
		private Lock lock = new ReentrantLock();
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();

		public void print1() {
			for (int s = 1; s <= 5; s++) {
				lock.lock();
				try {
					while (!print1) {
						condition1.await();
					}
					for (int i = 1; i <= 3; i++) {
						System.out.println("print1:" + i);
					}
					print1 = false;
					print2 = true;
					condition2.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}

		public void print2() {
			for (int s = 1; s <= 5; s++) {
				lock.lock();
				try {
					while (!print2) {
						condition2.await();
					}
					for (int i = 1; i <= 3; i++) {
						System.out.println("print2:" + i);
					}
					print2 = false;
					print3 = true;
					condition3.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}

		public void print3() {
			for (int s = 1; s <= 5; s++) {
				lock.lock();
				try {
					while (!print3) {
						condition3.await();
					}
					for (int i = 1; i <= 3; i++) {
						System.out.println("print3:" + i);
					}
					print3 = false;
					print1 = true;
					condition1.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

}


