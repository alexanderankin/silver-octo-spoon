import java.util.Random;

/**
 * This is a data structure.
 */
public class LinkedDS<T> implements PrimQ<T>, Reorder {
  /**
   * This is a node on the DS
   */
  public static class Node<T> {
    Node<T> next;
    T data;
    public Node() {}
    public Node(T d) { data = d; }
    public T getData() { return data; }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Node @");
      sb.append(this.hashCode());
      sb.append(": [");

      Node list = this.next;
      while (list != null) {
        sb.append(list.data);
        sb.append(", ");
      }
      sb.append("]");

      return sb.toString();
    }
  }

  /**
   * This is the first Node in this list
   */
  public Node<T> firstNode;
  public int numberOfEntries;

  public LinkedDS () {
    numberOfEntries = 0;
  }

  public LinkedDS (LinkedDS l) {
    numberOfEntries = l.size();
    if (numberOfEntries == 0)
      return;

    Node<T> head = l.getHead();
    firstNode = new Node(head.data);
    Node<T> pointer = firstNode;

    while (head.next != null) {
      head = head.next;
      pointer.next = new Node(head.data);
      pointer = pointer.next;
    }
  }

  private Node<T> getHead() {
    return firstNode;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<T> pointer = firstNode;
    while (pointer != null) {
      sb.append(pointer.data.toString());
      sb.append(" ");
      pointer = pointer.next;
      if (pointer == firstNode) {
        break;
      }
    }
    return sb.toString();
  }

  /**
   * Add a new Object to the PrimQ<T> in the next available location. If all
   * goes well, return true.
   */
  public boolean addItem(T newEntry){
    if (firstNode == null) {
      firstNode = new Node(newEntry);
    } else {
      Node<T> temp = firstNode;
      while (temp.next != null)
        temp = temp.next;
      temp.next = new Node(newEntry);
    }
    numberOfEntries++;
    return true;
  }

  /**
   * Performs unshift operation like standard Javascript method
   * 
   * @return updated size of array
   */
  public int unshift(T newEntry){
    Node<T> item = new Node<T>(newEntry);
    item.next = firstNode;
    firstNode = item;
    return ++numberOfEntries;
  }
  
  /**
   * Remove and return the "oldest" item in the PrimQ.  If the PrimQ is empty,
   * return null.
   */
  public T removeItem() {
    if (numberOfEntries == 0) {
      return null;
    }

    Node<T> temp = firstNode;
    firstNode = temp.next;
    numberOfEntries--;
    return temp.getData();
  }
    
  /**
   * Return true if the PrimQ is empty, and false otherwise.
   */
  public boolean empty() {
    return numberOfEntries == 0;
  }
  
  /**
   * Return the number of items currently in the PrimQ.
   */
  public int size() {
    return numberOfEntries;
  }

  /**
   * Reset the PrimQ to empty status by reinitializing the variables appropriately
   */
  public void clear() {
    numberOfEntries = 0;
    firstNode = null;
  }

  /**
   * Logically reverse the data in the Reorder object so that the item
   * that was logically first will now be logically last and vice
   * versa.  The physical implementation of this can be done in 
   * many different ways, depending upon how you actually implemented
   * your physical LinkedDS<T> class
   * 
   * 
   * UNTESTED
   */
  public void reverse() {
    Node<T> prev = null;
    Node<T> curr = firstNode;
    Node<T> next = null;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    firstNode = prev;
  }

  /**
   * Remove the logical last item of the DS and put it at the 
   * front.  As with reverse(), this can be done physically in
   * different ways depending on the underlying implementation.  
   */
  public void shiftRight() {
    Node<T> temp = null;
    Node<T> reference = firstNode;
    Node<T> penultimate = null;

    while (reference.next != null) {
      if (reference.next.next == null) {
        penultimate = reference;
      }
      reference = reference.next;
    }

    reference.next = firstNode;
    firstNode = reference;
    penultimate.next = null;
  }

  /**
   * Remove the logical first item of the DS and put it at the
   * end.  As above, this can be done in different ways.
   */
  public void shiftLeft() {
    Node<T> last = firstNode;
    firstNode = firstNode.next;

    Node<T> reference = firstNode;
    while (reference.next != null) {
      reference = reference.next;
    }

    reference.next = last;
    reference = reference.next;
    reference.next = null;
  }

  /**
   * Reorganize the items in the object in a pseudo-random way.  The exact
   * way is up to you but it should utilize a Random object (see Random in 
   * the Java API).  Thus, after this operation the "oldest" item in the
   * DS could be arbitrary.
   */
  public void shuffle() {
    Random r = new Random();
    T temp;

    for (int counter = 0; counter < 10; counter++) {
      Node<T> list = firstNode;
      while (list.next != null) {
        if (r.nextDouble() > 0.5) {
          temp = list.data;
          list.data = list.next.data;
          list.next.data = temp;
        }

        list = list.next;
      }
    }
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
    numberOfEntries = numberOfEntries - num;

    while (num--> 0 && firstNode != null) {
      firstNode = firstNode.next;
    }
  }

  /**
   * Same idea as leftShift above, but in the opposite direction.  For example, 
   * if a list has 8 nodes in it (numbered from 1 to 8) a rightShift of 3 would 
   * shift out nodes 8, 7 and 6 and the old node 5 would now be the last node
   * in the list.  If num <= 0 rightShift should do nothing and if num >= the 
   * length of the list, the result should be an empty list.
   */
  public void rightShift(int num) {
    numberOfEntries = numberOfEntries - num;

    int a = numberOfEntries;
    Node<T> pointer = firstNode;
    for (; --a > 0; ) {
      pointer = pointer.next;
    }
    pointer.next = null;
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
    num = Math.floorMod(num, numberOfEntries);
    if (num == 0) {
      return;
    }
    Node<T> head = firstNode;
    Node<T> reference = firstNode;
    Node<T> middle = null;
    Node<T> newTail = null;

    int counter = 0;
    while (reference != null) {
      if (counter == num) {
        firstNode = reference;
      }
      if (counter == (num - 1)) {
        middle = reference;
      }
      if (counter == (numberOfEntries - 1)) {
        newTail = reference;
        // reference.next = head;
      }
      reference = reference.next;
      counter++;
    }

    newTail.next = head;
    middle.next = null;
  }

  /**
   * Same idea as leftRotate above, but in the opposite direction.  For example, if a list 
   * has 8 nodes in it (numbered from 1 to 8), a rightRotate of 3 would shift nodes 8, 7 and 
   * 6 to the beginning of the list, so that the old node 8 would now be node 3, the old node 
   * 7 would now be node 2 and the old node 6 would now be node 1.  The behavior for num > the 
   * length of the list and for num < 0 should be analogous to that described above for leftRotate.
   */
  public void rightRotate(int num) {
    leftRotate(-num);
  }
}
