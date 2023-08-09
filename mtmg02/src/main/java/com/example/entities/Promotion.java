package com.example.entities;

import com.example.filestorage.MyFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String detail;

    private Integer discountLevel;

    private LocalDateTime endTime;

    @OneToOne
    private MyFile image;

    private LocalDateTime startTime;

    private String title;

}
