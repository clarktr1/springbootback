package com.example.demo.controllers;

import com.example.demo.entities.Auth;
import com.example.demo.entities.Review;
import com.example.demo.entities.User;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewController(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> createReview(@RequestBody Review review) {
        Optional<User> user = this.userRepository.findByUserName(review.getAuthor());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to make a post");
        }

        this.reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review has been created. Pending review.");
    }

    @GetMapping(path = "/pending")
    public Iterable<Review> getPendingReviews(String userName) {
        if (userName.equals("admin")) {
            return reviewRepository.findByStatus(Auth.Status.PENDING);
        } else {
            return null;
        }
    }

    @PutMapping(path = "/pending/{id}")
    public ResponseEntity<String> approveReview(String userName, @PathVariable Long id, Auth.Status status) {
        if (userName.equals("admin")) {
            Optional<Review> reviewOptional = reviewRepository.findById(id);
            if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();
                review.setStatus(status);
                reviewRepository.save(review);
                if (status.equals(Auth.Status.APPROVED)) {
                    return ResponseEntity.status(HttpStatus.OK).body("Review is approved");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("Review is denied");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authorized");
    }

    @GetMapping(path = "/approved/resturant_{id}")
    public Iterable<Review> findApprovedByRestaurant(@PathVariable Long id){
        return reviewRepository.findByRestaurantIdAndStatus(id, Auth.Status.APPROVED);
    }
}
