public class Operator extends Person {
  private int wage;
  private Customer[] customers;
  private int customer_count;

  public Operator(final String name, final String surname, final String address, final String phone, final int id,
      final int wage) {
    super(name, surname, address, phone, id);
    setWage(wage);
    setCustomers(new Customer[100]);
    setCustomer_count(0);
  }

  public void print() {
    print_operator();
    print_customers();
  }

  public void print_operator() {
    System.out.println("--------------------------");
    System.out.println("Name & Surname: " + getName() + " " + getSurname());
    System.out.println("Address: " + getAddress());
    System.out.println("Phone: " + getPhone());
    System.out.println("ID: " + getId());
    System.out.println("Wage: " + getWage());
    System.out.println("--------------------------");
  }

  public void print_customers() {
    for (int i = 0; i < getCustomer_count(); i++) {
      System.out.print("Customer #" + (i + 1) + " ");
      if (getCustomers()[i] instanceof RetailCustomer)
        System.out.println("( a reatil customer )");
      else if (getCustomers()[i] instanceof CorporateCustomer)
        System.out.println("( a corporate customer )");
      else
        throw new IllegalArgumentException("Invalid customer type.");
      getCustomers()[i].print();
      System.out.println("--------------------------");
    }
    if (getCustomer_count() == 0) {
      System.out.println("This operator doesn't have any customers.");
      System.out.println("--------------------------");
    }
  }

  public void define_customers(final Customer[] customers) {
    setCustomers(customers);
  }

  public void addCustomer(final Customer customer) {
    getCustomers()[getCustomer_count()] = customer;
    setCustomer_count(getCustomer_count() + 1);
  }

  public int getWage() {
    return wage;
  }

  public void setWage(final int wage) {
    this.wage = wage;
  }

  public Customer[] getCustomers() {
    return customers;
  }

  public void setCustomers(final Customer[] customers) {
    this.customers = customers;
  }

  public int getCustomer_count() {
    return customer_count;
  }

  public void setCustomer_count(final int customer_count) {
    this.customer_count = customer_count;
  }

}
