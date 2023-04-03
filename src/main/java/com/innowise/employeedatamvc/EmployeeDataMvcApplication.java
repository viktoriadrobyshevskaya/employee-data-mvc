package com.innowise.employeedatamvc;

import com.innowise.employeedatamvc.dto.EmployeeDto;
import com.innowise.employeedatamvc.entity.Role;
import com.innowise.employeedatamvc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeDataMvcApplication implements ApplicationRunner {
    private final EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDataMvcApplication.class, args);
    }

    public void run(ApplicationArguments args) {
        if (employeeService.findAll().isEmpty()) {
            EmployeeDto employee = EmployeeDto.builder()
                    .email("vikitosya@gmail.com")
                    .password("123456")
                    .firstName("Viktoria")
                    .lastName("Drobyshevskaya")
                    .role(Role.ROLE_ADMIN)
                    .build();

            employeeService.create(employee);
        }
    }
}
