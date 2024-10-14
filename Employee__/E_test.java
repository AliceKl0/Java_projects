package Employee__;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class E_test {

    @Test
    public void testAddEmployee() {
        Actions act_with_e = new Actions();
        Employee emp1 = new Employee(1, "John", "Stone", 1985, "London",
                50000, "Single");

        act_with_e.add_(emp1);

        // Проверяем, что сотрудник добавлен
        Employee found = act_with_e.find_by_id(1);
        assertNotNull(found);
        assertEquals("John", found.getFirstName());
        assertEquals("Stone", found.getLastName());
    }

    @Test
    public void testAddEmployeeWithSameId() {
        Actions act_with_e = new Actions();
        Employee emp1 = new Employee(1, "John", "Stone", 1985, "London",
                50000, "Single");
        Employee emp2 = new Employee(1, "Alice", "Klimovich", 2005, "Moscow",
                70000, "Smth");

        act_with_e.add_(emp1);
        act_with_e.add_(emp2);

        // Проверяем, что второй сотрудник не был добавлен
        assertEquals(true, act_with_e.find_by_id(1).getFirstName().equals("John"));
    }

    @Test
    public void testFindEmployeeById() {
        Actions act_with_e = new Actions();
        Employee emp1 = new Employee(1, "John", "Stone", 1985, "London",
                50000, "Single");

        act_with_e.add_(emp1);

        // Поиск сотрудника по ID
        Employee found = act_with_e.find_by_id(1);
        assertNotNull(found);
        assertEquals(1, found.getId_());
        assertEquals("John", found.getFirstName());
    }

    @Test
    public void testUpdateEmployee() {
        Actions act_with_e = new Actions();
        Employee emp1 = new Employee(1, "John", "Stone", 1985, "London",
                50000, "Single");

        act_with_e.add_(emp1);

        act_with_e.change_info_int(1, "зарплата", 10000);

        Employee change_ = act_with_e.find_by_id(1);
        assertEquals(10000, change_.getSalary());
    }

    @Test
    public void testCalculateTotalSalary() {
        Actions act_with_e = new Actions();
        Employee emp1 = new Employee(1, "John", "Stone", 1985, "London",
                50000, "Single");
        Employee emp2 = new Employee(2, "Alice", "Klimovich", 2005, "Moscow",
                70000, "Smth");

        act_with_e.add_(emp1);
        act_with_e.add_(emp2);

        double total_ = act_with_e.sum_salary();
        assertEquals(120000, total_, 0.001);
        System.out.println(total_);
    }
}
