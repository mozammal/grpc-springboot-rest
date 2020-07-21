package com.mozammal.springbootrpcrest.service;

import com.mozammal.springbootrpcrest.model.Employee;
import com.mozammal.springbootrpcrest.model.EmployeeRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import com.mozammal.proto.code.*;

import java.util.Optional;

@GRpcService
public class EmployeeServiceGrpcImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    private final EmployeeRepository repository;

    public EmployeeServiceGrpcImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateById(UpdateEmployeeByRequest request, StreamObserver<UpdateEmployeeByResponse> responseObserver) {
        Optional<Employee> existingEmployee = repository.findById(request.getId());
        existingEmployee.map(employee -> employee.updateEmplyee(request))
                .ifPresent(employee -> {
                    repository.save(employee);
                    responseObserver.onNext(UpdateEmployeeByResponse.newBuilder()
                            .setId(employee.getId())
                            .build());
                });
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteEmployeeByIdRequest request, StreamObserver<DeleteEmployeeResponse> responseObserver) {
        repository.deleteById(request.getId());
        responseObserver.onNext(DeleteEmployeeResponse.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void findById(
            FindEmployeeByIdRequest request, StreamObserver<EmployeeEntity> responseObserver) {
        Optional<Employee> employee = repository.findById(request.getId());
        employee
                .map(e -> e.toProto()).
                ifPresent(e -> responseObserver.onNext(e));
        responseObserver.onCompleted();
    }

    @Override
    public void add(
            AddEmployeeRequest request, StreamObserver<AddEmployeeResponse> responseObserver) {
        Employee employee = Employee.fromProto(request);
        employee = repository.save(employee);
        responseObserver.onNext(AddEmployeeResponse.newBuilder().setId(employee.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(
            FindAllEmployeeRequest request, StreamObserver<EmployeeEntity> responseObserver) {
        repository
                .findAll()
                .forEach(
                        employee -> {
                            responseObserver.onNext(employee.toProto());
                        });
        responseObserver.onCompleted();
    }
}
