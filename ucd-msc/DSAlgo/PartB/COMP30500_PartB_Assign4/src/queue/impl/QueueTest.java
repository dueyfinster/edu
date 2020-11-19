package queue.impl;

public class QueueTest {
	
	
	public static void main(String[] args) {
		LinkedDequeue<String> ldq = new LinkedDequeue<String>();
		
		ldq.addFirst("Hello");
		ldq.addFirst("Boo");
		ldq.addFirst("Athlone");
		System.out.println(ldq);
		System.out.println("Removing: " + ldq.removeFirst());
		ldq.addLast("Toast");
		System.out.println(ldq);
		System.out.println("Removing: " + ldq.removeLast());
		System.out.println(ldq);
		System.out.println("Removing: " + ldq.removeLast());
		System.out.println(ldq);
		
		ArrayQueue<String> aq = new ArrayQueue<String>();
		
		aq.enqueue("Hello");
		aq.enqueue("Boo");
		aq.enqueue("Athlone");

		System.out.println(aq);
		System.out.println("Front is: " + aq.front());
		
		System.out.println("Dequeuing: " + aq.dequeue());
		
		System.out.println(aq);
		
		System.out.println("Dequeuing: " + aq.dequeue());
		
		System.out.println(aq);
		
		System.out.println("Size is: " + aq.size());
		System.out.println("Empty: " + aq.isEmpty());
		System.out.println("Dequeuing: " + aq.dequeue());
		System.out.println("Empty: " + aq.isEmpty());
		
		
	}

}
