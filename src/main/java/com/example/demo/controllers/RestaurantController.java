package com.example.demo.controllers;

import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"https://springrestaurant.netlify.app/", "http://127.0.0.1:5173/"})
@RequestMapping(path = "/restaurants")
public class RestaurantController {
    final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping(path="/all")
    public Iterable<Restaurant> getAll(){
        return this.restaurantRepository.findAll();
    }


    @PostMapping(path = "/add")
    public ResponseEntity<String> addRestaurant(Restaurant restaurant) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
        if(restaurantOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restaurant already exists!");
        }

        restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant successfully added.");
    }

    @GetMapping(path = "/{id}")
    public Optional<Restaurant> getRestauranttById(@PathVariable Long id){
        return restaurantRepository.findById(id);
    }

    @GetMapping("/{zipCode}")
    public List<Restaurant> getByZipCode(@PathVariable Integer zipCode, @RequestParam String allergy) {
        if (allergy.toLowerCase().equals("peanut")) {
            return this.restaurantRepository.findByZipCodeOrderByPeanutScoreDesc(zipCode);
        } else if (allergy.toLowerCase().equals("egg")) {
            return this.restaurantRepository.findByZipCodeOrderByEggScoreDesc(zipCode);
        } else if (allergy.toLowerCase().equals("dairy")) {
            return this.restaurantRepository.findByZipCodeOrderByDairyScoreDesc(zipCode);
        } else {
            return this.restaurantRepository.findByZipCode(zipCode);
        }
    }

    @GetMapping(path = "/genre/{genre}")
    @CrossOrigin(origins = {"https://springrestaurant.netlify.app/", "http://127.0.0.1:5173/"})
    public List<Restaurant> getByGenre(@PathVariable String genre){
        return this.restaurantRepository.findByGenre(genre);
    }


}
