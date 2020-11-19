package questions;

import stacks.ArrayStack;

public class PCQ3A {
	public static void main(String[] args) {
		ArrayStack as = new ArrayStack();
		System.out.println(as);
		as.push("Ireland");
		System.out.println(as);
		as.pop(); 
		System.out.println(as);
		as.push("England");
		System.out.println(as);
		as.pop();
		System.out.println(as);
		as.push("Wales"); 
		System.out.println(as);
		as.pop(); 
		System.out.println(as);
		as.push("Scotland");
		System.out.println(as);
		as.pop(); 
		System.out.println(as);
		as.push("France");
		System.out.println(as);
		as.push("Germany");
		System.out.println(as);
		
	}

}
