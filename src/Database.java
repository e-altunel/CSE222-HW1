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
          System.out.println("* Syntax errors in the file: ");
        }
        System.out.println("Line " + line_count + " -> " + e.getMessage());
        continue;
      }
    }
    scanner.close();
    hasError = false;
    try {
      linkData();
    } catch (final Exception e) {
      if (!hasError) {
        hasError = true;
        System.out.println("* Linking errors: ");
      }
      System.out.println(e.getMessage());
    }
  }

  private void linkData() {
    for (int i = 0; i < getOrder_count(); i++) {
      try {
        addOrderTo(getOrders()[i].getCustomer_id(), getOrders()[i]);
      } catch (final Exception e) {
        removePerson(i);
        throw e;
      }
    }
    for (int i = 0; i < getPerson_count(); i++) {
      if (getPersons()[i] instanceof Customer) {
        try {
          addCustomerTo(((Customer) getPersons()[i]).getOperator_id(), (Customer) getPersons()[i]);
        } catch (final Exception e) {
          removePerson(i);
          throw e;
        }
      }
    }
  }

  private Operator parseOperator(final String[] parts)
      throws IllegalArgumentException {
    if (parts.length != 7 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid operator data: " + String.join(";", parts));
    }
    final Operator operator = new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]));
    if (getPerson(operator.getId()) != null) {
      throw new IllegalArgumentException("Duplicate person id: " + operator.getId());
    }
    return operator;
  }

  private CorporateCustomer parseCorporateCustomer(final String[] parts) {
    if (parts.length != 8 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid corporate customer data: " + String.join(";", parts));
    }
    final CorporateCustomer corporateCustomer = new CorporateCustomer(parts[1], parts[2], parts[3], parts[4],
        Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
    if (getPerson(corporateCustomer.getId()) != null) {
      throw new IllegalArgumentException("Duplicate person id: " + corporateCustomer.getId());
    }
    return corporateCustomer;
  }

  private RetailCustomer parseRetailCustomer(final String[] parts) {
    if (parts.length != 7 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid retail customer data: " + String.join(";", parts));
    }
    final RetailCustomer retailCustomer = new RetailCustomer(parts[1], parts[2], parts[3], parts[4],
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]));
    if (getPerson(retailCustomer.getId()) != null) {
      throw new IllegalArgumentException("Duplicate person id: " + retailCustomer.getId());
    }
    return retailCustomer;
  }

  private Order parseOrder(final String[] parts) {
    if (parts.length != 6 || !checkStrings(parts)) {
      throw new IllegalArgumentException("Invalid order data: " + String.join(";", parts));
    }
    final Order order = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
    if (order.getCount() > 0)
      return order;
    throw new IllegalArgumentException("Invalid order count: " + order.getCount());
  }

  public Person getPerson(final int id) {
    for (int i = 0; i < getPerson_count(); i++) {
      if (getPersons()[i].getId() == id) {
        return getPersons()[i];
      }
    }
    return null;
  }

  private static boolean checkStrings(final String[] parts) {
    for (final String part : parts) {
      if (part == null || part.length() == 0) {
        return false;
      }
    }
    return true;
  }

  private void addCustomerTo(final int operator_id, final Customer customer) {
    final Operator operator = (Operator) getPerson(operator_id);
    if (operator == null)
      throw new IllegalArgumentException("Operator not found: " + operator_id);
    operator.addCustomer(customer);
  }

  private void addOrderTo(final int customer_id, final Order order) {
    final Customer customer = (Customer) getPerson(customer_id);
    if (customer == null)
      throw new IllegalArgumentException("Customer not found: " + customer_id);
    customer.addOrder(order);
  }

  public Person[] getPersons() {
    return persons;
  }

  public void setPersons(final Person[] persons) {
    this.persons = persons;
  }

  public void removePerson(final int index) {
    for (int i = index; i < getPerson_count() - 1; i++) {
      getPersons()[i] = getPersons()[i + 1];
    }
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
    for (int i = index; i < getOrder_count() - 1; i++) {
      getOrders()[i] = getOrders()[i + 1];
    }
    setOrder_count(getOrder_count() - 1);
  }

  public int getOrder_count() {
    return order_count;
  }

  public void setOrder_count(final int order_count) {
    this.order_count = order_count;
  }
}
