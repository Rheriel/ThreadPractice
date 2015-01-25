package com.halfmind.learning.threads;

import java.util.Scanner;

class ProducerConsumer {
	
	public void producer() throws InterruptedException {
		synchronized(this){
			System.out.println("In producer thread...");
			wait();
			System.out.println("Resumed.");
		}
	}
	
	public void consumer() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized(this){
			System.out.println("Waiting for return key...");
			scanner.nextLine();
			System.out.println("Resuming...");
			scanner.close();
			notify();
			Thread.sleep(5000); // To probe notify() won't relinquish the lock until the synchronized block ends.
		}
		
	}
}

public class WaitAndNotify {
	
	public static void main(String[] args) throws InterruptedException {
		
		final ProducerConsumer processor = new ProducerConsumer();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}
