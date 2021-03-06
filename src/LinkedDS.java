/**
 * This is a data structure.
 */
public class LinkedDS<T> implements PrimQ<T>, Reorder {
  /**
   * This is a node on the DS
   */
  public class Node<T> {
    private Node<T> next;
    private Node<T> prev;
    private T data;
    public Node() {
    }
    public Node(T d) {
      data = d;
    }
    public T getData() { return data; }
    public Node<T> getNext() { return next; }
    public void setNext(Node<T> n) { next = n; }
    public Node<T> getPrev() { return prev; }
    public void setPrev(Node<T> p) { prev = p; }
    @Override
    public String toString() { return data.toString(); }
  }

  /**
   * This is the first Node in this list
   */
  private Node<T> firstNode;
  private int numOfEntries;

  public LinkedDS () {
    firstNode = null;
    numOfEntries = 0;
  }

  public LinkedDS (LinkedDS l) {
    numOfEntries = l.size();
    
    for (int i; i < numOfEntries; i++) {
      T temp = l.removeItem();;

    }
  }

  /**
   * Return a string representation of elements separated by a space and
   * trailing space.
   */
  @Override
  public String toString() {
    Node<T> pointer = firstNode;
    if (pointer == null)
      return "";
    StringBuilder sb = new StringBuilder();
    do {

      sb.append(pointer.getData().toString());
      sb.append(" ");
      pointer = pointer.getNext();
    } while (pointer != firstNode);
    return sb.toString();
  }

  public void debug() {
    Node<T> n = firstNode;
    do {
      if (n == null) break;
      System.out.println(n.getData());
      n = n.getNext();
    } while (n != firstNode);

    System.out.println("done printing");
  }
  
  public void debugBack() {
    Node<T> n = firstNode;
    do {
      if (n == null) break;
      System.out.println(n.getData());
      n = n.getPrev();
    } while (n != firstNode);

    System.out.println("done printing");
  }

  /**
   * Add a new Object to the PrimQ<T> in the next available location. If all
   * goes well, return true.
   * 
   * Newest added object is furthest along from firstNode going "next".
   */
  public boolean addItem(T newEntry){
    Node<T> n = new Node<T>(newEntry);
    numOfEntries++;

    if (firstNode == null) {
      n.setNext(n);
      n.setPrev(n);
      firstNode = n;
    }

    else {
      n.setNext(firstNode);
      n.setPrev(firstNode.getPrev());
      firstNode.getPrev().setNext(n);
      firstNode.setPrev(n);
    }

    return true;
  }
  
  /**
   * Remove and return the "oldest" item in the PrimQ.  If the PrimQ is empty,
   * return null.
   * 
   * Oldest added object is the closest along from firstNode going "next" (ie
   * it is firstNode).
   */
  public T removeItem() {
    if (numOfEntries == 0)
      return null;
    
    numOfEntries--;
    if (numOfEntries == 1) {
      T value = firstNode.getData();
      clear();
      return value;
    }

    T value = firstNode.getData();

    Node<T> head = firstNode;
    Node<T> prev = firstNode.getPrev();

    prev.setNext(head.getNext());
    head.getNext().setPrev(prev);

    firstNode = head.getNext();
    return value;
  }
    
  /**
   * Return true if the PrimQ is empty, and false otherwise.
   */
  public boolean empty() {
    return numOfEntries == 0;
  }
  
  /**
   * Return the number of items currently in the PrimQ.
   */
  public int size() {
    return numOfEntries;
  }

  /**
   * Reset the PrimQ to empty status by reinitializing the variables appropriately
   */
  public void clear() {
    firstNode = null;
    numOfEntries = 0;
  }

  /**
   * Logically reverse the data in the Reorder object so that the item
   * that was logically first will now be logically last and vice
   * versa.  The physical implementation of this can be done in 
   * many different ways, depending upon how you actually implemented
   * your physical LinkedDS<T> class
   */
  public void reverse() {
    if (numOfEntries == 0 || numOfEntries == 1)
      return;

    if (numOfEntries == 2) {
      firstNode = firstNode.getNext();
      return;
    }

    Node<T> head = firstNode;
    Node<T> temp = null;
    Node<T> current = firstNode;
    do {
      temp = current.getPrev();
      current.setPrev(current.getNext());
      current.setNext(temp);
      current = current.getPrev();
    } while (current != firstNode);

    firstNode = firstNode.getNext();
  }

  /**
   * Remove the logical last item of the DS and put it at the 
   * front.  As with reverse(), this can be done physically in
   * different ways depending on the underlying implementation.  
   */
  public void shiftRight() {
    Node<T> head = firstNode;
    firstNode.getPrev().setNext(head);
    firstNode.getNext().setPrev(head);
    firstNode = head.getPrev();
  }

  /**
   * Remove the logical first item of the DS and put it at the
   * end.  As above, this can be done in different ways.
   */
  public void shiftLeft() {
    Node<T> head = firstNode;
    firstNode.getPrev().setNext(head);
    firstNode.getNext().setPrev(head);
    firstNode = head.getNext();
  }

  /**
   * Reorganize the items in the object in a pseudo-random way.  The exact
   * way is up to you but it should utilize a Random object (see Random in 
   * the Java API).  Thus, after this operation the "oldest" item in the
   * DS could be arbitrary.
   */
  public void shuffle() {

  }

  /**
   * Shift the contents of the DS num places to the left (assume the beginning 
   * is the leftmost node), removing the leftmost num nodes.  For example, if 
   * a list has 8 nodes in it (numbered from 1 to 8), a leftShift of 3 would 
   * shift out nodes 1, 2 and 3 and the old node 4 would now be node 1.  
   * If num <= 0 leftShift should do nothing and if num >= the length of the 
   * list, the result should be an empty list.
   */
  public void leftShift(int num) {
    if (num < 0)
      throw new IllegalArgumentException();

    if (num >= numOfEntries) {
      clear();
      return;
    }

    numOfEntries = size() - num;

    if (numOfEntries == 1) {
      
      firstNode = firstNode.getPrev();
      firstNode.setNext(null);
      firstNode.setPrev(null);
    } else {      
      Node<T> n = firstNode;

      for (int counter = 0; counter < num; counter++) {
        n = n.getNext();
      }

      n.setPrev(firstNode.getPrev());
      firstNode.getPrev().setNext(n);
      firstNode = n;
    }
  }

  /**
   * Same idea as leftShift above, but in the opposite direction.  For example, 
   * if a list has 8 nodes in it (numbered from 1 to 8) a rightShift of 3 would 
   * shift out nodes 8, 7 and 6 and the old node 5 would now be the last node
   * in the list.  If num <= 0 rightShift should do nothing and if num >= the 
   * length of the list, the result should be an empty list.
   * 
   * NOT DONE
   */
  public void rightShift(int num) {
    if (num < 0)
      throw new IllegalArgumentException();

    if (num >= numOfEntries) {
      clear();
      return;
    }

    numOfEntries = size() - num;

    switch (numOfEntries) {
      case 1:
        firstNode = firstNode.getPrev();
        firstNode.setNext(null);
        firstNode.setPrev(null);
        break;
      case 2:
        firstNode.getPrev().setNext(null);
        firstNode.getPrev().getPrev().setPrev(null);
        firstNode = firstNode.getPrev();
        break;
      default:
        Node<T> n = firstNode;
        for (int counter = 0; counter < num; counter++) {
          n = n.getNext();
        }
        n.setPrev(firstNode.getPrev());
    }
  }

  /**
   * In this method you will still shift the contents of the list num places to 
   * the left, but rather than removing nodes from the list you will simply change 
   * their ordering in a cyclic way.  For example, if a list has 8 nodes in it 
   * numbered from 1 to 8), a leftRotate of 3 would shift nodes 1, 2 and 3 to the
   * end of the list, so that the old node 4 would now be node 1, and the old nodes 
   * 1, 2 and 3 would now be nodes 6, 7 and 8 (in that order).  The rotation should 
   * work modulo the length of the list, so, for example, if the list is length 8 then
   * a leftRotate of 10 should be equivalent to a leftRotate of 2.  If num < 0, the 
   * rotation should still be done but it will in fact be a right rotation rather than
   * a left rotation.
   */
  public void leftRotate(int num) {
    if (size() == 0)
      return;

    num = num % size();

    for (; num--> 0;) {
      shiftLeft();
    }
  }

  /**
   * Same idea as leftRotate above, but in the opposite direction.  For example, if a list 
   * has 8 nodes in it (numbered from 1 to 8), a rightRotate of 3 would shift nodes 8, 7 and 
   * 6 to the beginning of the list, so that the old node 8 would now be node 3, the old node 
   * 7 would now be node 2 and the old node 6 would now be node 1.  The behavior for num > the 
   * length of the list and for num < 0 should be analogous to that described above for leftRotate.
   */
  public void rightRotate(int num) {
    if (size() == 0)
      return;

    num = num % size();

    for (; num--> 0;) {
      shiftRight();
    }
  }
}
