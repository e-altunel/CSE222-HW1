public abstract class Customer extends Person {
  private Order[] orders;
  private int order_count;
  private int operator_id;

  public Customer(final String name, final String surname, final String address, final String phone, final int id,
      final int operator_id) {
    super(name, surname, address, phone, id);
    setOperator_id(operator_id);
    setOrders(new Order[100]);
    setOrder_count(0);
  }

  public void print() {
    print_customer();
    print_orders();
  }

  public void print_customer() {
    System.out.println("Name & Surname: " + getName() + " " + getSurname());
    System.out.println("Address: " + getAddress());
    System.out.println("Phone: " + getPhone());
    System.out.println("ID: " + getId());
    System.out.println("Operator ID: " + getOperator_id());
  }

  public void print_orders() {
    for (int i = 0; i < getOrder_count(); i++) {
      System.out.print("Order #" + (i + 1) + " => ");
      getOrders()[i].print_order();
    }
    if (getOrder_count() == 0)
      System.out.println("This customer doesn't have any orders.");
  }

  public void define_orders(final Order[] orders) {
    setOrders(orders);
  }

  public void addOrder(final Order order) {
    getOrders()[getOrder_count()] = order;
    setOrder_count(getOrder_count() + 1);
  }

  public Order[] getOrders() {
    return orders;
  }

  public void setOrders(final Order[] orders) {
    this.orders = orders;
  }

  public int getOperator_id() {
    return operator_id;
  }

  public void setOperator_id(final int operator_id) {
    if (operator_id <= 0)
      throw new IllegalArgumentException("Operator ID is invalid: " + operator_id);
    this.operator_id = operator_id;
  }

  public int getOrder_count() {
    return order_count;
  }

  public void setOrder_count(final int order_count) {
    this.order_count = order_count;
  }
}
