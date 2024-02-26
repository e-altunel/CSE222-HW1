import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

    /*
     * 
     * Person[] database;
     * try {
     * database = readPersonsFromFile(file_name);
     * } catch (FileNotFoundException e) {
     * System.out.println("File not found: " + file_name);
     * return;
     * }
     * 
     * for (Person p : database) {
     * if (p instanceof Operator) {
     * ((Operator) p).print_operator();
     * ((Operator) p).print_customers();
     * } else if (p instanceof Customer) {
     * ((Customer) p).print_customer();
     * ((Customer) p).print_orders();
     * }
     * System.out.println("\033[31m--------------------------\033[0m");
     * }
     */
  }

  private static Person[] readPersonsFromFile(String file_name) throws FileNotFoundException {
    List<Person> persons = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    File file = new File(file_name);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split(";");
      String className = parts[0];
      switch (className) {
        case "operator":
          persons.add(new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]),
              Integer.parseInt(parts[6])));
          break;
        case "retail_customer":
          persons.add(new RetailCustomer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]),
              Integer.parseInt(parts[6])));
          break;
        case "corporate_customer":
          persons.add(new CorporateCustomer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]),
              Integer.parseInt(parts[6]), parts[7]));
          break;
        case "order":
          Order o = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
              Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
          if (o.getCount() > 0)
            orders.add(o);
          break;
        default:
          throw new IllegalArgumentException("Invalid class name: " + className);
      }
    }
    scanner.close();
    // Set Orders and Customers
    for (Person p : persons) {
      if (p instanceof Customer) {
        Customer c = (Customer) p;
        List<Order> customerOrders = new ArrayList<>();
        for (Order o : orders) {
          if (o.getCustomer_id() == c.getId()) {
            customerOrders.add(o);
          }
        }
        c.define_orders(customerOrders.toArray(new Order[0]));
      } else if (p instanceof Operator) {
        Operator o = (Operator) p;
        List<Customer> operatorCustomers = new ArrayList<>();
        for (Person p2 : persons) {
          if (p2 instanceof Customer) {
            Customer c = (Customer) p2;
            if (c.getOperator_id() == o.getId()) {
              operatorCustomers.add(c);
            }
          }
        }
      }
    }
    return persons.toArray(new Person[0]);
  }
}
