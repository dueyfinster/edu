package Lab1;

public class ListDriver{
	
	public static void main(String[] args){
		NumberFactory nm = new NumberFactory();
		
		//String list1 = ("1 2 3 4 5 6 7 8 9 10");
		//nm.getNumberList(list1).display();
		
		//String list2 = ("1.2 2.3 3.4 4.5 5.6 6.7 7.8 8.9 9.10 10.11");
		//nm.getNumberList(list2).display();
		
		//String list3 = ("abcdefghijk");
		//nm.getNumberList(list3).display();
		
		//String list4 = ("0x1234 0x5678 0x91011 0x121314");
		//nm.getNumberList(list4).display();
		
		/*
		 * EXCERCISE 2
		 */
		
		//String list5 = ("C1245 C56789 C101112 C131415");
		//nm.getNumberList(list5).display();
		
		String list6 = ("I1245 I56789 I101112 I131415");
		nm.getNumberList(list6).display();
		
	}
}