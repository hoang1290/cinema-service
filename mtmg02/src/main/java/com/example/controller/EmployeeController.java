package com.example.controller;

import com.example.constant.RoleEnum;
import com.example.entities.Account;
import com.example.repository.RoleRepository;
import com.example.service.AccountService;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/employees")
    public String getEmployees(Model model){
        model.addAttribute("employees",employeeService.findAllEmployee(roleRepository.findByName(RoleEnum.ROLE_EMPLOYEE)));
        return "viewEmployeeList";
    }
    @GetMapping("/add-employee")
    public String addEmployee(Model model){
        model.addAttribute("employee",new Account());
        return "createEmployee";
    }

    @PostMapping("/add-employee")
    public String createEmployee(Model model,
                               @RequestParam("avatar") MultipartFile avatarFile,
                               @ModelAttribute("employee") Account account
    ) {
        employeeService.createEmployee(avatarFile, account);
        model.addAttribute("employees",employeeService.findAllEmployee(roleRepository.findByName(RoleEnum.ROLE_EMPLOYEE)));
        return "viewEmployeeList";
    }

    @GetMapping("/edit-employee/{id}")
    public String editEmployee(Model model, @PathVariable UUID id){
        model.addAttribute("employee",accountService.findById(id));
        return "editEmployee";
    }
    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(Model model, @PathVariable UUID id){
        employeeService.delete(id);
        model.addAttribute("employees",employeeService.findAllEmployee(roleRepository.findByName(RoleEnum.ROLE_EMPLOYEE)));
        return "viewEmployeeList";
    }

    @PostMapping("/update-employee")
    public String uploadAvatar(Model model,
                               @RequestParam("avatar") MultipartFile avatarFile,
                               @ModelAttribute("employee") Account account
    ) {
        employeeService.editEmployee(avatarFile, account);
        model.addAttribute("employees",employeeService.findAllEmployee(roleRepository.findByName(RoleEnum.ROLE_EMPLOYEE)));
        return "viewEmployeeList";
    }
}
