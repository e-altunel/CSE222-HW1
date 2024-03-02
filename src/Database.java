import java.io.File;
import java.util.Scanner;

public class Database {
  protected Person[] persons;
  protected int person_count;
  protected Order[] orders;
  protected int order_count;

  public Database(final String file_name) {
    setPersons(new Person[100]);
    setPerson_count(0);
    setOrders(new Order[100]);
    setOrder_count(0);
    try {
      readFromFile(file_name);
    } catch (final Exception e) {
      System.out.println(e.getMessage());
      return;
    }
  }

  public void readFromFile(final String file_name)
      throws Exception {
    final File file = new File(file_name);
    final Scanner scanner = new Scanner(file);
    int line_count = 0;
    boolean hasError = false;
    while (scanner.hasNextLine()) {
      final String line = scanner.nextLine();
      line_count++;
      final String[] parts = line.split(";");
      try {
        final int part_count = countChar(line, ';') + 1;
        if (part_count != parts.length)
          throw new IllegalArgumentException("Invalid semicolon count");
        if (line.length() == 0)
          throw new IllegalArgumentException("Empty line");
        if (parts.length == 0)
          throw new IllegalArgumentException("Line without semicolon");
        final String className = parts[0];
        switch (className) {
          case "operator":
            getPersons()[getPerson_count()] = parseOperator(parts);
            setPerson_count(getPerson_count() + 1);
            break;
          case "retail_customer":
            getPersons()[getPerson_count()] = parseRetailCustomer(parts);
            setPerson_count(getPerson_count() + 1);
            break;
          case "corporate_customer":
            getPersons()[getPerson_count()] = parseCorporateCustomer(parts);
            setPerson_count(getPerson_count() + 1);
            break;
          case "order":
            getOrders()[getOrder_count()] = parseOrder(parts);
            setOrder_count(getOrder_count() + 1);
            break;
          default:
            scanner.close();
            throw new IllegalArgumentException("Invalid class name: " + className);
        }
      } catch (final Exception e) {
        if (!hasError) {
          hasError = true;
          System.out.println("* Errors in the file: ");
        }
        System.out.println("Line " + line_count + " -> " + e.getMessage());
        continue;
      }
    }
    scanner.close();
    linkData();
    removeUnlinkedOrders(0);
    removeUnlinkedCustomer();
  }

  private void linkData() {
    for (int i = 0; i < getPerson_count(); i++) {
      if (getPersons()[i] instanceof Customer) {
        Order[] orders = new Order[100];
        int order_count = 0;
        for (int j = 0; j < getOrder_count(); j++) {
          if (getOrders()[j].getCustomer_id() == getPersons()[i].getId()) {
            orders[order_count] = getOrders()[j];
            order_count++;
          }
        }
        ((Customer) getPersons()[i]).define_orders(orders);
      }
    }
    for (int i = 0; i < getPerson_count(); i++) {
      if (getPersons()[i] instanceof Operator) {
        Customer[] customers = new Customer[100];
        int customer_count = 0;
        for (int j = 0; j < getPerson_count(); j++) {
          if (getPersons()[j] instanceof Customer
              && ((Customer) getPersons()[j]).getOperator_id() == getPersons()[i].getId()) {
            customers[customer_count] = (Customer) getPersons()[j];
            customer_count++;
          }
        }
        ((Operator) getPersons()[i]).define_customers(customers);
      }
    }
  }

  private int checkId(final int id) {
    for (int i = 0; i < getPerson_count(); i++)
      if (getPersons()[i].getId() == id)
        return 1;
    return 0;
  }

  private void removeUnlinkedOrders(int isCustomer) {
    for (int i = getOrder_count() - 1; i >= 0; i--) {
      if (checkId(getOrders()[i].getCustomer_id()) == 0) {
        if (isCustomer == 1)
          System.out
              .println("Order \"" + getOrders()[i].getProduct_name() + "\" is removed because its customer removed");
        else
          System.out
              .println(
                  "Order \"" + getOrders()[i].getProduct_name() + "\" is removed because its customer is not found");
        removeOrder(i);
      }
    }
  }

  public void removeUnlinkedCustomer() {
    for (int i = getPerson_count() - 1; i >= 0; i--) {
      if (getPersons()[i] instanceof Customer && checkId(((Customer) getPersons()[i]).getOperator_id()) == 0) {
        System.out
            .println("Customer \"" + getPersons()[i].getName() + "\" is removed because its operator is not found");
        removePerson(i);
      }
    }
    removeUnlinkedOrders(1);
  }

  private Operator parseOperator(final String[] parts)
      throws IllegalArgumentException {
    if (parts.length != 7 || checkStrings(parts) == 0)
      throw new IllegalArgumentException("Invalid operator data: " + String.join(";", parts));
    final Operator operator = new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]));
    if (getPerson(operator.getId()) != null)
      throw new IllegalArgumentException("Duplicate person id: " + operator.getId());
    return operator;
  }

  private CorporateCustomer parseCorporateCustomer(final String[] parts) {
    if (parts.length != 8 || checkStrings(parts) == 0)
      throw new IllegalArgumentException("Invalid corporate customer data: " + String.join(";", parts));
    final CorporateCustomer corporateCustomer = new CorporateCustomer(parts[1], parts[2], parts[3], parts[4],
        Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
    if (getPerson(corporateCustomer.getId()) != null)
      throw new IllegalArgumentException("Duplicate person id: " + corporateCustomer.getId());
    return corporateCustomer;
  }

  private RetailCustomer parseRetailCustomer(final String[] parts) {
    if (parts.length != 7 || checkStrings(parts) == 0)
      throw new IllegalArgumentException("Invalid retail customer data: " + String.join(";", parts));
    final RetailCustomer retailCustomer = new RetailCustomer(parts[1], parts[2], parts[3], parts[4],
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]));
    if (getPerson(retailCustomer.getId()) != null)
      throw new IllegalArgumentException("Duplicate person id: " + retailCustomer.getId());
    return retailCustomer;
  }

  private Order parseOrder(final String[] parts) {
    if (parts.length != 6 || checkStrings(parts) == 0)
      throw new IllegalArgumentException("Invalid order data: " + String.join(";", parts));
    final Order order = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
    if (order.getCount() > 0)
      return order;
    throw new IllegalArgumentException("Invalid order count: " + order.getCount());
  }

  public Person getPerson(final int id) {
    for (int i = 0; i < getPerson_count(); i++)
      if (getPersons()[i].getId() == id)
        return getPersons()[i];
    return null;
  }

  private static int checkStrings(final String[] parts) {
    for (final String part : parts)
      if (part == null || part.length() == 0)
        return 0;
    return 1;
  }

  public Person[] getPersons() {
    return persons;
  }

  public void setPersons(final Person[] persons) {
    this.persons = persons;
  }

  public void removePerson(final int index) {
    for (int i = index; i < getPerson_count() - 1; i++)
      getPersons()[i] = getPersons()[i + 1];
    setPerson_count(getPerson_count() - 1);
  }

  public int getPerson_count() {
    return person_count;
  }

  public void setPerson_count(final int person_count) {
    this.person_count = person_count;
  }

  public Order[] getOrders() {
    return orders;
  }

  public void setOrders(final Order[] orders) {
    this.orders = orders;
  }

  public void removeOrder(final int index) {
    for (int i = index; i < getOrder_count() - 1; i++)
      getOrders()[i] = getOrders()[i + 1];
    setOrder_count(getOrder_count() - 1);
  }

  public int getOrder_count() {
    return order_count;
  }

  public void setOrder_count(final int order_count) {
    this.order_count = order_count;
  }

  private int countChar(final String str, final char c) {
    int count = 0;
    for (int i = 0; i < str.length(); i++)
      if (str.charAt(i) == c)
        count++;
    return count;
  }
}
