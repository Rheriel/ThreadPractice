package com.halfmind.learning.threads;



public class ThreadMain {
	
	private static int number = 0;

	public static void main(String[] args) {
		
		class MyThread extends Thread {

			public void run() {
				for (int i = 0; i < 10000; i++) {
					System.out.println("Thread:" + number++);
				}
			}
			
		}
		
		Thread newThread = new MyThread();
		
		newThread.start();
		for(int i = 0; i<500; i++){
			number++;
		}
	}

}
