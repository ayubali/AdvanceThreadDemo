package com.thread.synchonizedkeyword;

public class App {
	private int count = 0;

	public synchronized void incrementCount() {
		count++;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App app = new App();
		app.doWork();

	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					incrementCount();
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					incrementCount();
				}

			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Count: " + count);

	}
}
