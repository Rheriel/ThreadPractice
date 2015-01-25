package com.halfmind.learning.threads;

import java.util.LinkedList;
import java.util.Random;

class BasicProducerConsumer {
	
	private LinkedList<Integer> queue = new LinkedList<Integer>();
	private static final int LIMIT = 10;
	
	private Object lock = new Object();
	
	public void producer() throws InterruptedException {
		
		int counter = 0;
		
		while(true){
			synchronized (lock) {
				while(queue.size()==LIMIT){
					lock.wait();
				}
				queue.add(counter++);
				lock.notify();
			}
		}
	}
	
	public void consumer() throws InterruptedException {
		Random random = new Random();
		while(true){
			synchronized (lock) {
				while(queue.isEmpty()){
					lock.wait();
				}
				System.out.print("Queue size is: " + queue.size());
				int taken = queue.removeFirst();
				System.out.println("; Obtained value: " + taken);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}

public class WaitAndNotifyProducerConsumer {
	
	public static void main(String[] args) throws InterruptedException {
		
		final BasicProducerConsumer processor = new BasicProducerConsumer();
		
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
