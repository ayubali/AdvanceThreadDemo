package com.thread.interrupted;

import java.util.Random;

public class App {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start............");
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1e8; i++) {
//					if (Thread.currentThread().isInterrupted()) {
//						System.out.println("interrupted");
//						break;
//					}
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
					Math.sin(random.nextDouble());
				}

			}
		});
		t.start();
		t.interrupt();
		t.join();
		System.out.println("Finished............");

	}
}
