public abstract class Person {
  private String name;
  private String surname;
  private String address;
  private String phone;
  private int id;

  public Person(final String name, final String surname, final String address, final String phone, final int id) {
    setName(name);
    setSurname(surname);
    setAddress(address);
    setPhone(phone);
    setId(id);
  }

  public abstract void print();

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

}
