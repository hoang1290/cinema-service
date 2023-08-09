package com.example.service.impl;

import com.example.entities.Account;
import com.example.entities.Invoice;
import com.example.repository.InvoiceRepository;
import com.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findByAccount(UUID id) {
        return invoiceRepository.findByAccount(id);
    }
}
