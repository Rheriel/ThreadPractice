package com.halfmind.learning.threads;

public class SynchronizedMain {
	
	private int count = 0;
	
	public static void main(String[] args) {
		SynchronizedMain app = new SynchronizedMain();
		
		app.doWork();
		
	}
	
	public synchronized void incrementCount() {
		count++;
	}
	
	public void doWork(){
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				for(int i=0; i< 10000; i++){
					incrementCount();
				}
			}

		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				for(int i=0; i< 10000; i++){
					incrementCount();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException ie) {
		}
		
		System.out.println("The value of count is: " + count);
		
	}

}
