package queue.impl;

import queue.EmptyQueueException;
import queue.Queue;

public class ArrayQueue<E> implements Queue<E> {

	private int start_size = 100;
	private E[] queue;
	private int size;

	public ArrayQueue() {
		queue = (E[]) (new Object[start_size]);
		size = -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == -1);
	}

	@Override
	public E front() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("No items in the queue");
		}

		return queue[0];
	}

	@Override
	public void enqueue(E element) {
		if (queue.length == size) {
			queue = java.util.Arrays.copyOf(queue, queue.length * 2);
		} 
		queue[++size] = element;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
	         throw new EmptyQueueException ("Queue is empty");
		
		E element = queue[0];

		 size--;

		 E[] newQueue = (E[]) (new Object[start_size]);
		 
	      for (int i=0; i <= size; i++){
	        
			newQueue[i] = queue[i+1]; // swap places
	      }
	      
	      queue = newQueue;
	 
	      return element;
	}
	
	public String toString() {
		if (isEmpty()) {
		    return "0";
		} else {
			int it = size;
			String result = "";
			while(it>=0){
				result =  /*it + "=" +*/ queue[it] + " " + result;
				it--;
			}
			
			return "" + size() + "\t" + result;
		}
	 }

}
