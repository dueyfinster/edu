package questions;

import queue.impl.ArrayQueue;

public class PCQ7A {
	public static void main(String[] args) {
		ArrayQueue<String> aq = new ArrayQueue<String>();
		aq.enqueue("Ireland");
		System.out.println(aq);
		aq.dequeue();
		System.out.println(aq);
		aq.enqueue("England");
		System.out.println(aq);
		aq.dequeue();
		System.out.println(aq);
		aq.enqueue("Wales");
		System.out.println(aq);
		aq.enqueue("Scotland");
		System.out.println(aq);
		aq.dequeue();
		aq.dequeue();
		aq.enqueue("France");
		aq.enqueue("Wales");
		aq.enqueue("Scotland");
		System.out.println(aq);
		aq.dequeue();
		aq.dequeue();
		aq.dequeue();
		aq.enqueue("France");
		aq.enqueue("Wales");
		System.out.println(aq);
		aq.dequeue();
		aq.dequeue();
		aq.enqueue("Wales");
		System.out.println(aq);
		aq.enqueue("Germany");
		
		System.out.println(aq);

	}
}
