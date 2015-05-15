package com.thread.deadlock;

public class DeadLockDemo {

	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				processor.firstThread();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				processor.secondThread();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		processor.finished();
	}

}
