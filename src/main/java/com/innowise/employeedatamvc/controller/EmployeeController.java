package com.innowise.employeedatamvc.controller;

import com.innowise.employeedatamvc.dto.EmployeeDto;
import com.innowise.employeedatamvc.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@Validated
@AllArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    final EmployeeService employeeService;

    @GetMapping
    public String showAllEmployees(Model model) {
        model.addAttribute("allEmps", employeeService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showSignUpForm(Model model, EmployeeDto employee) {
        model.addAttribute("employee", employee);
        model.addAttribute("rolesList", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "add-employee";
    }

    @PostMapping("/add")
    public String createEmployee(@Valid EmployeeDto employee, BindingResult result) {
        if (result.hasErrors()) {
            return "add-employee";
        }
        employeeService.create(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String findEmployeeById(@PathVariable("id") Long id, Model model) {
        EmployeeDto employeeDto = employeeService.findById(id);
        model.addAttribute("allEmps", Collections.singletonList(employeeDto));
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        EmployeeDto employeeDto = employeeService.findById(id);
        model.addAttribute("employee", employeeDto);
        model.addAttribute("rolesList", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        return "update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @Valid EmployeeDto employee, BindingResult result) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-employee";
        }
        employeeService.update(employee, id);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String removeEmployee(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}