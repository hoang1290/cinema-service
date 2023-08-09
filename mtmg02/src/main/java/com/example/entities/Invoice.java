package com.example.entities;

import com.example.constant.InvoiceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Integer addScore;
    @CreationTimestamp
    private LocalDateTime bookingDate;

    private String movieName;

    private LocalDateTime scheduleShow;

    private String scheduleShowTime;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceStatus invoiceStatus;

    private BigDecimal totalMoney;

    private Integer useScore;

    private String seat;

}
