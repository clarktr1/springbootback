package com.example.demo.repositories;

import com.example.demo.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> getRestaurantByNameAndZipCode(String name, Integer zipcode);
    List<Restaurant> findByZipCodeOrderByPeanutScoreDesc(Integer zipCode);
    List<Restaurant> findByZipCodeOrderByEggScoreDesc(Integer zipCode);
    List<Restaurant> findByZipCodeOrderByDairyScoreDesc(Integer zipCode);
    List<Restaurant> findByZipCode(Integer zipCode);
    List<Restaurant> findByGenre(String genre);
}
