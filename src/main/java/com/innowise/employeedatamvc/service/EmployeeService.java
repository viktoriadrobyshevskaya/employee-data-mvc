package com.innowise.employeedatamvc.service;

import com.innowise.employeedatamvc.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    void create(EmployeeDto employee);

    EmployeeDto findById(Long id);

    void deleteById(Long id);

    EmployeeDto findByEmail(String email);

    EmployeeDto update(EmployeeDto employeeDto, Long id);
}
