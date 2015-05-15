package com.thread.synchronizmethod;

public class SynchonizedExample {
	private volatile int count = 0;
	
	private synchronized void increment()
	{
			count++;
		
	}

	public static void main(String args[]) throws InterruptedException {
		SynchonizedExample example = new SynchonizedExample();
		example.doworks();

	}

	public void doworks() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}

		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;

				}
			}
		};

		t1.start();
		t2.start();

		
		t1.join();
		t2.join();
		System.out.println("Main ----counter: " + count);

	}

}
