package questions;

import stacks.ArrayStack;

public class PCQ2A {
	public static void main(String[] args) {
		ArrayStack as = new ArrayStack();
		as.push("e");
		System.out.println(as);
		as.push("s");
		System.out.println(as);
		as.push("c");
		System.out.println(as);
		String popped = as.pop();
		System.out.println(as);
		as.push("u"); 
		System.out.println(as);
		as.push("a");
		System.out.println(as);
		popped = popped +  as.pop();
		System.out.println(as);
		as.push("o"); 
		System.out.println(as);
		as.push("t");
		System.out.println(as);
		popped = popped +  as.pop();
		System.out.println(as);
		as.push("h");
		System.out.println(as);
		
		//System.out.println("Word popped is: " + popped);
		
		//System.out.println("Word is: ");
		//System.out.print(as.pop() + as.pop() + as.pop() + as.pop() + as.pop());
	
		
	}

}
