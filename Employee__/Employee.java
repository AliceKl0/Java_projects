package Employee__;

public class Employee {
    private int id_;
    private String FirstName;
    private String LastName;
    private int BirthYear;
    private String BirthPlace;
    private double Salary;
    private String MaritalSt;

    public Employee(int id_, String FirstName, String LastName, int BirthYear, String BirthPlace, double Salary,
                    String MaritalSt) {
        this.id_ = id_;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthYear = BirthYear;
        this.BirthPlace = BirthPlace;
        this.Salary = Salary;
        this.MaritalSt = MaritalSt;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getBirthYear() {
        return BirthYear;
    }

    public void setBirthYear(int BirthYear) {
        this.BirthYear = BirthYear;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String BirthPlace) {
        this.BirthPlace = BirthPlace;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getMaritalSt() {
        return MaritalSt;
    }

    public void setMaritalSt(String MaritalSt) {
        this.MaritalSt = MaritalSt;
    }

    public void display_info() {
        System.out.println('\n' + "ID: " + id_ + '\n' + "Имя: " + FirstName + '\n' + "Фамилия: " + LastName + '\n' +
                "Год рождения: " + BirthYear + '\n'
                + "Место рождения: " + BirthPlace + '\n' + "Зарплата: " + Salary + '\n' +
                "Семейное положение: " + MaritalSt);
    }
}