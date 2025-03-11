package com.example.EmployeeManagementProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService service, EmployeeRepository employeeRepository){
        this.service=service;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(value = "department", required = false) String department) {
        if (department != null && !department.isEmpty()) {
            return employeeRepository.findByDepartment(department); //
        }
        return service.getAllEmployee(); //
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable Long id){
        return service.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public List<Employee> saveemployee(@RequestBody Employee employee){
        return Collections.singletonList(employeeRepository.save(employee));
    }
//    sample comment1
@PutMapping("/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
    return employeeRepository.findById(id)
            .map(existingEmployee -> {
                existingEmployee.setName(updatedEmployee.getName());
                existingEmployee.setDepartment(updatedEmployee.getDepartment());
                existingEmployee.setSalary(updatedEmployee.getSalary());
                Employee savedEmployee = employeeRepository.save(existingEmployee);
                return ResponseEntity.ok(savedEmployee);
            })
            .orElse(ResponseEntity.notFound().build());
}
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeePartially(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    if (updatedEmployee.getName() != null) {
                        existingEmployee.setName(updatedEmployee.getName());
                    }
                    if (updatedEmployee.getDepartment() != null) {
                        existingEmployee.setDepartment(updatedEmployee.getDepartment());
                    }
                    if (updatedEmployee.getSalary() != null) {
                        existingEmployee.setSalary(updatedEmployee.getSalary());
                    }
                    Employee savedEmployee = employeeRepository.save(existingEmployee);
                    return ResponseEntity.ok(savedEmployee);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
