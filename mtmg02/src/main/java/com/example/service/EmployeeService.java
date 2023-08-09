package com.example.service;

import com.example.entities.Account;
import com.example.entities.Employee;
import com.example.entities.Roles;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Account> findAllEmployee(Roles roles);

    void delete(UUID id);

    Account createEmployee(MultipartFile avatarFile, Account account);

    Account editEmployee(MultipartFile avatarFile, Account account);
}
