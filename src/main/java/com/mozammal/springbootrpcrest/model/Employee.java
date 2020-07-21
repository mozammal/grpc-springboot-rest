package com.mozammal.springbootrpcrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.mozammal.proto.code.*;

@Data
@Entity
@NoArgsConstructor
public class Employee {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Employee(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public static Employee fromProto(AddEmployeeRequest proto) {
        return new Employee(proto.getName(), proto.getRole());
    }

    public static Employee fromProto(EmployeeEntity proto) {
        return new Employee(proto.getId(), proto.getName(), proto.getRole());
    }

    public static List<Employee> toEmployees(Iterator<EmployeeEntity> employeesIterator) {
        List<Employee> employees = new ArrayList<>();
        while (employeesIterator.hasNext()) {
            employees.add(fromProto(employeesIterator.next()));
        }
        return employees;
    }

    public EmployeeEntity toProto() {
        return EmployeeEntity.newBuilder().setId(getId()).setName(getName()).setRole(getRole()).build();
    }

    public Employee updateEmplyee(UpdateEmployeeByRequest proto) {
        setName(proto.getName());
        setRole(proto.getRole());
        return this;
    }
}
