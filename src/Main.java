import java.util.Scanner;

public class Main {
  static final String file_name = "content.txt";

  public static void main(String[] args) {
    Database db = new Database(file_name);

    System.out.println("Please enter your ID: ");
    Scanner scanner = new Scanner(System.in);
    int id = scanner.nextInt();
    scanner.close();
    Person p = db.getPerson(id);
    if (p == null) {
      System.out.println("No person found with ID: " + id);
      return;
    }
    if (p instanceof Operator)
      System.out.println("*** Operator ***");
    else if (p instanceof Customer)
      System.out.println("*** Customer ***");
    p.print();
  }
}
