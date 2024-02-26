import java.io.File;
import java.util.Scanner;

public class Database {
  private Person[] persons;
  private int person_count;
  private Order[] orders;
  private int order_count;

  public Database(String file_name) {
    persons = new Person[100];
    person_count = 0;
    orders = new Order[100];
    order_count = 0;
    try {
      readFromFile(file_name);
    } catch (Exception e) {
      System.out.println("Error reading from file: " + e.getMessage());
      System.out.println(person_count + " " + order_count);
      System.out.println("File not found: " + file_name);
      return;
    }
  }

  public void readFromFile(String file_name)
      throws Exception {
    File file = new File(file_name);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split(";");
      String className = parts[0];
      try {
        switch (className) {
          case "operator":
            persons[person_count] = parseOperator(parts);
            person_count++;
            break;
          case "retail_customer":
            persons[person_count] = parseRetailCustomer(parts);
            person_count++;
            break;
          case "corporate_customer":
            persons[person_count] = parseCorporateCustomer(parts);
            person_count++;
            break;
          case "order":
            orders[order_count] = parseOrder(parts);
            order_count++;
            break;
          default:
            scanner.close();
            throw new IllegalArgumentException("Invalid class name: " + className);
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    scanner.close();
    for (int i = 0; i < order_count; i++) {
      addOrderTo(orders[i].getCustomer_id(), orders[i]);
    }
    for (int i = 0; i < person_count; i++) {
      if (persons[i] instanceof Customer) {
        addCustomerTo(((Customer) persons[i]).getOperator_id(), (Customer) persons[i]);
      }
    }
  }

  private Operator parseOperator(String[] parts)
      throws IllegalArgumentException {
    if (parts.length != 7 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid operator data: " + String.join(";", parts));
    }
    Operator operator = new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]));
    if (getPerson(operator.getId()) != null) {
      throw new IllegalArgumentException("Duplicate person id: " + operator.getId());
    }
    return operator;
  }

  private CorporateCustomer parseCorporateCustomer(String[] parts) {
    if (parts.length != 8 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid corporate customer data: " + String.join(";", parts));
    }
    CorporateCustomer corporateCustomer = new CorporateCustomer(parts[1], parts[2], parts[3], parts[4],
        Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
    if (getPerson(corporateCustomer.getId()) != null) {
      throw new IllegalArgumentException("Duplicate person id: " + corporateCustomer.getId());
    }
    return corporateCustomer;
  }

  private RetailCustomer parseRetailCustomer(String[] parts) {
    if (parts.length != 7 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid retail customer data: " + String.join(";", parts));
    }
    RetailCustomer retailCustomer = new RetailCustomer(parts[1], parts[2], parts[3], parts[4],
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]));
    if (getPerson(retailCustomer.getId()) != null) {
      throw new IllegalArgumentException("Duplicate person id: " + retailCustomer.getId());
    }
    return retailCustomer;
  }

  private Order parseOrder(String[] parts) {
    if (parts.length != 6 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid order data: " + String.join(";", parts));
    }
    Order order = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
    if (order.getCount() > 0)
      return order;
    throw new IllegalArgumentException("Invalid order count: " + order.getCount());
  }

  public Person getPerson(int id) {
    for (int i = 0; i < person_count; i++) {
      if (persons[i].getId() == id) {
        return persons[i];
      }
    }
    return null;
  }

  private boolean checkStrings(String[] parts) {
    for (String part : parts) {
      if (part == null || part.length() == 0) {
        return false;
      }
    }
    return true;
  }

  private void addCustomerTo(int operator_id, Customer customer) {
    Operator operator = (Operator) getPerson(operator_id);
    if (operator == null)
      throw new IllegalArgumentException("Operator not found: " + operator_id);
    operator.addCustomer(customer);
  }

  private void addOrderTo(int customer_id, Order order) {
    Customer customer = (Customer) getPerson(customer_id);
    if (customer == null)
      throw new IllegalArgumentException("Customer not found: " + customer_id);
    customer.addOrder(order);
  }

  public Person[] getPersons() {
    return persons;
  }

  public void setPersons(Person[] persons) {
    this.persons = persons;
  }

  public int getPerson_count() {
    return person_count;
  }

  public void setPerson_count(int person_count) {
    this.person_count = person_count;
  }

  public Order[] getOrders() {
    return orders;
  }

  public void setOrders(Order[] orders) {
    this.orders = orders;
  }

  public int getOrder_count() {
    return order_count;
  }

  public void setOrder_count(int order_count) {
    this.order_count = order_count;
  }
}
