package stacks;

public class ArrayStack {
	String[] stack;
	int head;
	int next;
	
	public ArrayStack(){
		head = -1;
		next = 0;
		stack = new String[10];
	}
	
	ArrayStack(String s){
		head = -1;
		next = 0;
		stack = new String[10];
		push(s);
	}

	
	public void push(Object s){
		if (stack.length == head + 1) {
			stack = java.util.Arrays.copyOf(stack, stack.length * 2);
		} 
		stack[next] = s.toString();
		head = next;
		next++;
	}
	
	public String pop(){
		if (isEmpty()) {
		   return null;
		}
		String result = stack[head];
		head--;
		next--;
		return result;
	}
	
	public boolean isEmpty(){
		return (head == -1);
	}
	
	public String top(){
		return stack[head];
	}
	
	public int size(){
		return head+1;
	}
	
	public String toString() {
		if (isEmpty()) {
		    return "0";
		} else {
			int it = head;
			String result = "";
			while(it>=0){
				result =  /*it + "=" +*/ stack[it] + " " + result;
				it--;
			}
			
			return "" + size() + "\t" + result;
		}
	 }

}
