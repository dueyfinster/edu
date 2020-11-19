package queue.impl;

import queue.Deque;
import queue.EmptyDequeException;
import queue.Node;

public class LinkedDequeue<E> implements Deque<E>{

	private int size = 0;
	private Node<E> rear = null;
	private Node<E> front = null;
	

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {	
		return (size == 0);
	}

	@Override
	public E getFirst() throws EmptyDequeException {
		if(size >0){
			return front.getElement();
		}else{
			throw new EmptyDequeException("Queue is empty");
		}
	}

	@Override
	public E getLast() throws EmptyDequeException {
		if(size >0){
			return rear.getElement();
		}else{
			throw new EmptyDequeException("Queue is empty");
		}
	}

	@Override
	public void addFirst(E element) {
		if(size==0){
			Node<E> node = new Node<E>();
			node.setNext(null);
			node.setPrevious(null);
			node.setElement(element);
			front = node;
			rear = node;
		}else{
			Node<E> node = new Node<E>();
			node.setNext(front);
			front.setPrevious(node);
			node.setPrevious(null);
			node.setElement(element);
			front = node;
		}
		size++;
	}

	@Override
	public void addLast(E element) {
		if(size==0){
			Node<E> node = new Node<E>();
			node.setNext(null);
			node.setPrevious(null);
			node.setElement(element);
			front = node;
			rear = node;
		}else{
			Node<E> node = new Node<E>();
			node.setElement(element);
			
			rear.setNext(node);
			node.setPrevious(rear);
			node.setNext(null);
			
			rear = node;
		}
		size++;	
	}

	@Override
	public E removeFirst() throws EmptyDequeException {
		Node<E> node = new Node<E>();
		E e;
		if(size>0){
			node = front.getNext();
			if(node==null){
				e = front.getElement();
				front = null;
				rear = null;
			}else{
				e = front.getElement();
				node.setPrevious(null);
				front = node;
			}
			size--;
		}else{
			throw new EmptyDequeException("No front element to remove");
		}
		return e;
	}

	@Override
	public E removeLast() throws EmptyDequeException {
		Node<E> node = new Node<E>();
		E e;
		if(size>0){
			node = rear.getPrevious();
			if(node==null){
				e = rear.getElement();
				front = null;
				rear = null;
			}else{
				e = rear.getElement();
				node.setNext(null);
				rear = node;
			}
			size--;
		}else{
			throw new EmptyDequeException("No last Node element to remove");
		}
		return e;
	}
	
	public String toString(){
		String result = "";
		if(front!=null)
			result = getNextNode(front, result);
		if(result=="")
			result = "List is empty";
		
		return result;
	}
	
	private String getNextNode(Node n, String result){
		result = "\n Node is: "+ n + " and Element is: " + n.getElement().toString();
		if(n.getNext()!=null)
			result = result + getNextNode(n.getNext(), result);
		return result;
	}

}
