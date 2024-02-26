public class Operator extends Person {
  private int wage;
  private Customer[] customers;
  private int customer_count;

  public Operator(String name, String surname, String address, String phone, int id, int wage) {
    super(name, surname, address, phone, id);
    this.wage = wage;
    this.customers = new Customer[100];
    this.customer_count = 0;
  }

  public void print() {
    print_operator();
    print_customers();
  }

  // region Methods
  public void print_operator() {
    System.out.println("--------------------------");
    System.out.println("Name & Surname: " + getName() + " " + getSurname());
    System.out.println("Address: " + getAddress());
    System.out.println("Phone: " + getPhone());
    System.out.println("ID: " + getId());
    System.out.println("Wage: " + wage);
    System.out.println("--------------------------");
  }

  public void print_customers() {
    for (int i = 0; i < customer_count; i++) {
      System.out.print("Customer #" + (i + 1) + " ");
      if (customers[i] instanceof RetailCustomer)
        System.out.println("( a reatil customer )");
      else if (customers[i] instanceof CorporateCustomer)
        System.out.println("( a corporate customer )");
      customers[i].print_customer();
      customers[i].print_orders();
      System.out.println("--------------------------");
    }
    if (customer_count == 0) {
      System.out.println("This operator doesn't have any customers.");
      System.out.println("--------------------------");
    }
  }

  public void define_customers(Customer[] customers) {
    setCustomers(customers);
  }

  public void addCustomer(Customer customer) {
    customers[customer_count++] = customer;
  }
  // endregion Methods

  // region Getters and Setters
  public int getWage() {
    return wage;
  }

  public void setWage(int wage) {
    this.wage = wage;
  }

  public Customer[] getCustomers() {
    return customers;
  }

  public void setCustomers(Customer[] customers) {
    this.customers = customers;
  }

  public int getCustomer_count() {
    return customer_count;
  }

  public void setCustomer_count(int customer_count) {
    this.customer_count = customer_count;
  }

  // endregion Getters and Setters
}
