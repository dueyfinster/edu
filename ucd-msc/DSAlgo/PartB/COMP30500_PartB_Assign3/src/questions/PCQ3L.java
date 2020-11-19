package questions;

import stacks.LinkedStack;

public class PCQ3L {
	public static void main(String[] args) {
		LinkedStack ls = new LinkedStack();
		System.out.println(ls);
		ls.push("Ireland");
		System.out.println(ls);
		ls.pop(); 
		System.out.println(ls);
		ls.push("England");
		System.out.println(ls);
		ls.pop();
		System.out.println(ls);
		ls.push("Wales"); 
		System.out.println(ls);
		ls.pop(); 
		System.out.println(ls);
		ls.push("Scotland");
		System.out.println(ls);
		ls.pop(); 
		System.out.println(ls);
		ls.push("France");
		System.out.println(ls);
		ls.push("Germany");
		System.out.println(ls);
	}

}
