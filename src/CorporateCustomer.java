public class CorporateCustomer extends Customer {
  String company_name;

  public CorporateCustomer(final String name, final String surname, final String address, final String phone,
      final int id, final int operator_id,
      final String company_name) {
    super(name, surname, address, phone, id, operator_id);
    setCompany_name(company_name);
  }

  public void print_customer() {
    super.print_customer();
    System.out.println("Company: " + getCompany_name());
  }

  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(final String company_name) {
    this.company_name = company_name;
  }
}
