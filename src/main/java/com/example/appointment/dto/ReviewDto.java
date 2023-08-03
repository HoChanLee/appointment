package com.example.appointment.dto;

import com.example.appointment.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private long appointmentId;
    private String review;

    public Review toEntity(){
        return Review.builder()
                .appointmentId(this.appointmentId)
                .review(this.review)
                .build();
    }
}
