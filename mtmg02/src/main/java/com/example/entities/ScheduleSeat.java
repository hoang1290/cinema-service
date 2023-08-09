package com.example.entities;

import com.example.constant.SeatStatus;
import com.example.constant.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScheduleSeat {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String seatColumn;

    private Integer seatRow;

    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private CinemaRoom cinemaRoom;
}
