package cn.leo.demo.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo01 {
	public static AtomicInteger ai = new AtomicInteger(1);

	public static void main(String[] args) {
		new Thread("thread-A") {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": " + ai.getAndIncrement());
				}
			}
		}.start();

		new Thread("thread-B") {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": " + ai.getAndIncrement());
				}
			}
		}.start();

		new Thread("thread-C") {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": " + ai.getAndIncrement());
				}
			}
		}.start();
	}

}
