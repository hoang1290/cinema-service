package com.example.repository;

import com.example.entities.Account;
import com.example.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,String> {

    @Query(value = "SELECT a.invoices FROM Account a WHERE a.id = ?1")
    List<Invoice> findByAccount(UUID id);
}
