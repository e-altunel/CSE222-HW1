public class Order {
  protected String product_name;
  protected int count;
  protected int total_price;
  protected int status;
  protected int customer_id;

  public Order(final String product_name, final int count, final int total_price, final int status,
      final int customer_id) {
    setProduct_name(product_name);
    setCount(count);
    setTotal_price(total_price);
    setStatus(status);
    setCustomer_id(customer_id);
  }

  public void print_order() {
    System.out.print("Product name: " + getProduct_name());
    System.out.print(" - Count: " + getCount());
    System.out.print(" - Total price: " + getTotal_price());
    System.out.print(" - Status: ");
    switch (getStatus()) {
      case 0:
        System.out.println("Initialized");
        break;
      case 1:
        System.out.println("Processing");
        break;
      case 2:
        System.out.println("Completed");
        break;
      default:
        System.out.println("Cancelled");
        break;
    }
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(final String product_name) {
    if (product_name == null)
      throw new IllegalArgumentException("Product name is null");
    if (product_name.length() == 0)
      throw new IllegalArgumentException("Product name is empty");
    this.product_name = product_name;
  }

  public int getCount() {
    return count;
  }

  public void setCount(final int count) {
    if (count <= 0)
      throw new IllegalArgumentException("Count is invalid: " + count);
    this.count = count;
  }

  public int getTotal_price() {
    return total_price;
  }

  public void setTotal_price(final int total_price) {
    if (total_price <= 0)
      throw new IllegalArgumentException("Total price is invalid: " + total_price);
    this.total_price = total_price;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(final int status) {
    if (status < 0 || status > 3)
      throw new IllegalArgumentException("Status is invalid: " + status);
    this.status = status;
  }

  public int getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(final int customer_id) {
    if (customer_id <= 0)
      throw new IllegalArgumentException("Customer ID is invalid: " + customer_id);
    this.customer_id = customer_id;
  }

}
