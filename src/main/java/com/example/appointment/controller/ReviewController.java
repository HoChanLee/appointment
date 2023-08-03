package com.example.appointment.controller;

import com.example.appointment.domain.Review;
import com.example.appointment.dto.ReviewDto;
import com.example.appointment.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    /**
     * 리뷰
     */
    @PostMapping("/write")
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto){
        Review entity = reviewDto.toEntity();
        Review result = reviewService.addReview(entity);
        return ResponseEntity.ok(result);
    }
}
