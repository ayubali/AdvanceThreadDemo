package com.thread.basic;

import java.util.Scanner;

class Processor extends Thread {
	private volatile boolean running = true;

	public Processor(String name) {
		setName(name);
	}

	@Override
	public void run() {
		while (running) {
			System.out.println("Thread Name: " + getName() + " message: hello");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		this.running = false;
	}

}

public class ThreadWithVolatile {
	public static void main(String args[])
	{
		Processor processor = new Processor("Message-Processer");
		processor.start();

		System.out.println("Press enter to run the thread...............");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		processor.shutdown();
	}

}
