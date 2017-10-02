public class Main {
  public static void main(String[] args) {
    LinkedDS<Integer> l = new LinkedDS<Integer>();
    boolean value = l.addItem(1);
    // System.out.println("addItem returned " + value);
    value = l.addItem(2);
    value = l.addItem(3);
    value = l.addItem(4);
    value = l.addItem(5);
    l.debug();
    // l.rightShift(2);
    // l.debug();

  }
  public static void main1(String[] args) {
    // System.out.println(5 % 0);
  }
}