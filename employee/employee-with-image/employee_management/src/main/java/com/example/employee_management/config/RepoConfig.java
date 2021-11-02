package com.example.employee_management.config;

import com.example.employee_management.repository.EmployeeDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDao("employee.csv");
    }
}
