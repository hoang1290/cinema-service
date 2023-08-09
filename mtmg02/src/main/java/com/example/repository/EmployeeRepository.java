package com.example.repository;

import com.example.entities.Account;
import com.example.entities.Employee;
import com.example.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(value = "FROM Account a WHERE a.role =?1")
    List<Account> findEmployeesByRole(Roles roles);

    @Query(value = "FROM Account a WHERE a.id = ?1")
    Account findByAccountId(UUID id);
}
