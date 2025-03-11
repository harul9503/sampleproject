package com.example.EmployeeManagementProject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    public void printConfig() {
        System.out.println("Application Name: " + appName);
        System.out.println("Database URL: " + dbUrl);
    }
}
