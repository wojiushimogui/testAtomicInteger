package com.wrh.lock;

	import java.util.concurrent.atomic.AtomicInteger;
	
	public class TestAtomicIntegerLock2 {
	
		private static int synValue = 0;
		public static void main(String[] args) {
			int threadNum = 10;
			int maxValue = 1000000;
			
			Thread[] t = new Thread[threadNum];
			Long begin = System.nanoTime();
			for(int i=0;i<threadNum;i++){
				AtomicIntegerLock aIL = new AtomicIntegerLock(0);
				t[i]=new Thread(new Runnable(){
	
					@Override
					public void run() {
						for(int j=0;j<maxValue;j++){
							aIL.getAndIncrement();
						}
					}
					
				});
			}
			for(int i=0;i<threadNum;i++){
				t[i].start();
			}
			//main线程等待前面开启的所有线程结束
			for(int i=0;i<threadNum;i++){
				try {
					t[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("使用lock所花费的时间为："+(System.nanoTime()-begin));
			
			
			int[] lock = new int[0];
			begin = System.nanoTime();
			for(int i=0;i<threadNum;i++){
				AtomicInteger ai = new AtomicInteger(0);
				t[i]=new Thread(new Runnable(){
	
					@Override
					public void run() {
						for(int j=0;j<maxValue;j++){
							ai.incrementAndGet();
						}
					}
					
				});
			}
			for(int i=0;i<threadNum;i++){
				t[i].start();
			}
			//main线程等待前面开启的所有线程结束
			for(int i=0;i<threadNum;i++){
				try {
					t[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("使用原子操作类AtomicInteger所花费的时间为："+(System.nanoTime()-begin));
			
		}
	
	}
