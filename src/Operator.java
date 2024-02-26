public class Operator extends Person {
  private int wage;
  private Customer[] customers;

  public Operator(String name, String surname, String address, String phone, int id, int wage) {
    super(name, surname, address, phone, id);
    this.wage = wage;
  }

  public String toString() {
    return super.toString() + " Wage: " + wage;
  }

  // region Methods
  public void print_operator() {
  }

  public void print_customers() {
  }

  public void define_customers(Customer[] customers) {
    setCustomers(customers);
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

  // endregion Getters and Setters
}
