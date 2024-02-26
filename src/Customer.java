public abstract class Customer extends Person {
  private Order[] orders;
  private int order_count;
  private int operator_id;

  public Customer(String name, String surname, String address, String phone, int id, int operator_id) {
    super(name, surname, address, phone, id);
    this.operator_id = operator_id;
    this.orders = new Order[100];
    this.order_count = 0;
  }

  // region Methods

  public void print() {
    print_customer();
    print_orders();
  }

  public void print_customer() {
    System.out.println("Name & Surname: " + getName() + " " + getSurname());
    System.out.println("Address: " + getAddress());
    System.out.println("Phone: " + getPhone());
    System.out.println("ID: " + getId());
    System.out.println("Operator ID: " + operator_id);
  }

  public void print_orders() {
    for (int i = 0; i < order_count; i++) {
      System.out.print("Order #" + (i + 1) + " => ");
      orders[i].print_order();
    }
    if (order_count == 0)
      System.out.println("This customer doesn't have any orders.");
  }

  public void define_orders(Order[] orders) {
    setOrders(orders);
  }

  public void addOrder(Order order) {
    orders[order_count++] = order;
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

  public int getOrder_count() {
    return order_count;
  }

  public void setOrder_count(int order_count) {
    this.order_count = order_count;
  }
  // endregion Getters and Setters
}
