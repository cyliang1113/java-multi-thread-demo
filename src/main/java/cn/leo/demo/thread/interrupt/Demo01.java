package cn.leo.demo.thread.interrupt;

public class Demo01 {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("1111111");
					Thread.currentThread().interrupt();
					System.out.println("2222222");
				}
			}
		}).start();
	}
}
