package Lab3;

public class StackTest2
{
  public static final int MAX_ITEMS = 15;

  public static void main(String[] args)
  {
    StackArrayBased stack = new StackArrayBased();

    Integer items[] = new Integer[MAX_ITEMS];

    for (int i=0; i<MAX_ITEMS; i++)
    {
      items[i] = new Integer(i);
      if (!stack.isFull())
      {
        stack.push(items[i]);
      }  // end if
    }  // end for

    while (!stack.isEmpty())
    {
      // cast result of pop to Integer
      System.out.println((Integer)(stack.pop()));
    }  // end while

  }  // end main
}