package questions;

import stacks.LinkedStack;

public class PCQ2L {
	public static void main(String[] args) {
		LinkedStack ls = new LinkedStack();
		ls.push("e");
		System.out.println(ls);
		ls.push("s");
		System.out.println(ls);
		ls.push("c");
		System.out.println(ls);
		String popped = ls.pop();
		System.out.println(ls);
		ls.push("u"); 
		System.out.println(ls);
		ls.push("a");
		System.out.println(ls);
		popped = popped + ls.pop();
		System.out.println(ls);
		ls.push("o"); 
		System.out.println(ls);
		ls.push("t");
		System.out.println(ls);
		popped = popped + ls.pop();
		System.out.println(ls);
		ls.push("h");
		System.out.println(ls);
		
		//System.out.println("Word popped is: " + popped);
		
		
		//System.out.println("Word is: ");
		//System.out.print(ls.pop() + ls.pop() + ls.pop() + ls.pop() + ls.pop());
	}

}
