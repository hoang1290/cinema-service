package com.example.service.impl;

import com.example.constant.AccountStatus;
import com.example.constant.RoleEnum;
import com.example.entities.Account;
import com.example.entities.Employee;
import com.example.entities.Roles;
import com.example.filestorage.MyFile;
import com.example.repository.AccountRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import com.example.service.MyFileService;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private MyFileService myFileService;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Account> findAllEmployee(Roles roles) {
        return employeeRepository.findEmployeesByRole(roles);
    }


    @Override
    public void delete(UUID id) {
        Account account = employeeRepository.findByAccountId(id);
        account.setRole(roleService.findByName(RoleEnum.ROLE_MEMBER));
        accountRepository.save(account);
    }

    @Override
    public Account createEmployee(MultipartFile file, Account account) {

        account.setAccountStatus(AccountStatus.ACTIVE);
        //Set role member
        Roles role = roleService.findByName(RoleEnum.ROLE_EMPLOYEE);
        account.setRole(role);

        Employee employee = new Employee();
        account.setEmployee(employeeRepository.save(employee));

        String encryptPassword = passwordEncoder.encode(account.getPassword());
        String encryptRePassword = passwordEncoder.encode(account.getRePassword());

        account.setPassword(encryptPassword);
        account.setRePassword(encryptRePassword);

        MyFile myFile = new MyFile();
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        account.setImage(myFileService.save(myFile));
        return accountRepository.save(account);
    }

    @Override
    public Account editEmployee(MultipartFile file, Account account) {
        Account employee = accountRepository.findById(account.getId()).orElse(null);
        employee.setUsername(account.getUsername());

        String encryptPassword = passwordEncoder.encode(account.getPassword());
        String encryptRePassword = passwordEncoder.encode(account.getRePassword());

        employee.setPassword(encryptPassword);
        employee.setRePassword(encryptRePassword);
        employee.setFullName(account.getFullName());
        employee.setDateOfBirth(account.getDateOfBirth());
        employee.setGender(account.getGender());
        employee.setIdentityCard(account.getIdentityCard());
        employee.setEmail(account.getEmail());
        employee.setAddress(account.getAddress());
        employee.setPhoneNumber(account.getPhoneNumber());

        MyFile myFile = new MyFile();
        if(employee.getImage().getId() != 1)
            myFile.setId(employee.getImage().getId());
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        employee.setImage(myFileService.save(myFile));
        return accountRepository.save(employee);
    }
}
