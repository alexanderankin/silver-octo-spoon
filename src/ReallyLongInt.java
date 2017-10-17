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
      temp = lista.data + listb.data;
      result.addItem((temp > 10 ? temp % 10 : temp) + (carry ? 1 : 0));
      carry = temp > 10;

      lista = lista.next;
      listb = listb.next;
    }

    if (carry) {
      result.addItem(1);
    }

    return null;
  }

  // You must implement the methods below.  See the descriptions in the
  // assignment sheet

  public ReallyLongInt add(ReallyLongInt rightOp)
  {
    ReallyLongInt result = new ReallyLongInt();
    reverse();
    rightOp.reverse();

    Node<Integer> lista = firstNode;
    Node<Integer> listb = rightOp.firstNode;

    int temp;
    int _i;
    boolean carry = false;

    while (lista != null && listb != null) {
      temp = lista.data + listb.data;
      _i = 0;
      if (temp > 10) {
        _i += temp % 10;
      } else {
        _i += temp;
      }

      if (carry) {
        _i += 1;
      }

      if (_i > 10) {
        System.out.println("_i > 10 " + _i);
      }

      // result.addItem((temp > 10 ? temp % 10 : temp) + (carry ? 1 : 0));
      System.out.println(_i);
      System.out.println(result.toString());
      result.addItem(_i);
      carry = temp > 10;

      lista = lista.next;
      listb = listb.next;
    }

    boolean firstIter = true;
    Node<Integer> remainingList = (lista == null ? listb : lista);
    while (remainingList != null) {
      // System.out.println(firstIter && carry ? 1 + remainingList.data : remainingList.data);
      result.addItem(firstIter && carry ? 1 + remainingList.data : remainingList.data);
      firstIter = false;
      remainingList = remainingList.next;
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
    if (rightOp.size() > numberOfEntries) {
      throw new IllegalArgumentException("No negative numbers allowed");
    }

    ReallyLongInt result = new ReallyLongInt();
    reverse();
    rightOp.reverse();

    Node<Integer> lista = firstNode;
    Node<Integer> listb = rightOp.firstNode;

    int temp;
    boolean carry = false;

    while (/*lista != null && */listb != null) {
      temp = (carry ? lista.data - 1 : lista.data) - listb.data;
      result.addItem(temp < 0 ? temp + 10 : temp);
      carry = temp < 0;

      lista = lista.next;
      listb = listb.next;
    }

    boolean firstIter = true;
    Node<Integer> remainingList = lista;
    while (remainingList != null) {
      result.addItem(firstIter && carry ? remainingList.data - 1 : remainingList.data);
      remainingList = remainingList.next;
    }

    return result;
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
