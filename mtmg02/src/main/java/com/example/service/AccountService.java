package com.example.service;

import com.example.entities.Account;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface AccountService {
    Account findByUsername(String username);

    Account createMember(Account account);

    Account save(Account account);

    Account update(MultipartFile avatarFile,Account account);

    Account findById(UUID id);
}
