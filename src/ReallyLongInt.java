// /** CS 0445 Fall 2017 (Adapted  from Dr. John Ramirez's assignment code)
//  This is a partial implementation of the ReallyLongInt class.  You need to
//  complete the implementations of the remaining methods.  Also, for this class
//  to work, you must complete the implementation of the LinkedDS class.
//  See additional comments below.
// */
// public class ReallyLongInt 	extends LinkedDS<Integer> 
// 							implements Comparable<ReallyLongInt>
// {
// 	// Instance variables are inherited.  You may not add any new instance variables
	
// 	// Default constructor
// 	private ReallyLongInt()
// 	{
// 		super();
// 	}

// 	// Note that we are adding the digits here in the FRONT. This is more efficient
// 	// (no traversal is necessary) and results in the LEAST significant digit first
// 	// in the list.  It is assumed that String s is a valid representation of an
// 	// unsigned integer with no leading zeros.
// 	public ReallyLongInt(String s)
// 	{
// 		super();
// 		char c;
// 		int digit;
// 		// Iterate through the String, getting each character and converting it into
// 		// an int.  Then make an Integer and add at the front of the list.  Note that
// 		// the addItem() method (from LinkedDS) does not need to traverse the list since
// 		// it is adding in position 0.  
// 		for (int i = 0; i < s.length(); i++)
// 		{
// 			c = s.charAt(i);
// 			if (('0' <= c) && (c <= '9'))
// 			{
// 				digit = c - '0';
// 				this.addItem(new Integer(digit));
// 			}
// 			else throw new NumberFormatException("Illegal digit " + c);
// 		}
// 	}

// 	// Simple call to super to copy the nodes from the argument ReallyLongInt
// 	// into a new one.
// 	public ReallyLongInt(ReallyLongInt rightOp)
// 	{
// 		super(rightOp);
// 	}
	
// 	// Method to put digits of number into a String.  Since the numbers are
// 	// stored "backward" (least significant digit first) we first reverse the
// 	// number, then traverse it to add the digits to a StringBuilder, then
// 	// reverse it again.  This seems like a lot of work, but given the
// 	// limitations of the super classes it is what we must do.
// 	public String toString()
// 	{
// 		StringBuilder sb = new StringBuilder();
// 		if (numberOfEntries > 0)
// 		{
// 			this.reverse();
// 			for (Node curr = firstNode; curr != null; curr = curr.next)
// 			{
// 				sb.append(curr.data);
// 			}
// 			this.reverse();
// 		}
// 		return sb.toString();
// 	}

// 	// You must implement the methods below.  See the descriptions in the
// 	// assignment sheet

// 	public ReallyLongInt add(ReallyLongInt rightOp)
// 	{
// 	}
	
// 	public ReallyLongInt subtract(ReallyLongInt rightOp)
// 	{	
// 	}

// 	public int compareTo(ReallyLongInt rOp)
// 	{
// 	}

// 	public boolean equals(Object rightOp)
// 	{
// 	}

// 	public void multTenToThe(int num)
// 	{
// 	}

// 	public void divTenToThe(int num)
// 	{
// 	}
// }
