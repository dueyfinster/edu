package queue;


    public class Node<E> {
      // Instance variables:
      private E element;
      private Node<E> next;
      private Node<E> previous;
      
      /** Creates a node with null references to its element and next node. */
      public Node() {
        this(null, null, null);
      }
      /** Creates a node with the given element and next node. */
      public Node(E e, Node<E> p, Node<E> n) {
        element = e;
        previous = p;
        next = n;   
      }
      // Accessor methods:
      public E getElement() {
        return element; 
      }
      public Node<E> getNext() { 
        return next;
      }
      public Node<E> getPrevious() { 
          return previous;
      }
      // Modifier methods:
      public void setElement(E newElem) { 
        element = newElem; 
      }
      public void setNext(Node<E> newNext) {
        next = newNext; 
      }
	  public void setPrevious(Node<E> newPrevious) {
			previous = newPrevious; 
	  }
    }
