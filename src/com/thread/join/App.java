package com.thread.join;

class ThreadA extends Thread {

	public ThreadA(String name) {
		setName(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + " " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		ThreadA a = new ThreadA("Thread One");
		ThreadA b = new ThreadA("Thread Two");
		a.start();
		b.start();

		a.join();
		b.join();

		System.err.println("Finished.............main....");
	}

}
