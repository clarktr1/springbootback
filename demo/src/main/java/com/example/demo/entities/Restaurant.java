package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer zipCode;
    private String genre;
    private String image;
    private Integer overallScore;
    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;


}
