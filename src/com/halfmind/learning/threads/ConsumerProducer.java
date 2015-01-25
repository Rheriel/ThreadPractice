package com.halfmind.learning.threads;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerProducer {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void producer() throws InterruptedException {
		
		Random random = new Random();
		
		while(true){
			Thread.sleep(2000);
			queue.put(random.nextInt(100));
		}
		
	}
	
	public static void consumer() throws InterruptedException {
		Random random = new Random();
		
		while(true){
			Thread.sleep(1000);
			
			if (queue.isEmpty()) {
				System.out.println("Queue is empty! Waiting for producer...");
			} else {
				if (random.nextInt(10) == 0) {
					Integer taken = queue.take();
					System.out.println("Taken value: " + taken + "; Queue size: " + queue.size());
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					consumer();
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
