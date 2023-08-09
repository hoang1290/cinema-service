package com.example.entities;

import com.example.filestorage.MyFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String actor;

    private String content;

    private String director;

    private Integer duration;

    private Date fromDate;

    private String movieProductionCompany;

    private Date toDate;

    private String version;

    private String movieNameEnglish;

    private String movieNameVn;

    @ManyToOne
    private MyFile largeImage;

    @ManyToOne
    private MyFile smallImage;

    @ManyToOne
    private CinemaRoom cinemaRoom;

    @ManyToMany
    private List<Schedule> schedules;

    @ManyToMany
    private List<Type> types;


}
