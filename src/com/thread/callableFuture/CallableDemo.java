package com.thread.callableFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableDemo {

	public static void main(String... args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> future = service.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				System.out.println("Starting................");
//				if (duration > 2000) {
//					throw new IOException("Sleeping for too long..");
//				}
				
				Thread.sleep(1000);
				System.out.println("finished");
				return duration;
			}
		});
		service.shutdown();
		service.awaitTermination(1, TimeUnit.DAYS);
		try {
			System.out.println("future: " + future.get());
		} catch (ExecutionException e) {
			System.out.println("ex: "+ e.getMessage());
			e.printStackTrace();
		}
	}

}
