package com.thread.waitnotify.eassy;

class A {
	private String marketNews = null;

	public void publishNews() throws InterruptedException {
		B b = new B(this);
		b.start();
		synchronized (this) {
			wait();
		}
		System.out.println("News: " + this.marketNews);
	}

	public void SetNews(String marketNews) {
		this.marketNews = marketNews;
	}

}

class B extends Thread {
	A a = null;

	public B(A a) {
		this.a = a;
	}

	@Override
	public void run() {
		a.SetNews("Economy is recovering...");
		synchronized (a) {
			a.notify();
		}
	}
}

public class App {

	public static void main(String[] args) throws InterruptedException {
		A a = new A();
		a.publishNews();

	}

}
