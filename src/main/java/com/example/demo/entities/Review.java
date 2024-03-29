package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Setter(AccessLevel.PUBLIC)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;
    private long restaurantId;
    private int peanutScore;
    private int dairyScore;
    private int eggScore;
    private int overallScore;
    private String commentary;
    private String status = "PENDING";
    private Date date_posted;
}
