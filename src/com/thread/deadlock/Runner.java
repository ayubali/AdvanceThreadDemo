package com.thread.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void accuireLock(Lock firstLock, Lock secondLock)
			throws InterruptedException {
		while (true) {

			// acquire lock

			boolean getFirstLock = false;
			boolean getSecondLock = false;
			try {
				getFirstLock = firstLock.tryLock();
				getSecondLock = secondLock.tryLock();
			} finally {
				if (getFirstLock && getSecondLock) {
					return;
				}
				if (getFirstLock) {
					firstLock.unlock();
				}
				if (getSecondLock) {
					secondLock.unlock();
				}
			}

			// lock not acquire lock
			Thread.sleep(100);
		}
	}

	public void firstThread() throws InterruptedException {

		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();
			try {
				Account.transfer(acc1, acc2, rand.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void secondThread() throws InterruptedException {
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();
			// lock2.lock();
			// lock1.lock();

			try {
				Account.transfer(acc2, acc1, rand.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("total balance: "
				+ (acc1.getBalance() + acc2.getBalance()));
	}

}
