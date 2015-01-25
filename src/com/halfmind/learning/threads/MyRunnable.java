package com.halfmind.learning.threads;

import java.util.Scanner;

public class MyRunnable implements Runnable {

	private static volatile boolean isRunning = true;
	
	public void run() {
		while(isRunning){
			System.out.println("I'm running!");
			
			try{
				Thread.sleep(5000);
			} catch (InterruptedException ie){
				// Interrupted do nothing.
			}
		}
	}
	
	static class RunnableImplementor {
		public static void main(String[] args) {
			
			Thread myThread = new Thread(new MyRunnable());
			
			myThread.start();
			
			System.out.println("Press enter to stop!");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			System.out.println("I stopped running!");
			isRunning = false;
			scanner.close();
			
		}
	}

}
