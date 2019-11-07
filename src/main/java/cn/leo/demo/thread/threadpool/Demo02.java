package cn.leo.demo.thread.threadpool;

import java.util.concurrent.*;

public class Demo02 {
	public static void main(String[] args) {

		ExecutorService pool = Executors.newCachedThreadPool();

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + ": run1...");
			try {
				Thread.sleep( 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + ": run2...");
			try {
				Thread.sleep( 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}
}
