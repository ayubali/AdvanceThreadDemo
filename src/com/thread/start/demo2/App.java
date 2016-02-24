package com.thread.start.demo2;

class Runner implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(" hello : " + i);
		}
	}
}

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread  thread = new Thread(new Runner());
		Thread  thread1 = new Thread(new Runner());
		thread.start();
		thread1.start();

	}

}
