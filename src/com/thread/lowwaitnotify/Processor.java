package com.thread.lowwaitnotify;

import java.util.LinkedList;

public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int SIZE = 10;
	private Object lock = new Object();

	public void producer() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {

				while (list.size() == SIZE) {
					lock.wait();
				}
			}
			list.add(++value);
			lock.notify();

		}

	}

	public void consumer() throws InterruptedException {

		while (true) {
			synchronized (lock) {

				while (list.size() == 0) {
					lock.wait();
				}
			}
			System.out.println("list size:"+list.size());
			int value = list.removeFirst();
			System.out.println("; value : "+ value);
			lock.notify();
		}
	}

}
