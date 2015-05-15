package com.thread.waitnotify;


public class WaitNotifyDemo {

	public static void main(String[] args) {
		final Processor processor = new Processor();
		Thread producer = new Thread() {
			@Override
			public void run() {
				processor.producer();
			}
		};

		Thread comsumer = new Thread() {
			@Override
			public void run() {
				processor.consumer();
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
