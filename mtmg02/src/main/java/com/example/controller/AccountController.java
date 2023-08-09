package com.example.controller;

import com.example.auth.CustomUserDetails;
import com.example.entities.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute Account account,
            Model model
    ) {
        // Check account in DB
        Account accountDB = accountService.findByUsername(account.getUsername());
        if (accountDB != null) {
            model.addAttribute("message", "Account already exists");
            return "register";
        }

        model.addAttribute("message", "Register successfully");
        model.addAttribute("account", accountService.createMember(account));
        return "login";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @GetMapping("/loginErr")
    public String loginAccount(Model model) {
        model.addAttribute("message", "Email or password not correct");
        model.addAttribute("account", new Account());
        return "login";

    }

    @GetMapping("/profile")
    public String showEditProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

            Account account = userDetails.getAccount();
            model.addAttribute("account", account);
        }
        return "profile";
    }
    @GetMapping("/account-information")
    public String getAccountInformation(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

            Account account = userDetails.getAccount();
            model.addAttribute("account", account);
        }
        return "accountInformation";
    }

    @PostMapping("/upload-profile")
    public String uploadAvatar(Model model,
            @RequestParam("avatar") MultipartFile avatarFile,
            @ModelAttribute Account account
    ) {
        model.addAttribute("account", accountService.update(avatarFile, account));
        return "profile";
    }


}
