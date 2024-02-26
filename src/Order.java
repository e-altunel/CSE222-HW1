public class Order {
  private String product_name;
  private int count;
  private int total_price;
  private int status;
  private int customer_id;

  public Order(String product_name, int count, int total_price, int status, int customer_id) {
    this.product_name = product_name;
    this.count = count;
    this.total_price = total_price;
    this.status = status;
    this.customer_id = customer_id;
  }

  // region Methods
  public void print_order() {
    System.out.print("Product name: " + product_name);
    System.out.print(" - Count: " + count);
    System.out.print(" - Total price: " + total_price);
    System.out.print(" - Status: ");
    switch (status) {
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
  // endregion Methods

  // region Getters and Setters
  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getTotal_price() {
    return total_price;
  }

  public void setTotal_price(int total_price) {
    this.total_price = total_price;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(int customer_id) {
    this.customer_id = customer_id;
  }
  // endregion Getters and Setters

}
