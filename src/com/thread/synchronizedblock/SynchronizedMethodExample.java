package com.thread.synchronizedblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedMethodExample {
	public List<Integer> list1 = new ArrayList<Integer>();
	public List<Integer> list2 = new ArrayList<Integer>();
	private Random random = new Random();

	private synchronized void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list1.add(new Integer(random.nextInt(100)));
	}

	private  synchronized void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list2.add(new Integer(random.nextInt(100)));
	}

	private void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		final SynchronizedMethodExample example = new SynchronizedMethodExample();
		long start = System.currentTimeMillis();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				example.process();
			}
		};
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				example.process();
			}
		};
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		long end = System.currentTimeMillis();
		System.out.println("Time takes: " + (end - start));
		System.out.println("list1: " + example.list1.size() + " list2 :"
				+ example.list2.size());
	}

}
