package com.thread.coundownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Starting................");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending..................");
		latch.countDown();
	}
}

public class CountDownLatchDemo {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor= Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed.........");	
		
	}

}
