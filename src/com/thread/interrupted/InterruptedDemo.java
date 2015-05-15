package com.thread.interrupted;

import java.util.Random;

public class InterruptedDemo {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("starting.............");
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1E8; i++) {
					// if(Thread.currentThread().isInterrupted())
					// {
					// break;
					// }
					Math.sin(random.nextDouble());

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}

			}
		});
		thread.start();

		Thread.sleep(500);
		thread.interrupt();
		thread.join();

		System.out.println("finished");

	}

}
