package ru.easyum.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.easyum.rest.entity.Employee;
import ru.easyum.rest.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

//    @Autowired
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok().body(employeeService.findAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.findEmployeeById(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> add(@Valid @RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return ResponseEntity.ok().body("Employee is added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Employee employee, @PathVariable Long id){
        employeeService.updateEmployee(employee, id);
        return ResponseEntity.ok().body("Employee is update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body("Employee is deleted");
    }



}

