package com.example.entities;

import com.example.constant.AccountStatus;
import com.example.filestorage.MyFile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String username;

    private String password;

    private String rePassword;

    private String address;

    private String email;

    private String fullName;

    private String gender;

    private String identityCard;

    @ManyToOne
    private MyFile image;

    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime registerDate;

    private Date dateOfBirth;

    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus;

    @ManyToOne
    @JoinColumn(name = "role_id")

    private Roles role;

    @OneToOne
    private Member member;

    @OneToOne
    private Employee employee;

    @OneToMany
    private List<Invoice> invoices;

    @OneToMany
    private List<Ticket> tickets;
}

