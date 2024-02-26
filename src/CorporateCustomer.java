public class CorporateCustomer extends Customer {
  String company_name;

  public CorporateCustomer(String name, String surname, String address, String phone, int id, int operator_id,
      String company_name) {
    super(name, surname, address, phone, id, operator_id);
    this.company_name = company_name;
  }

  public String toString() {
    return super.toString() + " Company: " + company_name;
  }

  // region Methods
  public void print_customer() {
    super.print_customer();
    System.out.println("Company: " + company_name);
  }
  // endregion Methods

  // region Getters and Setters
  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }
  // endregion Getters and Setters
}
