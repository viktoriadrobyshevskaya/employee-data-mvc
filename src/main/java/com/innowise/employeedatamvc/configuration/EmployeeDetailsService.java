package com.innowise.employeedatamvc.configuration;

import com.innowise.employeedatamvc.entity.Employee;
import com.innowise.employeedatamvc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository repository;

    @Override
    public EmployeeUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByEmail(email);

        if (employee.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new EmployeeUserDetails(employee.get());
    }
}
