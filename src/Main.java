public class Main {
  public static void main(String[] args) {
    // ReallyLongInt R1 = new ReallyLongInt("123456789");
    // // R1.multTenToThe(2);
    // R1.divTenToThe(2);
    // // System.out.println(R1);
    // ReallyLongInt R2 = new ReallyLongInt("3456789");
    // // System.out.println(R2);
    // // System.out.println(R1.equals(R2));

    ReallyLongInt R3 = new ReallyLongInt("512");
    ReallyLongInt R4 = new ReallyLongInt("512");
    System.out.println(R3.debug(R4));


    System.exit(0);



    RLITest.main(args);
    System.exit(0);

    LinkedDS<Integer> l = new LinkedDS<Integer>();
    for (int i = 0; i < 5; i++) {
      l.addItem(i);
    }

    System.out.println(l);
    l.shiftRight();
    l.shiftRight();
    System.out.println(l);
    l.shiftLeft();
    System.out.println(l);
    l.shuffle();
    System.out.println(l);
    
  }
}
