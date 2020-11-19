package Lab2;

public class ListDriver{
	public static void main(String args[]){
		ListReferenceBased list = new ListReferenceBased();
		
		//add item and print
		list.add(1, 10);
		list.add(2, 20);
		list.add(3, 30);
		list.add(4, 40);
		list.add(5, 50);
		list.display();

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
		
		//tell largest item
		System.out.println("After adding some items, which is largest?: " + list.getLargest());
	}
}