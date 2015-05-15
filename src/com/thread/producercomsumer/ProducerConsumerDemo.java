package com.thread.producercomsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {

	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
			10);

	public static void producer() {
		Random random = new Random();
		while (true) {
			try {
				queue.put(random.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void consumer() {
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(100);
				if (random.nextInt(10) == 0) {
					Integer i = queue.take();
					System.out.println("Taken value: " + i + " size: "
							+ queue.size());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread producer = new Thread() {
			@Override
			public void run() {
				producer();
			}
		};

		Thread comsumer = new Thread() {
			@Override
			public void run() {
				consumer();
			}
		};

		producer.start();
		comsumer.start();
		try {
			producer.join();
			comsumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
