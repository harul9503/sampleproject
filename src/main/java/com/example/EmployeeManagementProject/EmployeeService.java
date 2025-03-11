package com.example.EmployeeManagementProject;

import com.example.EmployeeManagementProject.Employee;
import com.example.EmployeeManagementProject.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    public List<Employee> getAllEmployee(){
        return repository.findAll();
    }
    public Optional<Employee> getEmployeeById(Long id){
        return repository.findById(id);
    }
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return repository.findById(id).map(existingEmployee -> {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            return repository.save(existingEmployee);
        }).orElse(null);
    }
}
