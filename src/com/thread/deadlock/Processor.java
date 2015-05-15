package com.thread.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void acquireLock(Lock firstLock, Lock secondLock) {
		while (true) {
			// acquire lock
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} finally {
				if (gotFirstLock && gotSecondLock) {
					return;
				}
				if (gotFirstLock) {
					firstLock.unlock();
				}
				if (gotSecondLock) {
					secondLock.unlock();
				}
			}

			// locks not acquired
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void firstThread() {

		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			acquireLock(lock1, lock2);
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}

	}

	public void secondThread() {
		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			acquireLock(lock2, lock1);
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}

	}

	public void finished() {
		System.out.println("Account 1 balance : " + acc1.getBalance());
		System.out.println("Account 2 balance : " + acc2.getBalance());
		System.out.println("Total balance: "
				+ (acc1.getBalance() + acc2.getBalance()));

	}

}
