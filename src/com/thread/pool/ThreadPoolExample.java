package com.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	private String name;

	public Processor(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Starting thread: ....." + name);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending thread: ....." + name);

	}
}

public class ThreadPoolExample {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			service.submit(new Processor(Integer.toString(i)));
		}
		service.shutdown();
		System.out.println("All the tast submitted...");
		service.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("All the tast completed...");

	}

}