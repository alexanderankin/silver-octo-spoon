public class Main {
  public static void main(String[] args) {
    RLITest.main(args);
    System.exit(0);


    // ReallyLongInt R1 = new ReallyLongInt("1234567");
    // ReallyLongInt R1 = new ReallyLongInt("12");
    // System.out.println(R1);
    // System.exit(0);
    // R1.multTenToThe(6);
    // System.out.println(R1);
    // R1.divTenToThe(8);
    // System.out.println(R1);
    // System.out.println();

    // ReallyLongInt R1 = new ReallyLongInt("123456789");
    // // R1.multTenToThe(2);
    // R1.divTenToThe(2);
    // // System.out.println(R1);
    // ReallyLongInt R2 = new ReallyLongInt("3456789");
    // // System.out.println(R2);
    // // System.out.println(R1.equals(R2));


    /*// negative number carry is true
    ReallyLongInt R3 = new ReallyLongInt("101");
    ReallyLongInt R4 = new ReallyLongInt("102");
    System.out.println(R3.subtract(R4));*/

    // negative number carry is true
// node -p "444444544444448 > 444444444444448"
    // ReallyLongInt R3 = new ReallyLongInt("4444");
    // ReallyLongInt R4 = new ReallyLongInt("444444444444447");
    // System.out.println(R3.compareTo(R4));


    System.exit(0);



    RLITest.main(args);
    System.exit(0);

    
  }

  public static void main1(String[] args) {
    LinkedDS<Integer> l = new LinkedDS<Integer>();
    for (int i = 0; i < 5; i++) {
      l.addItem(i);
    }

    l.shuffle();

    System.out.println(l);
    System.exit(0);

    l.shiftRight();
    l.shiftRight();
    System.out.println(l);
    l.shiftLeft();
    System.out.println(l);
    l.shuffle();
    System.out.println(l);
    
  }
}
