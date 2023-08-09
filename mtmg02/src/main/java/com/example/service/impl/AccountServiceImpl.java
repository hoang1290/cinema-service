package com.example.service.impl;

import com.example.auth.CustomUserDetails;
import com.example.constant.AccountStatus;
import com.example.constant.RoleEnum;
import com.example.entities.Account;
import com.example.entities.Employee;
import com.example.entities.Member;
import com.example.entities.Roles;
import com.example.filestorage.MyFile;
import com.example.repository.AccountRepository;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private MyFileService myFileService;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account createMember(Account account) {

        account.setAccountStatus(AccountStatus.ACTIVE);
        //Set role member
        Roles role = roleService.findByName(RoleEnum.ROLE_MEMBER);
        account.setRole(role);

        //Set image
        account.setImage(myFileService.finById(1));

        // Encoder password
        String encryptPassword = passwordEncoder.encode(account.getPassword());
        String encryptRePassword = passwordEncoder.encode(account.getRePassword());
        account.setPassword(encryptPassword);
        account.setRePassword(encryptRePassword);

        // Member set Account and set core
        Member member = new Member();
        member.setScore(0);

        // Account set Member
        account.setMember(memberService.save(member));
        return accountRepository.save(account);
    }



    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(MultipartFile file, Account account) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Account loggedAccount = userDetails.getAccount();
        loggedAccount.setUsername(account.getUsername());

        String encryptPassword = passwordEncoder.encode(account.getPassword());
        String encryptRePassword = passwordEncoder.encode(account.getRePassword());

        loggedAccount.setPassword(encryptPassword);
        loggedAccount.setRePassword(encryptRePassword);
        loggedAccount.setFullName(account.getFullName());
        loggedAccount.setDateOfBirth(account.getDateOfBirth());
        loggedAccount.setGender(account.getGender());
        loggedAccount.setIdentityCard(account.getIdentityCard());
        loggedAccount.setEmail(account.getEmail());
        loggedAccount.setAddress(account.getAddress());
        loggedAccount.setPhoneNumber(account.getPhoneNumber());


        MyFile myFile = new MyFile();
        if(loggedAccount.getImage().getId() != 1)
            myFile.setId(loggedAccount.getImage().getId());
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loggedAccount.setImage(myFileService.save(myFile));
        return accountRepository.save(loggedAccount);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }


}
