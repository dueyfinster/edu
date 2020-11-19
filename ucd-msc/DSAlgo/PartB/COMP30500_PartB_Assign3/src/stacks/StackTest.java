package stacks;
public class StackTest {
         public static void main(String[] args) {
        	 //testArrayStack();
        	 testLinkedStack();
         } 
         
         static void testArrayStack(){
        	 ArrayStack s = new ArrayStack();
             System.out.println(s);
             s.push("A");
             System.out.println(s);
             s.push("B");
             System.out.println(s);
             s.push("C");
             System.out.println(s);
             s.pop();
             System.out.println(s);
             System.out.println(s.top());
         }
         
         static void testLinkedStack(){
        	 LinkedStack s = new LinkedStack();
             System.out.println(s);
             s.push("A");
             System.out.println(s);
             s.push("B");
             System.out.println(s);
             s.push("C");
             System.out.println(s);
         }
}
