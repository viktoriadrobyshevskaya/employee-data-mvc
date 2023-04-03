package com.innowise.employeedatamvc.service;

import com.innowise.employeedatamvc.dto.EmployeeDto;
import com.innowise.employeedatamvc.entity.Employee;
import com.innowise.employeedatamvc.exception.EmployeeException;
import com.innowise.employeedatamvc.mapper.EmployeeMapper;
import com.innowise.employeedatamvc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeMapper.mapToDtoList(employeeRepository.findAll());
    }

    @Override
    @Transactional
    public void create(EmployeeDto employeeDto) {
        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        Employee newEmp = employeeMapper.mapToEntity(employeeDto);
        employeeMapper.mapToDto(employeeRepository.save(newEmp));
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        return employeeMapper.mapToDto(employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeException("Could not find Employee by email: " + email)));
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto, Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeMapper.updateEmployee(employee, employeeDto);
            return employeeMapper.mapToDto(employee);
        }

        throw new EmployeeException("Could not find Employee by ID: " + id);
    }

    @Override
    public EmployeeDto findById(Long id) {
        return employeeMapper.mapToDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Could not find Employee by ID: " + id)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
