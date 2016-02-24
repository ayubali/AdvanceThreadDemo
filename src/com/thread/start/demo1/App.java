package com.thread.start.demo1;
class Runner extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + " hello : " + i);
		}
	}
}

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.start();

		Runner runner1 = new Runner();
		runner1.start();

	}

}
