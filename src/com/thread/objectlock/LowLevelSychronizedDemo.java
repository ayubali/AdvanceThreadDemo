package com.thread.objectlock;

import java.util.LinkedList;
import java.util.List;

class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private Object lock = new Object();
	final private int LIMIT = 10;

	public void producer() {
		int value = 0;
		synchronized (lock) {

			while (true) {
				try {
					if (list.size() == LIMIT) {
						lock.wait();

					}
					list.add(value++);
					lock.notify();
					Thread.sleep(200);
				} catch (Exception e) {
					System.out.println("e: ");
					e.printStackTrace();
				}
				
			}
		}
	}

	public void comsumer() {

		synchronized (lock) {
			while (true) {
				try {
					if (list.size() == 0) {
						lock.wait();
					}

					Integer value = list.removeFirst();
					System.out.println("Remove Value: " + value
							+ " list size: " + list.size());
					lock.notify();
					Thread.sleep(200);

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}
	}
}

public class LowLevelSychronizedDemo {

	public static void main(String[] args) {
		final Processor processor = new Processor();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				processor.producer();
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				processor.comsumer();
			}
		};

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}