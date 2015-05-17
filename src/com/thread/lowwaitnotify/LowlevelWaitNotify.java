
package com.thread.lowwaitnotify;



public class LowlevelWaitNotify {
	public static void main(String[] args) {
		final Processor processor = new Processor();
		Thread producer = new Thread() {
			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Thread comsumer = new Thread() {
			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
