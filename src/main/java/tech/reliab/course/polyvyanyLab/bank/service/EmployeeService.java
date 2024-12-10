package tech.reliab.course.polyvyanyLab.bank.service;

import java.util.List;

import tech.reliab.course.polyvyanyLab.bank.entity.Employee;
import tech.reliab.course.polyvyanyLab.bank.entity.BankOffice;

public interface EmployeeService {
    Employee create(Employee employee);

    public Employee getEmployeeById(int id);

    public List<Employee> getAllEmployees();

    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}