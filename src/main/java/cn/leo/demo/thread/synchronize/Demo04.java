package cn.leo.demo.thread.synchronize;

import static cn.leo.demo.thread.atomic.Demo01.ai;

public class Demo04 {
	public static Object lock = new Object();

	public static int ai = 1;
	public static int max = 5;

	public static void main(String[] args) {
		new Thread("thread-B") {
			@Override
			public void run() {
				while (true) {
					// 偶数
					synchronized (lock) {
						if (ai > max) {
							return;
						}

						int i = ai % 2;
						if (i == 0) {
							System.out.println(Thread.currentThread().getName() + ": " + ai);
							ai++;
							lock.notify();
						} else {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}
				}
			}
		}.start();

		new Thread("thread-A") {
			@Override
			public void run() {
				while (true) {
					// 奇数
					synchronized (lock) {
						if (ai > max) {
							return;
						}

						int i = ai % 2;
						if (i != 0) {
							System.out.println(Thread.currentThread().getName() + ": " + ai);
							ai++;
							lock.notify();
						} else {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();

	}

}
