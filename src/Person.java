public abstract class Person {
  private String name;
  private String surname;
  private String address;
  private String phone;
  private int id;

  public Person(String name, String surname, String address, String phone, int id) {
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.phone = phone;
    this.id = id;
  }

  public String toString() {
    return id + " " + name + " " + surname;
  }

  // region Getters and Setters

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  // endregion Getter and Setters
}
