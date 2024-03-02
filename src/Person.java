public abstract class Person {
  protected String name;
  protected String surname;
  protected String address;
  protected String phone;
  protected int id;

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
    if (name == null)
      throw new IllegalArgumentException("Name is null");
    if (name.length() == 0)
      throw new IllegalArgumentException("Name is empty");
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(final String surname) {
    if (surname == null)
      throw new IllegalArgumentException("Surname is null");
    if (surname.length() == 0)
      throw new IllegalArgumentException("Surname is empty");
    this.surname = surname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    if (address == null)
      throw new IllegalArgumentException("Address is null");
    if (address.length() == 0)
      throw new IllegalArgumentException("Address is empty");
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(final String phone) {
    if (phone == null)
      throw new IllegalArgumentException("Phone is null");
    if (phone.length() == 0)
      throw new IllegalArgumentException("Phone is empty");
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    if (id <= 0)
      throw new IllegalArgumentException("ID is invalid: " + id);
    this.id = id;
  }

}
