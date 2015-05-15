package com.thread.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();
	private int connections = 0;
	private Semaphore sem = new Semaphore(10);

	private Connection() {
	}

	public static Connection getConnection() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			doConnect();
		} finally {
			sem.release();
		}
	}

	public void doConnect() {

		synchronized (this) {
			connections++;
			System.out.println("current connections: " + connections);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
		}

	}
}
