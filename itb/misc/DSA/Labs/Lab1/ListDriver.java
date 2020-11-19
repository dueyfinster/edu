package Lab1;

public class ListDriver{
	public static void main(String[] args){
		ListArrayBased list = new ListArrayBased();
		
		//add item and print
		list.add(1, 10);
		System.out.println("Item One is: " + list.get(1));

		//Remove all
		list.removeAll();
		System.out.println("After remove all, is list empty?: " + list.isEmpty());
		
		//add item and print
		list.add(1, 20);
		System.out.println("After adding one item, is list empty now?: " + list.isEmpty());
		
		//add a few items and tell size
		list.add(1, 10);
		list.add(2, 20);
		list.add(3, 30);
		list.add(4, 40);
		list.add(5, 50);
		System.out.println("After adding some items, how many are there?: " + list.size());
	}
}