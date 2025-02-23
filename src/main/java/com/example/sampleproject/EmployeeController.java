package com.example.sampleproject;

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
    public List<Employee> getAllEmployees(){
        return service.getAllEmployee();
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
//    sample comment
}
