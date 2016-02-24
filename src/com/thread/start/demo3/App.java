package com.thread.start.demo3;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(" hello : " + i);
				}				
			}
		});

		thread.start();
	}

}
