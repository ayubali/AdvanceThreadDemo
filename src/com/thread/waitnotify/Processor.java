package com.thread.waitnotify;

import java.util.Scanner;

public class Processor {

	public void producer() throws InterruptedException {

		synchronized (this) {
			System.out.println("Producer thread is running...");
			wait();
			System.out.println("Resumed..");
		}
	}

	public void consumer() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("waiting for return key");
			sc.nextLine();
			System.out.println("Return key is pressed");
			notify();
			Thread.sleep(5000);
		}
	}
}
