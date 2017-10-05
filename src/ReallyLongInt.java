/** CS 0445 Fall 2017 (Adapted  from Dr. John Ramirez's assignment code)
 This is a partial implementation of the ReallyLongInt class.  You need to
 complete the implementations of the remaining methods.  Also, for this class
 to work, you must complete the implementation of the LinkedDS class.
 See additional comments below.
*/
public class ReallyLongInt 	extends LinkedDS<Integer> 
							implements Comparable<ReallyLongInt>
{
	// Instance variables are inherited.  You may not add any new instance variables
	
	// Default constructor
	private ReallyLongInt()
	{
		super();
	}

	// Note that we are adding the digits here in the FRONT. This is more efficient
	// (no traversal is necessary) and results in the LEAST significant digit first
	// in the list.  It is assumed that String s is a valid representation of an
	// unsigned integer with no leading zeros.
	public ReallyLongInt(String s)
	{
		super();
		char c;
		int digit;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.  Note that
		// the addItem() method (from LinkedDS) does not need to traverse the list since
		// it is adding in position 0.  
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9'))
			{
				digit = c - '0';
				this.addItem(new Integer(digit));
			}
			else throw new NumberFormatException("Illegal digit " + c);
		}
	}

	// Simple call to super to copy the nodes from the argument ReallyLongInt
	// into a new one.
	public ReallyLongInt(ReallyLongInt rightOp)
	{
		super(rightOp);
	}
	
	// Method to put digits of number into a String.  Since the numbers are
	// stored "backward" (least significant digit first) we first reverse the
	// number, then traverse it to add the digits to a StringBuilder, then
	// reverse it again.  This seems like a lot of work, but given the
	// limitations of the super classes it is what we must do.
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (numberOfEntries > 0)
		{
			this.reverse();
			for (Node curr = firstNode; curr != null; curr = curr.next)
			{
				sb.append(curr.data);
			}
			this.reverse();
		}
		return sb.toString();
	}

  public void debug() {
    reverse();
    Node<Integer> list = firstNode;
    while (list != null) {
      System.out.println(list.data);
      list = list.next;
    }
  }
  public ReallyLongInt debug(ReallyLongInt other) {
    ReallyLongInt result = new ReallyLongInt();
    reverse();
    other.reverse();
    Node<Integer> lista = firstNode;
    Node<Integer> listb = other.firstNode;

    int temp;
    boolean carry = false;

    while (lista != null && listb != null) {
      System.out.println("lista.data " + lista.data + " listb.data " + listb.data + " value from carry " + (carry ? 1 : 0));

      temp = lista.data + listb.data;

      System.out.println("value = " + (temp > 10 ? temp % 10 : temp) + "temp " + temp + " carry " + (carry ? 1 : 0));
      System.out.println("\t\tvalue + carry " + ((temp > 10 ? temp % 10 : temp) + (carry ? 1 : 0)));
      result.addItem((temp > 10 ? temp % 10 : temp) + (carry ? 1 : 0));

      carry = temp > 10;

      lista = lista.next;
      listb = listb.next;
    }

    if (carry) {
      result.addItem(1);
    }

    return result;
  }

  // You must implement the methods below.  See the descriptions in the
  // assignment sheet

  public ReallyLongInt add(ReallyLongInt rightOp)
  {
    ReallyLongInt result = new ReallyLongInt();
    Node<Integer> resultList = result.firstNode;

    Node<Integer> lista = firstNode;
    Node<Integer> listb = rightOp.firstNode;

    int lengtha = numberOfEntries;
    int lengthb = rightOp.numberOfEntries;
    // System.out.println("la " + lengtha + " lb " + lengthb);

    if (lengtha != lengthb) {
      if (lengtha > lengthb) {
        for (int idx = 0; idx < (lengtha - lengthb); idx++) {
          System.out.println("adding " + new Integer(lista.data));
          result.addItem(new Integer(lista.data));
          lista = lista.next;
        }
      } else {
        for (int idx = 0; idx < (lengthb - lengtha); idx++) {
          result.addItem(new Integer(listb.data));
          listb = listb.next;
        }

      }
    }
    
    while (lista != null && listb != null) {
      if ((lista.data + listb.data) < 10) {
        result.unshift(lista.data + listb.data);
      }
      else {
        System.out.println("result.firstNode.next.data " + result.firstNode.next.data);
        result.firstNode.next.data = result.firstNode.next.data + 1;
        result.unshift(lista.data + listb.data - 10);
      }
      lista = lista.next;
      listb = listb.next;
    }
    return result;
	}
	
  /**
   * 
   * First this method reverses the lists, such that the list start at the
   * first digit.
   */
	public ReallyLongInt subtract(ReallyLongInt rightOp)
	{
    return null;
  }

	public int compareTo(ReallyLongInt rOp)
	{
    return 0;
	}

	public boolean equals(Object rightOp)
	{
    ReallyLongInt other = (ReallyLongInt) rightOp;

    Node<Integer> lista = firstNode;
    Node<Integer> listb = other.firstNode;

    do {
      if (!lista.data.equals(listb.data)) {
        return false;
      }
      lista = lista.next;
      listb = listb.next;
    } while (lista.next != null && listb.next != null);

    return (listb.next == lista.next);
	}

	public void multTenToThe(int num)
	{
    for (; num--> 0; ) {
      Node<Integer> zero = new Node<Integer>(new Integer(0));
      zero.next = firstNode;
      firstNode = zero;
    }
  }

  public void divTenToThe(int num)
  {
    for (; num-- > 0; ) {
      this.removeItem();
    }
	}
}
