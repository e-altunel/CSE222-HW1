public abstract class Customer extends Person {
  private Order[] orders;
  private int operator_id;

  public Customer(String name, String surname, String address, String phone, int id, int operator_id) {
    super(name, surname, address, phone, id);
    this.operator_id = operator_id;
  }

  public String toString() {
    return super.toString() + " Operator: " + operator_id + " Orders: " + orders.length;
  }

  // region Methods

  public void print_customer() {
    System.out.println("*** Customer Screen ***");
    System.out.println("Name & Surname: " + getName() + " " + getSurname());
    System.out.println("Address: " + getAddress());
    System.out.println("Phone: " + getPhone());
    System.out.println("ID: " + getId());
    System.out.println("Operator ID: " + operator_id);
  }

  public void print_orders() {
    int index = 1;
    for (Order order : orders) {
      System.out.print("Order #" + index++ + " => ");
      order.print_order();
    }
  }

  public void define_orders(Order[] orders) {
    setOrders(orders);
  }
  // endregion Methods

  // region Getters and Setters
  public Order[] getOrders() {
    return orders;
  }

  public void setOrders(Order[] orders) {
    this.orders = orders;
  }

  public int getOperator_id() {
    return operator_id;
  }

  public void setOperator_id(int operator_id) {
    this.operator_id = operator_id;
  }
  // endregion Getters and Setters
}
