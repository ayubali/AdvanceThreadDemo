package com.thread.waitnotify;

import java.util.Scanner;

public class Processor {

	public void  producer()  {
		synchronized (this) {
			System.out.println("starting...............producer");
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			System.out.println("resume.................producer");
		}
		
	}
	public void consumer() {
		Scanner scanner = new Scanner(System.in);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (this) {
			System.out.println("Enter key................");
			scanner.nextLine();
			System.out.println("Return key is pressed.....");
			notify();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("comsumer................get chance");
		}
	}
}
