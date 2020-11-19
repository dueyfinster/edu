package questions;

import queue.impl.LinkedDequeue;

public class PCQ7L {

	public static void main(String[] args) {
		LinkedDequeue<String> ldq = new LinkedDequeue<String>();
		ldq.addFirst("Ireland");
		System.out.println(ldq);
		ldq.removeLast();
		System.out.println(ldq);
		
		ldq.addLast("England");
		System.out.println(ldq);
		ldq.removeFirst();
		System.out.println(ldq);
		ldq.addLast("Wales");
		System.out.println(ldq);
		ldq.addFirst("Scotland");
		System.out.println(ldq);
		ldq.addLast("France");
		System.out.println(ldq);
		ldq.removeFirst();
		ldq.removeLast();
		System.out.println(ldq);
		ldq.addLast("Germany");
		System.out.println(ldq);

	}

}
