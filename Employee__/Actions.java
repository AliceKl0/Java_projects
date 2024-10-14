package Employee__;

import java.util.Scanner;
import java.util.ArrayList;

public class Actions {
    private static final int max_=100;
    private static ArrayList<Employee> List_ = new ArrayList<>();

    // Добавляем нового сотрудника
    public void add_(Employee employee) {
        if (List_.size() >= max_) {
            System.out.println("Кол-во сотрудников в базе достигло 100!");
            return;
        }

        // Проверка на уникальность ID
        for (Employee e : List_) {
            if (e.getId_() == employee.getId_()) {
                System.out.println("Сотрудник с данным ID уже есть в базе!");
                return;
            }
        }
        List_.add(employee);
        System.out.println("Сотрудник добавлен в базу!");
    }

    // Ищем по ID
    public Employee find_by_id(int id_) {
        for (Employee e : List_) {
            if (e.getId_() == id_) {
                e.display_info();
                return e;
            }
        }
        System.out.println("Сотрудника с введённым ID в базе нет!");
        return null;
    }

    // Поиск по имени и ДР
    public void find_by_name_birth(String FistName, int BirtYear) {
        boolean found = false;
        for (Employee e : List_) {
            if (e.getFirstName().equals(FistName) || e.getBirthYear() == BirtYear) {
                e.display_info();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Сотрудников с аналогичными данными в базе нет!");
        }
    }

    // Сумма зарплат
    public double sum_salary() {
        double result = 0;
        for (Employee e : List_) {
            result += e.getSalary();
        }
        return result;
    }

    // Меняем инфо о сотруднике (Строковый тип данных)
    public void change_info_str(int id_, String meaning, String new_) {
        Employee employee = find_by_id(id_);
        if (employee != null) {
            switch (meaning.toLowerCase()) {
                case "имя":
                    employee.setFirstName(new_);
                    break;
                case "фамилия":
                    employee.setLastName(new_);
                    break;
                case "место рождения":
                    employee.setBirthPlace(new_);
                    break;
                case "семейное положение":
                    employee.setMaritalSt(new_);
                    break;
                default:
                    System.out.println("Поля не существует");
            }
            System.out.println("Информация успешно обновлена!");
        }
    }

    // Меняем инфо о сотруднике (Числовой тип данных)
    public void change_info_int(int id_, String meaning, double new_) {
        Employee employee = find_by_id(id_);
        if (employee != null) {
            switch (meaning.toLowerCase()) {
                case "год рождения":
                    employee.setBirthYear((int) new_);
                    break;
                case "зарплата":
                    employee.setSalary((double) new_);
                    break;
                default:
                    System.out.println("Поля не существует");
            }
            System.out.println("Информация успешно обновлена!");
        }
    }

    // Непосредственно взаимодействие с пользователем
    public static void main(String[] args) {
        Actions act_with_e = new Actions();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Меню:");
            System.out.println("1. Добавить сотрудника");
            System.out.println("2. Найти сотрудника по ID");
            System.out.println("3. Найти сотрудника по имени или году рождения");
            System.out.println("4. Обновить информацию о сотруднике");
            System.out.println("5. Рассчитать общую сумму зарплат");
            System.out.println("0. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите ID:");
                    int id_ = scanner.nextInt();
                    if (act_with_e.find_by_id(id_) == null) {
                        scanner.nextLine();
                        System.out.println("Введите имя:");
                        String FirstName = scanner.nextLine();
                        System.out.println("Введите фамилию:");
                        String LastName = scanner.nextLine();
                        System.out.println("Введите год рождения:");
                        int BirthYear = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введите место рождения:");
                        String BirthPlace = scanner.nextLine();
                        System.out.println("Введите зарплату:");
                        double Salary = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Введите семейное положение:");
                        String MaritalSt = scanner.nextLine();

                        Employee employee = new Employee(id_, FirstName, LastName, BirthYear, BirthPlace, Salary, MaritalSt);
                        act_with_e.add_(employee);
                    } else {
                        System.out.println("Сотрудник с данным ID же есть в базе!");
                        }
                    break;

                case 2:
                    System.out.println("Введите ID сотрудника:");
                    int searchId = scanner.nextInt();
                    act_with_e.find_by_id(searchId);
                    break;

                case 3:
                    System.out.println("Введите имя сотрудника:");
                    String searchName = scanner.nextLine();
                    System.out.println("Введите год рождения сотрудника:");
                    int searchYear = scanner.nextInt();
                    act_with_e.find_by_name_birth(searchName, searchYear);
                    break;

                case 4:
                    System.out.println("Введите ID сотрудника для обновления:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Какое поле вы хотите изменить? (имя, фамилия, год рождения, место рождения, " +
                            "зарплата, семейное положение)");
                    String meaning = scanner.nextLine();
                    if (meaning.equals("зарплата") || meaning.equals("год рождения")) {
                        System.out.println("Введите новое значение (число):");
                        double new_ = scanner.nextDouble();
                        act_with_e.change_info_int(id, meaning, new_);
                    }
                    else {
                        System.out.println("Введите новое значение (текст):");
                        String new_ = scanner.nextLine();
                        act_with_e.change_info_str(id, meaning, new_);
                    }
                    break;

                case 5:
                    double total_ = act_with_e.sum_salary();
                    System.out.println("Общая сумма зарплат: " + total_);
                    break;

                case 0:
                    System.out.println("...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Некорректный выбор");
            }
        }
    }
}