package com.halfmind.learning.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Service implements Runnable {
	
	private CountDownLatch latch;
	private String serviceName;
	
	public Service(String serviceName, CountDownLatch latch){
		this.serviceName = serviceName;
		this.latch = latch;
	}
	
	public void run() {
		
		System.out.println("Starting service: " + serviceName);
		
		try {
			Thread.sleep(6700);
		} catch (InterruptedException ie){
		}
		
		System.out.println("Service" + serviceName + " is RUNNING.");
		
		latch.countDown();
		
	}
	
}

public class CountDownLatchPractice {
	
	public static void main(String[] args) {
		
		
		CountDownLatch latch = new CountDownLatch(5);
		
		List<Service> services = Arrays.asList(new Service("Printing", latch),
				new Service("Logging", latch),
				new Service("Security", latch),
				new Service("Testing", latch),
				new Service("Tracking", latch));
		
		ExecutorService executor = Executors.newFixedThreadPool(services.size());
		
		System.out.println("Submitting services...");
		for (Service service : services) {
			executor.submit(service);
		}
		
		executor.shutdown();
		System.out.println("All services submitted.");
		
		System.out.println("Waiting for services to start.");
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All services started!");
		
	}

}
