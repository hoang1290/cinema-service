package com.example.service;

import com.example.entities.Invoice;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    List<Invoice> findByAccount(UUID id);

}
