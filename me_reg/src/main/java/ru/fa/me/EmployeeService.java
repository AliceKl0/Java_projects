package ru.fa.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    void save(Employee employee){employeeRepository.save(employee);}

    void delete(int id)
    {
        Employee employee=employeeRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id" + id));
        employeeRepository.delete(employee);
    }

    void update(int id, String newName) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        employee.setName(newName);
        employeeRepository.save(employee);
    }

}
