package jPoker;


public class RunPoker {
  public static void main(String[] args) {

    System.out.println("Let's play poker");
    System.out.println("----------------------------------------");
    System.out.println("Shuffling cards");
	Deck Deal = new Deck();
    System.out.println("Dealing cards");
	Hand A = new Hand(Deal);
	Hand B = new Hand(Deal);

    System.out.println("");
    System.out.print("Player One's Cards: ");
    A.display();System.out.println("");
    System.out.println("----------------------------------------");
    A.displayAll();

    System.out.println("");
    System.out.print("\nPlayer Two's Cards: ");
    B.display();System.out.println("");
    System.out.println("----------------------------------------");
    B.displayAll();

	


  }
}
