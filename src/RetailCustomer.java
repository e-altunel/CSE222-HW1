public class RetailCustomer extends Customer {

  public RetailCustomer(String name, String surname, String address, String phone, int id, int operator_id) {
    super(name, surname, address, phone, id, operator_id);
  }

  public String toString() {
    return super.toString() + " Retail Customer";
  }
}
