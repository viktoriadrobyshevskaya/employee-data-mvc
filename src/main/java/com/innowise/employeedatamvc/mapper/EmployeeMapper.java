package com.innowise.employeedatamvc.mapper;

import com.innowise.employeedatamvc.dto.EmployeeDto;
import com.innowise.employeedatamvc.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = Optional.class)
public abstract class EmployeeMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract EmployeeDto mapToDto(Employee employee);

    public abstract List<EmployeeDto> mapToDtoList(List<Employee> employees);

    public abstract Employee mapToEntity(EmployeeDto employeeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", expression = "java(Optional.ofNullable(employeeDto.getPassword())" +
            ".map(passwordEncoder::encode).orElseGet(employee::getPassword))")
    public abstract void updateEmployee(@MappingTarget Employee employee, EmployeeDto employeeDto);
}
