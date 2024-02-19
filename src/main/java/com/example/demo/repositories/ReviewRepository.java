package com.example.demo.repositories;

import ch.qos.logback.core.status.Status;
import com.example.demo.entities.Auth;
import com.example.demo.entities.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ReviewRepository extends CrudRepository<Review, Long> {
    public Iterable<Review> findByStatus(Auth.Status status);
    List<Review> findByRestaurantIdAndStatus(Long id, Auth.Status reviewStatus);
    List<Review> findByRestaurantId(Long id);
}
