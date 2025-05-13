public class Person {
    private int personId;
    private int yearOfBirth;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String phoneNumber;

    public Person(int personId, int yearOfBirth, String firstName, String lastName, String middleName, String address,
                  String phoneNumber) {
        this.personId = personId;
        this.yearOfBirth = yearOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Геттеры и сеттеры (снова не для всех атрибутов аналогично логики в Book)


    public int getPersonId() {
        return personId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
