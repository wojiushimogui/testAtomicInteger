package com.wrh.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicIntegerLock {
	
	private volatile int value;
	
	private Lock lock = new ReentrantLock();

	public AtomicIntegerLock(int value) {
		this.value = value;
	}
	
	public void set(int newValue){
		lock.lock();
		try{
			this.value = newValue;
		}finally{
			lock.unlock();
		}
	}
	
	public final int get(){
		lock.lock();
		try{
			return value;
		}finally{
			lock.unlock();
		}
	}
	
	public final int getAndSet(int newValue){
		lock.lock();
		try{
			int oldValue = value;
			value = newValue;
			return oldValue;
		}finally{
			lock.unlock();
		}
	}
	
	public final int getAndAdd(int delta){
		lock.lock();
		try{
			int oldValue = value;
			value+=delta;
			return oldValue;
		}finally{
			lock.unlock();
		}
	}
	
	public final int addAndGet(int delta){
		lock.lock();
		try{
			value+=delta;
			return value;
		}finally{
			lock.unlock();
		}
	}
	
	public final boolean getAndCompare(int expect,int newValue){
		lock.lock();
		try{
			if(this.value == expect){
				value = newValue;
				return true;
			}
			else{
				return false;
			}
		}finally{
			lock.unlock();
		}
	}
	
	public final int getAndIncrement(){
		lock.lock();
		try{
			return value++;
		}finally{
			lock.unlock();
		}
	}
	
	public final int getAndDecrement(){
		lock.lock();
		try{
			return value--;
		}finally{
			lock.unlock();
		}
	}
	
	public final int incrementAndGet(){
		lock.lock();
		try{
			return ++value;
		}finally{
			lock.unlock();
		}
	}
	
	public final int decrementAndGet(){
		lock.lock();
		try{
			return --value;
		}finally{
			lock.unlock();
		}
	}
	
	public final String  toString(){
		return Integer.toString(get());
	}
	
}

