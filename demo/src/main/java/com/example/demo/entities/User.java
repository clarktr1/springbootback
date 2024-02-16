package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Setter(AccessLevel.PUBLIC)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    public String userName;
    public String city;
    public String state;
    public Integer zipCode;
    public boolean peanutAllergy;
    public boolean dairyAllergy;
    public boolean eggAllergy;


}
