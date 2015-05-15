package com.thread.reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Processor {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 1000; i++) {
			count++;
		}
	}

	public void firstMethod() {
		lock.lock();

		try {
			try {
				System.out.println("waiting........");
				cond.await();
				Thread.sleep(1);
				System.out.println("waken up........");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			increment();
		} finally {

			lock.unlock();
		}

	}

	public void secondMethod() {
		try {
			Thread.sleep(1000);
			lock.lock();
			System.out.println("enter return key...");

			new Scanner(System.in).nextLine();
			System.out.println("got return key...");
			cond.signal();
			increment();

		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Finished Count: " + count);
	}

}

public class ReentrantLockDemo {

	public static void main(String[] args) {
		final Processor processor = new Processor();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				processor.firstMethod();
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				processor.secondMethod();
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

		processor.finished();
	}
}
