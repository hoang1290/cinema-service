package com.example.entities;

import com.example.constant.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private TicketType ticketType;

    @ManyToOne
    private Schedule schedule;

    @OneToOne
    private Movie movie;

    @OneToMany
    private List<Seat> seats;

    @OneToOne
    private ShowDate showDate;



}
