package com.mozammal.springbootrpcrest.grpcspringbootrest.web;

import com.mozammal.springbootrpcrest.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.mozammal.proto.code.*;

@Slf4j
@RestController
public class EmployeeController {


    private final EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeService;

    public EmployeeController(EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping(value = "/employees", produces = "application/json")
    AddEmployeeResponse addEmployee(@RequestBody Employee employee) {
        AddEmployeeResponse response = employeeService.add(AddEmployeeRequest.newBuilder()
                .setName(employee.getName())
                .setRole(employee.getRole()).build());
        return response;
    }

    @GetMapping(value = "/employees")
    List<Employee> findAllEmployees() {
        Iterator<EmployeeEntity> employees =
                employeeService.findAll(FindAllEmployeeRequest.getDefaultInstance());

        return Employee.toEmployees(employees);
    }

    @GetMapping(value = "/employees/{id}", produces = "application/json")
    EmployeeEntity findEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee =
                employeeService.findById(FindEmployeeByIdRequest.newBuilder()
                        .setId(id)
                        .build());
        return employee;
    }

    @PutMapping(value = "/employees/{id}", produces = "application/json")
    UpdateEmployeeByResponse updatEmployeeById(@RequestBody Employee employee, @PathVariable Long id) {
        UpdateEmployeeByResponse response = employeeService.updateById(UpdateEmployeeByRequest.newBuilder()
                .setId(id)
                .setName(employee.getName())
                .setRole(employee.getRole())
                .build());
        return response;
    }

    @DeleteMapping("/employees/{id}")
    DeleteEmployeeResponse removeEmployeeById(@PathVariable Long id) {
        DeleteEmployeeResponse response = employeeService.delete(DeleteEmployeeByIdRequest.newBuilder()
                .setId(id)
                .build());
        return response;
    }

}
